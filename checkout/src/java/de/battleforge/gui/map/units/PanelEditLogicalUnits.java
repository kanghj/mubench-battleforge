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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.jdo.Transaction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.battleforge.gui.tree.BFTreeNode;
import de.battleforge.jdo.BFLogicalUnit;
import de.battleforge.jdo.BFLogicalUnitStructure;
import de.battleforge.jdo.DBWrapper;

public class PanelEditLogicalUnits extends JPanel {

    JLabel labName, labLogicalUnit;

    JComboBox cbLogicalUnitStructur;

    JTextField tfName;

    BFLogicalUnit luh;

    BFTreeNode nodeLUH;

    boolean bNew = false;

    public PanelEditLogicalUnits() {
        createComponents();
        createLayout();
    }

    private void createComponents() {
        labName = new JLabel("Name:");
        labLogicalUnit = new JLabel("Logische Einheit:");

        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(150, 20));

        cbLogicalUnitStructur = new JComboBox();
        cbLogicalUnitStructur.setPreferredSize(new Dimension(150, 20));
        cbLogicalUnitStructur.setRenderer(new CellRendererLUStructure());
    }

    private void createLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(2, 0, 2, 5);
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        this.add(labName, c);

        c.insets = new Insets(2, 0, 2, 0);
        c.gridx = 1;
        c.gridy = 0;
        this.add(tfName, c);

        c.insets = new Insets(2, 0, 2, 5);
        c.gridx = 0;
        c.gridy = 1;
        this.add(labLogicalUnit, c);

        c.insets = new Insets(2, 0, 2, 0);
        c.gridx = 1;
        c.gridy = 1;
        this.add(cbLogicalUnitStructur, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weighty = 1.0;
        this.add(new JLabel(""), c);
    }

    /**
     * fill combobox with logical unit that belong the owner/ownertyp and
     * subordiante this logical unit
     */
    private void fillComboBox() {
        // TODO: fill combobox only with logial units for ownertyp of user

        Iterable<BFLogicalUnitStructure> logicalUnitStructurs = DBWrapper.getLogicalUnitStructure(DBWrapper.getCurrentGameUser().getOwner());
        for (BFLogicalUnitStructure logicalUnitStructur : logicalUnitStructurs) {
            cbLogicalUnitStructur.addItem(logicalUnitStructur);
        }
    }

    /**
     * set the logical unit and mode of th dialog add/edit
     * 
     * @param node
     * @param bNew
     */
    public void setLogicalUnit(BFTreeNode node, boolean bNew) {
        this.bNew = bNew;
        fillComboBox();
        
        if (node != null && true == (node.getUserObject() instanceof BFLogicalUnit)) {        
            luh = (BFLogicalUnit) node.getUserObject();
            
            if( !bNew) {
                tfName.setText(luh.getName());
                cbLogicalUnitStructur.setSelectedItem(luh.getLogicalUnitStructure());
                
            } // if    
        } // if
       
        nodeLUH = node;

    }

    /**
     * add a new logical unit to tree or edit name of logical unit
     */
    public void addNewLogicalUnit() {
        if (bNew) {
            
           Transaction tx = DBWrapper.getCurrentPM().currentTransaction();
           tx.begin();
           BFLogicalUnit lu = new BFLogicalUnit();
           lu.setName(tfName.getText());
           lu.setLogicalUnitStructure((BFLogicalUnitStructure) cbLogicalUnitStructur.getSelectedItem());
           lu.setOwner(DBWrapper.getCurrentGameUser().getOwner());
           lu.setParentLogicalUnit(luh);
            
           DBWrapper.getCurrentPM().makePersistent(lu);    
           tx.commit();
            
           LogicalUnitTreeNode tn = new LogicalUnitTreeNode(lu);
           nodeLUH.add(tn);

        } else {            
            Transaction tx = DBWrapper.getCurrentPM().currentTransaction();
            tx.begin();
            
            luh.setName(tfName.getText());
            luh.setLogicalUnitStructure((BFLogicalUnitStructure) cbLogicalUnitStructur.getSelectedItem());

            tx.commit();
           

        } // if
    }
}

class CellRendererLUStructure extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value instanceof BFLogicalUnitStructure) {
            value = ((BFLogicalUnitStructure) value).getName();
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}
