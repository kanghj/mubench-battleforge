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
package de.battleforge.gui.map;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.StatusBar;
import de.battleforge.gui.map.units.PanelUnitTree;
import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.gui.tree.Filter;
import de.battleforge.gui.tree.Sorter;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFUnit;

/**
 * <p>
 * Title: <b>PanelSystemDetail</b><br>
 * Description: <i>Display the selected system and the unit tree</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class PanelSystemList extends JPanel implements ActionCallbackListener {

    /**
     * ComboBox model
     */
    // private DefaultComboBoxModel modelOwner = new DefaultComboBoxModel();
    /**
     * ComboBox to select systems
     */
    // private JComboBox systemSelect = null;
    /**
     * Tree panel
     */
    private PanelUnitTree myPanelUnitTree;

    /**
     * Systems
     */
    private BFSystem mSelectedSystem;

    // private EventListenerList mListener;

    /**
     * Constructor
     * 
     * @param myPMap
     *            the PanelMap to work with
     */
    public PanelSystemList() {
        super(new BorderLayout());

        // mListener = new EventListenerList();

        // ImageIcon icon = Tools.getIcon("ownerlogo", "clan_ghostbear.png");
        // signPanel = new PanelImageDisplay(icon, 0);
        //
        // Dimension dim = new Dimension(icon.getIconWidth(),
        // icon.getIconHeight() + 10);
        // signPanel.setPreferredSize(dim);
        // signPanel.setMaximumSize(dim);
        // signPanel.setMinimumSize(dim);

        // systemSelect = new JComboBox();
        // systemSelect.addItemListener( this );
        // systemSelect.setEditable(false);
        // systemSelect.setModel(modelOwner);
        // systemSelect.setRenderer(new BTComboBoxRenderer("getSystem"));
        // this.add(systemSelect, BorderLayout.NORTH);

        // fillComboBox();

        myPanelUnitTree = new PanelUnitTree();

        JPanel panel = new JPanel();

        JToggleButton sortButton = new JToggleButton("sort");
        JToggleButton filterButton = new JToggleButton("filter");

        panel.add(sortButton);
        panel.add(filterButton);

        sortButton.addItemListener(new ItemListener() {

            private Sorter mySorter = new Sorter() {

                public BFTreeNode[] sort(BFTreeNode[] childs) {
                    if ((childs.length > 0) && !(childs[0].getUserObject() instanceof BFUnit)) {
                        return childs;

                    }

                    List<BFTreeNode> list = Arrays.asList(childs);

                    Collections.reverse(list);

                    return list.toArray(new BFTreeNode[childs.length]);

                }

            };

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myPanelUnitTree.setSorter(mySorter);

                } else {
                    myPanelUnitTree.setSorter(null);

                }
            }

        });

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton showSystem = new JRadioButton("Systeme");
        showSystem.setSelected(true);
        buttonGroup.add(showSystem);
        showSystem.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myPanelUnitTree.showTree(PanelUnitTree.SHOW_SYSTEMTREE);

                }
            }
        });
        panel.add(showSystem);

        JRadioButton showLogicalUnits = new JRadioButton("Einheiten");
        showLogicalUnits.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myPanelUnitTree.showTree(PanelUnitTree.SHOW_UNITTREE);

                }
            }
        });
        panel.add(showLogicalUnits);
        buttonGroup.add(showLogicalUnits);

        filterButton.addItemListener(new ItemListener() {

            private Filter myFilter = new Filter() {

                public BFTreeNode[] filter(BFTreeNode[] childs) {
                    if ((childs.length > 0) && !(childs[0].getUserObject() instanceof BFSystem)) {
                        return childs;

                    }

                    ArrayList<BFTreeNode> newChilds = new ArrayList<BFTreeNode>();

                    for (BFTreeNode node : childs) {
                        BFSystem system = (BFSystem) node.getUserObject();

                        if (system.getName().startsWith("A")) {
                            newChilds.add(node);

                        } // if
                    } // for

                    return newChilds.toArray(new BFTreeNode[newChilds.size()]);

                }

            };

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myPanelUnitTree.addFilter(myFilter);

                } else {
                    myPanelUnitTree.removeFilter(myFilter);

                }
            }

        });

        add(panel, BorderLayout.NORTH);
        add(myPanelUnitTree, BorderLayout.CENTER);

        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);

    }

    /**
     * Sets the selected system
     * 
     * @param targetSystem
     *            system to be set
     */
    public void setSelectedSystem(BFSystem targetSystem) {
        if ((targetSystem != null) && (mSelectedSystem != targetSystem)) {
            mSelectedSystem = targetSystem;

            StatusBar.gibExemplar().setKoordinaten(targetSystem.getX(), targetSystem.getY());

        } /* if */
    }

    /**
     * Returns the selected system as string
     * 
     * @return String
     */
    public BFSystem getSelectedSystem() {
        return mSelectedSystem;
    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case CHANGE_CURRENT_SYSTEM:
            setSelectedSystem((BFSystem) object.getObject());

        }

        return true;

    }

    /**
     * Fills the comboBox with all the systems from the database
     */
    // private void fillComboBox() {
    // Iterator it = Universe.alUniverse.iterator();
    // while ( it.hasNext() ) {
    // Systems s = (Systems) it.next();
    // modelOwner.addElement( s );
    // if ( s.getSystem().equals( "Terra" ) ) {
    // modelOwner.setSelectedItem( s );
    // }
    // }
    // }

    /**
     * Refresh from outside
     */
    // public final void refresh() {
    // myPanelUnitTree.refresh();
    // }
    // public void itemStateChanged( ItemEvent e ) {
    // if ( e.getStateChange() == ItemEvent.SELECTED ) {
    // setSelectedSystem( (Systems) e.getItem() );
    //
    // }
    // }
}
