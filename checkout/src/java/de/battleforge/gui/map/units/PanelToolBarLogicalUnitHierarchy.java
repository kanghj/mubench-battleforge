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
package de.battleforge.gui.map.units;

import javax.swing.JButton;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.gui.map.AbstractToolBarPanel;

public class PanelToolBarLogicalUnitHierarchy extends AbstractToolBarPanel {

    public PanelToolBarLogicalUnitHierarchy() {
        super();
    }

    @Override
    protected JButton[] getButtons() {
        return new JButton[] {
                new JButton(ActionManager.getAction("", "Einheiten zuordnen löschen", "arrowLeft.png", ACTIONS.REMOVE_UNIT_FROM_LOGICALUNIT)),
                new JButton(ActionManager.getAction("", "neue logische Einheit anlegen", "new.png", ACTIONS.ADD_NEW_LOGICALUNIT)),
                new JButton(ActionManager.getAction("", "logische Einheit edititeren", "edit.png", ACTIONS.EDIT_LOGICALUNIT)),
                new JButton(ActionManager.getAction("", "logische Einheit löschen", "delete.png", ACTIONS.DELETE_LOGICALUNIT)) };
    } // getButtons
}
