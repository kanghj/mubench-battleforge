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
package de.battleforge.gui.dialog;

import javax.swing.SwingUtilities;

public abstract class AsynchronousRunnable implements Runnable {

    private AbstractDialog mParent;

    private Runnable mSwitchRunnable;

    public AsynchronousRunnable(AbstractDialog parent) {
        mParent = parent;

        mSwitchRunnable = new Runnable() {
            public void run() {
                mParent.setCursor(mParent.getGlassPane().getCursor());
                mParent.getGlassPane().setVisible(!mParent.getGlassPane().isVisible());

            }
        };
    } // AsynchronousRunnable

    public final void run() {
        switchGlassPaneOnParent();

        execute();

        switchGlassPaneOnParent();

    }

    private void switchGlassPaneOnParent() {
        try {
            SwingUtilities.invokeAndWait(mSwitchRunnable);
        } catch (Exception e) {
            // do nothing

        } // try
    }

    protected abstract void execute();

}
