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
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.AbstractReferenceMap;
import org.apache.commons.collections.map.ReferenceMap;
import org.apache.log4j.Logger;

import de.battleforge.util.BFProperties;
import de.battleforge.util.BFProperties.BFProps;

/**
 * Eine Hilfsklasse, welche fuer das Handling von Bildern und Icons zustaendig
 * ist. Diese kann nicht instanziiert werden. Sie enthaelt nur statische
 * Hilfsmethoden. Wird ein Bild oder Icon angefordert, so wird erst im Cache
 * nachgeschaut, ob dieses schonmal geladen wurde. Wenn ja, so wird eine
 * Referenz auf dieses Objekt zurueckgegeben. D.h. jedes Icon wird nur einmal
 * geladen und im Speicher gehalten. <br>
 * <br>
 * <b>Implementation Note:</b> Als Cache wird die Implementierung von
 * <code>org.apache.commons.collections.ReferenceMap</code> genutzt. Damit ist
 * es moeglich, dass die gecachten Icons, wenn diese nicht mehr referenziert
 * werden, vom Garbage-Collector entfernt werden koennen und somit keine
 * Speicherlecks entstehen.
 * 
 * @author kotzbrocken2
 */
public abstract class ImageFactory {

    /**
     * Fuer's logging.
     */
    private static Logger sLogger = Logger.getLogger(ImageFactory.class);

    /**
     * Hier werden die gefundenen Icons gecacht. Die Standardgroesse der Map ist
     * 50. Der Load-Faktor 75%, d.h. wenn die Map zu 75% gefuellt ist, wird
     * rehasht.
     */
    private static ReferenceMap sImageIcons = new ReferenceMap(AbstractReferenceMap.HARD, AbstractReferenceMap.SOFT, 50, 0.75f);

    /**
     * Hier werden die gewandelten Images gecacht. Die Standardgroesse der Map
     * ist 50. Der Load-Faktor 75%, d.h. wenn die Map zu 75% gefuellt ist, wird
     * rehasht.
     */
    private static ReferenceMap sIcons = new ReferenceMap(AbstractReferenceMap.SOFT, AbstractReferenceMap.SOFT, 50, 0.75f);

    /**
     * Hier werden die gewandelten Icons gecacht. Die Standardgroesse der Map
     * ist 50. Der Load-Faktor 75%, d.h. wenn die Map zu 75% gefuellt ist, wird
     * rehasht.
     */
    private static ReferenceMap sImages = new ReferenceMap(AbstractReferenceMap.SOFT, AbstractReferenceMap.SOFT, 50, 0.75f);

    /**
     * Der Name des Standard-Icons.
     */
    // public static final String DEFAULT_ICON_NAME = "BossIcon.gif";
    /**
     * Da befinden sich normalerweise die Icons.
     */
    // public static final String DEFAULT_ICON_LOCATION = "/icons";
    /**
     * Das leere Standard-Icon der Groesse 16x16 Punkte.
     */
    // public static final Icon EMPTY_ICON = new EmptyIcon( 16, 16 );
    /**
     * Das Icon der Groesse 16x16 Punkte, welches für nicht gefundene Icons
     * verwendet werden kann.
     */
    // public static final Icon ICON_NOT_FOUND_ICON = new IconNotFoundIcon( 16,
    // 16 );
    /**
     * Das Standard-Icon. Normalerweise das BOSS-Icon. Wird dies jedoch nicht
     * gefunden, so ist es das <code>ICON_NOT_FOUND_ICON</code> Icon.
     */
    // public static final Icon DEFAULT_ICON = getDefaultIcon();
    /**
     * Das leere Standard-Icon als Image der Groesse 16x16 Punkte.
     */
    // public static final Image EMPTY_IMAGE = getIconAsImage( EMPTY_ICON );
    /**
     * Das Icon der Groesse 16x16 Punkte als Image, welches für nicht gefundene
     * Icons verwendet werden kann.
     */
    // public static final Image IMAGE_NOT_FOUND_IMAGE = getIconAsImage(
    // ICON_NOT_FOUND_ICON );
    /**
     * Das Standard-Icon als Image. Normalerweise das BOSS-Icon. Wird dies
     * jedoch nicht gefunden, so ist es das <code>IMAGE_NOT_FOUND_IMAGE</code>
     * Icon.
     */
    // public static final Image DEFAULT_IMAGE = getIconAsImage( DEFAULT_ICON );
    public static final String THEMES_DIR = "themes/";

    // public static final String IMAGES_DIR = "images" + File.separator;
    //
    private static final String DEFAULT_IMAGE_PATH = "images/";

    private static final String DEFAULT_ICON_PATH = "icons/";

