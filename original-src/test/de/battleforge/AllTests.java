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
package de.battleforge;

import junit.framework.Test;
import junit.framework.TestSuite;
import de.battleforge.gui.map.BFMainFrameTest;
import de.battleforge.gui.map.ScreenTyperTest;

/**
 * <p>
 * Title: <b>All Tests</b><br>
 * Description: <i>A JUnit TestSet for BattleForge-Map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 *
 * @author Meldric
 * @version 1.0
 */
public final class AllTests {
    /**
     * Empty constructor
     */
    private AllTests() {
    }

    /**
     * JUnit test
     *
     * @return TestSuite suite
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for de.battleforge.map");

        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(BFMainFrameTest.class));
        suite.addTest(new TestSuite(ScreenTyperTest.class));

        //$JUnit-END$
        return suite;
    }
}
