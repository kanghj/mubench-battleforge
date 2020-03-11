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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.gui.map.units.PanelBuildUnits.CustomListBoxRenderer;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFOwner;
import de.battleforge.jdo.BFOwnerType;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>PanelDiplomacy</b><br>
 * Description: <i>Manages the relationship between factions</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.12
 */
public class PanelDiplomacy extends JPanel implements ActionListener {

    private JComboBox cbFilter = new JComboBox();

    private JPanel allPanel = null;

    private JScrollPane sp;

    private boolean reverseSort = false;

    private TreeSet sortedOwners = new TreeSet(new OwnerComparator(reverseSort));

    private boolean sortOld = false;

    private JToggleButton up;

    private JToggleButton down;

    private HashMap<String, JPanel> hm = new HashMap<String, JPanel>();

    /**
     * Constructor
     */
    public PanelDiplomacy() {
        this.setLayout(new BorderLayout());
        JPanel pTemp = null;

        cbFilter.addItem(Internationalization.getString("Diplomacy.AllEntry"));

        // add only those owner who are attached to at least one system
        // Iterator i = Universe.ownerList.getArrayList().iterator();
        Iterator i = DBWrapper.getOwner().iterator();

        while (i.hasNext()) {
            BFOwner o = (BFOwner) i.next();
            if (o.getHasSystems()) {
                sortedOwners.add(o);
            }
        }

        allPanel = createAllOwnersList();
        hm.put(Internationalization.getString("Diplomacy.AllEntry"), allPanel);

        // add all owners of specific types to the diplomacy panel
        // Iterator iter = Universe.ownerTypeList.getArrayList().iterator();
        Iterator iter = DBWrapper.getOwnerType().iterator();
        while (iter.hasNext()) {
            BFOwnerType ot = (BFOwnerType) iter.next();
            String OwnerTypeName = Internationalization.getString("OwnerType." + ot.getName());
            cbFilter.addItem(OwnerTypeName);
            // hm.put(OwnerTypeName, createOwnerList(ot.getOwnerTypeID()));
            hm.put(OwnerTypeName, createOwnerList(ot));

            if (DBWrapper.getCurrentGameUser().getOwner().getOwnerType() == ot) {
                cbFilter.setSelectedItem(OwnerTypeName);
                pTemp = hm.get(OwnerTypeName);
            }
        }

        if (DBWrapper.getCurrentGameUser().getOwner().getOwnerType() == null) {
            pTemp = allPanel;
        }

        JPanel pFilter = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        pFilter.add(new JLabel(Internationalization.getString("Diplomacy.Filter")), c);

        c.gridx = 1;
        pFilter.add(cbFilter, c);

        c.gridx = 2;
        c.weightx = 1.0;
        pFilter.add(new JLabel(""), c);

        c.gridx = 3;
        c.weightx = 0.0;
        pFilter.add(new JLabel(Internationalization.getString("Diplomacy.LabelSort")), c);

        c.gridx = 4;
        c.insets = new Insets(5, 5, 5, 0);
        up = new JToggleButton(ImageFactory.getIcon("sortUp.png"));
        up.setActionCommand("sortUp");
        up.addActionListener(this);
        pFilter.add(up, c);

        c.gridx = 5;
        c.insets = new Insets(5, 5, 5, 20);
        down = new JToggleButton(ImageFactory.getIcon("sortDown.png"));
        down.setActionCommand("sortDown");
        down.addActionListener(this);
        pFilter.add(down, c);

        ButtonGroup bg = new ButtonGroup();
        bg.add(up);
        bg.add(down);
        up.setSelected(true);

        c.gridx = 6;
        c.insets = new Insets(5, 5, 5, 5);
        pFilter.add(new JLabel(Internationalization.getString("Diplomacy.LabelResetAll")));

        c.gridx = 7;
        JButton reset = new JButton(Internationalization.getString("Diplomacy.ResetAll"));
        reset.setActionCommand("resetAll");
        reset.addActionListener(this);
        pFilter.add(reset, c);

        this.add(pFilter, BorderLayout.NORTH);

        sp = new JScrollPane(pTemp);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setPreferredSize(new Dimension(250, 200));

        this.add(new PanelOwnerInformationDiplomacy(), BorderLayout.CENTER);
        this.add(sp, BorderLayout.WEST);

        ActionManager.getAction(ACTIONS.CHANGE_OWNER).execute(DBWrapper.getCurrentGameUser().getOwner());

        cbFilter.addActionListener(this);
    }

    /**
     * Renew the panels after the selection of a new sort direction
     */
    public void recreatePanels() {
        TreeSet sortedOwnersTmp = new TreeSet(new OwnerComparator(reverseSort));

        // add only those owner who are attached to at least one system
        // Iterator i = Universe.ownerList.getArrayList().iterator();
        Iterator i = DBWrapper.getOwner().iterator();
        while (i.hasNext()) {
            BFOwner o = (BFOwner) i.next();
            if (o.getHasSystems()) {
                sortedOwnersTmp.add(o);
            }
        }

        sortedOwners = sortedOwnersTmp;

        hm.clear();

        allPanel = createAllOwnersList();
        hm.put(Internationalization.getString("Diplomacy.AllEntry"), allPanel);

        // Iterator iter = Universe.ownerTypeList.getArrayList().iterator();
        Iterator iter = DBWrapper.getOwnerType().iterator();
        while (iter.hasNext()) {
            BFOwnerType ot = (BFOwnerType) iter.next();
            String OwnerTypeName = Internationalization.getString("OwnerType." + ot.getName());
            // hm.put(OwnerTypeName, createOwnerList(ot.getOwnerTypeID()));
            hm.put(OwnerTypeName, createOwnerList(ot));
        }

        sp.getViewport().setView(hm.get(cbFilter.getSelectedItem()));
    }

