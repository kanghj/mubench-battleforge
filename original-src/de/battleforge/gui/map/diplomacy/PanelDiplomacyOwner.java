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

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFOwner;
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
 * @version 1.7
 */
public class PanelDiplomacyOwner extends JPanel implements MouseListener {

    /**
     * The owner
     */
    private BFOwner o = null;

    /**
     * Constructor
     * 
     * @param o
     */
    public PanelDiplomacyOwner(BFOwner owner) {

        o = owner;
        // String ownerType = o.getOwnerTypeAsString();
        String ownerType = o.getOwnerType().getName();

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
        this.addMouseListener(this);
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 5, 10, 5);
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        JLabel ownerName = new JLabel();
        if (ownerType.equals(Internationalization.getString("OwnerType.Clan"))) {
            ownerName.setText("Clan " + o.getName());
        } else {
            ownerName.setText(o.getName());
        }
        ownerName.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.add(ownerName, c);

        c.gridwidth = 1;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        this.add(new PanelImageDisplay(ImageFactory.getImage("ownerlogo", o.getLogo()), borders.NO, 0, true, 80, 80), c);

        c.insets = new Insets(0, 5, 0, 5);
        c.gridheight = 3;
        c.gridx = 1;
        c.weighty = 0.0;
        c.weightx = 1.0;
        JPanel info = new JPanel(new GridLayout(4, 0));
        info.setFont(new Font("SansSerif", Font.PLAIN, 10));

        info.setOpaque(false);
        info.add(new JLabel("[" + o.getShortName() + "] - " + ownerType));
        info.add(new JLabel("Verfeindet"));
        info.add(new JLabel("Meldric"));
        String systemCount = String.valueOf(o.getSystems().length);
        info.add(new JLabel(systemCount + " Systeme"));
        this.add(info, c);
    }

    /**
     * Owner
     * 
     * @return o
     */
    public BFOwner getOwner() {
        return o;
    }

    /**
     * 
     */
    public void mousePressed(MouseEvent me) {
        //
    }

    /**
     * 
     */
    public void mouseClicked(MouseEvent me) {
        // An owner was clicked upon
        // Update views where owner is displayed with
        // firing the action
        ActionManager.getAction(ACTIONS.CHANGE_OWNER).execute(o);
    }

    /**
     * 
     */
    public void mouseExited(MouseEvent me) {
        //
    }

    /**
     * 
     */
    public void mouseEntered(MouseEvent me) {
        //
    }

    /**
     * 
     */
    public void mouseReleased(MouseEvent me) {
        //
    }
}
