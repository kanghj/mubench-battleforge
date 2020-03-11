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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import de.battleforge.action.ActionCallbackListener;
import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFOwner;

public abstract class AbstractPanelOwnerInformation extends JPanel implements ActionCallbackListener {

    /**
     * Owner
     */
    private BFOwner o;

    /**
     * Image of the ownerlogo
     */
    private Image ownerImage;

    /**
     * Panel to display logo
     */
    private PanelImageDisplay plLogo;

    /**
     * ScrollPane
     */
    private JScrollPane sp;

    /**
     * Owner description text area
     */
    private JTextArea ownerDescription = new JTextArea();

    /**
     * Header label
     */
    private JLabel header = new JLabel();

    /**
     * Owner name panel
     */
    private JLabel ownerName = new JLabel();

    /**
     * Number of systems that an owner controls
     */
    private JLabel systemCount = new JLabel();

    /**
     * 
     */
    private JLabel user = new JLabel();

    /**
     * Create description panel
     * 
     * @return panel
     */
    private JPanel createDescriptionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.anchor = GridBagConstraints.PAGE_START;
        c1.insets = new Insets(0, 0, 0, 10);
        c1.gridwidth = 1;
        c1.gridheight = 1;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 0.0;
        c1.weighty = 0.0;
        panel.add(new JLabel("Name:"), c1);

        c1.gridx = 1;
        c1.weightx = 1.0;
        panel.add(ownerName, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        c1.weightx = 0.0;
        panel.add(new JLabel("Status:"), c1);

        c1.gridx = 1;
        c1.weightx = 1.0;
        panel.add(new JLabel("Verfeindet"), c1);

        c1.gridx = 0;
        c1.gridy = 2;
        c1.weightx = 0.0;
        panel.add(new JLabel("Anführer:"), c1);

        c1.gridx = 1;
        c1.weightx = 1.0;
        panel.add(user, c1);

        c1.gridx = 0;
        c1.gridy = 3;
        c1.weightx = 0.0;
        panel.add(new JLabel("Stärke:"), c1);

        c1.gridx = 1;
        c1.weightx = 1.0;
        panel.add(systemCount, c1);

        c1.fill = GridBagConstraints.NONE;
        c1.anchor = GridBagConstraints.WEST;
        c1.insets = new Insets(10, 0, 10, 0);
        c1.gridx = 0;
        c1.gridy = 4;
        c1.gridwidth = 2;
        c1.weightx = 1.0;
        panel.add(new JButton("Friedensangebot machen"), c1);

        // Fill up unused space
        c1.insets = new Insets(0, 0, 0, 0);
        c1.gridx = 0;
        c1.gridy = 5;
        c1.gridwidth = 2;
        c1.weighty = 1.0;
        panel.add(new JLabel(""), c1);

        return panel;
    }

    /**
     * Create history panel
     * 
     * @return panel
     */
    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(ownerDescription, BorderLayout.CENTER);
        return panel;
    }

    /**
     * 
     * 
     */
    public AbstractPanelOwnerInformation() {
        ownerImage = ImageFactory.getImage("ownerlogo", "ghostbear.png");
        plLogo = new PanelImageDisplay(ownerImage, borders.NO, 1, true, 120, 120);
        ownerDescription.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ownerDescription.setOpaque(false);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(5, 10, 0, 0);
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setText("...");
        content.add(header, c);

        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 15);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        content.add(plLogo, c);

        c.gridx = 1;
        c.weightx = 1.0;
        c.insets = new Insets(10, 0, 10, 10);
        content.add(createDescriptionPanel(), c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 0, 0);
        content.add(createHistoryPanel(), c);

        sp = new JScrollPane(content);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setPreferredSize(new Dimension(10, 10));

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(10, 150));
        this.add(sp, BorderLayout.CENTER);
        addActions();
    }

    protected abstract void addActions();

    public void setTexts() {

        header.setText(o.getName());
        ownerName.setText(o.getName() + " [" + o.getShortName() + "] - " + o.getOwnerType().getName());
        // systemCount.setText(o.getSystems().size() + " Systeme");

        // TODO: Take care that there is an owner to display!!!
        // user.setText(o.getUserForOwner().getName());
        plLogo.setImage(ImageFactory.getImage("ownerlogo", o.getLogo()));

        // try {
        // ownerDescription.setText(o.get OwnerDescription());
        // } catch ( BFException e ) {
        // //TODO: Print to logger
        // e.printStackTrace();
        // }

    }

    public void setOwner(BFOwner owner) {
        o = owner;
        this.setTexts();
        this.repaint();
    }
}
