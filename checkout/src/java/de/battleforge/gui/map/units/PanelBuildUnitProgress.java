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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.util.ImageFactory;

public class PanelBuildUnitProgress extends JPanel {

    private JProgressBar jpb;

    private JPanel createUnitPanel() {
        JLabel lab1 = new JLabel("Unit to build:");
        JLabel lab2 = new JLabel("Unittype:");
        JLabel lab3 = new JLabel("Prize in C-Bills:");
        JLabel lab4 = new JLabel("Time of completion:");

        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 0, 0, 5);
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        p.add(lab1, c);

        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        p.add(new JLabel("Value"), c);

        c.insets = new Insets(0, 0, 0, 5);
        c.gridx = 0;
        c.gridy = 1;
        p.add(lab2, c);

        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        p.add(new JLabel("Value"), c);

        c.insets = new Insets(0, 0, 0, 5);
        c.gridx = 0;
        c.gridy = 2;
        p.add(lab3, c);

        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 2;
        p.add(new JLabel("Value"), c);

        c.insets = new Insets(0, 0, 0, 5);
        c.gridx = 0;
        c.gridy = 3;
        p.add(lab4, c);

        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 3;
        p.add(new JLabel("Value"), c);

        c.insets = new Insets(0, 5, 0, 5);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.weighty = 1.0;
        p.add(new JLabel(""), c);

        return p;
    }

    private JPanel createProgressPanel() {
        JPanel p = new JPanel(new BorderLayout());
        jpb = new JProgressBar();
        jpb.setPreferredSize(new Dimension(10, 1));
        jpb.setOrientation(SwingConstants.VERTICAL);
        p.add(jpb, BorderLayout.EAST);
        return p;
    }

    private JPanel generateUsedSlotsGraphics(int slots) {
        Icon icon = null;

        JPanel icons = new JPanel();
        icons.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 5));
        icons.setLayout(new BoxLayout(icons, BoxLayout.PAGE_AXIS));
        icons.setOpaque(false);
        for (int i = 1; i <= slots; i++) {
            icon = ImageFactory.getIcon("slot_small.png");
            icons.add(new JLabel(icon));
        }

        return icons;
    }

    private JPanel createImagePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 2));

        Image unitImage;
        unitImage = ImageFactory.getImage("units/dropships", "overlord.png");
        PanelImageDisplay imagePanel = new PanelImageDisplay(unitImage, borders.LINE, 1, true, 50, 50);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        buttonPanel.add(new JButton(ActionManager.getAction("", "Einheit zuordnen", "arrowRight.png", ACTIONS.ADD_UNIT_TO_LOGICALUNIT)));

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(imagePanel, BorderLayout.PAGE_START);
        p1.setOpaque(false);

        p.add(p1, BorderLayout.CENTER);
        p.setOpaque(false);
        p.add(generateUsedSlotsGraphics(3), BorderLayout.EAST);
        return p;
    }

    public PanelBuildUnitProgress() {
        this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.setLayout(new BorderLayout());
        this.add(createImagePanel(), BorderLayout.WEST);
        this.add(createUnitPanel(), BorderLayout.CENTER);
        this.add(createProgressPanel(), BorderLayout.EAST);
    }
}
