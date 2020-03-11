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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * <p>
 * Title: <b>PanelImageDisplay</b><br>
 * Description: <i>A Java2D panel to display images antialiased</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class PanelImageDisplay extends JPanel {
    /**
     * the imageIcon
     */
    private Image img = null;

    /**
     * max horizontal size
     */
    private int maxX = 0;

    /**
     * max vertical size
     */
    private int maxY = 0;

    /**
     * image width
     */
    private int imgX = 0;

    /**
     * image height
     */
    private int imgY = 0;

    private int alignment = 0;

    private Dimension size;

    public enum borders {
        NO, ETCHED, LINE
    }

    /**
     * a panel to display an image
     * 
     * @param inImg
     *            incoming image
     * @param border
     *            the border to use
     */
    public PanelImageDisplay(Image inImg, borders border, int alignment, boolean scaleImage, int scaleToWidth, int scaleToHeight) {
        this.alignment = alignment;
        this.setOpaque(false);
        switch (border) {
        case NO:
            break;
        case ETCHED:
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            break;
        case LINE:
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            break;
        }
        if (inImg != null) {
            img = inImg;
            if (scaleImage) {
                imgX = scaleToWidth;
                imgY = scaleToHeight;
            } else {
                imgX = img.getWidth(this);
                imgY = img.getHeight(this);
            }
            size = new Dimension(imgX, imgY);
            this.setPreferredSize(size);
            this.setMinimumSize(size);
            this.setMaximumSize(size);
        }
    }

    /**
     * set the image from outside
     * 
     * @param image
     *            incoming image to set
     */
    public final void setImage(Image image) {
        if (image != null) {
            this.img = image;
            repaint();
        }
    }

    /**
     * paintComponent
     * 
     * @param graphics
     *            the context
     */
    @Override
    public final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics.create();

        maxX = this.getWidth();
        maxY = this.getHeight();

        if (img != null) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            Insets insets = getInsets();

            switch (alignment) {
            case 0: // left
                g.drawImage(img, insets.left, insets.top, imgX, imgY, this);
                break;
            case 1: // centered
                g.drawImage(img, ((maxX - imgX) / 2), ((maxY - imgY) / 2), imgX, imgY, this);
                break;
            }
        }
    }
}
