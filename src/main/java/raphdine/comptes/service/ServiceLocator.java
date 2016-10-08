/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service;

import raphdine.comptes.service.impl.CompteService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.utils.Logger;

/**
 *
 * @author Raphiki
 */
public class ServiceLocator {

    private static final Logger LOGGER = Logger.getInstance();
    private static final ServiceLocator INSTANCE = new ServiceLocator();

    private final SessionFactory sf;

    private ServiceLocator() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Ecriture.class);
        if ("DESKTOP-M0G63FK".equals(System.getenv("COMPUTERNAME"))) {
            configuration.setProperty("hibernate.connection.url", "jdbc:h2:D:\\Dropbox\\Partage\\Didine\\Comptes\\Appli\\Data\\Comptes");
        } else {
            configuration.setProperty("hibernate.connection.url", "jdbc:h2:N:\\raphiki\\Dropbox\\Partage\\Didine\\Comptes\\Appli\\Data\\Comptes");
        }
        configuration.setProperty("connection.driver_class", "org.h2.Driver");
        configuration.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.default_schema", "ecritures");
        configuration.setProperty("show_sql", "true");
        configuration.configure();

        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sf = configuration.buildSessionFactory(sr);
    }

    public static ServiceLocator getInstance() {
        return INSTANCE;
    }

    public Session getSession() {
        return sf.openSession();
    }

    public ICompteService getComptesService() {
        return new CompteService();
    }
}
