/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.utils;

import java.lang.reflect.Method;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import raphdine.comptes.service.IDao;
import raphdine.comptes.service.impl.AbstractDao;

/**
 *
 * @author Raphiki
 */
public final class DaoProxy {

    private static final Logger LOGGER = Logger.getInstance();

    public static <T extends IDao> T forClass(Class<T> clazz) {
        LOGGER.debug("Proxy demandé pour la classe {}", clazz);

        ProxyFactory pf = new ProxyFactory();
        pf.setSuperclass(clazz);
        Class createdClass = pf.createClass();
        try {
            Object instance = createdClass.newInstance();
            ((ProxyObject) instance).setHandler(new DaoInvocationHandler());
            return (T) instance;
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(e, "Proxy error pour la classe {}", clazz);
        }
        return null;
    }

    private static class DaoInvocationHandler implements MethodHandler {

        private static final Logger LOGGER = Logger.getInstance();

        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            LOGGER.debug("Method proxy invoked !");
            if (thisMethod.isAnnotationPresent(Transactional.class) && self instanceof AbstractDao) {
                AbstractDao dao = (AbstractDao) self;
                dao.openTransaction();
                Object o = proceed.invoke(dao, args);
                dao.closeTransaction();
                return o;
            } else {
                return proceed.invoke(self, args);
            }
        }
    }
}
