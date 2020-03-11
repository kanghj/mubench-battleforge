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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: <b>Properties</b><br>
 * Description: <i>Get Stringresources from property file</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public class BFProperties {

    private static final String UNKNOWN = "unkown";

    private static final String REGENERATION_WARNING = "DO NOT EDIT!!! File will be regenerated!";

    public enum BFProps {
        PHP_CONNECTION, LOGIN_USER, LOGIN_PASSWORD, USE_PROXY, USE_SYSTEM_PROXY, PROXY_SERVER, PROXY_PORT, PROXY_USER, PROXY_PASSWORD, SHOW_SPLASHSCREEN, THEME, LANGUAGE, COUNTRY, PLAY_VOICE, PLAY_MUSIC, PLAY_SOUND, IRC_SERVER, IRC_PORT, IRC_CHANNEL, IRC_NICKNAME, IRC_PASSWORD, IRC_USER, IRC_NAME, BROWSER, VERSION, DOC_DIR, LOGFILE

    } // end of enum BFProps

    /**
     * Our logger.
     */
    private static Logger sLogger = Logger.getLogger(BFProperties.class);

    /**
     * Properties
     */
    private static Properties sProperties = new Properties();

    /**
     * Our file in which the user-properties are stored.
     */
    private static File sUserPropertyFile;

    /**
     * Static initializer.
     */
    static {
        try {
            sProperties.load(BFProperties.class.getClassLoader().getResourceAsStream("battleforge.properties"));

        } catch (IOException ioe) {
            sLogger.error("IO-Exception occured", ioe);

        } // try
    } // static

    /**
     * Loads the userproperties from the given directory. The version of the
     * battleforge-property-file and the version of the user-property-file will
     * be compared. If they differ then the stored userproperties will be
     * loaded, the user-property-file will be deleted and the valid
     * userproperties will be restored within the new user-property-file. So we
     * avoid version-conflicts by validating the user-properties.
     * 
     * @param dir
     *            the directory where the user-property-file will be
     * @return <true> if the user-properties would be changed or the
     *         user-property-file doesn't exist (this indicates a first start)
     *         and the property-dialog should be displayed
     * @throws IOException
     *             if something goes wrong
     */
    public static boolean loadUserProperties(String dir) throws IOException {
        sUserPropertyFile = new File(dir + "/battleforge.properties");

        Properties userProperties = new Properties();

        // if the user-property-file exist
        if (sUserPropertyFile.exists()) {

            // load the properties
            FileInputStream fIn = new FileInputStream(sUserPropertyFile);

            userProperties.load(fIn);

            fIn.close();

            String versionKey = BFProps.VERSION.name().toLowerCase();

            // look if the versions are identical and if so store the
            // userproperties into the property-object
            if (sProperties.get(versionKey).equals(userProperties.get(versionKey))) {
                sProperties.putAll(userProperties);

                updateSystemProperties();

                return false;

            } // if
        } // if

        resetUserProperties();

        // go througth all entries of the user-properties and store them during
        // this session
        for (Map.Entry userProperty : userProperties.entrySet()) {
            String key = (String) userProperty.getKey();

            // only store known values
            if (sProperties.containsKey(key) && !BFProps.VERSION.name().toLowerCase().equals(key)) {
                setProperty(BFProps.valueOf((String) userProperty.getKey()), (String) userProperty.getValue(), true);

            } // if
        } // for

        return true;

    } // loadUserProperties

    private static void updateSystemProperties() {
        if (getBoolean(BFProps.USE_PROXY)) {
            if (getBoolean(BFProps.USE_SYSTEM_PROXY)) {
                System.setProperty("java.net.useSystemProxies", "true");
                System.getProperties().remove("http.proxyHost");
                System.getProperties().remove("http.proxyPort");

            } else {
                System.setProperty("http.proxyHost", BFProperties.getProperty(BFProps.PROXY_SERVER));
                System.setProperty("http.proxyPort", BFProperties.getProperty(BFProps.PROXY_PORT));
                System.getProperties().remove("java.net.useSystemProxies");

            } // if
        } // if
    } // updateSystemProperties

    public static void resetUserProperties() throws IOException {
        // create parent dirs if necessary
        sUserPropertyFile.getParentFile().mkdirs();

        // delete the file
        sUserPropertyFile.delete();

        // create the file
        sUserPropertyFile.createNewFile();

        setProperty(BFProps.VERSION, (String) sProperties.remove(BFProps.VERSION.name().toLowerCase()), true);

    } // resetUserProperties

    public static void setProperty(BFProps key, String value) {
        setProperty(key, value, false);

    } /* setProperty */

    public static void setProperty(BFProps key, String value, boolean store) {
        String strKey = key.name().toLowerCase();

        String prop = sProperties.getProperty(strKey);

        if ((prop == null) || !prop.equals(value)) {
            if (BFProps.PROXY_PASSWORD.equals(key)) {
                try {
                    Cipher c = Cipher.getInstance("AES");

                    byte[] secretKey = new byte[16];
                    
                    secretKey[0]=78;  
                    secretKey[1]=-44; 
                    secretKey[2]=-10;
                    secretKey[3]=-42;
                    secretKey[4]=-44;
                    secretKey[5]=106;
                    secretKey[6]=-30;
                    secretKey[7]=-1;
                    secretKey[8]=-104;
                    secretKey[9]=105;
                    secretKey[10]=24;
                    secretKey[11]=-103;
                    secretKey[12]=-9;
                    secretKey[13]=-81;
                    secretKey[14]=-32;
                    secretKey[15]=95;

                    Key k = new SecretKeySpec(secretKey, "AES");
                    c.init(Cipher.ENCRYPT_MODE, k);

                    value = new String(c.doFinal(value.getBytes()));

                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();

                } catch (NoSuchPaddingException e1) {
                    e1.printStackTrace();

                } catch (InvalidKeyException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (IllegalBlockSizeException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (BadPaddingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                }
            }

            sProperties.setProperty(strKey, value);

            if (store) {
                Properties props = new Properties();

                try {
                    FileInputStream fIn = new FileInputStream(sUserPropertyFile);

                    props.load(fIn);

                    fIn.close();

                    props.setProperty(strKey, value);

                    FileOutputStream fOut = new FileOutputStream(sUserPropertyFile);

                    props.store(fOut, REGENERATION_WARNING);

                    fOut.close();

                } catch (IOException e) {
                    sLogger.error("Exception occured", e);

                } // try
            } // if

            updateSystemProperties();

        } // if
    } /* setProperty */

    /**
     * Get Property
     * 
     * @param key
     *            String
     * @return String
     */
    public static String getProperty(BFProps key) {
        String value = sProperties.getProperty(key.name().toLowerCase(), UNKNOWN);

        if (BFProps.PROXY_PASSWORD.equals(key)) {
            try {
                byte[] secretKey = new byte[16];
                
                secretKey[0]=78;  
                secretKey[1]=-44; 
                secretKey[2]=-10;
                secretKey[3]=-42;
                secretKey[4]=-44;
                secretKey[5]=106;
                secretKey[6]=-30;
                secretKey[7]=-1;
                secretKey[8]=-104;
                secretKey[9]=105;
                secretKey[10]=24;
                secretKey[11]=-103;
                secretKey[12]=-9;
                secretKey[13]=-81;
                secretKey[14]=-32;
                secretKey[15]=95;

                Cipher c = Cipher.getInstance("AES");

                Key k = new SecretKeySpec(secretKey, "AES");
                c.init(Cipher.DECRYPT_MODE, k);

                value = new String(c.doFinal(value.getBytes()));

            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();

            } catch (NoSuchPaddingException e1) {
                e1.printStackTrace();

            } catch (InvalidKeyException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

            } catch (IllegalBlockSizeException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

            } catch (BadPaddingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

            }
        }

        return value;

    } /* getProperty */

    /**
     * Get int
     * 
     * @param key
     *            String
     * @return int
     */
    public static int getInt(BFProps key) {
        try {
            return Integer.parseInt(getProperty(key));

        } catch (NumberFormatException nfe) {
            sLogger.warn("Numberformat wrong", nfe);

        } // try

        return 0;

    } /* getInt */

    /**
     * Set int
     * 
     * @param key
     *            String
     * @param value
     *            int
     */
    public static void setInt(BFProps key, int value) {
        setProperty(key, String.valueOf(value));

    } /* setInt */

    public static void setInt(BFProps key, int value, boolean store) {
        setProperty(key, String.valueOf(value), store);

    } /* setInt */

    public static boolean getBoolean(BFProps key) {
        return Boolean.parseBoolean(getProperty(key));

    } /* getBoolean */

    /**
     * Set int
     * 
     * @param key
     *            String
     * @param value
     *            int
     */
    public static void setBoolean(BFProps key, boolean value) {
        setProperty(key, String.valueOf(value));

    } /* setBoolean */

    public static void setBoolean(BFProps key, boolean value, boolean store) {
        setProperty(key, String.valueOf(value), store);

    } /* setBoolean */
} // end of class BFProperties
