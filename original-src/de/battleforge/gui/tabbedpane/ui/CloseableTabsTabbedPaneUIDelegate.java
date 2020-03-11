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

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import de.battleforge.gui.tabbedpane.CloseableTabsTabbedPane;
import de.battleforge.gui.tabbedpane.TabCloseEvent;
import de.battleforge.gui.tabbedpane.TabCloseListener;
import de.battleforge.gui.tabbedpane.TabCloseVetoException;
import de.battleforge.gui.util.ImageFactory;

/**
 * @author ziegenbalg
 */
public class CloseableTabsTabbedPaneUIDelegate {

    private JButton mCloseButton;

    private MouseInputListener mMouseInputListener;

    private int mRolloverTab;

    private Point mLastMousePoint;

    private CloseableTabsTabbedPane mTabPane;

    private final static int CLOSE_BUTTON_INSET = 4;

    public CloseableTabsTabbedPaneUIDelegate(CloseableTabsTabbedPane tabPane) {
        mCloseButton = new CloseButton();

        mCloseButton.setOpaque(false);
        mCloseButton.setBorderPainted(false);
        mCloseButton.setContentAreaFilled(false);
        mCloseButton.setSize(18, 18);

        mRolloverTab = -1;

        mLastMousePoint = null;

        mTabPane = tabPane;

    } /* MetalCloseableTabTabbedPaneUI */

    /**
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#createMouseListener()
     */
    MouseListener createMouseListener(MouseListener listener) {
        mMouseInputListener = new MouseHandler(listener);

        return mMouseInputListener;

    }

