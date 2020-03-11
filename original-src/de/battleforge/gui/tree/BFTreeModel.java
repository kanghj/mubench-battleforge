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

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;

public class BFTreeModel extends DefaultTreeModel implements TreeModel {

    private static Logger sLogger = Logger.getLogger(BFTreeModel.class);

    public BFTreeModel(BFTreeNode root) {
        super(root);

    } // BFTreeModel

    @Override
    public void reload(TreeNode node) {
        sortAndFilter((BFTreeNode) node);

    } // reload

    public void setSorter(Sorter sorter) {
        BFTreeNode rootNode = (BFTreeNode) getRoot();

        rootNode.setSorter(sorter);

        nodeStructureChanged(rootNode);

    } // setSorter

    public Sorter getSorter() {
        return ((BFTreeNode) getRoot()).getSorter();

    } // getSorter

    public void addFilter(Filter filter) {
        BFTreeNode rootNode = (BFTreeNode) getRoot();

        rootNode.addFilter(filter);

        nodeStructureChanged(rootNode);

    } // addFilter

    public void sortAndFilter() {
        BFTreeNode rootNode = (BFTreeNode) getRoot();

        rootNode.sortAndFilter();

        nodeStructureChanged(rootNode);

    } // sortAndFilter

    public void sortAndFilter(BFTreeNode node) {
        node.sortAndFilter();

        nodeStructureChanged(node);

    } // sortAndFilter

    public void removeFilter(Filter filter) {
        BFTreeNode rootNode = (BFTreeNode) getRoot();

        rootNode.removeFilter(filter);

        nodeStructureChanged(rootNode);

    }
} // end of class BFTreeModel
