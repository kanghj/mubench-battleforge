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
package de.battleforge.gui.map.diplomacy;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.map.AbstractPanelOwnerInformation;
import de.battleforge.jdo.BFOwner;

public class PanelOwnerInformationDiplomacy extends AbstractPanelOwnerInformation {

    @Override
    protected void addActions() {
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_OWNER, this);

    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case CHANGE_OWNER:
            setOwner((BFOwner) object.getObject());
            break;

        case CHANGE_LANGUAGE:
            setTexts();
            repaint();
            break;

        default:
            break;
        }
        return true;
    }

}
