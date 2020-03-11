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
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.dialog.EditLogicalUnits;
import de.battleforge.gui.tree.BFTree;
import de.battleforge.gui.tree.BFTreeModel;
import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.gui.tree.LogicalUnitTreeCellRenderer;
import de.battleforge.jdo.BFDropship;
import de.battleforge.jdo.BFJumpship;
import de.battleforge.jdo.BFLogicalUnit;
import de.battleforge.jdo.BFMech;
import de.battleforge.jdo.BFOwner;
import de.battleforge.jdo.BFUnit;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.BFException;

public class PanelManageUnits extends JPanel implements ActionCallbackListener {
    
    private static Logger sLogger = Logger.getLogger(PanelManageUnits.class);
    
    private BFTree allUnitTree, logicalUnitTree;

    private BFTreeNode jumpshipRoot, dropshipRoot, mechRoot;

    BFTreeNode root, ownUnitsRoot, confederateUnitsRoot;

    private JComboBox cbOwner;

    private boolean editableOwner = false;

    private BFOwner owner;

    private JScrollPane sp1, sp2;

    private PanelToolBarLogicalUnitAll pToolBar1;

    private PanelToolBarLogicalUnitHierarchy pToolBar2;

    public PanelManageUnits(boolean editOwner, BFOwner owner) {
        editableOwner = editOwner;
        if (owner == null) {
            this.owner = DBWrapper.getCurrentGameUser().getOwner();
        } else {
            this.owner = owner;
        }
        init();

    }

    private void init() {
        createComponents();
        createLayout();
        registerAction();
    }

    private void registerAction() {
        ActionManager.addActionCallbackListener(ACTIONS.ADD_UNIT_TO_LOGICALUNIT, this);
        ActionManager.addActionCallbackListener(ACTIONS.REMOVE_UNIT_FROM_LOGICALUNIT, this);
        ActionManager.addActionCallbackListener(ACTIONS.ADD_NEW_LOGICALUNIT, this);
        ActionManager.addActionCallbackListener(ACTIONS.EDIT_LOGICALUNIT, this);
        ActionManager.addActionCallbackListener(ACTIONS.DELETE_LOGICALUNIT, this);
    }

    private void createComponents() {
        allUnitTree = new BFTree(createUnitTree());
        allUnitTree.setCellRenderer(new LogicalUnitTreeCellRenderer());

        logicalUnitTree = new BFTree(createLogicalUnitTree());
        logicalUnitTree.setCellRenderer(new LogicalUnitTreeCellRenderer());

        sp1 = new JScrollPane(allUnitTree);
        sp1.setPreferredSize(new Dimension(250, 1));
        sp2 = new JScrollPane(logicalUnitTree);
        sp2.setPreferredSize(new Dimension(250, 1));

        cbOwner = new JComboBox();
        cbOwner.setEditable(editableOwner);
        cbOwner.setEnabled(editableOwner);
        // cbOwner.setRenderer(new BTComboBoxRenderer());
        fillComboBox();
        if (owner != null) {
            cbOwner.setSelectedItem(owner);
        }

        pToolBar1 = new PanelToolBarLogicalUnitAll();
        pToolBar2 = new PanelToolBarLogicalUnitHierarchy();
    }

    private void createLayout() {

        JPanel p2 = new JPanel();
        p2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        p2.setLayout(new BorderLayout());
        p2.add(this.cbOwner, BorderLayout.SOUTH);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(pToolBar1, BorderLayout.NORTH);
        p3.add(sp1, BorderLayout.CENTER);

        JPanel p4 = new JPanel();
        p4.setAlignmentX(Component.LEFT_ALIGNMENT);
        p4.setLayout(new BorderLayout());
        p4.add(pToolBar2, BorderLayout.NORTH);
        p4.add(sp2, BorderLayout.CENTER);

        JSplitPane sp = new JSplitPane();
        sp.setLeftComponent(p3);
        sp.setRightComponent(p4);

        setLayout(new BorderLayout());

        this.add(p2, BorderLayout.NORTH);
        this.add(sp, BorderLayout.CENTER);
    }

    private void fillComboBox() {
        ArrayList<BFOwner> al = new ArrayList<BFOwner>(DBWrapper.getOwner());

        Iterator iter = al.iterator();
        while (iter.hasNext()) {
            cbOwner.addItem(iter.next());
        }
    }

