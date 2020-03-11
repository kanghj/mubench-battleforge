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

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Werner
 * @version 1.0
 */
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelArrayListControl extends AbstractTableModel {
    ArrayList al = new ArrayList();

    String filter;

    String[] columnName;

    String[] columnValues;

    int columnCount = 0;

    // wird verwendet, wenn Arrayliste in umgekehrter Reihenfolge in die Tabelle
    // eingefügt werden soll
    int counterArrayList = 0;

    boolean mode = false;

    // alle Celle editierbar machen
    @Override
    public boolean isCellEditable(int cool, int row) {
        return false;
    }

    /**
     * 
     * @uml.property name="columnCount"
     */
    public int getColumnCount() {
        return columnCount;
    }

    // ---- Spaltennamen ermitteln ----
    @Override
    public String getColumnName(int col) {
        return columnName[col];
    }

    // --------------- eigene Methoden ------------------
    public void setData(ArrayList data) {
        al = data;
        mode = false;
    }

    public void setData(ArrayList data, boolean mode) {
        al = data;
        this.mode = mode;
        counterArrayList = data.size() - 1;
    }

    public ArrayList getData() {
        return al;
    }

    /**
     * @param filter
     *            Filterkriterium der Tabelle
     */
    public void setFilter(String filter, Object object) {
        this.filter = filter;

        ArrayList arr = new ArrayList();
        Object o1;
        int count = al.size();
        for (int i = 0; i < count; i++) {
            Object o = al.get(i);
            try {
                o1 = o.getClass().getMethod(filter, new Class[] {}).invoke(o, new Object[] {});

                String s1 = o1.toString();
                String s2 = object.toString();
                if (s1.equals(s2)) {
                    arr.add(o);
                }
            } catch (Exception e) {
                System.out.println("ModelArrayListControl -> set Filter: " + e);
            }
        }
        try {
            al = arr;
        } catch (Exception a) {
            System.out.println("ModelArrayListControl -> setFilter:" + a);
        }
    }

    /**
     * @param name
     * @param values
     *            name -> Stringarray in dem die Spaltennamen enthalten sind
     *            values -> Stringarray in dem die Namen der Getter, der Objekte
     *            des ubergebenen Vectors, stehen
     */
    public void setHeaderAndColumn(String[] name, String[] values) {
        columnName = name;
        columnCount = name.length;
        columnValues = values;
    }

    // ---- Zeilenanzahl ermitteln ----
    public int getRowCount() {
        int i = 0;
        i = al.size();

        /*
         * try{ if( a.lastElement() != null ) { i = v.indexOf(v.lastElement()) +
         * 1; } } catch (NoSuchElementException a) { i=0; }
         */
        return i;
    }

    // ---- Tabelle auslesen ----
    @Override
    public void setValueAt(Object aValue, int row, int col) {
    }

    // ---- Tabelle füllen -----
    public Object getValueAt(int row, int col) {
        Object o = null;

        // o = al.get(row);
        if (mode == false) {
            o = al.get(row);
        } else {
            // counterArrayList--;
            o = al.get(counterArrayList - row);
        }

        try {
            o = o.getClass().getMethod(columnValues[col], new Class[] {}).invoke(o, new Object[] {});
        } catch (Exception e) {
            o = "ERROR";
            System.out.println("ModelArrayListControl: " + e);
        }
        return o;
    }
}
