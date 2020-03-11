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
package de.battleforge.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class TextDisplay extends JDialog {

    public TextDisplay(Dialog owner, String title, String labelText, String text) {
        super(owner, "", true);

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(createContentPane(labelText, text));

        pack();

        setLocationRelativeTo(owner);

    } // TextDisplayDialog

    public TextDisplay(Frame owner, String title, String labelText, String text) {
        super(owner, "", true);

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(createContentPane(labelText, text));

        pack();

        setLocationRelativeTo(owner);

    } // TextDisplayDialog

    private JPanel createContentPane(String labelText, String text) {
        JPanel contentPane = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();

        textArea.setText(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (labelText != null) {
            JLabel label = new JLabel(labelText);

            label.setBorder(new EmptyBorder(5, 5, 5, 5));

            contentPane.add(label, BorderLayout.NORTH);

        } /* if */

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);

        scrollPane.setPreferredSize(new Dimension(600, 300));

        textArea.setCaretPosition(0);

        return contentPane;

    } // createContentPane

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(new JButton(new OKAction()));

        return buttonPanel;

    } // createButtonPanel

    public static void showDialog(Dialog owner, String title, String labelText, String text) {
        TextDisplay dialog = new TextDisplay(owner, title, labelText, text);

        dialog.setVisible(true);

    } // showDialog

    private class OKAction extends AbstractAction {

        public OKAction() {
            super("OK");

        } // OKAction

        public void actionPerformed(ActionEvent e) {
            dispose();

        } // actionPerformed
    } // end of inner-class OKAction
} // end of class ErrorDetailsDialog