    /**
     * create a treemodel that shows all units without logical unit
     * 
     * @return BFTreeModel
     */
    private BFTreeModel createUnitTree() {
        root = new BFTreeNode("Einheiten ohne Zuordnung");
        jumpshipRoot = new BFTreeNode("Sprungschiffe");
        dropshipRoot = new BFTreeNode("Landungsschiffe");
        mechRoot = new BFTreeNode("Mechs");

        root.add(jumpshipRoot);
        root.add(dropshipRoot);
        root.add(mechRoot);

        BFJumpship[] jumpships = DBWrapper.getJumpships(DBWrapper.getCurrentGameUser().getOwner());
        for (BFJumpship jumpship : jumpships) {
            if (jumpship.getLogicalUnit() == null) {
                BFTreeNode jumpShipNode = new BFTreeNode(jumpship);
                jumpshipRoot.add(jumpShipNode);
            } /* if */

        } /* for */

        BFMech[] mechs = DBWrapper.getMechs(DBWrapper.getCurrentGameUser().getOwner());
        for (BFMech mech : mechs) {
            if (mech.getLogicalUnit() == null) {
                BFTreeNode mechNode = new BFTreeNode(mech);
                mechRoot.add(mechNode);
            } /* if */

        } /* for */

        BFDropship[] dropships = DBWrapper.getDropships(DBWrapper.getCurrentGameUser().getOwner());
        for (BFDropship dropship : dropships) {
            if (dropship.getLogicalUnit() == null) {
                BFTreeNode dropshipNode = new BFTreeNode(dropship);
                mechRoot.add(dropshipNode);
            } /* if */

        } /* for */

        return new BFTreeModel(root);
    }

    /** füllt den Treeview mit Systemen und Einheiten */
    public BFTreeModel createLogicalUnitTree() {
        BFTreeNode root = new BFTreeNode("Logische Einheiten");
        ownUnitsRoot = new BFTreeNode("Eigene Einheiten");
        confederateUnitsRoot = new BFTreeNode("Verbündete Einheiten");

        root.add(ownUnitsRoot);
        root.add(confederateUnitsRoot);

        BFLogicalUnit[] logicalunits = DBWrapper.getLogicalUnits(DBWrapper.getCurrentGameUser().getOwner());

        for (BFLogicalUnit logicalunit : logicalunits) {
            BFTreeNode nodeLogicalUnit = new LogicalUnitTreeNode(logicalunit);

            ownUnitsRoot.add(nodeLogicalUnit);

        } // for

        // TODO: show confederate units
        return new BFTreeModel(root);
    }

    /**
     * remove units from unit tree and add them to logical unit tree
     */
    private void addUnits() {
        if (allUnitTree.getSelectionPaths() == null) {
            JOptionPane.showMessageDialog(Frame.getFrames()[0], "Keine Einheit zum Zuordnen gewählt!");
        } else if (logicalUnitTree.getSelectionPaths() == null) {
            JOptionPane.showMessageDialog(Frame.getFrames()[0], "Zieleinheit nicht eindeutig !");
        } else {
            TreePath[] node = allUnitTree.getSelectionPaths();
            for (TreePath element : node) {
                Object ob = (element).getLastPathComponent();
                BFTreeNode unitNode = (BFTreeNode) ob;
                Object o = ((BFTreeNode) ob).getUserObject();

                if ((o.getClass() == BFJumpship.class) || (o.getClass() == BFDropship.class) || (o.getClass() == BFMech.class)) {
                    TreePath destPath = logicalUnitTree.getSelectionPath();
                    BFTreeNode destNode = (BFTreeNode) destPath.getLastPathComponent();

                    if ((destNode != null) && (destNode.getUserObject().getClass() == BFLogicalUnit.class)) {
                        unitNode.removeFromParent();
                        destNode.add(unitNode);
                    }
                }
            }
            allUnitTree.setSelectionPath(null);
            ((BFTreeModel) logicalUnitTree.getModel()).reload();
            ((BFTreeModel) allUnitTree.getModel()).reload();
        }
    }

