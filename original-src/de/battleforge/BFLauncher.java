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

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.gui.AbstractBFMainFrame;
import de.battleforge.gui.FrameInitSplash;
import de.battleforge.gui.Splash;
import de.battleforge.gui.dialog.Error;
import de.battleforge.gui.dialog.Login;
import de.battleforge.gui.dialog.Property;
import de.battleforge.gui.map.BFMainFrame;
import de.battleforge.sound.SoundPlayer;
import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.Tools;
import de.battleforge.util.BFProperties.BFProps;

/**
 * <p>
 * Title: <b>BFLauncher</b><br>
 * Description: <i>Launcher</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author Kotzbrocken2
 * @version 1.10
 */
public class BFLauncher {

    static {
        // all the first to do is to prepare the properties
        prepareUserProperties();

        // setting up the RollingFileAppender for logging
        File dir = new File(System.getProperty("user.home") + File.separator + "Battleforge");

        dir.mkdirs();

        String fileName = dir + File.separator + "battleforge.log";

        BFProperties.setProperty(BFProps.LOGFILE, fileName);

        PatternLayout layout = new PatternLayout("%d [%t] %p %c %x - %m%n");

        try {
            RollingFileAppender appender = new RollingFileAppender(layout, fileName, true);

            appender.setMaxBackupIndex(3);

            appender.setMaxFileSize("500kb");

            Logger.getRootLogger().addAppender(appender);

        } catch (IOException e) {
            Logger.getRootLogger().error("Exception occured", e);

        } // try
    } // static

    /**
     * Our logger.
     */
    private static Logger sLogger = Logger.getLogger(BFLauncher.class);

    private static AbstractBFMainFrame mMainFrame;

    private static WindowAdapter mWindowListener = new WindowAdapter() {

        @Override
        public void windowClosed(WindowEvent e) {
            ActionManager.getAction(ACTIONS.SHUTDOWN).execute();

            mMainFrame.stop();

            SoundPlayer.stopAllSounds();

        }
    };

    private static Splash splash;

    /**
     * Main class of splash
     * 
     * @param args
     *            arguments coming in
     * @throws Exception
     *             ex
     */
    public static void main(final String[] args) {
        prepareDoc();

        if (splash != null) {
            splash.dispose();

        } /* if */

        startMainFrame((args.length > 0) && args[0].equalsIgnoreCase("true"));

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                // delete our temp-files
                delete(new File(BFProperties.getProperty(BFProps.DOC_DIR)));

            } // run
        });
    } /* main */

    private static void startMainFrame(boolean startAdmin) {
        FrameInitSplash myInitSplash = new FrameInitSplash();

        myInitSplash.setVisible(true);

        if (!init(myInitSplash)) {
            myInitSplash.dispose();

            return;

        } // if

        mMainFrame = new BFMainFrame();

        myInitSplash.dispose();

        mMainFrame.addWindowListener(mWindowListener);
        mMainFrame.addComponentListener(new ComponentAdapter() {

            @Override
            public final void componentResized(ComponentEvent ce) {
                Dimension minSize = mMainFrame.getMinimumSize();
                Dimension newSize = mMainFrame.getSize();

                if ((mMainFrame.getHeight() < minSize.height)) {
                    newSize.height = minSize.height;

                } // if

                if ((mMainFrame.getWidth() < minSize.width)) {
                    newSize.width = minSize.width;

                } // if

                if (!newSize.equals(mMainFrame.getSize())) {
                    mMainFrame.setSize(newSize);

                } // if
            }
        });
        mMainFrame.setVisible(true);

        mMainFrame.start(myInitSplash);

    }

    /**
     * Here the creation of the universe is called.
     * 
     * @return
     */
    private static boolean init(FrameInitSplash myInitSplash) {
        Login login = new Login(myInitSplash);
        login.setVisible(true);
        
        if ( login.isCanceled() ) {
            return false;
            
        }

        myInitSplash.getScreenTyper().typeString("Welcome to BattleForge!");

        myInitSplash.getScreenTyper().typeString("Switching Interface to local language...");
        myInitSplash.getScreenTyper().typeString("Connecting...");

        return true;

    }

    static void delete(File file) {
        File[] files = file.listFiles();

        if ((files != null) && (files.length > 0)) {
            for (File element : files) {
                delete(element);

            } // for
        } // if

        file.delete();

    } // delete

    private static void prepareUserProperties() {
        try {
            File dir = new File(System.getProperty("user.home") + File.separator + "Battleforge");

            boolean showDialog = BFProperties.loadUserProperties(dir.toString());

            // does the user want to have the Splashscreen?
            if (BFProperties.getBoolean(BFProps.SHOW_SPLASHSCREEN) || showDialog) {
                splash = new Splash();
                splash.setVisible(true);

                Tools.wait(2000);

            } /* if */

            if (showDialog) {
                Property dialog = new Property(splash);

                dialog.setVisible(true);

            } // if
        } catch (IOException e) {
            Error.showDialog((Frame) null, e, false);

        } // try
    } // prepareUserProperties

    private static void prepareDoc() {
        Thread t = new Thread() {

            @Override
            public void run() {
                File dir = new File(System.getProperty("java.io.tmpdir") + File.separator + "Battleforge");
                BFProperties.setProperty(BFProps.DOC_DIR, dir.toString());

                try {
                    Tools.copyFromResourceToDir("doc", dir, true);

                } catch (IOException e) {
                    sLogger.warn(Internationalization.getStringForLog("exception.io"), e);

                } // try
            } // run
        };

        t.setPriority(Thread.MIN_PRIORITY);

        t.start();

    } // prepareDoc
} // end of class BFLauncher
