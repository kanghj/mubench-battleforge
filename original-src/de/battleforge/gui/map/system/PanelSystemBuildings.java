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
import java.awt.Component;
import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFSystemProperty;
import de.battleforge.jdo.DBWrapper;

/**
 * <p>
 * Title: <b>PanelSystemBuildings</b><br>
 * Description: <i>Displays all buildings for a system</i><br>
 * Copyright: Copyright (c) 2006<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.10
 */
public class PanelSystemBuildings extends JPanel {

    /**
     * Constructor
     */
    public PanelSystemBuildings() {
        //
    }

    /**
     * Set System
     * @param sys
     */
    public void setSystem(BFSystem sys) {
        
        this.removeAll();

        CustomListBoxRenderer renderer = new CustomListBoxRenderer();
        JList list = new JList();
        list.setModel(new DefaultListModel());
        list.setCellRenderer(renderer);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(1);

        DefaultListModel dlm = (DefaultListModel) list.getModel();

        Iterator iter = DBWrapper.getBuildingList(sys).iterator();
        while (iter.hasNext()) {
            BFSystemProperty b = (BFSystemProperty)iter.next();
            PanelBuilding pb = new PanelBuilding(b.getBuilding(), b.getProgress());
            dlm.addElement(pb);
            dlm.addElement(pb);
        }

        JScrollPane jscroll = new JScrollPane(list);
        //TODO: bug in jlist: die höhe wird 16 pix zu hoch gesetzt
        jscroll.setPreferredSize(new Dimension(200, 10));
        jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
        this.add(jscroll, BorderLayout.CENTER);
        this.add(new PanelToolBarBuildings(), BorderLayout.NORTH);
    }

    public class CustomListBoxRenderer implements ListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {

            JPanel p = (JPanel) value;
            p.setOpaque(true);

            if (isSelected) {
                p.setBackground(list.getSelectionBackground());
                p.setForeground(list.getSelectionForeground());
            } else {
                p.setBackground(list.getBackground());
                p.setForeground(list.getForeground());
            }

            return p;
        }
    }
}
