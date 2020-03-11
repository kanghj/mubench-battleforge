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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.gui.tree.BFTree;
import de.battleforge.gui.tree.BFTreeModel;
import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.gui.tree.Filter;
import de.battleforge.gui.tree.LogicalUnitTreeCellRenderer;
import de.battleforge.gui.tree.Sorter;
import de.battleforge.gui.tree.SystemTreeCellRenderer;
import de.battleforge.jdo.BFDropship;
import de.battleforge.jdo.BFJumpship;
import de.battleforge.jdo.BFLogicalUnit;
import de.battleforge.jdo.BFMech;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFUnit;
import de.battleforge.jdo.DBWrapper;

public class PanelUnitTree extends JPanel implements TreeSelectionListener, ActionListener {

    private static Logger sLogger = Logger.getLogger(PanelUnitTree.class);

    private BFTree mSystemTree;

    private JPopupMenu mPopupMenu;

    private Filter mTreeFilterForeignUnits;

    public static int SHOW_SYSTEMTREE = 1;

    public static int SHOW_UNITTREE = 2;

    public PanelUnitTree() {
        super(new BorderLayout());

        mPopupMenu = new JPopupMenu();

        mTreeFilterForeignUnits = new Filter() {

            public BFTreeNode[] filter(BFTreeNode[] childs) {
                ArrayList<BFTreeNode> filteredChilds = new ArrayList<BFTreeNode>(childs.length);

                for (BFTreeNode node : childs) {
                    if (node.getUserObject() instanceof BFUnit) {
                        BFUnit unit = (BFUnit) node.getUserObject();

                        if (unit.getOwner() == DBWrapper.getCurrentGameUser().getOwner()) {
                            filteredChilds.add(node);

                        } // if
                    } else {
                        filteredChilds.add(node);

                    } // if
                } // for

                return filteredChilds.toArray(new BFTreeNode[filteredChilds.size()]);

            }

        };

        mSystemTree = new BFTree();
        mSystemTree.setRootVisible(false);
        mSystemTree.setShowsRootHandles(true);
        mSystemTree.addTreeSelectionListener(this);
        mSystemTree.setAction(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                handleActionOnTree();

            }

        });

        add(new JScrollPane(mSystemTree), BorderLayout.CENTER);

        showTree(SHOW_SYSTEMTREE);
    }

    /** füllt den Treeview mit Systemen und Einheiten */
    private BFTreeModel createTree() {
        BFTreeNode root = new BFTreeNode("Systeme");
        BFTreeNode fightingSystemsRoot = new BFTreeNode("Umkämpfte Systeme");
        BFTreeNode ownSystemsRoot = new BFTreeNode("Eigene Systeme");
        BFTreeNode confederateSystemsRoot = new BFTreeNode("Verbündete Systeme");
        BFTreeNode thirdPartySystemsRoot = new BFTreeNode("Fremde Systeme");

        root.add(fightingSystemsRoot);
        root.add(ownSystemsRoot);
        root.add(confederateSystemsRoot);
        root.add(thirdPartySystemsRoot);

        /* Systeme mit Einheiten werden im Treeview zuerst dargestellt */
        BFSystem[] systems = DBWrapper.getAllSystems();
        DBWrapper.getAllUnitsForSystems();
        for (BFSystem system : systems) {
            SystemTreeNode nodeSystem = new SystemTreeNode(system);

            if (system.getOwner() == DBWrapper.getCurrentGameUser().getOwner()) {
                ownSystemsRoot.add(nodeSystem);

            } else {
                thirdPartySystemsRoot.add(nodeSystem);

            } // if
        }

        BFTreeModel model = new BFTreeModel(root);

        model.addFilter(mTreeFilterForeignUnits);

        return model;

    }

    /** click im JTree SystemUnitInformation */
    public void valueChanged(TreeSelectionEvent e) {
        TreePath newLeadSelectionPath = e.getNewLeadSelectionPath();

        if (newLeadSelectionPath == null) {
            mSystemTree.setComponentPopupMenu(null);

        } else {
            createPopup((BFTreeNode) newLeadSelectionPath.getLastPathComponent());

        }
    }

    public SystemTreeNode findCurrentSystemTreeNode(UnitTreeNode unitTreeNode) {

        SystemTreeNode currentSystemNode = null;
        BFTreeNode parent = (BFTreeNode) unitTreeNode.getParent();
        boolean findCurrentSystemNode = false;

        while (!findCurrentSystemNode || (parent == null)) {

            if (parent instanceof SystemTreeNode) {
                currentSystemNode = (SystemTreeNode) parent;
                findCurrentSystemNode = true;

            } else {
                parent = (BFTreeNode) parent.getParent();

            }
        }

        return currentSystemNode;
    }

    /**
     * Erzeugt das Popupmenu dynamisch, je nachdem was im Tree ausgewählt ist
     * 
     * @param tn
     */
    private void createPopup(BFTreeNode tn) {
        if ((tn != null) && (tn instanceof UnitTreeNode)) {
            BFUnit unit = (BFUnit) tn.getUserObject();

            SystemTreeNode currentSystemNode = findCurrentSystemTreeNode((UnitTreeNode) tn);

            // create menu for dropship
            if (unit instanceof BFDropship) {

                if (unit.canLandOnCurrentSystem()) {

                    TreeMenuItem menuDropship = new TreeMenuItem(currentSystemNode, (UnitTreeNode) tn, "abdocken");
                    menuDropship.addActionListener(this);

                    mPopupMenu.add(menuDropship);

                } // if

                if (unit.getCurrentSystem() != null) {

                    JMenu menu = new JMenu("andocken");
                    mPopupMenu.add(menu);

                    Enumeration nodes = currentSystemNode.children();

                    while (nodes.hasMoreElements()) {
                        UnitTreeNode unitNode = (UnitTreeNode) nodes.nextElement();
                        sLogger.debug(unitNode.getUserObject());

                        if (unit.canDocking(unitNode.getUserObject())) {
                            TreeMenuItem unitItem = new TreeMenuItem(unitNode, (UnitTreeNode) tn, unitNode.getUserObject().getName());
                            unitItem.addActionListener(this);
                            menu.add(unitItem);

                        }// if
                    } // while
                } // if

                // create menu for mech
            } else if (tn.getUserObject() instanceof BFMech) {

                if (unit.canLandOnCurrentSystem()) {

                    TreeMenuItem menuMech = new TreeMenuItem(currentSystemNode, (UnitTreeNode) tn, "ausladen");
                    menuMech.addActionListener(this);
                    mPopupMenu.add(menuMech);

                } // if

                if (unit.getCurrentSystem() != null) {
                    JMenu menu = new JMenu("einladen");
                    mPopupMenu.add(menu);

                    Enumeration nodes = tn.getParent().children();

                    while (nodes.hasMoreElements()) {
                        UnitTreeNode unitNode = (UnitTreeNode) nodes.nextElement();
                        sLogger.debug(unitNode.getUserObject());

                        if (unit.canDocking(unitNode.getUserObject())) {
                            TreeMenuItem unitItem = new TreeMenuItem(unitNode, (UnitTreeNode) tn, unitNode.getUserObject().getName());
                            unitItem.addActionListener(this);
                            menu.add(unitItem);

                        }// if
                    }
                }// if
            }// if
        }// if

        mSystemTree.setComponentPopupMenu(mPopupMenu.getComponentCount() == 0 ? null : mPopupMenu);
    }

    /* overwrite methode from actionlistener */
    public void actionPerformed(ActionEvent ae) {
        // String cmd = ae.getActionCommand();

        if (ae.getSource() instanceof TreeMenuItem) {
            TreeMenuItem o = (TreeMenuItem) ae.getSource();

            o.getUserObject().removeFromParent();
            sLogger.debug("remove object from parent -> " + o.getUserObject());

            o.getNewParentObject().add(o.getUserObject());
            sLogger.debug("add object -> " + o.getUserObject() + " -> new parent -> " + o.getNewParentObject());

            mSystemTree.setSelectionPath(null);

            ((BFTreeModel) this.mSystemTree.getModel()).reload();
        }
    }

    private void handleActionOnTree() {
        BFTreeNode node = (BFTreeNode) mSystemTree.getLastSelectedPathComponent();

        if (node == null) {
            return;

        } // if

        if (node.getUserObject() instanceof BFSystem) {
            ActionManager.getAction(ACTIONS.CHANGE_CURRENT_SYSTEM).execute(node.getUserObject());

        } else if (node.getUserObject() instanceof BFJumpship) {
            ActionManager.getAction(ACTIONS.OPEN_JUMPSHIP_TAB).execute(node.getUserObject());

        } // if

    }

    public void setSorter(Sorter mySorter) {
        ((BFTreeModel) mSystemTree.getModel()).setSorter(mySorter);

    }

    public void sort() {
        ((BFTreeModel) mSystemTree.getModel()).sortAndFilter();

    }

    public void removeFilter(Filter myFilter) {
        ((BFTreeModel) mSystemTree.getModel()).removeFilter(myFilter);

    }

    public void addFilter(Filter myFilter) {
        ((BFTreeModel) mSystemTree.getModel()).addFilter(myFilter);

    }

    /** füllt den Treeview mit Systemen und Einheiten */
    public BFTreeModel createLogicalUnitTree() {
        BFTreeNode root = new BFTreeNode("Logische Einheiten");
        BFTreeNode ownUnitsRoot = new BFTreeNode("Eigene Einheiten");
        BFTreeNode confederateUnitsRoot = new BFTreeNode("Verbündete Einheiten");

        root.add(ownUnitsRoot);
        root.add(confederateUnitsRoot);

        BFLogicalUnit[] logicalunits = DBWrapper.getLogicalUnits(DBWrapper.getCurrentGameUser().getOwner());

        for (BFLogicalUnit logicalunit : logicalunits) {
            BFTreeNode nodeLogicalUnit = new LogicalUnitTreeNode(logicalunit);

            ownUnitsRoot.add(nodeLogicalUnit);

        }

        // TODO: show confederate units
        return new BFTreeModel(root);
    }

    public void showTree(int i) {
        if (i == SHOW_SYSTEMTREE) {
            // TODO: hier wurde nur geknaupt. Muss man besser machen
            mSystemTree.setCellRenderer(new DefaultTreeCellRenderer());
            mSystemTree.setModel(createTree());
            mSystemTree.setCellRenderer(new SystemTreeCellRenderer());
        } else if (i == SHOW_UNITTREE) {
            // TODO: hier wurde nur geknaupt. Muss man besser machen
            mSystemTree.setCellRenderer(new DefaultTreeCellRenderer());
            mSystemTree.setModel(createLogicalUnitTree());
            mSystemTree.setCellRenderer(new LogicalUnitTreeCellRenderer());
        }
    }

}

class TreeMenuItem extends JMenuItem {
    UnitTreeNode unitNode;

    BFTreeNode newParentNode;

    TreeMenuItem(BFTreeNode newParentNode, UnitTreeNode unitNode, String text) {
        super(text);
        this.unitNode = unitNode;
        this.newParentNode = newParentNode;
    }

    public UnitTreeNode getUserObject() {
        return unitNode;
    }

    public BFTreeNode getNewParentObject() {
        return newParentNode;
    }

}
