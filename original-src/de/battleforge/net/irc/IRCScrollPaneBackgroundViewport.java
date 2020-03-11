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
package de.battleforge.net.irc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JViewport;

/**
 * <p>
 * Title: <b>ScrollPaneBackgroundViewport</b><br>
 * Description: <i>As setting JScrollPane to non-opaque did not work...</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class IRCScrollPaneBackgroundViewport extends JViewport {
    /**
     * The image to be painted
     */
    private Image image = null;

    /**
     * Width of the image to be painted
     */
    private int imageWidth;

    /**
     * Height of the image to be painted
     */
    private int imageHeight;

    /**
     * Tile flag
     */
    private boolean tile = false;

    /**
     * Setter for tilemode
     * 
     * @param value
     *            should image be tiled
     */
    public final void setTileMode(boolean value) {
        tile = value;
    }

    /**
     * Paints the component
     * 
     * @param g
     *            Graphics context
     */
    @Override
    public final void paintComponent(final Graphics g) {
        if (image != null) {
            if (tile) {
                // Draw the background image in tiles
                Rectangle d = getViewRect();
                for (int x = 0; x < d.width; x += imageWidth) {
                    for (int y = 0; y < d.height; y += imageHeight) {
                        g.drawImage(image, x, y, null, null);
                    }
                }
            } else {
                // Draw the image un-tiled
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }
        setOpaque(false);
        super.paintComponent(g);
    }

    /**
     * Sets the background image to use
     * 
     * @param img
     *            image to be set
     */
    public final void setBackgroundImage(Image img) {
        this.image = img;
        this.imageWidth = img.getWidth(this);
        this.imageHeight = img.getHeight(this);
    }
}
