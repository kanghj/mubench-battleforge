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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.battleforge.gui.util.ImageFactory;
import de.battleforge.util.Tools;

/**
 * <p>
 * Title: <b>ScreenTyper</b><br>
 * Description: <i>A simulated system screen for effects</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * <br>
 * This class types strings into a Java2D context with a science fiction
 * terminal behaviour.
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class ScreenTyper extends JPanel implements Runnable {
    /**
     * local variable to hold the string (to type) that comes into the
     * constructor
     */
    private String outputString;

    /**
     * font for typing
     */
    private Font font;

    /**
     * fontmetrics for the screen font
     */
    private FontMetrics fm = null;

    /**
     * counter for the typing alghorythm
     */
    private int i = 0;

    /**
     * fadevalue for the blinking cursor
     */
    private int fade = 255;

    /**
     * length of the string to type
     */
    private int stringLength = 0;

    /**
     * flag to indicate if a char was completly painted
     */
    private boolean endOfLine = false;

    /**
     * flag to paint or not
     */
    private boolean paint = false;

    /**
     * flag to show if the screen was already opened
     */
    private boolean openScreen = true;

    /**
     * count how often the cursor had blinked
     */
    private int blinkCounter = 0;

    /**
     * left border of the typed text
     */
    private int startX = 40;

    /**
     * vertical startvalue for typing
     */
    private int startY;

    /**
     * textcolor
     */
    private Color textColor = new Color(255, 204, 51);

    /**
     * backgroundcolor with transparency
     */
    private Color textBackgroundColor = new Color(255, 204, 51, 20);

    /**
     * an array of strings to store the old lines to display type history
     */
    private String[] formerLines = new String[10];

    /**
     * bufferedImage for painting in background
     */
    private BufferedImage myBufferedImage;

    /**
     * imageIcon to contain the screen-background
     */
    private Image img = null;

    /**
     * size of the panel
     */
    private Dimension size = null;

    /**
     * horizontal panel size
     */
    private int x = 0;

    /**
     * vertical panel size
     */
    private int y = 0;

    /**
     * The height of the used font
     */
    private int fmHeight = 0;

    /**
     * Constructor
     */
    public ScreenTyper() {
        formerLines[9] = "";
        formerLines[8] = "";
        formerLines[7] = "";
        formerLines[6] = "";
        formerLines[5] = "";
        formerLines[4] = "";
        formerLines[3] = "";
        formerLines[2] = "";
        formerLines[1] = "";
        formerLines[0] = "";

        img = ImageFactory.getImage("splash_init.png");

        x = img.getWidth(this);
        y = img.getHeight(this);
        size = new Dimension(x, y);
        this.setMinimumSize(size);
        this.setPreferredSize(size);

        outputString = "  ";
        stringLength = outputString.length();
        font = new Font("ARIAL", Font.PLAIN, 14);
        fm = getFontMetrics(font);
        fmHeight = fm.getHeight();
    }

    /**
     * Type a string to the screen
     * 
     * @param value
     *            String to type
     */
    public final void typeString(String value) {
        setOutputString(value);

        Thread th = new Thread(this);
        th.setPriority(Thread.MIN_PRIORITY);
        th.start();
        try {
            th.join();
        } catch (Exception e) {
            System.out.println("!Thread Exception in ScreenTyper!");
        }
        th.stop();
        Tools.wait(100);
    }

    /**
     * Set the new string and swap all the old strings
     * 
     * @param value
     *            string to type
     * 
     * @uml.property name="outputString"
     */
    public final void setOutputString(String value) {
        formerLines[9] = formerLines[8];
        formerLines[8] = formerLines[7];
        formerLines[7] = formerLines[6];
        formerLines[6] = formerLines[5];
        formerLines[5] = formerLines[4];
        formerLines[4] = formerLines[3];
        formerLines[3] = formerLines[2];
        formerLines[2] = formerLines[1];
        formerLines[1] = formerLines[0];
        formerLines[0] = outputString;

        this.outputString = value;
        stringLength = outputString.length();
        paint = true;
        i = 0;
        blinkCounter = 0;
        endOfLine = false;
    }

    /**
     * paints the string
     * 
     * @param g
     *            graphics context
     */
    @Override
    public final void paintComponent(final Graphics g) {
        if (createBuffer()) {
            Graphics2D g2 = (Graphics2D) myBufferedImage.getGraphics();
            g2.setFont(font);
            startY = this.getHeight() - (2 * (fmHeight + 20));

            g2.setColor(textColor);

            if (!paint) {
                g2.drawImage(img, 0, 0, this);

                // g2.setColor(textBackgroundColor);
                // g2.fillRect((this.getWidth() / 2) - (ix / 2),
                // (this.getHeight() / 2) - (iy / 2), ix, iy);
                // g2.setColor(textColor);
                // g2.drawRect((this.getWidth() / 2) - (ix / 2),
                // (this.getHeight() / 2) - (iy / 2), ix, iy);

                // hier die alten Zeilen schreiben
                g2.drawString(formerLines[9], startX, startY - (10 * fmHeight));
                g2.drawString(formerLines[8], startX, startY - (9 * fmHeight));
                g2.drawString(formerLines[7], startX, startY - (8 * fmHeight));
                g2.drawString(formerLines[6], startX, startY - (7 * fmHeight));
                g2.drawString(formerLines[5], startX, startY - (6 * fmHeight));
                g2.drawString(formerLines[4], startX, startY - (5 * fmHeight));
                g2.drawString(formerLines[3], startX, startY - (4 * fmHeight));
                g2.drawString(formerLines[2], startX, startY - (3 * fmHeight));
                g2.drawString(formerLines[1], startX, startY - (2 * fmHeight));
                g2.drawString(formerLines[0], startX, startY - (1 * fmHeight));
                g2.setColor(Color.YELLOW);
                g2.drawString(outputString, startX, startY);
            } else {
                if (openScreen) {
                    // // paint the yellow screen background
                    // g2.drawImage(img, 0, 0, this);
                    // ix = ix + 25;
                    // iy = iy + 20;
                    // if (ix >= (this.getWidth() - 40)) {
                    // ix = this.getWidth() - 40;
                    // }
                    // if (iy >= (startY + 10)) {
                    // iy = startY + 10;
                    // }
                    // g2.setColor(textBackgroundColor);
                    // g2.fillRect((this.getWidth() / 2) - (ix / 2),
                    // (this.getHeight() / 2) - (iy / 2), ix, iy);
                    // g2.setColor(Color.YELLOW);
                    // g2.drawRect((this.getWidth() / 2) - (ix / 2),
                    // (this.getHeight() / 2) - (iy / 2), ix, iy);
                    // if ((ix == (this.getWidth() - 40)) && (iy == (startY +
                    // 10))) {
                    // openScreen = false;
                    // }
                    openScreen = false;
                } else {
                    g2.drawImage(img, 0, 0, this);

                    // g2.setColor(textBackgroundColor);
                    // g2.fillRect((this.getWidth() / 2) - (ix / 2),
                    // (this.getHeight() / 2) - (iy / 2), ix, iy);
                    // g2.setColor(textColor);
                    // g2.drawRect((this.getWidth() / 2) - (ix / 2),
                    // (this.getHeight() / 2) - (iy / 2), ix, iy);

                    // write the older lines
                    g2.drawString(formerLines[9], startX, startY - (10 * fmHeight));
                    g2.drawString(formerLines[8], startX, startY - (9 * fmHeight));
                    g2.drawString(formerLines[7], startX, startY - (8 * fmHeight));
                    g2.drawString(formerLines[6], startX, startY - (7 * fmHeight));
                    g2.drawString(formerLines[5], startX, startY - (6 * fmHeight));
                    g2.drawString(formerLines[4], startX, startY - (5 * fmHeight));
                    g2.drawString(formerLines[3], startX, startY - (4 * fmHeight));
                    g2.drawString(formerLines[2], startX, startY - (3 * fmHeight));
                    g2.drawString(formerLines[1], startX, startY - (2 * fmHeight));
                    g2.drawString(formerLines[0], startX, startY - (1 * fmHeight));

                    i++;
                    if (i >= stringLength) {
                        i = 0;
                        endOfLine = true;
                    }

                    String tempString = outputString.substring(0, i + 1);
                    String lastChar = outputString.substring(i, i + 1);
                    if (!endOfLine) {
                        // the line was not typed completly yet... type on!
                        g2.setColor(Color.YELLOW);
                        g2.drawString(tempString, startX, startY);
                        g2.fillRect(startX + fm.stringWidth(tempString), startY - fm.getAscent(), 10, fm.getHeight());
                    } else {
                        // the line is completly typed here
                        if (blinkCounter <= 3) {
                            // blink the cursor
                            g2.setColor(Color.YELLOW);
                            g2.drawString(outputString, startX, startY);
                            g2.setColor(new Color(Color.YELLOW.getRed(), Color.YELLOW.getGreen(), Color.YELLOW.getBlue(), fade));
                            g2.fillRect(startX + fm.stringWidth(outputString), startY - fm.getAscent(), 10, fm.getHeight());
                            fade = fade - 50;
                            if (fade <= 0) {
                                blinkCounter++;
                                fade = 255;
                            }
                        } else {
                            // blinking is over here
                            g2.setColor(textColor);
                            g2.drawString(outputString, startX, startY);

                            // top the thread for now...
                            paint = false;
                        }
                    }
                }
            }

            // put the image from the bufferedImage to the visible context
            g.drawImage(myBufferedImage, 0, 0, this);
            g2.dispose();
        }
    }

    /**
     * Das BufferedImage kann erst erzeugt werden, wenn die Komponente
     * gezeichnet und die Größe damit bekannt ist.
     * 
     * @return boolean
     */
    private boolean createBuffer() {
        if (myBufferedImage != null) {
            // the buffer was already created
            return true;
        } else {
            if ((getWidth() == 0) || (getHeight() == 0)) {
                // component was not painted yet
                return false;
            }
            myBufferedImage = new BufferedImage(getWidth(), getHeight(), Transparency.OPAQUE);
        }
        return true;
    }

    /**
     * Run the animation as thread
     */
    public final void run() {
        while (paint) {
            repaint();
            Tools.wait(1);
        }
    }
}
