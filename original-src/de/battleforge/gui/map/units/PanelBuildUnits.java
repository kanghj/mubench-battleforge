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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.tree.BFTree;
import de.battleforge.gui.tree.BFTreeModel;
import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFOwner;
import de.battleforge.util.BFConstant;

/**
 * <p>
 * Title: <b>PanelBuildUnits</b><br>
 * Description: <i>Lists up the units to build</i><br>
 * Copyright: Copyright (c) 2006<br>
 * Company: BattleForge<br>
 * <br>
 * Information about the system is displayed here.
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class PanelBuildUnits extends JPanel implements ActionCallbackListener {

    /**
     * 
     */
    private PanelToolBarBuildUnitsAll pToolBar1;

    /**
     * 
     */
    private PanelToolBarBuildUnitsAll pToolBar2;

    /**
     * PanelBuildUnits
     * 
     * @param owner
     */
    public PanelBuildUnits(BFOwner owner) {
        // Create layout
        this.setLayout(new BorderLayout());
        this.add(generateUsedSlotsGraphics(5, 3), BorderLayout.WEST);

        JSplitPane jsplit = new JSplitPane();
        jsplit.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jsplit.setLeftComponent(generateUnitSelection());
        jsplit.setRightComponent(generateBuildList());
        jsplit.setDividerLocation(200);
        jsplit.setResizeWeight(1.0);
        this.add(jsplit, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(100, 100));

        // Register actions
        ActionManager.addActionCallbackListener(ACTIONS.BUILD_UNIT, this);
    }

    private JPanel generateUnitSelection() {
        BFTreeNode root = new BFTreeNode("Einheiten ohne Zuordnung");
        BFTreeNode jumpshipRoot = new BFTreeNode("Sprungschiffe");
        BFTreeNode dropshipRoot = new BFTreeNode("Landungsschiffe");
        BFTreeNode mechRoot = new BFTreeNode("Mechs");

        root.add(jumpshipRoot);
        root.add(dropshipRoot);
        root.add(mechRoot);

        BFTreeModel treeModel = new BFTreeModel(root);
        BFTree tree = new BFTree(treeModel);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel p = new JPanel(new BorderLayout());
        p.add(scrollPane, BorderLayout.CENTER);

        pToolBar1 = new PanelToolBarBuildUnitsAll();
        p.add(pToolBar1, BorderLayout.NORTH);

        return p;
    }

    private JPanel generateUsedSlotsGraphics(int usableSlots, int usedSlots) {
        Icon icon = null;

        JPanel icons = new JPanel();
        icons.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        icons.setLayout(new BoxLayout(icons, BoxLayout.PAGE_AXIS));
        for (int i = 1; i <= BFConstant.MAXIMUM_INDUSTRY_SLOTS; i++) {
            if (i <= usedSlots) {
                icon = ImageFactory.getIcon("slot_used.png");
            } else {
                if (i <= usableSlots) {
                    icon = ImageFactory.getIcon("slot_free.png");
                } else {
                    icon = ImageFactory.getIcon("slot_empty.png");
                }
            }
            icons.add(new JLabel(icon));
        }

        return icons;
    }

    private JPanel generateUnitsInProgress() {
        CustomListBoxRenderer renderer = new CustomListBoxRenderer();
        JList list = new JList();
        list.setModel(new DefaultListModel());
        list.setCellRenderer(renderer);

        DefaultListModel dlm = (DefaultListModel) list.getModel();
        dlm.addElement(new PanelBuildUnitProgress());
        dlm.addElement(new PanelBuildUnitProgress());
        dlm.addElement(new PanelBuildUnitProgress());
        dlm.addElement(new PanelBuildUnitProgress());

        JScrollPane jscroll = new JScrollPane(list);
        jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(jscroll, BorderLayout.CENTER);
        p.setMinimumSize(new Dimension(300, 1));

        return p;
    }

    private JPanel generateBuildList() {
        JPanel p = new JPanel(new BorderLayout());
        pToolBar2 = new PanelToolBarBuildUnitsAll();
        p.add(pToolBar2, BorderLayout.NORTH);
        p.add(generateUnitsInProgress(), BorderLayout.CENTER);

        return p;
    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case BUILD_UNIT:
            System.out.println("Build Unit");
            break;
        } // switch
        return true;
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
