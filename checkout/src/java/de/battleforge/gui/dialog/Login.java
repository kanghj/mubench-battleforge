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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.jdo.Query;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.battleforge.BFLauncher;
import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.action.BFAction;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFGame;
import de.battleforge.jdo.BFUser;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.net.HTTPReader;
import de.battleforge.util.BFException;
import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.JCrypt;
import de.battleforge.util.BFProperties.BFProps;

/**
 * @author Werner
 * @author kotzbrocken2
 */
public class Login extends AbstractDialog {

    /**
     * Textfield user
     */
    private JTextField tfUser;

    /**
     * Passwordfield password
     */
    private JPasswordField tfPassword;

    private JComboBox cbGames;

    private boolean isCanceled;

    /**
     * Constructor
     * 
     * @param parent
     *            JFrame
     * @param pUser
     *            BF_User
     */
    public Login(JFrame parent) {
        super(parent, "loginDialog.title", OPTIONS.USERDEFINED);

        // fill login data if stored
        tfUser.setText(BFProperties.getProperty(BFProps.LOGIN_USER));
        tfPassword.setText(BFProperties.getProperty(BFProps.LOGIN_PASSWORD));
        
        ActionManager.addActionCallbackListener(ACTIONS.CANCEL, this);

    }

    @Override
    protected String getImageName() {
        return "login_logo.png";

    }

    @Override
    protected Container createMainPane() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.BOTH;

        final JLabel lblUser = new JLabel(Internationalization.getString("loginDialog.user"));
        final JLabel lblPwd = new JLabel(Internationalization.getString("loginDialog.password"));
        final JLabel lblGames = new JLabel(Internationalization.getString("loginDialog.game"));
        final JLabel lblLng = new JLabel(Internationalization.getString("loginDialog.language"));

        gbc.insets = new Insets(0, 0, 5, 7);
        panel.add(lblUser, gbc);

        gbc.gridy = 1;
        panel.add(lblPwd, gbc);

        gbc.gridy = 2;
        panel.add(lblGames, gbc);

        gbc.insets = new Insets(0, 0, 0, 7);
        gbc.gridy = 3;
        panel.add(lblLng, gbc);

        tfUser = new JTextField();
        tfPassword = new JPasswordField();

        cbGames = new JComboBox();
        final JComboBox cbLanguage = new JComboBox();

        cbGames.setEnabled(false);

        cbLanguage.addItem("DE");
        cbLanguage.addItem("EN");

        cbLanguage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cbLanguage.getSelectedItem().equals("DE")) {
                    Internationalization.setLocale("de", "DE");

                } else {
                    Internationalization.setLocale("en", "EN");

                }

                BFProperties.setProperty(BFProps.LANGUAGE, Internationalization.getLanguage());
                BFProperties.setProperty(BFProps.COUNTRY, Internationalization.getCountry());

                setTitle(Internationalization.getString("loginDialog.title"));

                lblUser.setText(Internationalization.getString("loginDialog.user"));
                lblPwd.setText(Internationalization.getString("loginDialog.password"));
                lblGames.setText(Internationalization.getString("loginDialog.game"));
                lblLng.setText(Internationalization.getString("loginDialog.language"));

            }

        });

        cbLanguage.setSelectedItem(BFProperties.getProperty(BFProps.COUNTRY));
        Internationalization.setLocale(BFProperties.getProperty(BFProps.LANGUAGE), BFProperties.getProperty(BFProps.COUNTRY));

        gbc.insets = new Insets(0, 7, 5, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(tfUser, gbc);

        gbc.gridy = 1;
        panel.add(tfPassword, gbc);

        gbc.gridy = 2;
        panel.add(cbGames, gbc);

        gbc.insets = new Insets(0, 7, 0, 0);
        gbc.gridy = 3;
        panel.add(cbLanguage, gbc);

        Dimension prefSize = cbGames.getPreferredSize();

        prefSize.height = tfPassword.getPreferredSize().height;

        cbGames.setPreferredSize(prefSize);

        prefSize = cbLanguage.getPreferredSize();

        prefSize.height = tfPassword.getPreferredSize().height;

        cbLanguage.setPreferredSize(prefSize);

        return panel;

    }

    @Override
    protected JButton[] getButtons() {
        return new JButton[] { new JButton(new LoginAction()), new JButton(new PropertiesAction()), getCancelButton() };

    }

    @Override
    protected int getDefaultButtonIndex() {
        return 0;

    }

    @Override
    protected JLabel createImageLabel() {
        JLabel lbl = super.createImageLabel();

        lbl.setLayout(new BorderLayout());

        lbl.add(new JLabel(ImageFactory.getIcon("logo.gif")));

        return lbl;

    }
    
    public boolean isCanceled() {
        return isCanceled;

    }
    
    @Override
    public boolean handleAction(ACTIONS action, ActionObject o) {
        switch (action) {
        case CANCEL:
            isCanceled = true;

        } // switch

        return super.handleAction(action, o);
        
    }
    

    private class LoginAction extends AbstractAction {

        private boolean mLoginAction;

        private LoginAction() {
            super(Internationalization.getString("loginDialog.login"));

            mLoginAction = true;

        }

        public void actionPerformed(ActionEvent evt) {
            if (mLoginAction) {
                AsynchronousRunnable run = new AsynchronousRunnable(Login.this) {
                    @Override
                    protected void execute() {

                        new HTTPReader(null);

                        BFUser user = DBWrapper.doLogin(tfUser.getText(), JCrypt.crypt("Battleforge2000", new String(tfPassword.getPassword())));

                        Collection<BFGame> games = user.getGames();
                        
                        if ( games.size() == 1 ) {
                            // TODO: setCurrentGame
                            //games.iterator().next();
                            dispose();

                        } else {
                            cbGames.setModel(new DefaultComboBoxModel(games.toArray()));
                            cbGames.setEnabled(true);
                            
                            putValue(NAME, Internationalization.getString("loginDialog.start"));

                        } // if
                    }
                };

                executeAsync(run);

            } else {
                dispose();

            } // if
        }
    }

    private class PropertiesAction extends AbstractAction {

        private PropertiesAction() {
            super(Internationalization.getString("loginDialog.properties"));

        }

        public void actionPerformed(ActionEvent evt) {
            Property dialog = new Property(Login.this);

            dialog.setVisible(true);

        }
    }
}
