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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import de.battleforge.gui.util.ImageFactory;

/**
 * <p>
 * Title: <b>PanelBackground</b><br>
 * Description: <i>A Panel with Background image</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class PanelBackground extends JPanel {

    /**
     * The Background image
     */
    private Image img;

    /**
     * Size
     */
    private Dimension size = null;

    /**
     * Width of the image
     */
    private int x = 0;

    /**
     * Height of the image
     */
    private int y = 0;

    /**
     * Flag if size should be set
     */
    private boolean setsize = true;

    /**
     * Constructor
     * 
     * @param path
     *            Path to the image
     * @param image
     *            Name of the image
     * @param pSetSize
     *            flag for setsize
     */
    public PanelBackground(String image, boolean pSetSize) {
        super();
        this.setsize = pSetSize;
        this.setOpaque(false);
        img = ImageFactory.getImage(image);
        x = img.getWidth(this);
        y = img.getHeight(this);
        size = new Dimension(x, y);
        if (setsize) {
            this.setMinimumSize(size);
            this.setPreferredSize(size);
        }
        this.repaint();
    }

    /**
     * Paints the Panel
     * 
     * @param g
     *            Graphics Context
     */
    @Override
    public final void paint(final Graphics g) {
        if (setsize) {
            g.drawImage(img, 0, 0, x, y, this);
        } else {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        super.paint(g);
    }
}
