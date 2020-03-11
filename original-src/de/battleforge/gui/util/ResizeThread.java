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
package de.battleforge.gui.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Stack;

public class ResizeThread extends Thread {

    private Stack mSrcImages;

    private Stack mDestImages;

    private Object mLockObject;

    private ImageObserver mObserver;

    public ResizeThread(ImageObserver observer) {
        mSrcImages = new Stack();
        mDestImages = new Stack();
        mLockObject = new Object();

        setDaemon(true);
        setName("ResizeThread");
        setPriority(Thread.MIN_PRIORITY);

        mObserver = observer;

    } /* ResizeThread */

    public void addImage(Image src, BufferedImage dest) {
        synchronized (mLockObject) {
            mSrcImages.add(src);
            mDestImages.add(dest);

            mLockObject.notifyAll();

        } /* synchronized */
    } /* addImage */

    @Override
    public void run() {
        Image src = null;
        BufferedImage dest = null;

        while (true) {
            synchronized (mLockObject) {

                if (mSrcImages.isEmpty()) {
                    try {
                        mLockObject.wait();

                    } catch (InterruptedException e) {
                        // do nothing

                    } /* try */
                } /* if */

                if (!mSrcImages.isEmpty()) {
                    src = (Image) mSrcImages.pop();
                    dest = (BufferedImage) mDestImages.pop();

                } /* if */
            } /* synchronized */

            doResize(src, dest);

        } /* while */
    } /* run */

    private void doResize(Image src, BufferedImage dest) {
        if ((src != null) && (dest != null)) {
            BufferedImage tmpDest = new BufferedImage(dest.getWidth(), dest.getHeight(), dest.getType());

            PaintUtils.drawScaledImageHQ(src, tmpDest);

            synchronized (mLockObject) {
                dest.createGraphics().drawImage(tmpDest, 0, 0, null);

            } /* synchronized */

            mObserver.imageUpdate(dest, ImageObserver.ALLBITS, 0, 0, dest.getWidth(), dest.getHeight());

            System.gc();

        } /* if */
    } /* doResize */
}