    /**
     * remove units from logical unit tree and add them to unit tree
     */
    private void removeUnits() {
        if (logicalUnitTree.getSelectionPaths() == null) {
            JOptionPane.showMessageDialog(Frame.getFrames()[0], "Keine Einheiten ausgewählt !");
        } else {
            TreePath[] node = logicalUnitTree.getSelectionPaths();
            for (TreePath element : node) {
                BFTreeNode ob = (BFTreeNode) (element).getLastPathComponent();
                if (ob.getUserObject() instanceof BFUnit) {
                    remove(ob);
                }

            }
            logicalUnitTree.setSelectionPath(null);
            ((BFTreeModel) logicalUnitTree.getModel()).reload();
            ((BFTreeModel) allUnitTree.getModel()).reload();
        }
    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case ADD_UNIT_TO_LOGICALUNIT:
            addUnits();
            break;
        case REMOVE_UNIT_FROM_LOGICALUNIT:
            removeUnits();
            break;
        case ADD_NEW_LOGICALUNIT:
            sLogger.debug("ADD_NEW_LOGICALUNIT");
            newLogicalUnit(true);
            ((BFTreeModel) logicalUnitTree.getModel()).reload();
            break;
        case EDIT_LOGICALUNIT:
            sLogger.debug("EDIT_LOGICALUNIT");
            newLogicalUnit(false);
            break;
        case DELETE_LOGICALUNIT:
            deleteLogicalUnit();
            sLogger.debug("DELETE_LOGICALUNIT");
            break;
        } // switch
        return true;
    }

    public void save() throws BFException {

    }

    public void cancel() {
    }

    private void newLogicalUnit(boolean bNew) {
        if ((logicalUnitTree.getSelectionPaths() == null) || (logicalUnitTree.getSelectionPaths().length != 1)) {
            JOptionPane.showMessageDialog(Frame.getFrames()[0], "Keine oder mehrere log. Einheiten ausgewählt!");
        } else {
            BFTreeNode ob = (BFTreeNode) logicalUnitTree.getSelectionPath().getLastPathComponent();
            
            if(ob.getUserObject() instanceof BFLogicalUnit) {
                EditLogicalUnits myLogicalUnits = new EditLogicalUnits(Frame.getFrames()[0]);
                myLogicalUnits.setLogicalUnit((BFTreeNode)ob, bNew);
                myLogicalUnits.setVisible(true);
                
            } else if("Eigene Einheiten".equals((String)ob.getUserObject())){
                EditLogicalUnits myLogicalUnits = new EditLogicalUnits(Frame.getFrames()[0]);
                myLogicalUnits.setLogicalUnit( (BFTreeNode)ob, bNew);
                myLogicalUnits.setVisible(true);
                
            
//            if (bNew && !(ob.getUserObject() instanceof BFLogicalUnit) || ob.getUserObject() instanceof BFLogicalUnit) {
//                EditLogicalUnits myLogicalUnits = new EditLogicalUnits(Frame.getFrames()[0]);
//                LogicalUnitTreeNode lo = null;
//                if( ob.getUserObject() instanceof BFLogicalUnit ) {
//                    lo = (LogicalUnitTreeNode)ob;
//                    
//                } //if
//                myLogicalUnits.setLogicalUnit(lo, bNew);
//                myLogicalUnits.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(Frame.getFrames()[0], "Keine log. Einheiten ausgewählt!");
            } // if

        } // if
        logicalUnitTree.setSelectionPath(null);
    }

    private void deleteLogicalUnit() {
        if ((logicalUnitTree.getSelectionPaths() == null) || (logicalUnitTree.getSelectionPaths().length != 1)) {
            JOptionPane.showMessageDialog(Frame.getFrames()[0], "Keine oder mehrere log. Einheiten ausgewählt!");

        } else {
            boolean childIsLogicalUnit = false;
            Object ob = (logicalUnitTree.getSelectionPath()).getLastPathComponent();
            BFTreeNode node = (BFTreeNode) ob;
            for (int i = 0; i < node.getChildCount(); i++) {
                if ((((BFTreeNode) node.getChildAt(i)).getUserObject() instanceof BFLogicalUnit) == true) {
                    childIsLogicalUnit = true;
                    JOptionPane.showMessageDialog(Frame.getFrames()[0],
                            "Es befinden sich noch log. Einheiten unter dieser Struktur. Löschen sie diese zuerst!");
                    break;

                } // if
            } // for
            if (node.getUserObject() instanceof BFLogicalUnit == true && childIsLogicalUnit == false) {
                int j = node.getChildCount();
                for (int i = 0; i < j; i++) {
                    remove((BFTreeNode) node.getChildAt(0));

                } // for
                node.removeFromParent();
                ((BFTreeModel) logicalUnitTree.getModel()).reload();
                ((BFTreeModel) allUnitTree.getModel()).reload();

            } else if (childIsLogicalUnit == false) {
                JOptionPane.showMessageDialog(Frame.getFrames()[0], "Keine log. Einheiten ausgewählt!");

            } // if
        } // if
        logicalUnitTree.setSelectionPath(null);
    }

    private void remove(BFTreeNode unitNode) {
        Object o = unitNode.getUserObject();
        unitNode.removeFromParent();

        if (o.getClass() == BFJumpship.class) {
            jumpshipRoot.add(unitNode);

        } else if (o.getClass() == BFDropship.class) {
            dropshipRoot.add(unitNode);

        } else if (o.getClass() == BFMech.class) {
            mechRoot.add(unitNode);

        } // if
    }
}