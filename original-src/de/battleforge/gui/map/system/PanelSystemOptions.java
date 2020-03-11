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

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.jdo.BFSystem;

public class PanelSystemOptions extends JPanel implements ActionCallbackListener {

    /**
     * The Systems object
     */
    private BFSystem s = null;

    private PanelSystemBuildings centerPanel;

    public PanelSystemOptions() {
        super(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(new EmptyBorder(5, 5, 5, 15));
        leftPanel.add(new JCheckBox("Alarmbereitschaft"));
        leftPanel.add(new JCheckBox("Nachrichtensperre"));

        centerPanel = new PanelSystemBuildings();

        this.add(leftPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);

        this.setTexts();
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);
    }

    private void setSystem(BFSystem sys) {
        s = sys;
        setTexts();
        centerPanel.setSystem(s);
        this.repaint();
    }

    public void setTexts() {
    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case CHANGE_CURRENT_SYSTEM:
            setSystem((BFSystem) object.getObject());
            break;

        default:
            break;
        }
        return true;
    }
}