    private static final String IMAGE_NOT_FOUND_NAME = "/" + DEFAULT_IMAGE_PATH + "_noImageAvailable.png";

    /**
     * Statischer Initializer.
     */
    // static {
    // // sImageIcons.put( "16x16", EMPTY_ICON );
    //
    // } /* static */

    /**
     * Nicht benutzter Konstruktor, da diese Klasse nur statische Helper-
     * Methoden implementiert.
     */
    private ImageFactory() {
    } /* ImageFactory */

    /**
     * Es wird versucht, das mit <code>aName</code> benannte Icon zu laden.
     * Gelingt dies nicht, so wird <code>null</code> zurueckgegeben.
     * 
     * @param aName
     *            der Name des zu ladenden Icons
     * @return ein <code>Icon</code>-Objekt oder <code>null</code>
     * @see ImageFactory#getImageIcon
     */
    // public static Icon getIcon( String aName ) {
    // return getIcon( aName, ICON_NOT_FOUND_ICON );
    //
    // } /* getIcon */

    public static Icon getIcon(String aName) {
        return getIcon(DEFAULT_ICON_PATH, aName);

        // return icon != null ? icon : aDefaultIcon;

    } /* getIcon */

    /**
     * Es wird versucht, das mit <code>aName</code> benannte Icon zu laden.
     * Gelingt dies nicht, so wird das uebergebene <code>aDefaultIcon</code>-
     * Objekt zurueckgegeben.
     * 
     * @param aName
     *            der Name des zu ladenden Icons
     * @param aDefaultIcon
     *            das Default-Icon
     * 
     * @return ein <code>Icon</code>-Objekt oder das uebergebene
     *         <code>aDefaultIcon</code>
     * 
     * @see ImageFactory#getImageIcon
     */
    public static Icon getIcon(String path, String aName) {
        return getImageIcon(path, aName);

        // return icon != null ? icon : aDefaultIcon;

    } /* getIcon */

    public static Image getImage(String aName) {
        return getImage(DEFAULT_IMAGE_PATH, aName);

        // return icon != null ? icon : aDefaultIcon;

    } /* getIcon */

    /**
     * Es wird versucht, das mit <code>aName</code> benannte Icon zu laden.
     * Gelingt dies nicht, so wird das uebergebene <code>aDefaultIcon</code>-
     * Objekt zurueckgegeben.
     * 
     * @param aName
     *            der Name des zu ladenden Icons
     * @param aDefaultIcon
     *            das Default-Icon
     * 
     * @return ein <code>Icon</code>-Objekt oder das uebergebene
     *         <code>aDefaultIcon</code>
     * 
     * @see ImageFactory#getImageIcon
     */
    public static Image getImage(String path, String aName) {
        return getImageIcon(path, aName).getImage();

        // return icon != null ? icon : aDefaultIcon;

    } /* getIcon */

    /**
     * Liefert das uebergebene <code>Image</code> als <code>Icon</code>
     * zurueck.
     * 
     * @param aImage
     *            das zu wandelnde <code>Image</code>-Objekt
     * 
     * @return das gewandelte <code>Icon</code>-Object
     */
    // public static Icon getIcon( Image aImage ) {
    // if ( aImage == null ) {
    // throw new IllegalArgumentException( "Das uebergebene Image darf nicht
    // null sein!" );
    //
    // } /* if */
    //
    // Icon icon = (Icon) sIcons.get( aImage );
    //
    // if ( icon == null ) {
    // sLogger.debug( "getIcon: zu wandelndes Image nicht im Cache gefunden!" );
    //
    // // das Image wurde noch nie gewandelt bzw. ist schon aus dem Cache
    // geflogen
    // icon = new ImageIcon( aImage );
    //
    // // erzeugtes Icon cachen
    // sIcons.put( aImage, icon );
    // sImages.put( icon, aImage );
    //
    // } /* if */
    //
    // return icon;
    //
    // } /* getIcon */

    /**
     * Es wird versucht, das mit <code>aName</code> benannte Icon zu laden und
     * als <code>Image</code> zurueckzugeben. Gelingt dies nicht, so wird das
     * <code>IMAGE_NOT_FOUND_IMAGE</code> zurueckgegeben.
     * 
     * @param aName
     *            der Name des zu ladenden Icons
     * @return ein <code>Image</code>-Objekt
     */
    // public static Image getIconAsImage( String aName ) {
    // return getIconAsImage( aName, IMAGE_NOT_FOUND_IMAGE );
    //
    // } /* getIconAsImage */

