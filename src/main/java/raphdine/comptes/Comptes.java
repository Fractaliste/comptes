/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes;

import raphdine.comptes.ihm.WelcomeFrame;
import raphdine.comptes.utils.ImportExcel;
import raphdine.comptes.utils.Logger;

/**
 *
 * @author Raphiki
 */
public class Comptes {

    private static final Logger LOGGER = Logger.getInstance();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        importExcel();

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WelcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            mainFrame = new WelcomeFrame();
            mainFrame.setExtendedState(mainFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
            mainFrame.setVisible(true);
        });

    }
    private static WelcomeFrame mainFrame;

    private static void importExcel() {
        new ImportExcel().run();
//        System.exit(0);
    }

}
