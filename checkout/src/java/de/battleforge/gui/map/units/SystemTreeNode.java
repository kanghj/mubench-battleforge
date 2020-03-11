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

import java.util.Enumeration;
import java.util.Vector;

import javax.jdo.Transaction;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFUnit;
import de.battleforge.jdo.DBWrapper;

public class SystemTreeNode extends BFTreeNode {

    private boolean mSortAndFilter;

    public SystemTreeNode(BFSystem system) {
        super(system);

    }

    @Override
    public Enumeration children() {
        checkChildren();

        return super.children();

    }

    @Override
    public boolean getAllowsChildren() {
        return true;

    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        checkChildren();

        return super.getChildAt(childIndex);

    }

    @Override
    public int getChildCount() {
        checkChildren();
        return super.getChildCount();
    }

    @Override
    public int getIndex(TreeNode node) {
        checkChildren();

        return super.getIndex(node);

    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        if (!(child instanceof BFTreeNode)) {
            throw new IllegalArgumentException("only instances of BFTreeNode are allowed");

        } // if

        checkChildren();

        super.insert(child, index);

        if (child instanceof UnitTreeNode) {
            UnitTreeNode unitTreeNode = (UnitTreeNode) child;

            BFUnit unit = unitTreeNode.getUserObject();

            if (!mSortAndFilter) {
                Transaction tx = DBWrapper.getCurrentPM().currentTransaction();
                tx.begin();
                unit.setCurrentSystem(getUserObject());
                tx.commit();
            }
        }
    }

    @Override
    public boolean isLeaf() {
        checkChildren();

        return super.isLeaf();

    }

    @Override
    public void remove(int index) {
        checkChildren();

        BFTreeNode node = (BFTreeNode) super.getChildAt(index);

        super.remove(index);

        if (node instanceof UnitTreeNode) {
            UnitTreeNode unitTreeNode = (UnitTreeNode) node;

            BFUnit unit = unitTreeNode.getUserObject();

            if (!mSortAndFilter) {
                getUserObject().getUnits().remove(unit);

            }
        }
    }

    @Override
    public void sortAndFilter() {
        checkChildren();
        mSortAndFilter = true;

        super.sortAndFilter();

        mSortAndFilter = false;

    }

    @Override
    public String toString() {
        return getUserObject().toString();

    }

    @Override
    public BFSystem getUserObject() {
        return (BFSystem) super.getUserObject();

    }

    private void checkChildren() {
        if (children == null) {

            Iterable<BFUnit> units = getUserObject().getUnits();

            children = new Vector<BFTreeNode>();

            for (BFUnit unit : units) {
                UnitTreeNode node = new UnitTreeNode(unit);

                super.insert(node, getChildCount());
            }

        }
    }
}
