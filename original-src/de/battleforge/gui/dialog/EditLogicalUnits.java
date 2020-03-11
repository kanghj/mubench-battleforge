/* This software is free; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package de.battleforge.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import de.battleforge.BFLauncher;
import de.battleforge.gui.map.units.PanelEditLogicalUnits;
import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>PanelDiplomacyOwner</b><br>
 * Description: <i>The panel for the single owner diplomacy</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.1
 */
public class EditLogicalUnits extends AbstractDialog {

    private static Logger sLogger = Logger.getLogger(BFLauncher.class);

    private PanelEditLogicalUnits pEditLogicalUnits;

    /**
     * Constructor
     */
    public EditLogicalUnits(Frame parent) {
        super(parent, "Edit logical Units", OPTIONS.USERDEFINED);
    }

    public EditLogicalUnits(Dialog parent) {
        super(parent, "Edit logical Units", OPTIONS.USERDEFINED);
    }

    @Override
    protected Container createMainPane() {
        pEditLogicalUnits = new PanelEditLogicalUnits();

        JPanel jp = new JPanel(new BorderLayout());
        jp.add(pEditLogicalUnits, BorderLayout.CENTER);

        return jp;
    } // createMainPane

    @Override
    protected JButton[] getButtons() {
        return new JButton[] { new JButton(new SaveAction()), new JButton(new CancelAction()) };

    }

    @Override
    protected int getDefaultButtonIndex() {
        return 1;
    }

    @Override
    protected String getImageName() {
        return "dialog_createUnit.png";
    } // getImageName

    /**
     * 
     */
    private class SaveAction extends AbstractAction {

        public SaveAction() {
            super(Internationalization.getString("propertyDialog.button.save")); //$NON-NLS-1$
        } // SaveSystemAction

        public void actionPerformed(ActionEvent e) {
            pEditLogicalUnits.addNewLogicalUnit();
            dispose();

        } // actionPerformed
    } // end of inner-class SaveAction

    /**
     * 
     */
    private class CancelAction extends AbstractAction {

        public CancelAction() {
            super(Internationalization.getString("propertyDialog.button.cancel")); //$NON-NLS-1$
        } // SaveSystemAction

        public void actionPerformed(ActionEvent e) {
            dispose();

        } // actionPerformed
    } // end of inner-class SaveAction

    public void setLogicalUnit(BFTreeNode node, boolean bNew) {
        pEditLogicalUnits.setLogicalUnit(node, bNew);
    }
}
