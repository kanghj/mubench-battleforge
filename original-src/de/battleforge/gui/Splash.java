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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.BFProperties.BFProps;

/**
 * <p>
 * Title: <b>Splash</b><br>
 * Description: <i>A class preloader and splashscreen</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author Kotzbrocken2
 * @version 1.10
 */
public class Splash extends JFrame {

    /**
     * Empty constructor.
     */
    public Splash() {
        // choose the splashscreen
        int number = (int) ((Math.random() * 3) + 1);

        String imageName = "themes/" + BFProperties.getProperty(BFProps.THEME) + "/images/splash_" + number + ".png";
        String iconName = "themes/" + BFProperties.getProperty(BFProps.THEME) + "/images/icon.png";

        ImageIcon myIcon = null;
        ImageIcon splashImage = null;

        // create a splash window, in which we'll display an image
        try {
            myIcon = new ImageIcon(Splash.class.getClassLoader().getResource(iconName));
            splashImage = new ImageIcon(Splash.class.getClassLoader().getResource(imageName));

        } catch (Exception e) {
            return;

        } /* try */

        // create the label to display
        JLabel myLabel = new JLabel();

        myLabel.setIcon(splashImage);
        getContentPane().add(myLabel);
        setUndecorated(true);

        // set icon
        if (myIcon != null) {
            setSize(splashImage.getIconWidth(), splashImage.getIconHeight());
            setIconImage(myIcon.getImage());

        } /* if */

        setTitle(Internationalization.getString("SplashLoader.Title"));

        // center splash on screen
        setLocationRelativeTo(null);

    } /* Splash */
} // end of class Splash
