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

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFDropship;
import de.battleforge.jdo.BFJumpship;
import de.battleforge.jdo.BFLogicalUnit;
import de.battleforge.jdo.BFMech;
import de.battleforge.jdo.BFUnit;

public class LogicalUnitTreeCellRenderer extends DefaultTreeCellRenderer {

    private Icon mJumpshipIcon;

    private Icon mDropshipIcon;

    private Icon mMechIcon;

    public LogicalUnitTreeCellRenderer() {
        mJumpshipIcon = ImageFactory.getIcon("jumpship.png");
        mDropshipIcon = ImageFactory.getIcon("dropship_leopard.png");
        mMechIcon = ImageFactory.getIcon("mech_assault.png");

    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean focus) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object o = node.getUserObject();

        Icon icon = null;

        TreeModel model = tree.getModel();

        if (o instanceof BFJumpship) {
            icon = mJumpshipIcon;
            value = ((BFJumpship) o).getName();

        } else if (o instanceof BFDropship) {
            icon = mDropshipIcon;
            value = ((BFDropship) o).getName();

        } else if (o instanceof BFMech) {
            icon = mMechIcon;
            value = ((BFMech) o).getName();

        } else if (o instanceof BFUnit) {
            value = ((BFUnit) o).getName();

        } else if (o instanceof BFLogicalUnit) {
            value = ((BFLogicalUnit) o).getName();

        } // if

        setClosedIcon(icon);
        setOpenIcon(icon);
        setLeafIcon(icon);

        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, focus);

    }

}
