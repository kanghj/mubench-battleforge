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
package de.battleforge.gui.tree;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class BFTree extends JTree {

    private boolean mMouseOverNode;

    private boolean mValidate = true;

    public BFTree() {
        super();

        init();

    } // BFTree

    public BFTree(Hashtable<?, ?> value) {
        super(value);

        init();

    } // BFTree

    public BFTree(Object[] value) {
        super(value);

        init();

    } // BFTree

    public BFTree(TreeModel newModel) {
        super(newModel);

        init();

    } // BFTree

    public BFTree(TreeNode root, boolean asksAllowsChildren) {
        super(root, asksAllowsChildren);

        init();

    } // BFTree

    public BFTree(TreeNode root) {
        super(root);

        init();

    } // BFTree

    public BFTree(Vector<?> value) {
        super(value);

        init();

    } // BFTree

    private void init() {
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                TreePath path = getClosestPathForLocation(e.getX(), e.getY());

                mMouseOverNode = getUI().getPathBounds(BFTree.this, path).contains(e.getX(), e.getY());

            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int row = getRowForLocation(e.getX(), e.getY());

                if (!isRowSelected(row)) {
                    if (row > -1) {
                        setSelectionRow(row);

                    } // if

                } // if
            } // mousePressed

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    handleActionOnTree();

                } // if
            } // mouseClicked
        });

        addTreeWillExpandListener(new TreeWillExpandListener() {

            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                if (shouldThrowVetoException()) {
                    throw new ExpandVetoException(event);

                } // if
            }

            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                if (shouldThrowVetoException()) {
                    throw new ExpandVetoException(event);

                } // if
            }

            private boolean shouldThrowVetoException() {
                AWTEvent e = EventQueue.getCurrentEvent();

                return (mMouseOverNode && (e instanceof MouseEvent) && (e.getSource() == BFTree.this) && (((MouseEvent) e).getClickCount() % 2 == 0));
            }

        });

        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");

    } // init

    @Override
    public void setModel(TreeModel newModel) {
        super.setModel(newModel);

        newModel.addTreeModelListener(new MyTreeModelHandler());

    }

    private void handleActionOnTree() {
        Action action = getAction();

        if (action != null) {
            KeyEvent keyEvent = new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n');

            SwingUtilities.notifyAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), keyEvent, this, 0);

        } // if
    } // handleActionOnTree

    public void setAction(Action action) {
        ActionMap am = getActionMap();

        am.put("enter", action);

    } // setAction

    public Action getAction() {
        return getActionMap().get("enter");

    } // getAction

    @Override
    public void revalidate() {
        if (mValidate) {
            super.revalidate();

        } // if
    }

    @Override
    public void repaint() {
        if (mValidate) {
            super.repaint();

        } // if
    }

    protected class MyTreeModelHandler implements TreeModelListener {

        public void treeStructureChanged(TreeModelEvent e) {
            final TreePath[] selectionPaths = getSelectionPaths();

            // save the state of expanded nodes
            Enumeration<TreePath> descendantToggledPaths = getDescendantToggledPaths(e.getTreePath());

            final ArrayList<TreePath> expandedPaths = new ArrayList<TreePath>();

            while (descendantToggledPaths.hasMoreElements()) {
                TreePath element = descendantToggledPaths.nextElement();

                // check if the all elements on the path are expanded
                if (isExpanded(element)) {
                    expandedPaths.add(element);

                } // if
            } // while

            mValidate = false;

            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    // restore the expanded state
                    for (TreePath path : expandedPaths) {
                        expandPath(path);

                    } // for

                    if (selectionPaths != null) {
                        setSelectionPaths(selectionPaths);

                    } // if

                    mValidate = true;

                    revalidate();
                    repaint();

                } // run
            });
        } // treeStructureChanged

        public void treeNodesChanged(TreeModelEvent e) {
        }

        public void treeNodesInserted(TreeModelEvent e) {
        }

        public void treeNodesRemoved(TreeModelEvent e) {
        }
    } // end of inner-class MyTreeModelHandler
} // end of class BFTree
