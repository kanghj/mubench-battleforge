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
package de.battleforge.gui.character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * <p>
 * Title: <b>PanelCharacterCreation</b><br>
 * Description: <i>Creates a new character</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.0
 */
public class PanelCharacterCreation extends JPanel implements ActionListener {
    // Character attributes:
    // - Name
    // - Age / Birthdate
    // - Gender
    // - Experience Points
    // - Level
    // - Class (MechWarrior, Politician, Spy, Assassin,...)

    /**
     * Constructor
     */
    public PanelCharacterCreation() {
        createGui();
    }

    /**
     * Create gui for character creation
     */
    private void createGui() {

    }

    /**
     * Start acting when a button was pressed
     */
    public final void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("")) {
            //
        } else if (cmd.equals("")) {
            //
        }
    }
}
