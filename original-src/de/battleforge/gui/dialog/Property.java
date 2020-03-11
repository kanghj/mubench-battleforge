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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.BFProperties.BFProps;

public class Property extends AbstractDialog {

    private JTextField mTextFieldPHPHost;

    private JPasswordField mPasswordField;

    private JTextField mTextFieldNickName;

    private JTextField mTextFieldChannel;

    private JTextField mTextFieldPort;

    private JTextField mTextFieldServer;

    private JTextField mTextFieldBrowser;

    private JComboBox mLanguage;

    private JComboBox mCountry;

    private JCheckBox mUseProxy;

    private JCheckBox mPlayMusic;

    private JCheckBox mPlayVoice;

    private JCheckBox mShowSplash;

    private JButton mShowProxySettings;

    public Property(Dialog parent) {
        super(parent, "propertyDialog.title", OPTIONS.USERDEFINED);

    } // PropertyDialog

    public Property(Frame parent) {
        super(parent, "propertyDialog.title", OPTIONS.USERDEFINED);

    } // PropertyDialog

    @Override
    protected String getImageName() {
        return "dialog_property.png"; //$NON-NLS-1$

    } // getImageName

    @Override
    protected Container createMainPane() {
        initComponents();

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(getDatabasePanel());
        panel.add(getSystemPanel());
        panel.add(getIRCPanel());
        panel.add(new JPanel());

        getValuesFromProperties();

        return panel;

    } // createMainPane