    /**
     * Es wird versucht, das mit <code>aName</code> benannte Icon zu laden und
     * als <code>Image</code> zurueckzugeben. Gelingt dies nicht, so wird
     * <code>aDefaultImage</code> zurueckgegeben.
     * 
     * @param aName
     *            der Name des zu ladenden Icons
     * @param aDefaultImage
     *            das zurueckzugebende Standard-Image, falls das Laden des Icons
     *            nicht funktionierte
     * @return ein <code>Image</code>-Objekt oder <code>aDefaultImage</code>
     */
    // public static Image getIconAsImage( String path, String aName ) {
    // return getImageIcon( path, aName ).getImage();
    //
    // //return image != null ? image.getImage() : aDefaultImage;
    //
    // } /* getImage */

    /**
     * Das uebergebene <code>Icon</code> wird in ein <code>Image</code>-Objekt
     * umgewandelt. Nach der Wandlung wird dies ebenfalls gecacht. Bei einem
     * erneuten Aufruf der Methode mit dem gleichen <code>Icon</code>-Objekt
     * wird das schon einmal erzeugte Bild zurueckgegeben.
     * 
     * @param aIcon
     *            ein <code>Icon</code>-Objekt
     * 
     * @return ein <code>Image</code>-Objekt
     * 
     * @throws IllegalArgumentException
     *             wenn das uebergebene <code>Icon</code>-Objekt
     *             <code>null</code> ist
     */
    // public static Image getIconAsImage( Icon aIcon ) {
    // if ( aIcon == null ) {
    // throw new IllegalArgumentException( "Das uebergebene Icon darf nicht null
    // sein!" );
    //
    // } /* if */
    //
    // Image image = (Image) sImages.get( aIcon );
    //
    // if ( image == null ) {
    // sLogger.debug( "getIconAsImage: zu wandelndes Icon nicht im Cache
    // gefunden!" );
    //
    // // das Icon wurde noch nie gewandelt bzw. ist schon aus dem Cache
    // geflogen
    // if ( aIcon instanceof ImageIcon ) {
    // // wenn das Icon ein ImageIcon ist, so kann man sich das Erzeugen
    // // eines BufferedImage's sparen und direkt auf das Bild zugreifen
    // ImageIcon ii = (ImageIcon) aIcon;
    //
    // image = ii.getImage();
    //
    // } else {
    // // BufferedImage mit den Abmessungen des Icons erzeugen das Icon
    // // in dieses Bild malen lassen
    // BufferedImage bi = new BufferedImage( aIcon.getIconWidth(),
    // aIcon.getIconHeight(),
    // BufferedImage.TYPE_INT_ARGB );
    //
    // aIcon.paintIcon( null, bi.createGraphics(), 0, 0 );
    //
    // image = bi;
    //
    // } /* if */
    //
    // // gefundenes bzw. erzeugtes Bild cachen
    // sImages.put( aIcon, image );
    // sIcons.put( image, aIcon );
    //
    // } /* if */
    //
    // return image;
    //
    // } /* getIconAsImage */
    //
    /**
     * Gibt ein leeres Icon der Breite <code>width</code> und
     * <code>height</code> zurueck. Wurde ein Icon dieser Ausmasse schonmal
     * erzeugt, so wird dieses zurueckgegeben. Ansonsten wird ein neues erzeugt
     * und gecacht.
     * 
     * @param width
     *            die Breite des Icons
     * @param height
     *            die Hoehe des Icons
     * 
     * @return ein <code>Icon</code>-Objekt
     */
    // public static Icon getEmptyIcon( int width, int height ) {
    // // eindeutigen Key erzeugen
    // String key = width + "x" + height + "%EMPTY";
    //
    // // Icon aus dem Cache holen
    // Icon icon = (Icon) sImageIcons.get( key );
    //
    // // Icon im Cache vorhanden?
    // if ( icon == null ) {
    // // Icon nicht gefunden --> neu erzeugen und speichern
    // icon = new EmptyIcon( width, height );
    //
    // sImageIcons.put( key, icon );
    //
    // } /* if */
    //
    // return icon;
    //
    // } /* getEmptyIcon */

    /**
     * Gibt ein IconNotFoundIcon der Breite <code>width</code> und
     * <code>height</code> zurueck. Wurde ein Icon dieser Ausmasse schonmal
     * erzeugt, so wird dieses zurueckgegeben. Ansonsten wird ein neues erzeugt
     * und gecacht.
     * 
     * @param width
     *            die Breite des Icons
     * @param height
     *            die Hoehe des Icons
     * 
     * @return ein <code>Icon</code>-Objekt
     */
    // public static Icon getIconNotFoundIcon( int width, int height ) {
    // // eindeutigen Key erzeugen
    // String key = width + "x" + height + "%NOTFOUND";
    //
    // // Icon aus dem Cache holen
    // Icon icon = (Icon) sImageIcons.get( key );
    //
    // // Icon im Cache vorhanden?
    // if ( icon == null ) {
    // // Icon nicht gefunden --> neu erzeugen und speichern
    // icon = new IconNotFoundIcon( width, height );
    //
    // sImageIcons.put( key, icon );
    //
    // } /* if */
    //
    // return icon;
    //
    // } /* getEmptyIcon */

