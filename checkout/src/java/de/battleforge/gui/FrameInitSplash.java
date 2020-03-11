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
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import de.battleforge.gui.util.ImageFactory;

/**
 * <p>
 * Title: <b>FrameInitSplash</b><br>
 * Description: <i>The Initsplash</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * <br>
 * Shows the splash while initializing
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class FrameInitSplash extends JFrame {
    /**
     * 
     * @uml.property name="myScreenTyper"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    private ScreenTyper myScreenTyper;

    /**
     * Progressbar
     */
    private JProgressBar myProgress = new JProgressBar();

    /**
     * Getter for the ScreenTyper
     * 
     * @return ScreenTyper
     */
    public final ScreenTyper getScreenTyper() {
        return myScreenTyper;
    }

    /**
     * Sets the value of the progressbar
     * 
     * @param value
     *            Value to set
     */
    public final void setProgressValue(int value) {
        myProgress.setValue(value);
    }

    /**
     * Sets the string of the progressbar
     * 
     * @param value
     *            String to set
     */
    public final void setProgressString(String value) {
        myProgress.setString(value);

    }

    /**
     * Sets the progressbar to indeterminate
     * 
     * @param value
     *            Boolean
     */
    public final void setProgressIndeterminated(boolean value) {
        myProgress.setIndeterminate(value);
    }

    /**
     * Constructor
     */
    public FrameInitSplash() {
        myScreenTyper = new ScreenTyper();

        BorderLayout l = new BorderLayout();
        myScreenTyper.setLayout(l);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        myProgress.setStringPainted(true);
        myProgress.setBorderPainted(true);
        myProgress.setIndeterminate(true);
        myProgress.setValue(0);

        myScreenTyper.add(myProgress, BorderLayout.SOUTH);

        this.setUndecorated(true);
        this.setTitle("Init...");
        this.setIconImage(ImageFactory.getImage("icon.png"));
        this.getContentPane().add(myScreenTyper);
        this.setSize(myScreenTyper.getPreferredSize());

        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
    }
}
