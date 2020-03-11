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
package de.battleforge.gui.dialog.admin.editSystem;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

import de.battleforge.BFLauncher;
import de.battleforge.gui.dialog.AbstractDialog;
import de.battleforge.jdo.BFSystem;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>Edit System</b><br>
 * Description: <i>Editor for attributes of a selected system.</i><br>
 * Copyright: Copyright (c) 2006<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.1
 */
public class EditSystem extends AbstractDialog {

    /**
     * Logger
     */
    private static Logger sLogger = Logger.getLogger(BFLauncher.class);

    /**
     * The system
     */
    private BFSystem system;

    /**
     * Panel to edit the owners
     */
    private PanelOwner panelEditOwner;

    /**
     * Panel to edit the history
     */
    private PanelHistory panelEditHistory;

    /**
     * Constructor
     */
    public EditSystem(Frame parent, BFSystem sys) {
        super(parent, "editSystemsDialog.Title", OPTIONS.USERDEFINED);
        this.system = sys;
        panelEditOwner.setSystem(system);
        panelEditHistory.setSystem(system);
        this.setTitle(this.getTitle() + " (" + system.getName() + ")");
    }

    public EditSystem(Dialog parent, BFSystem sys) {
        super(parent, "editSystemsDialog.Title", OPTIONS.USERDEFINED);
        this.system = sys;
        panelEditOwner.setSystem(system);
        panelEditHistory.setSystem(system);
        this.setTitle(this.getTitle() + " (" + system.getName() + ")");
    }

    @Override
    protected Container createMainPane() {
        JPanel jp = new JPanel(new BorderLayout());
        jp.setLayout(new BorderLayout());

        panelEditOwner = new PanelOwner();
        panelEditHistory = new PanelHistory();

        JTabbedPane tp = new JTabbedPane();
        tp.add(panelEditOwner, Internationalization.getString("editSystemsDialog.OwnerTab"));
        tp.add(panelEditHistory, Internationalization.getString("editSystemsDialog.HistoryTab"));

        jp.add(tp, BorderLayout.CENTER);

        return jp;
    } // createMainPane

    @Override
    protected JButton[] getButtons() {
        return new JButton[] { new JButton(new SaveSystemAction()), getCancelButton() };
    }

    @Override
    protected int getDefaultButtonIndex() {
        return 1;
    }

    @Override
    protected String getImageName() {
        return "dialog_editSystem.png"; //$NON-NLS-1$
    } // getImageName

    /**
     * SaveSystemAction
     */
    private class SaveSystemAction extends AbstractAction {

        public SaveSystemAction() {
            super(Internationalization.getString("propertyDialog.button.save")); //$NON-NLS-1$
        } // SaveSystemAction

        public void actionPerformed(ActionEvent e) {
            panelEditOwner.save();
            panelEditHistory.save();
            dispose();

        } // actionPerformed
    } // end of inner-class SaveAction
}
