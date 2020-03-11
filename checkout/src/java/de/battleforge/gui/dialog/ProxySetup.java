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

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.BFProperties.BFProps;

/**
 * <p>
 * Title: <b>ProxySetupDialog</b><br>
 * Description: <i>A dialog to enter proxy data</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public class ProxySetup extends AbstractDialog {

    /**
     * Checkbox to save password or not
     */
    private JCheckBox cbStorePassword;

    /**
     * Textfield for editing server
     */
    private JTextField tfProxyServer;

    /**
     * Textfield for editing port
     */
    private JTextField tfProxyPort;

    /**
     * Textfield for editing user
     */
    private JTextField tfProxyUser;

    /**
     * Textfield for editing password
     */
    private JPasswordField tfProxyPassword;

    private JRadioButton mUseSystemProxy;

    private JRadioButton mUseProxy;

    private JCheckBox mProxyNeedAuth;

    /**
     * Constructor
     * 
     * @param parent
     *            Frame
     */
    public ProxySetup(Frame parent) {
        super(parent, "proxySetupDialog.title", OPTIONS.OK_CANCEL);

    } // ProxySetupDialog

    public ProxySetup(Dialog parent) {
        super(parent, "proxySetupDialog.title", OPTIONS.OK_CANCEL);

    } // ProxySetupDialog

    @Override
    protected JButton getOKButton() {
        return new JButton(new SaveAction());

    } // getOKButton

    @Override
    protected String getImageName() {
        return "dialog_proxy.png";

    } // getImageName

    @Override
    protected Container createMainPane() {
        tfProxyPort = new JTextField(BFProperties.getProperty(BFProps.PROXY_PORT), 3);
        tfProxyUser = new JTextField(BFProperties.getProperty(BFProps.PROXY_USER), 5);
        tfProxyServer = new JTextField(BFProperties.getProperty(BFProps.PROXY_SERVER), 8);
        tfProxyPassword = new JPasswordField(BFProperties.getProperty(BFProps.PROXY_PASSWORD), 8);
        cbStorePassword = new JCheckBox(Internationalization.getString("proxySetupDialog.storePasswordLabel"));
        mProxyNeedAuth = new JCheckBox(Internationalization.getString("proxySetupDialog.proxyNeedAuthLabel"));
        mUseSystemProxy = new JRadioButton(Internationalization.getString("proxySetupDialog.useSystemProxyLabel"));
        mUseProxy = new JRadioButton(Internationalization.getString("proxySetupDialog.useProxyLabel"));

        ButtonGroup proxyGroup = new ButtonGroup();

        proxyGroup.add(mUseSystemProxy);
        proxyGroup.add(mUseProxy);

        if (BFProperties.getBoolean(BFProps.USE_SYSTEM_PROXY)) {
            mUseSystemProxy.setSelected(true);

        } else {
            mUseProxy.setSelected(true);

        } // if

        JPanel contentPane = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0, 5, 3, 5);
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 4;

        contentPane.add(mUseSystemProxy, c);

        c.gridy = 1;

        contentPane.add(mUseProxy, c);

        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(5, 30, 3, 5);

        contentPane.add(new JLabel(Internationalization.getString("proxySetupDialog.proxyServerLabel")), c);

        c.gridx = 1;
        c.weightx = .7;
        c.insets = new Insets(5, 5, 3, 5);

        contentPane.add(tfProxyServer, c);

        c.gridx = 2;
        c.weightx = 0;

        contentPane.add(new JLabel(Internationalization.getString("proxySetupDialog.proxyPortLabel")), c);

        c.gridx = 3;
        c.weightx = .3;

        contentPane.add(tfProxyPort, c);

        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.gridwidth = 4;

        contentPane.add(mProxyNeedAuth, c);

        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.gridwidth = 1;
        c.insets = new Insets(5, 30, 3, 5);

        contentPane.add(new JLabel(Internationalization.getString("proxySetupDialog.proxyNameLabel")), c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridwidth = 3;
        c.insets = new Insets(5, 5, 3, 5);

        contentPane.add(tfProxyUser, c);

        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 30, 3, 5);

        contentPane.add(new JLabel(Internationalization.getString("proxySetupDialog.proxyPasswordLabel")), c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridwidth = 3;
        c.insets = new Insets(5, 5, 3, 5);

        contentPane.add(tfProxyPassword, c);

        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 1.0;
        c.gridwidth = 4;
        c.insets = new Insets(5, 30, 3, 5);

        contentPane.add(cbStorePassword, c);

        JLabel lWarning = new JLabel(Internationalization.getString("message.warning.savePassword"));

        Dimension preferredSize = contentPane.getPreferredSize();

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 7;
        c.weighty = 1.0;

        contentPane.add(lWarning, c);

        Dimension warningLabelPreferredSize = lWarning.getPreferredSize();

        warningLabelPreferredSize.width = preferredSize.width;

        lWarning.setPreferredSize(warningLabelPreferredSize);

        // setMinimumSize( new Dimension( 400, 350 ) );
        // setPreferredSize( getMinimumSize() );

        return contentPane;

    } // createMainPane

    private class SaveAction extends AbstractAction {

        public SaveAction() {
            super(Internationalization.getString("proxySetupDialog.saveButton"));

        } // SaveAction

        public void actionPerformed(ActionEvent e) {
            BFProperties.setProperty(BFProps.PROXY_SERVER, tfProxyServer.getText(), true);
            BFProperties.setProperty(BFProps.PROXY_PORT, tfProxyPort.getText(), true);
            BFProperties.setProperty(BFProps.PROXY_USER, tfProxyUser.getText(), true);
            BFProperties.setProperty(BFProps.PROXY_PASSWORD, new String(tfProxyPassword.getPassword()), cbStorePassword.isSelected());
            BFProperties.setBoolean(BFProps.USE_SYSTEM_PROXY, mUseSystemProxy.isSelected(), true);

            dispose();

        } // actionPerformed
    } // end of inner-class SaveAction
} // end of class ProxySetupDialog
