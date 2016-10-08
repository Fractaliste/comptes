/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import raphdine.comptes.modele.CategorieEnum;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.service.ServiceLocator;

/**
 *
 * @author Raphiki
 */
public class ImportExcel {

    private static final Logger LOGGER = Logger.getInstance();
    private static final DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    private static final String LOG_FILE = "target\\import_comptes.log";
    private final String path;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public ImportExcel() {
        path = "src\\main\\resources\\Comptes.xlsx";
    }

    public void run() {
        deleteDbContent();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            extract(fis);
        } catch (FileNotFoundException e) {
            LOGGER.error(e, "Le chemin {} n'existe pas", path);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                LOGGER.error(e, "fos.close() impossible", path);
            }
        }
        replaceLogFile();
    }

    private void extract(FileInputStream fis) {
        try {
            Workbook wb = WorkbookFactory.create(fis);

            Sheet sheet = wb.getSheet("Compte 2016");
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                save(extractEntity(row));
            }
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            LOGGER.error(e, "Pb lors de l'import", path);
        }
    }

    private void deleteDbContent() {
        Session s = ServiceLocator.getInstance().getSession();
        Transaction tx = s.getTransaction();
        tx.begin();
        Query deleteQuery = s.createQuery("delete from  Ecriture");
        deleteQuery.executeUpdate();
        tx.commit();
    }

    private Ecriture extractEntity(Row row) {
        try {
            LOGGER.debug("Row {} value 3 => {}", row.getRowNum(), row.getCell(3));

            Ecriture e = new Ecriture();

            e.setDate(DateUtils.dateToCalendar(row.getCell(0).getDateCellValue()));
            e.setCategorie(extractCategorie(row.getCell(1).getStringCellValue()));
            e.setIntitule(row.getCell(2).getStringCellValue());
            if (row.getCell(3) != null) {
                e.setNumeroCheque(extractNumeroCheque(row.getCell(3).getNumericCellValue()));
            }
            if (row.getCell(4) != null) {
                e.setRemboursement(row.getCell(4).getStringCellValue());
            }
            if (row.getCell(5) == null) {
                e.setPasseEnBanque(Boolean.FALSE);
            } else {
                e.setPasseEnBanque(Boolean.TRUE);
            }
            if (row.getCell(6) != null) {
                e.setDebit((float) row.getCell(6).getNumericCellValue());
            }
            if (row.getCell(7) != null) {
                e.setCredit((float) row.getCell(7).getNumericCellValue());
            }
            if (row.getCell(6) != null && row.getCell(7) != null || row.getCell(6) == null && row.getCell(7) == null) {
                LOGGER.error("Erreur de cohérence débit {}/crédit {} ({},{})", row.getCell(6), row.getCell(7), row.getCell(6) != null && row.getCell(7) != null, row.getCell(6) == null && row.getCell(7) == null);
                throw new IllegalArgumentException("Erreur de cohérence débit/crédit");
            }
            return e;
        } catch (Exception e) {
            appendLogFile(String.valueOf(row.getRowNum()));
            LOGGER.error(e, "Erreur ligne {}", row.getRowNum());
            return null;
        }
    }

    private void replaceLogFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(LOG_FILE);
            fos.write(bos.toByteArray());
        } catch (FileNotFoundException e) {
            LOGGER.error(e, "Le chemin {} n'existe pas", path);
        } catch (IOException e) {
            LOGGER.error(e, "IO lors de l'écriture des logs");
        } finally {
            try {
                bos.close();
            } catch (Exception e) {
                LOGGER.error(e, "bos.close() impossible", path);
            }
            try {
                fos.close();
            } catch (Exception e) {
                LOGGER.error(e, "fos.close() impossible", path);
            }
        }
    }

    private void appendLogFile(String s) {
        try {
            bos.write((s + "\r\n").getBytes());
        } catch (IOException e) {
            LOGGER.error(e, "IO lors de l'ajout aux fichier de log => value {}", s);
        }
    }

    private CategorieEnum extractCategorie(String stringCellValue) {
        switch (stringCellValue) {
            case "Emprunt":
                return CategorieEnum.EMPRUNT;
            case "Enseignement":
                return CategorieEnum.ENSEIGNEMENT;
            case "Epargne":
                return CategorieEnum.EPARGNE;
            case "Gestion":
                return CategorieEnum.FRAIS_DE_GESTION;
            case "Alimentation":
                return CategorieEnum.ALIMENTATION;
            case "Communication":
                return CategorieEnum.COMMUNICATION;
            case "Divers":
                return CategorieEnum.DIVERS;
            case "Electroménager et Ameublement":
                return CategorieEnum.ELECTROMENAGER_AMEUBLEMENT;
            case "Opération bancaires":
                return CategorieEnum.OPERATION_BANCAIRE;
            case "Logement":
            case "Logement ":
                return CategorieEnum.LOGEMENT;
            case "Loisir et culture":
                return CategorieEnum.LOISIR_CULTURE;
            case "Transport":
                return CategorieEnum.TRANSPORT;
            case "Habillement":
                return CategorieEnum.HABILLEMENT;
            case "Salaire":
                return CategorieEnum.SALAIRE;
            case "santé":
            case "Santé":
                return CategorieEnum.SANTE;
            case "Voiture":
                return CategorieEnum.VOITURE;
            case "Vacances":
                return CategorieEnum.VACANCES;
            case "Restaurant, hotel, sortie":
                return CategorieEnum.RESTAURANT_HOTEL_SORTIE;
        }
        LOGGER.debug("Categorie manquante {}", stringCellValue);
        appendLogFile(stringCellValue);
        return null;
    }

    private String extractNumeroCheque(double numericCellValue) {
        if (numericCellValue > 0) {
            appendLogFile("" + numericCellValue);
        }
        return String.valueOf(numericCellValue);
    }

    private void save(Ecriture ecriture) {
        if (ecriture == null) {
            return;
        }
        LOGGER.debug("Enregistrement de l'écriture {}", ecriture);
    }
}
