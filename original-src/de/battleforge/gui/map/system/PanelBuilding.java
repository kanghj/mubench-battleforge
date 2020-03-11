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
package de.battleforge.gui.map.system;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFBuilding;

/**
 * <p>
 * Title: <b>PanelBuilding</b><br>
 * Description: <i>Represents a building on the buildings panel</i><br>
 * Copyright: Copyright (c) 2006<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.10
 */
public class PanelBuilding extends JPanel {

    /**
     * ProgressBar
     */
    private JProgressBar progressBar = new JProgressBar();

    /**
     * Constructor
     * @param building
     */
    public PanelBuilding(BFBuilding building, int progress) {
        String imageName = building.getName() + ".png";
        Image image = ImageFactory.getImage("", imageName);
        PanelImageDisplay imagePanel = new PanelImageDisplay(image, borders.LINE, 0, true, 100, 75);

        progressBar.setPreferredSize(new Dimension(10,1));
        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setValue(progress);

        this.setLayout(new BorderLayout());
        this.add(new JLabel(building.getName()), BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(progressBar, BorderLayout.EAST);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}
