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
package de.battleforge.gui.tabbedpane.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI;

import de.battleforge.gui.tabbedpane.CloseableTabsTabbedPane;

/**
 */
public class WindowsCloseableTabTabbedPaneUI extends WindowsTabbedPaneUI {

    public static ComponentUI createUI(JComponent x) {
        return new WindowsCloseableTabTabbedPaneUI();

    } /* createUI */

    private CloseableTabsTabbedPaneUIDelegate mDelegate;

    private CloseableTabsTabbedPane mTabPane;

    @Override
    public void installUI(JComponent c) {
        mTabPane = (CloseableTabsTabbedPane) c;

        mDelegate = new CloseableTabsTabbedPaneUIDelegate(mTabPane);

        super.installUI(c);

    } // installUI

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);

        mTabPane = null;
        mDelegate = null;

    } // uninstallUI

    @Override
    protected MouseListener createMouseListener() {
        return mDelegate.createMouseListener(super.createMouseListener());

    } // createMouseListener

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        int tabWidth = super.calculateTabWidth(tabPlacement, tabIndex, metrics);

        if (!mTabPane.isTabCloseable(tabIndex)) {
            return tabWidth;

        } // if

        return mDelegate.calculateTabWidth(tabWidth);

    } /* calculateTabWidth */

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        int tabHeight = super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);

        // if ( !mTabPane.isTabCloseable( tabIndex ) ) {
        // return tabHeight;
        //            
        // } // if

        return mDelegate.calculateTabHeight(tabHeight);

    } /* calculateTabHeight */

    @Override
    protected void installListeners() {
        super.installListeners();

        mDelegate.installListeners(mTabPane);

    } /* installListeners */

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();

        mDelegate.uninstallListeners(mTabPane);

    } /* uninstallListeners */

    @Override
    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);

        if (!mTabPane.isTabCloseable(tabIndex)) {
            return;

        } // if

        boolean selected = mTabPane.getSelectedIndex() == tabIndex;

        int deltaX = selected ? 0 : 2 * getTabLabelShiftX(tabPlacement, tabIndex, selected);
        int deltaY = selected ? 0 : 2 * getTabLabelShiftY(tabPlacement, tabIndex, selected);

        mDelegate.paintCloseButton(g, rects, tabIndex, deltaX, deltaY);

    } /* paintTab */

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect,
            boolean isSelected) {
        if (mTabPane.isTabCloseable(tabIndex)) {
            mDelegate.adjustTextRect(textRect);

            textRect.y++;

        } // if

        super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);

    } // paintText
} /* end of class WindowsCloseableTabTabbedPaneUI */