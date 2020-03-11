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

import javax.swing.JTable;

import de.battleforge.gui.util.ImageFactory;

/**
 * <p>
 * Title: <b>TableBackground</b><br>
 * Description: <i>This class is used to paint a background on a JTable</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class TableBackground extends JTable {

    /**
     * Image to be painted to background
     */
    private Image img;

    /**
     * Constructor
     * 
     * @param path
     *            Path to the image
     * @param image
     *            Name of the image
     */
    public TableBackground(String path, String image) {
        super();
        this.setOpaque(false);
        img = ImageFactory.getImage(path, image);
    }

    /**
     * Paints the component
     * 
     * @param g
     *            Graphics context
     */
    @Override
    public final void paint(Graphics g) {
        Dimension d = getSize();
        for (int x = 0; x < d.width; x += img.getWidth(this)) {
            for (int y = 0; y < d.height; y += img.getHeight(this)) {
                g.drawImage(img, x, y, this);
            }
        }
        super.paint(g);
    }
}