    int calculateTabWidth(int width) {
        return width + mCloseButton.getWidth() + CLOSE_BUTTON_INSET;

    } /* calculateTabWidth */

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#calculateTabHeight(int,
     *      int, int)
     */
    int calculateTabHeight(int height) {
        int neededHeight = mCloseButton.getHeight() + CLOSE_BUTTON_INSET;

        return Math.max(height, neededHeight);

    } /* calculateTabHeight */

    void installListeners(CloseableTabsTabbedPane tabPane) {
        tabPane.addMouseMotionListener(mMouseInputListener);

    } /* installListeners */

    void uninstallListeners(CloseableTabsTabbedPane tabPane) {
        tabPane.removeMouseMotionListener(mMouseInputListener);

    } /* uninstallListeners */

    private Rectangle getCloseButtonBounds(int tabIndex) {
        return calculateBounds(mTabPane.getUI().getTabBounds(mTabPane, tabIndex), mCloseButton);

    } /* getCloseButtonBounds */

    private Rectangle calculateBounds(Rectangle src, JButton button) {
        int x = src.x + src.width + -button.getWidth() - CLOSE_BUTTON_INSET / 2;
        int y = src.y + (src.height - button.getHeight() - CLOSE_BUTTON_INSET) / 2 + CLOSE_BUTTON_INSET / 2;

        return new Rectangle(x, y, button.getWidth(), button.getHeight());

    }

    /**
     * 
     */
    void paintCloseButton(Graphics g, Rectangle[] rects, int tabIndex) {
        paintCloseButton(g, rects, tabIndex, 0, 0);

    }

    void paintCloseButton(Graphics g, Rectangle[] rects, int tabIndex, int deltaX, int deltaY) {
        if (mTabPane.isTabCloseable(tabIndex) && ((mTabPane.getSelectedIndex() == tabIndex) || (mRolloverTab == tabIndex))) {
            if (mTabPane.getComponentOrientation().isLeftToRight()) {
                Rectangle closeButtonBounds = calculateBounds(rects[tabIndex], mCloseButton);

                boolean rollover = (mLastMousePoint == null) ? false : getCloseButtonBounds(tabIndex).contains(mLastMousePoint);

                mCloseButton.setRolloverEnabled(rollover);

                Graphics g2 = g.create();

                g2.translate(closeButtonBounds.x + deltaX, closeButtonBounds.y + deltaY);

                mCloseButton.paint(g2);

                g2.translate(-closeButtonBounds.x - deltaX, -closeButtonBounds.y - deltaY);

                g2.dispose();

            } else {

            } /* if */
        } /* if */
    } /* paintCloseButton */

    void adjustTextRect(Rectangle textRect) {
        textRect.x -= CLOSE_BUTTON_INSET;

    } // adjustTextRect

    /**
     * 
     * @author ziegenbalg
     */
    private class MouseHandler extends MouseInputAdapter {

        /**
         * 
         */
        private MouseListener mMouseListenerDelegate;

        /**
         * 
         */
        private boolean mMousePressedOverCloseButton;

        private boolean mLastMouseEventOverCloseButton;

        /**
         * 
         * @param listener
         */
        private MouseHandler(MouseListener listener) {
            mMouseListenerDelegate = listener;

        } /* MouseHandler */

        /**
         * 
         * @param x
         * @param y
         */
        private void setRolloverTab(int x, int y) {
            int tab = mTabPane.getUI().tabForCoordinate(mTabPane, x, y);

            mLastMousePoint = new Point(x, y);

            int oldRolloverTab = mRolloverTab;

            if ((tab > -1) && mTabPane.isTabCloseable(tab)) {

                if (mRolloverTab != tab) {
                    mRolloverTab = tab;

                } /* if */

            } else {
                mRolloverTab = -1;

            } // if

            if ((mRolloverTab != oldRolloverTab) || (isMouseEventOverCloseButton(x, y) != mLastMouseEventOverCloseButton)) {
                mLastMouseEventOverCloseButton = isMouseEventOverCloseButton(x, y);

                mTabPane.repaint();

            } // if
        } /* setRolloverTab */

        /**
         * 
         * @param e
         * @return
         */
        private boolean isMouseEventOverCloseButton(int x, int y) {
            int index = mTabPane.getUI().tabForCoordinate(mTabPane, x, y);

            return (index > -1) && mTabPane.isTabCloseable(index) && getCloseButtonBounds(index).contains(x, y);

        } /* isMouseEventOverCloseButton */

        /**
         * @see javax.swing.event.MouseInputAdapter#mouseEntered(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            setRolloverTab(e.getX(), e.getY());

            mMouseListenerDelegate.mouseEntered(e);

        } /* mouseEntered */

        /**
         * @see javax.swing.event.MouseInputAdapter#mouseDragged(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            setRolloverTab(e.getX(), e.getY());

            if (isMouseEventOverCloseButton(e.getX(), e.getY())) {
                mCloseButton.getModel().setPressed(true);

            } else {
                mCloseButton.getModel().setPressed(false);

            } /* if */

            mTabPane.repaint();

        } /* mouseDragged */

        /**
         * @see javax.swing.event.MouseInputAdapter#mouseMoved(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            setRolloverTab(e.getX(), e.getY());

        } /* mouseMoved */

        /**
         * @see javax.swing.event.MouseInputAdapter#mouseExited(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseExited(MouseEvent e) {
            setRolloverTab(e.getX(), e.getY());

            mMouseListenerDelegate.mouseExited(e);

        } /* mouseExited */

        /**
         * @see javax.swing.event.MouseInputAdapter#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mousePressed(MouseEvent e) {
            mMousePressedOverCloseButton = isMouseEventOverCloseButton(e.getX(), e.getY());

            mCloseButton.getModel().setPressed(mMousePressedOverCloseButton);

            mTabPane.repaint();

        } /* mousePressed */

        /**
         * @see javax.swing.event.MouseInputAdapter#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (isMouseEventOverCloseButton(e.getX(), e.getY())) {
                int index = mTabPane.getUI().tabForCoordinate(mTabPane, e.getX(), e.getY());

                TabCloseListener[] tabCloseListeners = mTabPane.getTabCloseListeners();

                TabCloseEvent closeEvent = new TabCloseEvent(mTabPane.getComponentAt(index));

                try {
                    for (int i = tabCloseListeners.length - 1; i >= 0; i--) {
                        tabCloseListeners[i].tabWillBeClosed(closeEvent);

                    } // for

                    mTabPane.removeTabAt(index);

                    for (int i = tabCloseListeners.length - 1; i >= 0; i--) {
                        tabCloseListeners[i].tabClosed(closeEvent);

                    } // for
                } catch (TabCloseVetoException e1) {
                    // sLogger.error( "Exception occured", e1 );

                }
            } else {
                if (!mMousePressedOverCloseButton) {
                    mMouseListenerDelegate.mousePressed(e);

                } /* if */
            } /* if */

            mCloseButton.getModel().setPressed(false);

            mTabPane.repaint();

        } /* mousePressed */
    } /* end of class MouseHandler */

    private class CloseButton extends JButton {

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Icon icon = null;

            if (isRolloverEnabled()) {
                icon = ImageFactory.getIcon("filledX.png");

            } else {
                icon = ImageFactory.getIcon("emptyX.png");

            } // if

            int x = (getWidth() - icon.getIconWidth()) / 2;
            int y = (getHeight() - icon.getIconHeight()) / 2;

            icon.paintIcon(this, g, x, y);

        } // paint
    } // end of inner-class CloseButton
} /* end of class MetalCloseableTabTabbedPaneUI */