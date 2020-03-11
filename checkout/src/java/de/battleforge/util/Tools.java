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

import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import de.battleforge.gui.dialog.Error;

/**
 * <p>
 * Title: <b>Tools</b><br>
 * Description: <i>Some tools for BattleForge-Map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public final class Tools {

    /**
     * Our logger.
     */
    private static Logger sLogger = Logger.getLogger(Tools.class);

    /**
     * boolean to indicate whether J3D is installed
     */
    private static boolean installed = false;

    /**
     * Empty contructor
     */
    private Tools() {
        // do nothing

    }

    /**
     * Wait a given number of milliseconds
     * 
     * @param time
     *            number of milliseconds to wait
     */
    public static void wait(int time) {
        try {
            Thread.sleep(time);

        } catch (Exception e) {
            sLogger.warn("Exception occured", e);

        }
    }

    /**
     * Returns the system object for a given stringvalue
     * 
     * @param systemAsString
     *            name of the system as String
     * @return system as object
     */
    // public static Systems getSystemByString(final String systemAsString) {
    // Iterator it = Universe.alUniverse.iterator();
    // Systems s = null;
    // while (it.hasNext()) {
    // s = (Systems) it.next();
    // if (s.getSystem().equals(systemAsString)) {
    // return s;
    // }
    // }
    // return null;
    // }
    /**
     * Starts the browser with a given file the file is transformed to an url
     * and then startBrowser is called again with a different signature
     * 
     * @param parent
     *            parent
     * @param path
     *            path to browse
     */
    public static void startBrowser(final JFrame parent, final String url) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    BrowserLauncher.openURL(url);

                } catch (IOException e) {
                    sLogger.error(Internationalization.getString("browser.start.error"), e);

                    Error.showDialog(parent, e, false);

                } // try

            } // run
        };

        t.start();

    } // startBrowser

    /**
     * Find out if the Java3D-API is present
     * 
     * @return boolean
     */
    public static boolean j3DInstalled() {
        try {
            // SimpleUniverse test = new SimpleUniverse();
            // test.getViewingPlatform();
            installed = true;

            sLogger.debug("Java3D is installed!");

        } catch (Throwable th) {
            sLogger.debug("Java3D is NOT installed!", th);

        } // try

        return installed;

    }

    /**
     * Vergleicht zwei Daten (Format:"dd.mm.yyyy"), die als String übergeben
     * werden
     * 
     * @param date1
     *            first date
     * @param date2
     *            second date
     * @return 0 -> date1 = date2, 1 -> date1 > date2; -1 -> date1 < date2
     */
    public static int compareDate(String date1, String date2) {
        GregorianCalendar date3 = getCalendar(date1);
        GregorianCalendar date4 = getCalendar(date2);

        int i = -1;
        if (date3.equals(date4)) {
            i = 0;
        }
        if (date3.after(date4)) {
            i = 1;
        }
        return i;
    }

    public static boolean copyFromResourceToDir(String resourceName, File destDir, boolean overwrite) throws IOException {
        if (resourceName == null) {
            throw new IllegalArgumentException("resourceName must not be null");

        } // if

        if (destDir == null) {
            throw new IllegalArgumentException("destDir must not be null");

        } // if

        if (destDir.exists() && !destDir.isDirectory()) {
            throw new IllegalArgumentException("destDir isn't a directory");

        } // if

        destDir.mkdirs();

        URL resource = Tools.class.getClassLoader().getResource(resourceName);

        if (resource == null) {
            throw new IllegalArgumentException("resource can't be found");

        } // if

        boolean fileCreated = false;

        JarURLConnection connection = (JarURLConnection) resource.openConnection();

        JarFile jarFile = connection.getJarFile();

        Enumeration enumeration = jarFile.entries();

        while (enumeration.hasMoreElements()) {
            JarEntry entry = (JarEntry) enumeration.nextElement();

            File file = new File(destDir + File.separator + entry.getName());

            if (entry.isDirectory() || entry.getName().startsWith("META-INF")) {
                continue;

            } // if

            if (overwrite && file.exists()) {
                if (!file.delete()) {
                    throw new IOException("File <" + file + "> can't be deleted!");

                } // if
            } // if

            if (!overwrite && file.exists()) {
                continue;

            } // if

            file.getParentFile().mkdirs();
            fileCreated = fileCreated ? fileCreated : file.createNewFile();

            copy(jarFile.getInputStream(entry), new FileOutputStream(file));

        } // while

        return fileCreated;

    } // copyFromResourceToDir

    public static boolean copy(InputStream src, OutputStream dest) {
        byte[] buffer = new byte[32 * 1024]; // 32k Buffer

        // setting up the streams
        InputStream inStream = src instanceof BufferedInputStream ? src : new BufferedInputStream(src);
        OutputStream outStream = dest instanceof BufferedOutputStream ? dest : new BufferedOutputStream(dest);

        int count = 0;

        try {
            // try to copy
            while (count > -1) {
                count = inStream.read(buffer);

                if (count > 0) {
                    outStream.write(buffer, 0, count);

                } // if
            } // while
        } catch (IOException e) {
            sLogger.warn(Internationalization.getStringForLog("exception.io"), e);

            // return false if an error occured
            return false;

        } finally {

            // finally try to close the streams
            try {
                inStream.close();

            } catch (IOException x) {
                sLogger.warn(Internationalization.getStringForLog("exception.io"), x);

            } // try

            try {
                outStream.close();

            } catch (IOException x) {
                sLogger.warn(Internationalization.getStringForLog("exception.io"), x);

            } // try
        } // try

        return true;

    } // copy

    /**
     * reads a textfile and returns the content as string
     * 
     * @param f
     *            file to read
     * @return String
     */
    public static String getTextResource(String name) {
        try {
            String resourceName = Internationalization.getString(name);

            InputStream in = new BufferedInputStream(Tools.class.getClassLoader().getResourceAsStream(resourceName));

            byte[] buffer = new byte[32 * 1024];

            int count = 0;

            StringBuilder strBuffer = new StringBuilder();

            while (count > -1) {
                count = in.read(buffer);

                if (count > 0) {
                    strBuffer.append(new String(buffer, 0, count));

                } // if
            } // while

            try {
                in.close();

            } catch (IOException ioe) {
                sLogger.warn(Internationalization.getStringForLog("exception.io"), ioe);

            } // try

            return strBuffer.toString();

        } catch (Exception e) {
            return "Fehler beim Lesen der Datei ";

        } // try
    } // getTextResource

    /**
     * Returns the client sysdate
     * 
     * @return sysdate as string
     */
    public static String getClientSysdate() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("ECT"));

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        return df.format(cal.getTime());

        //
        // return new String( cal.get( Calendar.DATE ) + "." + (cal.get(
        // Calendar.MONTH ) + 1) + "." + cal.get( Calendar.YEAR ) + " "
        // + cal.get( Calendar.HOUR_OF_DAY ) + ":" + cal.get( Calendar.MINUTE )
        // + ":" + cal.get( Calendar.SECOND ) );

    }

    /**
     * Liefert ein GregorianCalendar zu einem String in dem Format("dd.mm.yyyy")
     * zurück
     * 
     * @param date
     *            Datum als String Format ("dd.mm.yyyy")
     * @return GregorianCalender
     */
    private static GregorianCalendar getCalendar(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(df.parse(date));

            return calendar;

        } catch (ParseException e) {
            sLogger.error("Exception occured", e);

        } // try

        // we shouldn't get here
        return new GregorianCalendar();

    } // getCalendar

    /**
     * addiert zu einem Datum (als String) in dem Format dd.mm.yyyy die Anzahl
     * Tage days
     * 
     * @param date
     *            Date
     * @param days
     *            Days
     * @return String
     */
    public static String addDayToDate(String date, int days) {
        GregorianCalendar c = Tools.getCalendar(date);
        c.add(Calendar.DATE, days);

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        return df.format(c.getTime());

    } // adDayToDate

    public static Frame getFirstShowingFrame() {
        Frame[] frames = Frame.getFrames();

        if ((frames != null) && (frames.length > 0)) {

            for (Frame element : frames) {
                if (element.isShowing()) {
                    return element;

                } // if
            } // for
        } // if

        return null;

    } // getFirstShowingFrame

    public static String htmlEscape(String toHtml) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0, n = toHtml.length(); i < n; i++) {
            char c = toHtml.charAt(i);

            if ((c < 0x20) || (c > 0x7e) || (c == 0x26 /* & */) || (c == 0x3c /* < */) || (c == 0x3e /* > */)) {
                sb.append('&');
                sb.append('#');
                sb.append((int) c);
                sb.append(';');

            } else {
                sb.append(c);

            } // if
        } // for

        return sb.toString();

    }
} // end of class Tools
