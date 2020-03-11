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

import java.awt.EventQueue;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import de.battleforge.gui.dialog.Error;

public class ExceptionHandler implements Runnable {

    private static ArrayList<Throwable> mThrowables;

    private static Object mLockObject = new Object();

    private static Thread mThread;

    static boolean mDisplaying;

    private static long mLastAddingTimeStamp;

    private static long mFirstAddingTimeStamp;

    public ExceptionHandler() {
        // do nothing

    } // ExceptionHandler

    public static void handle(Throwable th, Class loggerClass) {
        handle(th, Logger.getLogger(loggerClass));

    } // handle

    public static void handle(Throwable th) {
        handle(th, Logger.getLogger(ExceptionHandler.class));

    } // handle

    public static void handle(final Throwable th, Logger logger) {
        if (th == null) {
            throw new IllegalArgumentException("th must not be null");

        } // if

        checkInstance();

        synchronized (mLockObject) {
            mLastAddingTimeStamp = System.currentTimeMillis();

            if (mThrowables.isEmpty()) {
                mFirstAddingTimeStamp = mLastAddingTimeStamp;

            } // if

            if (!mDisplaying && (mLastAddingTimeStamp - mFirstAddingTimeStamp > 5000)) {
                mThrowables.clear();

                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        mDisplaying = true;

                        Error.showDialog(Tools.getFirstShowingFrame(), new BFException(9998), true);

                        System.exit(1);

                    }
                });
            } // if

            mThrowables.add(th);

            if (logger != null) {
                logger.error(th.getMessage(), th);

            } /* if */

            mLockObject.notifyAll();

        } // synchronized
    } // handle

    private static void checkInstance() {
        synchronized (mLockObject) {
            if (mThread == null) {
                mThread = new Thread(new ExceptionHandler(), "ExceptionHandler");
                mThread.setDaemon(true);
                mThread.setPriority(Thread.MIN_PRIORITY);

                mThrowables = new ArrayList<Throwable>();

                mThread.start();

            } // if
        } // synchronized
    } // checkInstance

    public void run() {
        while (true) {
            synchronized (mLockObject) {
                try {
                    mLockObject.wait(250);

                } catch (InterruptedException ie) {
                    // do nothing

                } // try

                if (System.currentTimeMillis() - mLastAddingTimeStamp < 100) {
                    continue;

                } // if

                if (!mThrowables.isEmpty() && !mDisplaying) {

                    final Throwable[] throwables = new Throwable[mThrowables.size()];

                    mThrowables.toArray(throwables);
                    mThrowables.clear();

                    EventQueue.invokeLater(new Runnable() {

                        public void run() {
                            mDisplaying = true;

                            Error.showDialog(Tools.getFirstShowingFrame(), throwables, false);

                            mDisplaying = false;

                        } // run
                    });
                } // if
            } // synchronized
        } // while
    } // run
} // end of class ExceptionHandler
