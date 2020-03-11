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

import java.awt.Component;
import java.awt.Image;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFOwnerType;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>ScrollPaneBackground</b><br>
 * Description: <i>This class is used to paint a background on a JScroll</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class IRCScrollPaneBackground extends JScrollPane {

    /**
     * New Viewport
     */
    private IRCScrollPaneBackgroundViewport viewPort2 = null;

    /**
     * Constructor
     * 
     * @param view
     *            Component
     * @param vsbPolicy
     *            Vertical ScrollBar Policy
     * @param hsbPolicy
     *            Horizontal ScrollBar Policy
     */
    public IRCScrollPaneBackground(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);

        // Set the component to transparent
        if (view instanceof JComponent) {
            ((JComponent) view).setOpaque(false);
        }
    }

    /**
     * Constructor
     */
    public IRCScrollPaneBackground() {
        this(null, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Constructor
     * 
     * @param view
     *            Component
     */
    public IRCScrollPaneBackground(Component view) {
        this(view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Constructor
     * 
     * @param vsbPolicy
     *            Vertical ScrollBar Policy
     * @param hsbPolicy
     *            Horizontal ScrollBar Policy
     */
    public IRCScrollPaneBackground(int vsbPolicy, int hsbPolicy) {
        this(null, vsbPolicy, hsbPolicy);
    }

    /**
     * Setter for tilemode
     * 
     * @param value
     *            should image be tiled
     */
    public final void setTileMode(boolean value) {
        if (viewPort2 != null) {
            viewPort2.setTileMode(value);
        }
    }

    /**
     * Creates the Viewport
     * 
     * @return JViewPort
     */
    @Override
    public final JViewport createViewport() {
        Image communicationsBackground = null;

        viewPort2 = new IRCScrollPaneBackgroundViewport();

        Iterator iter = DBWrapper.getOwnerType().iterator();
        while (iter.hasNext()) {
            BFOwnerType ot = (BFOwnerType) iter.next();
            String OwnerTypeName = Internationalization.getString("OwnerType." + ot.getName());

            if (ot == DBWrapper.getCurrentGameUser().getOwner().getOwnerType()) {
                if (OwnerTypeName.equals(Internationalization.getString("OwnerType.Innere_Sphere"))) {
                    communicationsBackground = ImageFactory.getImage("IRCBackground_ComStar.jpg");
                } else if (OwnerTypeName.equals(Internationalization.getString("OwnerType.Pirate"))) {
                    communicationsBackground = ImageFactory.getImage("IRCBackground_Pirate.jpg");
                } else if (OwnerTypeName.equals(Internationalization.getString("OwnerType.Clan"))) {
                    communicationsBackground = ImageFactory.getImage("IRCBackground_Clan.jpg");
                }
            }
        }

        if (communicationsBackground == null) {
            communicationsBackground = ImageFactory.getImage("IRCBackground_ComStar.jpg");
        }
        viewPort2.setBackgroundImage(communicationsBackground);
        viewPort2.setOpaque(false);
        viewPort2.setScrollMode(JViewport.BLIT_SCROLL_MODE);
        return viewPort2;
    }
}
