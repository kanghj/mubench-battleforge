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

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class BFTreeNode extends DefaultMutableTreeNode {

    private Sorter mSorter;

    private ArrayList<BFTreeNode> mChildren;

    private HashSet<Filter> mFilter;

    private static Sorter mDefaultSorter = new Sorter() {

        public BFTreeNode[] sort(BFTreeNode[] childs) {
            return childs;

        }
    };

    public BFTreeNode(Object userObject) {
        super(userObject);

        mChildren = new ArrayList<BFTreeNode>();
        mFilter = new HashSet<Filter>();
        mSorter = mDefaultSorter;

    }

    @Override
    public void remove(int childIndex) {
        super.remove(childIndex);

        mChildren.remove(childIndex);

    }

    @Override
    public void insert(MutableTreeNode newChild, int childIndex) {
        if (!(newChild instanceof BFTreeNode)) {
            throw new IllegalArgumentException("only instances of BFTreeNode are allowed");

        } // if

        super.insert(newChild, childIndex);

        if (mChildren.contains(newChild)) {
            return;

        } // if

        mChildren.add(childIndex, (BFTreeNode) newChild);

    }

    public void setSorter(Sorter sorter) {
        if (sorter == null) {
            mSorter = mDefaultSorter;

        } else {
            mSorter = sorter;

        }

        for (BFTreeNode child : mChildren) {
            child.setSorter(sorter);

        } // for

        sortAndFilter();

    }

    public Sorter getSorter() {
        return mSorter;

    }

    public void addFilter(Filter aFilter) {
        mFilter.add(aFilter);

        for (BFTreeNode child : mChildren) {
            child.addFilter(aFilter);

        } // for

        sortAndFilter();

    }

    public void removeFilter(Filter aFilter) {
        mFilter.remove(aFilter);

        for (BFTreeNode child : mChildren) {
            child.removeFilter(aFilter);

        } // for

        sortAndFilter();

    }

    public void sortAndFilter() {
        BFTreeNode[] sortedChildren = mSorter.sort(filter(mChildren.toArray(new BFTreeNode[mChildren.size()])));

        for (int i = getChildCount() - 1; i >= 0; i--) {
            super.remove(i);

        } // for

        for (BFTreeNode node : sortedChildren) {
            super.add(node);

            node.sortAndFilter();

        } // for
    }

    private BFTreeNode[] filter(BFTreeNode[] childs) {
        BFTreeNode[] filteredChilds = childs;

        for (Filter filter : mFilter) {
            filteredChilds = filter.filter(filteredChilds);

        } // for

        return filteredChilds;

    }

}