    /**
     * Schaut im Cache nach, ob das angeforderte Icon vorhanden ist. Wenn ja, so
     * wird dieses zurueckgegeben. Wenn nein, dann wird versucht, das Bild zu
     * laden. Dieses wird dann im Cache zwischengespeichert und zurueckgegeben.
     * Kann das angegebene Icon nicht geladen werden, so wird <code>null</code>
     * zurueckgegeben.<br>
     * 
     * @param name
     *            der Name des zu ladenden Images
     * @return ein <code>ImageIcon</code>-Objekt oder <code>null</code>
     * @see ImageFactory#loadImage
     */
    private static ImageIcon getImageIcon(String path, String name) {
        if (name == null) {
            sLogger.info("name was null!");

            name = IMAGE_NOT_FOUND_NAME;

        } /* if */

        if (!path.endsWith("/")) {
            path += "/";

        } /* if */

        MultiKey key = new MultiKey(path, name);

        ImageIcon icon = (ImageIcon) sImageIcons.get(key);

        if (icon == null) {
            sLogger.debug("getImageIcon: zu ladendes Icon <" + name + "> nicht im Cache gefunden!");

            // das Bild wurde noch nie geladen oder es ist schon wieder
            // aus dem Cache geflogen, weil es nicht mehr benutzt wurde
            icon = loadImage(path, name);

            if (icon != null) {
                // Icon gefunden -> dann koennen wir es auch speichern
                sImageIcons.put(key, icon);
                sIcons.put(icon.getImage(), icon);
                sImages.put(icon, icon.getImage());

                sLogger.debug("getImageIcon: Icon <" + name + "> geladen und gespeichert!");

            } /* if */
        } else {
            sLogger.debug("getImageIcon: Icon <" + name + "> im Cache gefunden!");

        } /* if */

        return icon;

    } /* getImageIcon */

    /**
     * Versucht, das mit <code>aName</code> benannte Icon oder Bild zu laden.
     * Dabei kann der uebergebene <code>aName</code> ein relativer Bildname
     * sein (z.B. "Bild.gif") oder ein absoluter (z.B. "/GIFs/Bild.gif"). Es
     * wird auf jeden Fall erstmal versucht, im Standard-Verzeichnis das Bild zu
     * laden. Gelingt dies nicht, so wird versucht, das Bild ohne das Standard-
     * verzeichnisses zu laden.
     * 
     * @param aName
     *            der Name des zu ladenden Images
     * @return ein <code>ImageIcon</code>-Objekte oder <code>null</code>,
     *         wenn das Bild nicht geladen werden konnte
     */
    private static ImageIcon loadImage(String path, String name) {
        // first try to get the image from the theme
        URL resource = findResource(THEMES_DIR + BFProperties.getProperty(BFProps.THEME) + "/" + path + name);

        if (resource == null) {
            // the image was not found in the theme so take the normal path
            resource = findResource(THEMES_DIR + BFProperties.getProperty(BFProps.THEME) + "/" + DEFAULT_IMAGE_PATH + name);

        } /* if */

        if (resource == null) {
            // the image was not found in the theme so take the normal path
            resource = findResource(DEFAULT_IMAGE_PATH + path + name);

        } /* if */

        if (resource == null) {
            // the image was not found in the theme so take the normal path
            resource = findResource(THEMES_DIR + BFProperties.getProperty(BFProps.THEME) + IMAGE_NOT_FOUND_NAME);

        } /* if */

        return new ImageIcon((resource == null) ? new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB) : Toolkit.getDefaultToolkit().createImage(
                resource));

    } /* loadImage */

    private static URL findResource(String fullImagePath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        sLogger.debug("try to load " + fullImagePath);

        URL resource = classLoader.getResource(fullImagePath);

        if (resource == null) {
            sLogger.debug(fullImagePath + " not found");

        } else {
            sLogger.debug("found " + fullImagePath);

        } // if

        return resource;

    }

    /**
     * Lädt das Default-Icon.
     * 
     * @return das Standard-<code>Icon</code>
     */
    // private static Icon getDefaultIcon() {
    // Icon icon = getImageIcon( DEFAULT_ICON_NAME );
    //
    // if ( icon == null ) {
    // icon = ICON_NOT_FOUND_ICON;
    //
    // } /* if */
    //
    // return icon;
    //
    // } /* getDefaultIcon */
} /* end of class ImageFactory */