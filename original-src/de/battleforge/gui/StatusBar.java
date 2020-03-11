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
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

/**
 * <p>
 * Title: <b>StatusBar</b><br>
 * Description: <i>Provides a statusbar for BattleForge-Map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public final class StatusBar extends JPanel {
    /**
     * Contructor
     */
    private StatusBar() {
        SpringLayout layout = new SpringLayout();

        // this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(layout);

        mNachrichten.setFont(new Font("Arial", Font.BOLD, 10));
        mKoordinaten.setFont(new Font("Arial", Font.BOLD, 10));
        mZoomFaktor.setFont(new Font("Arial", Font.BOLD, 10));
        mJProgressBar.setFont(new Font("Arial", Font.BOLD, 10));
        mFpsCounter.setFont(new Font("Arial", Font.BOLD, 10));

        mNachrichten.setMinimumSize(new Dimension(100, 12));
        mNachrichten.setMaximumSize(new Dimension(3000, 12));
        mNachrichten.setPreferredSize(new Dimension(100, 12));

        mKoordinaten.setMinimumSize(new Dimension(100, 12));
        mKoordinaten.setMaximumSize(new Dimension(100, 12));
        mKoordinaten.setPreferredSize(new Dimension(100, 12));

        mZoomFaktor.setMinimumSize(new Dimension(70, 12));
        mZoomFaktor.setMaximumSize(new Dimension(70, 12));
        mZoomFaktor.setPreferredSize(new Dimension(70, 12));

        mJProgressBar.setMinimumSize(new Dimension(100, 12));
        mJProgressBar.setMaximumSize(new Dimension(100, 12));
        mJProgressBar.setPreferredSize(new Dimension(100, 12));

        mFpsCounter.setMinimumSize(new Dimension(100, 12));
        mFpsCounter.setMaximumSize(new Dimension(100, 12));
        mFpsCounter.setPreferredSize(new Dimension(100, 12));

        mNachrichten.setHorizontalAlignment(SwingConstants.LEFT);
        mKoordinaten.setHorizontalAlignment(SwingConstants.RIGHT);
        mZoomFaktor.setHorizontalAlignment(SwingConstants.RIGHT);
        mFpsCounter.setHorizontalAlignment(SwingConstants.LEFT);

        this.add(mFpsCounter);
        this.add(mNachrichten);
        this.add(mKoordinaten);
        this.add(mZoomFaktor);
        this.add(mJProgressBar);

        layout.putConstraint(SpringLayout.WEST, mFpsCounter, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, mNachrichten, 5, SpringLayout.EAST, mFpsCounter);
        layout.putConstraint(SpringLayout.WEST, mKoordinaten, 5, SpringLayout.EAST, mNachrichten);
        layout.putConstraint(SpringLayout.WEST, mZoomFaktor, 5, SpringLayout.EAST, mKoordinaten);
        layout.putConstraint(SpringLayout.WEST, mJProgressBar, 5, SpringLayout.EAST, mZoomFaktor);
        layout.putConstraint(SpringLayout.EAST, this, 5, SpringLayout.EAST, mJProgressBar);

        layout.putConstraint(SpringLayout.EAST, this, 5, SpringLayout.EAST, mJProgressBar);
        layout.putConstraint(SpringLayout.SOUTH, this, 1, SpringLayout.SOUTH, mJProgressBar);
    }

    /**
     * StatusBar exemplar
     * 
     * @uml.property name="exemplar"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    private static StatusBar exemplar = new StatusBar();

    /**
     * JLabel mKoordinaten
     */
    private JLabel mKoordinaten = new JLabel("");

    /**
     * JLabel mNachrichten
     */
    private JLabel mNachrichten = new JLabel("");

    /**
     * JLabel mZoomFaktor
     */
    private JLabel mZoomFaktor = new JLabel("");

    /**
     * JLabel mFpsCounter
     */
    private JLabel mFpsCounter = new JLabel("");

    /**
     * JProgressBar mJProgressBar
     */
    private JProgressBar mJProgressBar = new JProgressBar();

    /**
     * Returns an exemplar of the statusbar
     * 
     * @return exemplar of the statusbar
     */
    public static StatusBar gibExemplar() {
        return exemplar;
    }

    /**
     * Sets coordinate values
     * 
     * @param x
     *            value for x
     * @param y
     *            value for y
     */
    public void setKoordinaten(final int x, final int y) {
        mKoordinaten.setText("Pos: (" + x + "):(" + y + ")");
    }

    /**
     * Set progressbar to indeterminate
     * 
     * @param i
     *            true or false
     */
    public void setProgressIndeterminate(final boolean i) {
        mJProgressBar.setIndeterminate(i);
    }

    /**
     * Set progressbar to indeterminate
     * 
     * @param i
     *            Frame per second value
     */
    public void setFps(final int i) {
        mFpsCounter.setText(i + " fps (" + ((i == 0) ? 0 : 1000 / i) + "ms spf)");
    }

    /**
     * Set progressbar maximum value to max
     * 
     * @param max
     *            the value which maximum is set to
     */
    public void setProgressMax(final int max) {
        mJProgressBar.setMaximum(max);
    }

    /**
     * Set progressbar minimum value to min
     * 
     * @param min
     *            the value which minimum is set to
     */
    public void setProgressMin(final int min) {
        mJProgressBar.setMinimum(min);
    }

    /**
     * Set progressbar to value v
     * 
     * @param v
     *            the value to use
     */
    public void setProgressValue(final int v) {
        mJProgressBar.setValue(v);
    }

    /**
     * Set status to string z
     * 
     * @param z
     *            text for the statusbar
     */
    public void setStatus(final String z) {
        mNachrichten.setText("Status: " + z);
    }

    /**
     * Set zoom to int z
     * 
     * @param z
     *            value to set zoom-display to
     */
    public void setZoom(final int z) {
        mZoomFaktor.setText("Zoom: " + z + "%");
    }
}
