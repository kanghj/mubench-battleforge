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
package de.battleforge.util;

/**
 * <p>
 * Title: <b>Constant</b><br>
 * Description: <i>Defines constants for BattleForge-Map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * <br>
 * This class provides some essential constant values that are needed in lots of
 * other classes of the project.
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class BFConstant {

    /**
     * Jumpdistance in lightyears
     */
    public static final double JUMPDISTANCE = 30;

    /**
     * Key to find thanks text
     */
    public static final String ABOUT_THANKS = "about.thanks";

    /**
     * Key to find gpl text
     */
    public static final String ABOUT_GPL = "about.gpl";

    // -----------------------------------------------------------

    /**
     * Number of slots every industry may have
     */
    public static final int MAXIMUM_INDUSTRY_SLOTS = 9;
    // TODO: Get this value somehow from the rules list
}
