/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.utils;

import java.awt.Container;
import javax.swing.GroupLayout;

/**
 *
 * @author Raphiki
 */
public final class SwingUtils {

    public static GroupLayout extractGroupLayout(Container container) {
        if (container.getLayout() instanceof javax.swing.GroupLayout) {
            return (javax.swing.GroupLayout) container.getLayout();
        } else {
            return null;
        }
    }
}