    /**
     * Create the panel with all owners
     * 
     * @return
     */
    private JPanel createAllOwnersList() {
        
        CustomListBoxRenderer renderer = new CustomListBoxRenderer();
        JList list = new JList();
        list.setModel(new DefaultListModel());
        list.setCellRenderer(renderer);

        DefaultListModel dlm = (DefaultListModel) list.getModel();

        Iterator iter = sortedOwners.iterator();
        while (iter.hasNext()) {
            BFOwner o = (BFOwner) iter.next();
            if (o != DBWrapper.getCurrentGameUser().getOwner()) {
                PanelDiplomacyOwner myOwnerPanel = new PanelDiplomacyOwner(o);
                dlm.addElement(myOwnerPanel);
            }
        }

        JScrollPane jscroll = new JScrollPane(list);
        jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(jscroll, BorderLayout.CENTER);
        p.setMinimumSize(new Dimension(300, 1));

        return p;
    }

    /**
     * Create gui for diplomacy
     */
    // private JPanel createOwnerList(long ownerType) {
    //
    // JPanel cp = new JPanel();
    // cp.setLayout(new GridLayout(0,1));
    //
    // // add all owners of specific types to the diplomacy panel
    // Iterator iter = sortedOwners.iterator();
    // while (iter.hasNext()) {
    // BFOwner o = (BFOwner) iter.next();
    // if ((o != DBWrapper.getCurrentGameUser().getOwner()) && (o.getOwnerType()
    // == ownerType)) {
    // PanelDiplomacyOwner myOwnerPanel = new PanelDiplomacyOwner(o);
    // cp.add(myOwnerPanel);
    // }
    // }
    //        
    // JPanel sp = new JPanel();
    // sp.setLayout(new GridBagLayout());
    // GridBagConstraints c = new GridBagConstraints();
    //
    // c.fill = GridBagConstraints.BOTH;
    // c.anchor = GridBagConstraints.PAGE_START;
    // c.insets = new Insets(0, 0, 0, 0);
    // c.gridwidth = 1;
    // c.gridheight = 1;
    // c.gridx = 0;
    // c.gridy = 0;
    // c.weightx = 1.0;
    // c.weighty = 0.0;
    // sp.add(cp, c);
    //
    // c.gridy = 1;
    // c.weighty = 1.0;
    // sp.add(new JLabel(""), c);
    //
    // return sp;
    // }
    private JPanel createOwnerList(BFOwnerType ownerType) {

        JPanel cp = new JPanel();
        cp.setLayout(new GridLayout(0, 1));

        // add all owners of specific types to the diplomacy panel
        Iterator iter = sortedOwners.iterator();
        while (iter.hasNext()) {
            BFOwner o = (BFOwner) iter.next();
            if ((o != DBWrapper.getCurrentGameUser().getOwner()) && (o.getOwnerType() == ownerType)) {
                PanelDiplomacyOwner myOwnerPanel = new PanelDiplomacyOwner(o);
                cp.add(myOwnerPanel);
            }
        }

        JPanel sp = new JPanel();
        sp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;
        sp.add(cp, c);

        c.gridy = 1;
        c.weighty = 1.0;
        sp.add(new JLabel(""), c);

        return sp;
    }

    /**
     * Start acting when the combobox changes
     */
    public final void actionPerformed(ActionEvent e) {
        if ("comboBoxChanged".equals(e.getActionCommand())) {
            JComboBox cb = (JComboBox) e.getSource();
            Object newItem = cb.getSelectedItem();

            // if all are selected, no need to iterate the ownertypes
            if (newItem.equals(Internationalization.getString("Diplomacy.AllEntry"))) {
                sp.getViewport().setView(allPanel);
            } else {
                // Iterator iter =
                // Universe.ownerTypeList.getArrayList().iterator();
                Iterator iter = DBWrapper.getOwnerType().iterator();
                while (iter.hasNext()) {
                    BFOwnerType ot = (BFOwnerType) iter.next();
                    String OwnerTypeName = Internationalization.getString("OwnerType." + ot.getName());

                    if (newItem.equals(OwnerTypeName)) {
                        sp.getViewport().setView(hm.get(OwnerTypeName));
                    }
                }
            }
        } else if ("sortUp".equals(e.getActionCommand())) {
            reverseSort = false;
            if (reverseSort != sortOld) {
                sortOld = reverseSort;
                recreatePanels();
            }
        } else if ("sortDown".equals(e.getActionCommand())) {
            reverseSort = true;
            if (reverseSort != sortOld) {
                sortOld = reverseSort;
                recreatePanels();
            }
        } else if ("resetAll".equals(e.getActionCommand())) {
            //
        }
    }

    /**
     * OwnerComparator Used for sorting the owners
     */
    private class OwnerComparator implements Comparator {

        private boolean reverseSort = false;

        /**
         * Inner class Constructor
         * 
         * @param reverseSort
         */
        public OwnerComparator(boolean reverseSort) {
            this.reverseSort = reverseSort;
        }

        public int compare(Object arg0, Object arg1) {
            BFOwner o1 = (BFOwner) arg0;
            BFOwner o2 = (BFOwner) arg1;

            if (reverseSort) {
                // return -o1.getOwner().compareTo(o2.getOwner());
                return -o1.getName().compareTo(o2.getName());
            } else {
                // return o1.getOwner().compareTo(o2.getOwner());
                return o1.getName().compareTo(o2.getName());
            }
        } // compare
    } // end of inner-class OwnerComparator

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
