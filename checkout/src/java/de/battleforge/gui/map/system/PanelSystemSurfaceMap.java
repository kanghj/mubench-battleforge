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

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.battleforge.jdo.BFSystem;

/**
 * <p>
 * Title: <b>PanelSystemBattleProgress</b><br>
 * Description: <i>Displays the status of an ongoing battle</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.1
 */
public class PanelSystemSurfaceMap extends JPanel {

    /**
     * The system
     */
    BFSystem system;

    /**
     * Constructor
     * @param s
     */
    public PanelSystemSurfaceMap(BFSystem s) {
        system = s;
    }
}
