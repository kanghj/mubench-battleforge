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
package de.battleforge.net;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

import de.battleforge.gui.StatusBar;
import de.battleforge.gui.dialog.ProxySetup;
import de.battleforge.util.BFException;
import de.battleforge.util.BFProperties;
import de.battleforge.util.BFProperties.BFProps;

/**
 * <p>
 * Title: <b>HTTPReader</b><br>
 * Description: <i>Provides a connection to the net and handles it</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class HTTPReader {

    private URL host;

    /**
     * default constructor
     */
    public HTTPReader(final Frame parent) {
        if (BFProperties.getBoolean(BFProps.USE_PROXY)) {
            Authenticator.setDefault(new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String user = BFProperties.getProperty(BFProps.PROXY_USER);
                    String pwd = BFProperties.getProperty(BFProps.PROXY_PASSWORD);

                    if ((user == null) || (user.length() == 0) || (pwd == null) || (pwd.length() == 0)) {
                        ProxySetup mySetup = new ProxySetup(parent);

                        mySetup.setVisible(true);

                    } // if

                    user = BFProperties.getProperty(BFProps.PROXY_USER);
                    pwd = BFProperties.getProperty(BFProps.PROXY_PASSWORD);

                    return new PasswordAuthentication(user, pwd.toCharArray());

                }
            });
        }
    }

    /**
     * set the hostadress of the http-server
     * 
     * @param hostaddress
     *            adress
     * @throws BFException
     * @throws MalformedURLException
     *             exception
     */
    public final void setHost(String hostaddress) throws BFException {
        try {
            host = new URL(hostaddress);

        } catch (MalformedURLException e) {
            throw new BFException(10000, e);

        } // try
    }

    /**
     * get
     * 
     * @return String
     * @throws Exception
     *             Handtuch
     */
    public final String get() throws BFException {
        StatusBar.gibExemplar().setStatus("Connecting...");
        StatusBar.gibExemplar().setProgressIndeterminate(true);

        HttpURLConnection connection = null;

        StringBuffer pageContent = null;

        try {
            connection = (HttpURLConnection) host.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false); // get info fresh from server
            connection.setRequestProperty("Content-type", "text/plain");

            // connect to Server
            connection.connect();

            checkResponseCode(connection);

            // load data and show progress
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            int lineLength;
            int allLength;
            int progress;

            allLength = connection.getContentLength();
            progress = 0;
            pageContent = new StringBuffer(allLength < 0 ? 0 : allLength);

            StatusBar.gibExemplar().setProgressMin(0);
            StatusBar.gibExemplar().setProgressMax(allLength);
            StatusBar.gibExemplar().setStatus("Getting...");
            StatusBar.gibExemplar().setProgressIndeterminate(false);

            while ((line = reader.readLine()) != null) {
                lineLength = line.length();
                progress = progress + lineLength;
                pageContent.append(line).append("\n");

                StatusBar.gibExemplar().setProgressValue(progress);

            } // while

            reader.close();

            StatusBar.gibExemplar().setProgressValue(allLength);
            StatusBar.gibExemplar().setStatus("");
            StatusBar.gibExemplar().setProgressValue(0);

            checkResponseCode(connection);

        } catch (IOException e) {
            throw new BFException(10000, e);

        } finally {
            if (connection != null) {
                connection.disconnect();

            } // if

            StatusBar.gibExemplar().setProgressIndeterminate(false);
            StatusBar.gibExemplar().setStatus("");
            StatusBar.gibExemplar().setProgressValue(0);

        } // try

        return pageContent == null ? "" : pageContent.toString();

    }

    private void checkResponseCode(HttpURLConnection connection) throws BFException, IOException {
        if (connection.getResponseCode() / 100 != 2) {
            throw new BFException(60000 + connection.getResponseCode());

        } // if
    }
}
