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
package de.battleforge.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import de.battleforge.util.Tools;

public class StartBrowserLabel extends JPanel {

    private String mURL;

    private MouseAdapter mMouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            SwingUtilities.getWindowAncestor(StartBrowserLabel.this);

            Tools.startBrowser(null, mURL);

        } // mouseClicked
    };

    public StartBrowserLabel(Icon icon, String url, String text, String tooltip) {
        super(new GridBagLayout());

        JLabel lblIcon = getLabel(icon, tooltip);
        JLabel lblText = getLabel(null, tooltip);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        add(lblIcon, gbc);

        if ((text != null) && (text.length() > 0)) {
            String base = text;

            try {
                URL url1 = new URL(url);

                base = url1.getHost() + url1.getFile();

            } catch (MalformedURLException e1) {
                // ignore
            }

            lblText.setText("<html><a href=\"" + text + "\">" + base + "</a></html>");

            JPanel p = new JPanel(new BorderLayout());

            p.add(lblText);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 1;
            gbc.weightx = 1.0;

            add(lblText, gbc);

        } // if

        mURL = url;

        setBorder(new EmptyBorder(5, 5, 5, 5));

    } // StartBrowserLabel

    public StartBrowserLabel(Icon icon, String url, String text) {
        this(icon, url, text, url);

    } // StartBrowserLabel

    public StartBrowserLabel(Icon icon, String url) {
        this(icon, url, null, url);

    } // StartBrowserLabel

    public StartBrowserLabel(String url) {
        this(null, url, null, url);

    } // StartBrowserLabel

    private JLabel getLabel(Icon icon, String tooltip) {
        JLabel lbl = new JLabel(icon);

        lbl.setToolTipText(tooltip);
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl.addMouseListener(mMouseListener);

        return lbl;

    } // getLabel
} // end of class StartBrowserLabel
