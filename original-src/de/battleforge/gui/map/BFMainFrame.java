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
package de.battleforge.gui.map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.action.BFAction;
import de.battleforge.gui.AbstractBFMainFrame;
import de.battleforge.gui.FrameInitSplash;
import de.battleforge.gui.StatusBar;
import de.battleforge.gui.dialog.Info;
import de.battleforge.gui.dialog.Property;
import de.battleforge.gui.dialog.admin.editSystem.EditSystem;
import de.battleforge.gui.map.diplomacy.PanelDiplomacy;
import de.battleforge.gui.map.units.PanelManageUnits;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.net.irc.IRCClient;
import de.battleforge.security.PRIVILEGES;
import de.battleforge.security.Security;
import de.battleforge.sound.SoundPlayer;
import de.battleforge.sound.SoundPlayer.SOUNDTYPE;
import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.Tools;
import de.battleforge.util.BFProperties.BFProps;

/**
 * <p>
 * Title: <b>Main </b> <br>
 * Description: <i>The Mainapplication </i> <br>
 * Copyright: Copyright (c) 2004 <br>
 * Company: BattleForge <br>
 * <br>
 * This is the main application of the battleforge client. Here everything is
 * initialized and things are set up for work.
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public class BFMainFrame extends AbstractBFMainFrame implements ActionListener, ActionCallbackListener {

    /**
     * Filemenu entry
     */
    private JMenu menuFile;

    /**
     * Helpmenu entry
     */
    private JMenu menuHelp;

    /**
     * Languagemenu entry
     */
    private JMenu menuLanguage;

    /**
     * 
     */
    private JMenu menuAdmin;

    /**
     * 
     */
    private JMenuItem fileExit;

    /**
     * 
     */
    private JMenuItem fileProperties;

    /**
     * 
     */
    private JMenuItem helpAbout;

    /**
     * 
     */
    private JMenuItem helpContents;

    /**
     * 
     */
    private JMenuItem languageEnglish;

    /**
     * 
     */
    private JMenuItem languageGerman;

    /**
     * 
     */
    private JMenuItem adminEditSystem;

    /**
     * Menuitem to change password
     */
    private JMenuItem adminChangePassword;

    /**
     * The tab pane
     */
    private JTabbedPane tabPane;

    /**
     * The toolbar
     */
    private JToolBar toolBar;

    /**
     * The PanelMap to use
     */
    private PanelMap myPanelMap;

    private JToolBar myToolbar;

    /**
     * The mouse listener
     */
    private MouseListener mMouseListener;

    /**
     * Constructor
     */
    public BFMainFrame() {
    }

    /**
     * get mouse listener
     * 
     * @return
     */
    private MouseListener getMouseListener() {
        if (mMouseListener == null) {
            mMouseListener = new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (e.getSource() instanceof JButton) {
                        JButton button = (JButton) e.getSource();
                        button.setBorderPainted(true);
                        button.setContentAreaFilled(true);
                    } // if
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (e.getSource() instanceof JButton) {
                        JButton button = (JButton) e.getSource();
                        button.setBorderPainted(false);
                        button.setContentAreaFilled(false);
                    } // if
                }
            };
        } // if
        return mMouseListener;
    }

    /**
     * sets the texts for the different gui-elements if the language was
     * changed.
     */
    public final void setTexts() {
        // menu file
        menuFile.setText(Internationalization.getString("Main.Menu_File"));
        fileProperties.setText(Internationalization.getString("Main.Menu_File_Properties"));
        fileExit.setText(Internationalization.getString("Main.Menu_File_Exit"));

        // menu language
        menuLanguage.setText(Internationalization.getString("Main.Menu_Language"));
        languageGerman.setText(Internationalization.getString("Main.Menu_Language_German"));
        languageEnglish.setText(Internationalization.getString("Main.Menu_Language_English"));

        // menu admin
        menuAdmin.setText(Internationalization.getString("Main.Menu_Admin"));
        adminEditSystem.setText(Internationalization.getString("Main.Menu_Admin_EditSystem"));
        adminChangePassword.setText(Internationalization.getString("Main.Menu_Admin_ChangePassword"));

        // menu help
        menuHelp.setText(Internationalization.getString("Main.Menu_Help"));
        helpContents.setText(Internationalization.getString("Main.Menu_Help_Contents"));
        helpAbout.setText(Internationalization.getString("Main.Menu_Help_About"));

        // tabs
        tabPane.setTitleAt(0, Internationalization.getString("Main.RouteTab"));
        tabPane.setTitleAt(1, Internationalization.getString("Main.OrganizeUnitsTab"));
        tabPane.setTitleAt(2, Internationalization.getString("Main.DiplomacyTab"));

        // Frame
        setTitle(Internationalization.getString("Main.Title"));

    }

    /**
     * Edit the selected system
     */
    public void editSystem() {
        EditSystem mySystemEditor = new EditSystem(Tools.getFirstShowingFrame(), myPanelMap.my2DView.getCurrentSystem());
        mySystemEditor.setVisible(true);
        myPanelMap.my2DView.fullRepaint();
    }

    /**
     * @param e
     *            ActionEvent
     */
    public final void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (Internationalization.getString("Main.Menu_Language_German").equals(cmd)) {
            Internationalization.setLocale("de", "DE");
            ActionManager.getAction(ACTIONS.CHANGE_LANGUAGE).execute(null);
        } else if (Internationalization.getString("Main.Menu_Language_English").equals(cmd)) {
            Internationalization.setLocale("en", "EN");
            ActionManager.getAction(ACTIONS.CHANGE_LANGUAGE).execute(null);
        } else if (Internationalization.getString("Main.Menu_File_Exit").equals(cmd)) {
            dispose();
        } else if (Internationalization.getString("Main.Menu_Admin_EditSystem").equals(cmd)) {
            editSystem();
        } else if (Internationalization.getString("Main.Menu_Help_Contents").equals(cmd)) {
            // here help was pressed from the menu
            // check if there is a browser specified
            Tools.startBrowser(this, BFProperties.getProperty(BFProps.DOC_DIR) + "/doc/index.html");
        } else if (Internationalization.getString("Main.Menu_Help_About").equals(cmd)) {
            // here help was pressed from the menu
            Info myInfo = new Info(this);

            myInfo.setVisible(true);

        } else if (Internationalization.getString("Main.Menu_File_Properties").equals(cmd)) {
            Property dialog = new Property(this);

            dialog.setVisible(true);

            if (BFProperties.getBoolean(BFProps.PLAY_MUSIC)) {
                SoundPlayer.play(SOUNDTYPE.MUSIC, "mechw2splash", SoundPlayer.LOOP_CONTINUOUSLY);

            } else {
                SoundPlayer.stop(SOUNDTYPE.MUSIC, "mechw2splash");

            } // if
        }
    }

    /**
     * Generate a navigation button
     * 
     * @param imageName
     *            name of the image to put on the button
     * @param actionCommand
     *            actionCommand to set to the button
     * @param toolTipText
     *            text for a tooltip
     * @param altText
     *            text if there is no image or if the image is not found
     * @return JButton
     */
    private JButton makeToolBarButton(Action action) {
        // Create and initialize the button
        JButton button = new JButton(action);
        button.setText("");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setSize(20, 20);
        button.addMouseListener(getMouseListener());
        return button;
    }

    @Override
    public void start(FrameInitSplash splash) {
        // say hello
        SoundPlayer.play(SOUNDTYPE.VOICE, "0001");

        SoundPlayer.play(SOUNDTYPE.MUSIC, "mechw2splash", SoundPlayer.LOOP_CONTINUOUSLY);

    } // start

    @Override
    public void stop() {
        SoundPlayer.stop(SOUNDTYPE.MUSIC, "mechw2splash");

        SoundPlayer.playSynchron(SOUNDTYPE.VOICE, "0014");

    } // stop

    @Override
    protected void init() {
        // mSelectedSystem = Tools.getSystemByString("Terra");
        JMenuBar menuBar = createMenuBar();
        JPanel topPanel = new JPanel(new BorderLayout());
        tabPane = new JTabbedPane();
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        myPanelMap = new PanelMap();

        // Add the file menu to the menu bar
        menuBar.add(menuFile);
        menuFile.add(fileProperties);
        menuFile.add(fileExit);

        // Add the language menu to the menu bar
        menuBar.add(menuLanguage);
        menuLanguage.add(languageGerman);
        menuLanguage.add(languageEnglish);

        // Add the admin menu to the menu bar
        menuBar.add(menuAdmin);
        adminEditSystem.setEnabled(Security.getPrivilege(PRIVILEGES.CHANGE_SYSTEM_BASISDATA));
        menuAdmin.add(adminEditSystem);
        menuAdmin.add(adminChangePassword);

        // Add the help menu to the menu bar
        menuBar.add(menuHelp);
        menuHelp.add(helpContents);
        menuHelp.add(helpAbout);

        // set the menu bar to our JFrame
        setJMenuBar(menuBar);

        // put the gui together
        tabPane.setTabPlacement(SwingConstants.TOP);
        tabPane.addTab(Internationalization.getString("Main.RouteTab"), myPanelMap);
        tabPane.addTab(Internationalization.getString("Main.OrganizeUnitsTab"),
                new PanelManageUnits(false, DBWrapper.getCurrentGameUser().getOwner()));
        PanelDiplomacy myDiplomacy = new PanelDiplomacy();
        tabPane.addTab(Internationalization.getString("Main.DiplomacyTab"), myDiplomacy);
        IRCClient myIRCClient = new IRCClient();
        tabPane.addTab(Internationalization.getString("Main.ChatTab"), myIRCClient);
        tabPane.setSelectedIndex(0);

        // toolbar
        toolBar.add(new JLabel("Runde: dfjdfhjhjkh Balance: 8784324 Credits"));

        // toolBar.addSeparator( new Dimension( 50, 20 ) );

        toolBar.add(new JSeparator(SwingConstants.VERTICAL));
        myToolbar = new JToolBar();
        myToolbar.setFloatable(false);
        // first button
        // button = makeToolBarButton( "icon_fullView.gif", "test", "dfjd", "A"
        // );
        myToolbar.add(makeToolBarButton(ActionManager.getAction("View Map", "torte", "icon_viewInfos.png", ACTIONS.VIEW_FULL)));

        // Add ActionCallBackListeners
        ActionManager.addActionCallbackListener(ACTIONS.VIEW_FULL, this);
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_LANGUAGE, this);

        myToolbar.setBorder(null);
        myToolbar.setOpaque(false);
        toolBar.add(myToolbar);

        toolBar.setFloatable(false);
        add(toolBar, BorderLayout.NORTH);

        topPanel.add(tabPane, BorderLayout.CENTER);

        // south panel
        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(StatusBar.gibExemplar(), BorderLayout.SOUTH);
        setIconImage(ImageFactory.getImage("app", "icon.png"));
        setTitle(Internationalization.getString("Main.Title"));
        setTexts();

        // ActionManager.getAction( ACTIONS.CHANGE_CURRENT_SYSTEM ).execute(
        // Tools.getSystemByString( "Terra" ) );

        for (BFSystem system : DBWrapper.getAllSystems()) {
            if ("Terra".equals(system.getName())) {
                ActionManager.getAction(ACTIONS.CHANGE_CURRENT_SYSTEM).execute(system);

                break;

            }
        }

    }

    /**
     * Create menu bar
     * 
     * @return
     */
    private JMenuBar createMenuBar() {
        menuFile = new JMenu(Internationalization.getString("Main.Menu_File"));
        menuHelp = new JMenu(Internationalization.getString("Main.Menu_Help"));
        menuAdmin = new JMenu(Internationalization.getString("Main.Menu_Admin"));
        menuLanguage = new JMenu(Internationalization.getString("Main.Menu_Language"));

        JMenuBar menuBar = new JMenuBar();

        fileExit = new JMenuItem(new BFAction("Main.Menu_File_Exit", ACTIONS.EXIT));
        fileProperties = new JMenuItem(Internationalization.getString("Main.Menu_File_Properties"));
        helpAbout = new JMenuItem(Internationalization.getString("Main.Menu_Help_About"));
        helpContents = new JMenuItem(Internationalization.getString("Main.Menu_Help_Contents"));
        languageEnglish = new JMenuItem(Internationalization.getString("Main.Menu_Language_English"));
        languageGerman = new JMenuItem(Internationalization.getString("Main.Menu_Language_German"));
        adminEditSystem = new JMenuItem(Internationalization.getString("Main.Menu_Admin_EditSystem"));
        adminChangePassword = new JMenuItem(Internationalization.getString("Main.Menu_Admin_ChangePassword"));

        // JPanel topPanel = new JPanel( new BorderLayout() );
        tabPane = new JTabbedPane();
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);

        // set actionlistener
        fileProperties.addActionListener(this);
        fileExit.addActionListener(this);
        languageGerman.addActionListener(this);
        languageEnglish.addActionListener(this);
        helpContents.addActionListener(this);
        helpAbout.addActionListener(this);
        adminEditSystem.addActionListener(this);

        return menuBar;
    }

    /**
     * Handle actions
     */
    public boolean handleAction(ACTIONS action, ActionObject o) {
        switch (action) {
        case VIEW_FULL:
            System.out.println("viewFull");
            break;

        case CHANGE_CURRENT_SYSTEM:
            break;

        case CHANGE_LANGUAGE:
            setTexts();
            break;

        case EXIT:
            dispose();
            break;

        default:
            break;
        }
        return true;

    } // handleAction

} // end of class BFMainFrame
