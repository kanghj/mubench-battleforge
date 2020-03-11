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
package de.battleforge.gui.map.system;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFSystemDescription;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>PanelSystemHIstory</b><br>
 * Description: <i>Displays the history of a system.</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author Kotzbrocken2
 * @version 1.10
 */
public class PanelSystemHistory extends JPanel implements ActionCallbackListener {

    /**
     * TextArea to display description
     */
    private JTextArea detailTextValue = new JTextArea();

    /**
     * The local system
     */
    private BFSystem s = null;

    /**
     * Scrollpane to take the JTextArea
     */
    private JScrollPane sp;

    /**
     * Constructor
     */
    public PanelSystemHistory() {
        // Prepare the TextArea
        detailTextValue.setEditable(false);
        detailTextValue.setEnabled(true);
        detailTextValue.setLineWrap(true);
        detailTextValue.setWrapStyleWord(true);
        detailTextValue.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Do the layout
        sp = new JScrollPane(detailTextValue);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setPreferredSize(new Dimension(10, 10));
        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);

        // Adding action managers to react to global actions
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_LANGUAGE, this);
    }

    /**
     * setTexts The description is read from the database. If the description is
     * null, there is a short information for the user that the text is still
     * missing.
     */
    public void setTexts() {
        // Get the description for the system from the database

        String description = "";
        BFSystemDescription sd = DBWrapper.getSystemDescription(s, Internationalization.getLanguage());
        if (sd == null) {
            description = null;
        } else {
            description = sd.getDescription();
        }

        if (description == null) {
            // If the description is empty, inform the user that the information
            // is missing
            detailTextValue.setText(Internationalization.getString("MissingSystemDescription"));
        } else {
            // Otherwise the description is displayed
            detailTextValue.setText(description);
        }
        // Cursor is set to top of the text
        detailTextValue.setCaretPosition(0);
    }

    /**
     * setSystem Sets the local system to the system that is coming in as
     * parameter.
     * 
     * @param sys
     */
    public void setSystem(BFSystem sys) {
        s = sys;
        setTexts();
        this.repaint();
    }

    /**
     * handleActions The class will react to different actions that can be fired
     * from different places elsewhere.
     */
    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        // The system was changed
        case CHANGE_CURRENT_SYSTEM:
            setSystem((BFSystem) object.getObject());
            break;

        // The language was changed
        case CHANGE_LANGUAGE:
            setTexts();
            break;

        default:
            break;
        }
        // Return true to make sure that subsequent actions are still performed
        return true;
    }
}
