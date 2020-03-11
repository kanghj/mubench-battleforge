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
package de.battleforge.gui.dialog.admin.editSystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.jdo.Transaction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.battleforge.jdo.BFOwner;
import de.battleforge.jdo.BFStartArea;
import de.battleforge.jdo.BFStartSystemOwner;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>PanelOwner</b><br>
 * Description: <i>Owner panel to edit the owner of a system</i><br>
 * Copyright: Copyright (c) 2006<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.1
 */
public class PanelOwner extends JPanel {

    private BFSystem s;

    /**
     * Hashmap to store the comboboxes next to the according start areas
     */
    private HashMap<BFStartArea, JComboBox> hmComboBoxes = new HashMap<BFStartArea, JComboBox>();

    private HashMap<BFStartArea, BFStartSystemOwner> hmStartSystemOwner = new HashMap<BFStartArea, BFStartSystemOwner>();

    private JComboBox cb1;

    /**
     * Returns the hashmap with startarea and the comboboxes with the values
     * 
     * @return
     */
    public HashMap getHashMapStartSystemOwner() {
        return hmStartSystemOwner;
    }

    public HashMap getHashMapComboBoxes() {
        return hmComboBoxes;
    }

    public void setSystem(BFSystem sys) {
        s = sys;
        initialize();
    }

    private BFOwner getCurrentOwner() {
        return (BFOwner) cb1.getSelectedItem();
    }

    public void initialize() {

        cb1.setSelectedItem(s.getOwner());

        Iterator<Entry<BFStartArea, JComboBox>> iter = hmComboBoxes.entrySet().iterator();
        while (iter.hasNext()) {
            // Read the values from the database and initialize the combobox
            Entry<BFStartArea, JComboBox> e = iter.next();
            BFStartArea key = e.getKey();
            JComboBox value = e.getValue();

            BFStartSystemOwner sso = DBWrapper.getOwnerForStartArea(key, s);
            value.setSelectedItem(sso.getOwner());

            hmStartSystemOwner.put(key, sso);
        }
    }

    /**
     * Constructor
     */
    public PanelOwner() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 0, 5, 0);
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 0.0;

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0.5;
        p.add(new JLabel(Internationalization.getString("editSystemsDialog.OwnerTab.CurrentGame")), c);
        c.gridx = 1;
        c.weightx = 1.0;
        c.insets = new Insets(0, 15, 5, 0);
        cb1 = new JComboBox(DBWrapper.getOwner().toArray());
        cb1.setRenderer(new CustomComboBoxRenderer());
        p.add(cb1, c);

        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 0, 5, 0);
        p.add(new JLabel(Internationalization.getString("editSystemsDialog.OwnerTab.StartData")), c);
        c.insets = new Insets(0, 0, 5, 0);
        c.gridwidth = 1;

        int i = 2;

        // Get all start years from database
        Iterable<BFStartArea> startAreas = DBWrapper.getStartArea();
        Object[] allOwner = DBWrapper.getOwner().toArray();
        for (BFStartArea startArea : startAreas) {
            String startAreaName = startArea.getName();

            c.gridy = i;
            c.gridx = 0;
            c.insets = new Insets(0, 0, 5, 0);
            c.weightx = 0.5;
            JLabel l = new JLabel(startAreaName);
            l.setVerticalAlignment(SwingConstants.CENTER);
            p.add(l, c);
            c.gridx = 1;
            c.weightx = 1.0;

            JComboBox b = new JComboBox(allOwner);
            b.setRenderer(new CustomComboBoxRenderer());

            // Put the startarea and the owner-combobox into a hashmap
            hmComboBoxes.put(startArea, b);

            // Draw the combobox onto the panel
            c.insets = new Insets(0, 15, 5, 0);
            p.add(b, c);
            i++;
        }

        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 5, 0);
        c.gridy = i;
        c.gridx = 0;
        c.gridwidth = 2;
        p.add(new JLabel(""), c);

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(p, BorderLayout.CENTER);
    }

    protected void save() {
        Transaction tx = DBWrapper.getCurrentPM().currentTransaction();
        tx.begin();

        // save the current owner
        s.setOwner(getCurrentOwner());

        // save the owner for the starting years

        Iterator<Entry<BFStartArea, JComboBox>> iter = hmComboBoxes.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<BFStartArea, JComboBox> entry = iter.next();

            BFStartArea key = entry.getKey();
            JComboBox value = entry.getValue();

            hmStartSystemOwner.get(key).setOwner((BFOwner) value.getSelectedItem());
        }

        tx.commit();
    }

    class CustomComboBoxRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {

            BFOwner owner = (BFOwner) value;
            String name = Internationalization.getString("Owner." + owner.getName());

            return super.getListCellRendererComponent(list, name, index, isSelected, hasFocus);
        }
    }
}
