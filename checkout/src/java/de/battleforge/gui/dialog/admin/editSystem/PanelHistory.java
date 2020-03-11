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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;

import javax.jdo.Transaction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFSystemDescription;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.Internationalization;

public class PanelHistory extends JPanel implements ActionListener {

    private BFSystem s;

    private JTextArea ta;

    private JComboBox jcb;

    /**
     * Hashmap with locale as key and the BFSystemDescription as value
     */
    private HashMap<Locale, BFSystemDescription> hashmapBFSystemDescription = new HashMap<Locale, BFSystemDescription>();

    /**
     * Hashmap with locale as key and description as string as value
     */
    private HashMap<Locale, String> hashmapStrings = new HashMap<Locale, String>();

    private Locale[] l;

    private MyDocumentListener mdm = new MyDocumentListener();

    public void setSystem(BFSystem sys) {
        s = sys;
        initialize();
        setTexts();
    }

    private void initialize() {
        for (Locale element : l) {

            String description = "";
            BFSystemDescription sd = DBWrapper.getSystemDescription(s, element.getLanguage());
            if (sd == null) {
                description = null;
            } else {
                description = sd.getDescription();
            }

            hashmapBFSystemDescription.put(element, DBWrapper.getSystemDescription(s, element.getLanguage()));
            hashmapStrings.put(element, description);
        }
    }

    private Component getComboBoxLanguage() {
        if (jcb == null) {
            // TODO: Wenn ich hier auf englisch stelle, will ich auch GERMAN da
            // haben... nicht DEUTSCH ???
            jcb = new JComboBox(l);
            jcb.setSelectedItem(Internationalization.getLocale());
            jcb.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Locale locale = (Locale) value;
                    String text = locale.getDisplayLanguage(Internationalization.getLocale());
                    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
                }
            });
        } // if
        return jcb;
    }

    public PanelHistory() {

        // Model for the ComboBox, an array of available languages
        l = new Locale[] { Locale.GERMAN, Locale.ENGLISH };

        ta = new JTextArea();
        ta.setEditable(true);
        ta.setEnabled(true);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBorder(BorderFactory.createEtchedBorder());
        ta.getDocument().addDocumentListener(mdm);

        JScrollPane jsp = new JScrollPane(ta);
        jsp.setBorder(new EmptyBorder(5, 0, 0, 0));
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(10, 10));

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        JLabel jl = new JLabel("Language");
        jl.setBorder(new EmptyBorder(0, 0, 0, 10));

        p.add(jl, BorderLayout.WEST);
        p.add(getComboBoxLanguage(), BorderLayout.CENTER);
        jcb.addActionListener(this);

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(p, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
    }

    /**
     * action performed
     */
    public void actionPerformed(ActionEvent e) {
        // The ComboBox was changed
        setTexts();
    }

    public HashMap getHashMapBFSystemDescription() {
        return hashmapBFSystemDescription;
    }

    public HashMap getHashMapStrings() {
        return hashmapStrings;
    }

    /**
     * setTexts The description is read from the database. If the description is
     * null, there is a short information for the user that the text is still
     * missing.
     */
    public void setTexts() {

        // The current description comes from the hashmap according to the
        // selected language
        String description = (hashmapStrings.get(jcb.getSelectedItem()));

        if (description == null) {
            // If the description is empty, inform the user that the information
            // is missing
            ta.getDocument().removeDocumentListener(mdm);
            ta.setText(Internationalization.getString("MissingSystemDescription"));
            ta.getDocument().addDocumentListener(mdm);
        } else {
            // Otherwise the description is displayed
            ta.setText(description);
        }
        // Cursor is set to top of the text
        ta.setCaretPosition(0);
    }

    public void save() {
        Transaction tx = DBWrapper.getCurrentPM().currentTransaction();
        tx.begin();

        // save the history descriptions for the system

        Iterator<Entry<Locale, BFSystemDescription>> iter2 = hashmapBFSystemDescription.entrySet().iterator();
        while (iter2.hasNext()) {
            Entry<Locale, BFSystemDescription> entry2 = iter2.next();

            Locale key2 = entry2.getKey();
            BFSystemDescription value2 = entry2.getValue();

            // Store the description into a string
            String desc = hashmapStrings.get(key2);

            // Save only if this string is not empty and not null
            if ((desc != null) && (!"".equals(desc.trim()))) {
                if (value2 == null) {
                    // This object was not in the database before
                    // An insert is necessary
                    // Set language and system
                    value2 = new BFSystemDescription();
                    value2.setDescription(desc);
                    value2.setSystem(s);
                    value2.setLanguageCode(key2.getLanguage());
                    DBWrapper.getCurrentPM().makePersistent(value2);
                } else {
                    value2.setDescription(desc);
                }
            }
        }

        tx.commit();
    }

    class MyDocumentListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            Locale l = (Locale) jcb.getSelectedItem();
            hashmapStrings.put(l, ta.getText());
        }

        public void removeUpdate(DocumentEvent e) {
            Locale l = (Locale) jcb.getSelectedItem();
            hashmapStrings.put(l, ta.getText());

            // hm.get(l).setDescription(ta.getText());
        }

        public void changedUpdate(DocumentEvent e) {
            //
        }

        public void updateLog(DocumentEvent e, String action) {
            //
        }
    }
}