    private void initComponents() {
        mTextFieldBrowser = new JTextField();
        mTextFieldServer = new JTextField();
        mTextFieldPort = new JTextField();
        mTextFieldChannel = new JTextField();
        mTextFieldNickName = new JTextField();
        mPasswordField = new JPasswordField();
        mTextFieldPHPHost = new JTextField();

        mUseProxy = new JCheckBox(Internationalization.getString("propertyDialog.system.checkbox.useProxy"));
        mPlayMusic = new JCheckBox(Internationalization.getString("propertyDialog.system.checkbox.playMusic"));
        mPlayVoice = new JCheckBox(Internationalization.getString("propertyDialog.system.checkbox.playVoice"));
        mShowSplash = new JCheckBox(Internationalization.getString("propertyDialog.system.checkbox.showSplash"));
        mShowProxySettings = new JButton(new ShowProxySettingsAction());

        mShowProxySettings.setEnabled(mUseProxy.isSelected());

        mUseProxy.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                mShowProxySettings.setEnabled(mUseProxy.isSelected());

            }
        });
    } // initComponents

    @Override
    protected int getDefaultButtonIndex() {
        return 1;

    }

    @Override
    protected JButton[] getButtons() {
        return new JButton[] { new JButton(new ResetToDefaultAction()), new JButton(new SaveAction()), getCancelButton() };

    }

    private JPanel getSystemPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(getTitledBorder(Internationalization.getString("propertyDialog.section.system")));

        GridBagConstraints gbc = createDefaultConstrains();

        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.system.label.country")), gbc); //$NON-NLS-1$

        gbc.gridy = 1;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.system.label.language")), gbc); //$NON-NLS-1$

        gbc.gridx = 1;
        gbc.gridy = 0;

        panel.add(getComboBoxCountry(), gbc);

        gbc.gridy = 1;

        panel.add(getComboBoxLanguage(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(mPlayMusic, gbc);

        gbc.gridx = 1;

        panel.add(mPlayVoice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        panel.add(mShowSplash, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;

        panel.add(mUseProxy, gbc);

        gbc.gridx = 1;

        panel.add(mShowProxySettings, gbc);

        return panel;

    }

    private Component getComboBoxLanguage() {
        if (mLanguage == null) {
            mLanguage = new JComboBox(new Locale[] { Locale.GERMAN, Locale.ENGLISH });

            mLanguage.setSelectedItem(Locale.getDefault());

            mLanguage.setRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Locale locale = (Locale) value;

                    String text = locale.getDisplayLanguage(Internationalization.getLocale());

                    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);

                }
            });

        } // if

        return mLanguage;

    }

    private Component getComboBoxCountry() {
        if (mCountry == null) {
            mCountry = new JComboBox(new Locale[] { Locale.GERMANY, Locale.US, Locale.UK });

            mCountry.setSelectedItem(Locale.getDefault());

            mCountry.setRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Locale locale = (Locale) value;

                    String text = locale.getDisplayCountry(Internationalization.getLocale());

                    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);

                }
            });

        } // if

        return mCountry;
    }

    private JPanel getIRCPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(getTitledBorder(Internationalization.getString("propertyDialog.section.irc")));

        GridBagConstraints gbc = createDefaultConstrains();

        gbc.fill = GridBagConstraints.VERTICAL;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.irc.label.server")), gbc); //$NON-NLS-1$

        gbc.gridx = 2;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.irc.label.port")), gbc); //$NON-NLS-1$

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.irc.label.nickname")), gbc); //$NON-NLS-1$

        gbc.gridx = 2;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.irc.label.channel")), gbc); //$NON-NLS-1$

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.irc.label.password")), gbc); //$NON-NLS-1$

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = .7;
        gbc.gridx = 1;
        gbc.gridy = 0;

        panel.add(mTextFieldServer, gbc);

        gbc.weightx = .3;
        gbc.gridx = 3;

        panel.add(mTextFieldPort, gbc);

        gbc.weightx = .7;
        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(mTextFieldNickName, gbc);

        gbc.weightx = .3;
        gbc.gridx = 3;

        panel.add(mTextFieldChannel, gbc);

        gbc.weightx = .7;
        gbc.gridx = 1;
        gbc.gridy = 3;

        panel.add(mPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;

        panel.add(new JLabel(Internationalization.getString("message.warning.savePassword")), gbc);

        return panel;

    }

    private GridBagConstraints createDefaultConstrains() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.weightx = 0;
        gbc.weighty = 0;

        return gbc;

    } // createDefaultConstrains

    private JPanel getDatabasePanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(getTitledBorder(Internationalization.getString("propertyDialog.section.connection")));  //$NON-NLS-1$

        GridBagConstraints gbc = createDefaultConstrains();

        gbc.fill = GridBagConstraints.VERTICAL;

        panel.add(new JLabel(Internationalization.getString("propertyDialog.section.connection.label.URL")), gbc); //$NON-NLS-1$

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;

        panel.add(mTextFieldPHPHost, gbc);

        return panel;

    }

    private Border getTitledBorder(String title) {
        return BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title);

    }

    private void getValuesFromProperties() {
        mTextFieldBrowser.setText(BFProperties.getProperty(BFProps.BROWSER));
        mTextFieldServer.setText(BFProperties.getProperty(BFProps.IRC_SERVER));
        mTextFieldPort.setText(BFProperties.getProperty(BFProps.IRC_PORT));
        mTextFieldChannel.setText(BFProperties.getProperty(BFProps.IRC_CHANNEL));
        mTextFieldNickName.setText(BFProperties.getProperty(BFProps.IRC_NICKNAME));
        mPasswordField.setText(BFProperties.getProperty(BFProps.IRC_PASSWORD));
        mTextFieldPHPHost.setText(BFProperties.getProperty(BFProps.PHP_CONNECTION));

        mUseProxy.setSelected(BFProperties.getBoolean(BFProps.USE_PROXY));
        mPlayMusic.setSelected(BFProperties.getBoolean(BFProps.PLAY_MUSIC));
        mPlayVoice.setSelected(BFProperties.getBoolean(BFProps.PLAY_VOICE));
        mShowSplash.setSelected(BFProperties.getBoolean(BFProps.SHOW_SPLASHSCREEN));

        mLanguage.setSelectedItem(new Locale(BFProperties.getProperty(BFProps.LANGUAGE)));
        mCountry.setSelectedItem(new Locale(BFProperties.getProperty(BFProps.LANGUAGE), BFProperties.getProperty(BFProps.COUNTRY)));

    }

    private void saveProperties() {
        BFProperties.setProperty(BFProps.BROWSER, mTextFieldBrowser.getText(), true);
        BFProperties.setProperty(BFProps.IRC_SERVER, mTextFieldServer.getText(), true);
        BFProperties.setProperty(BFProps.IRC_PORT, mTextFieldPort.getText(), true);
        BFProperties.setProperty(BFProps.IRC_CHANNEL, mTextFieldChannel.getText(), true);
        BFProperties.setProperty(BFProps.IRC_NICKNAME, mTextFieldNickName.getText(), true);
        BFProperties.setProperty(BFProps.IRC_PASSWORD, new String(mPasswordField.getPassword()), true);
        BFProperties.setProperty(BFProps.PHP_CONNECTION, mTextFieldPHPHost.getText());

        BFProperties.setBoolean(BFProps.USE_PROXY, mUseProxy.isSelected(), true);
        BFProperties.setBoolean(BFProps.PLAY_MUSIC, mPlayMusic.isSelected(), true);
        BFProperties.setBoolean(BFProps.PLAY_VOICE, mPlayVoice.isSelected(), true);
        BFProperties.setBoolean(BFProps.SHOW_SPLASHSCREEN, mShowSplash.isSelected(), true);

        BFProperties.setProperty(BFProps.LANGUAGE, ((Locale) mLanguage.getSelectedItem()).getLanguage(), true);
        BFProperties.setProperty(BFProps.COUNTRY, ((Locale) mCountry.getSelectedItem()).getCountry(), true);

    } // saveProperties

    private class ShowProxySettingsAction extends AbstractAction {

        public ShowProxySettingsAction() {
            super(Internationalization.getString("propertyDialog.system.button.showProxySettings"));

        } // ShowProxySettingsAction

        public void actionPerformed(ActionEvent e) {
            ProxySetup dialog = new ProxySetup(Property.this);

            dialog.setVisible(true);

        } // actionPerformed
    } // ShowProxySettingsAction

    private class ResetToDefaultAction extends AbstractAction {

        public ResetToDefaultAction() {
            super(Internationalization.getString("propertyDialog.button.resetToDefault")); //$NON-NLS-1$

        } // ResetToDefaultAction

        public void actionPerformed(ActionEvent e) {
            try {
                BFProperties.resetUserProperties();

            } catch (IOException e1) {
                Error.showDialog(Property.this, e1, false);

            } // try

            getValuesFromProperties();

        } // actionPerformed
    } // end of inner-class SaveAction

    private class SaveAction extends AbstractAction {

        public SaveAction() {
            super(Internationalization.getString("propertyDialog.button.save")); //$NON-NLS-1$

        } // SaveAction

        public void actionPerformed(ActionEvent e) {
            saveProperties();

            dispose();

        } // actionPerformed
    } // end of inner-class SaveAction
} // end of class PropertyDialog
