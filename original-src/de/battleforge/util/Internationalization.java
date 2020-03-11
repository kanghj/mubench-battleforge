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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: <b>Internationalization</b><br>
 * Description: <i>Get internationalized strings for BattleForge-Map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public abstract class Internationalization {

    /**
     * Logger
     */
    private static Logger sLogger = Logger.getLogger(Internationalization.class);

    /**
     * The bundle with the normal messages.
     */
    private static ResourceBundle mMessages;

    /**
     * The bundle that maps error-numbers to messages.
     */
    private static ResourceBundle mErrors;

    /**
     * The bundle with the messages used in the log-file.
     */
    private static ResourceBundle mLogMessages;

    static {
        mMessages = ResourceBundle.getBundle("MessagesBundle");
        mErrors = ResourceBundle.getBundle("ErrorBundle");
        mLogMessages = ResourceBundle.getBundle("LogMessagesBundle");

    } // static

    /**
     * Sets the locale.
     * 
     * @param language
     *            defining the language
     * @param country
     *            defining the country
     */
    public static void setLocale(String language, String country) {
        mMessages = ResourceBundle.getBundle("MessagesBundle", new Locale(language, country));
        mErrors = ResourceBundle.getBundle("ErrorBundle", new Locale(language, country));
        mLogMessages = ResourceBundle.getBundle("LogMessagesBundle", new Locale(language, country));

    } /* setLocale */

    /**
     * Sets the locale.
     * 
     * @param locale
     *            Locale
     */
    public static void setLocale(Locale locale) {
        mMessages = ResourceBundle.getBundle("MessagesBundle", locale);
        mErrors = ResourceBundle.getBundle("ErrorBundle", locale);
        mLogMessages = ResourceBundle.getBundle("LogMessagesBundle", locale);

    } /* setLocale */

    /**
     * Returns the current locale.
     * 
     * @return Locale
     */
    public static Locale getLocale() {
        return mMessages.getLocale();

    } /* getLocale */

    /**
     * Returns the current country.
     * 
     * @return String
     */
    public static String getCountry() {
        return mMessages.getLocale().getCountry();

    } /* getCountry */

    /**
     * Returns the current language.
     * 
     * @return String
     */
    public static String getLanguage() {
        return mMessages.getLocale().getLanguage();

    } /* getLanguage */

    /**
     * Returns the associated message.
     * 
     * @param key
     *            String
     * @return String
     */
    public static String getString(String key) {
        return getStringSaveFromBundle(key, mMessages);

    } /* getString */

    public static String getErrorDescription(int messageID) {
        return getStringSaveFromBundle(String.valueOf(messageID) + ".description", mErrors);

    } // getErrorDescription

    public static String getErrorMessage(int messageID) {
        return getStringSaveFromBundle(String.valueOf(messageID) + ".message", mErrors);

    } // getErrorMessage

    public static String getErrorSolution(int messageID) {
        return getStringSaveFromBundle(String.valueOf(messageID) + ".solution", mErrors);

    } // getErrorSolution

    public static String getStringForLog(String key) {
        return getStringSaveFromBundle(key, mLogMessages);

    } // getStringForLog

    private static String getStringSaveFromBundle(String key, ResourceBundle bundle) {
        try {
            if (key == null) {
                return null;
            }
            return bundle.getString(key);

        } catch (MissingResourceException mre) {
            sLogger.debug("resource <" + key + "> can't be found in bundle: " + bundle);

        } // try

        return key;

    } // getStringSaveFromBundle
} // end of class Internationalization
