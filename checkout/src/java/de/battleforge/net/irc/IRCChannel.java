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
package de.battleforge.net.irc;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;
import org.schwering.irc.lib.IRCUser;

import de.battleforge.BFLauncher;
import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFOwner;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.sound.SoundPlayer;
import de.battleforge.sound.SoundPlayer.SOUNDTYPE;
import de.battleforge.util.Internationalization;
import de.battleforge.util.Reminder;

/**
 * <p>
 * Title: <b>IRCChannel</b><br>
 * Description: <i>A single IRC channel</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class IRCChannel extends JPanel implements ActionListener, ActionCallbackListener {

    // Number values
    private int beginningOpers = 0;

    private int beginningVoiced = 0;

    private int beginningNormal = 0;

    private int mCount = 0;

    // Labels
    private JLabel mInputLabel = new JLabel("Enter Message:");

    // Panels
    private JPanel mFunctionButtonPanel = new JPanel(new GridLayout(2, 0));

    private JPanel mSoundButtonPanel = new JPanel();

    private JPanel mMessageEnterPanel = new JPanel();

    // Buttons
    private JButton mMeButton = new JButton("/me");

    private JButton mPartButton = new JButton("/part");

    private JButton mJoinButton = new JButton("/join");

    private JButton mNickButton = new JButton("/nick");

    private JButton mGunfireButton = new JButton(ImageFactory.getIcon("mashinegun.png"));

    private JButton mKnifeButton = new JButton(ImageFactory.getIcon("knife.png"));

    private JButton mSendTextButton = new JButton();

    private JButton mSaveLogButton = new JButton();

    // Colors
    private Color PURPLE = new Color(156, 0, 156);

    // Others
    private IRCScrollPaneBackground mTextScrollPane = null;

    private DefaultListModel mNickModel = new DefaultListModel();

    private JTextField mInputTextField = new JTextField();

    private JTextPane mTextPane = new JTextPane();

    private IRCClient mIRCClient = null;

    private String mChannelName = "";

    private JList mNickList = new JList();

    private Font mFont = new Font("SansSerif", Font.PLAIN, 12);

    private static Logger sLogger = Logger.getLogger(BFLauncher.class);

    /**
     * Write channel log to html file (dump)
     */
    private void writeLog() {
        // TODO: Save the document to a file
    }

    /**
     * Add a user to the userlist (onJoin)
     * 
     * @param nick
     *            User to be added
     */
    public final void addUserToList(IRCUser u) {

        // TODO: fill those users into an hashmap to do some other things!
        String nick = u.getNick();

        char c = nick.charAt(0);
        if (c == '@') {
            mNickModel.add(beginningOpers, nick);
            beginningVoiced++;
            beginningNormal++;
        } else if (c == '+') {
            mNickModel.add(beginningVoiced, nick);
            beginningNormal++;
        } else {
            mNickModel.add(beginningNormal, nick);
        }
        mCount++;
    }

    /**
     * Remove a user from Userlist
     * 
     * @param nick
     *            Username
     * @return <code>true</code> if the element could be removed.
     */
    public final boolean removeUserFromList(String nick) {
        if (mNickModel.contains(nick)) {
            mNickModel.removeElement(nick);

            char c = nick.charAt(0);
            switch (c) {
            case '@':
                beginningVoiced--;
            case '+':
                beginningNormal--;
            default:
                mCount--;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes all nicknames from the userlist. In addition it resets the
     * class-vars for the indexes for opers, voiced- and normal users in the
     * nicklist and all users in the channel.
     */
    public final void removeAllNicks() {
        mNickList.removeAll();
        mNickModel.clear();
        beginningOpers = 0;
        beginningVoiced = 0;
        beginningNormal = 0;
        mCount = 0;
    }

    /**
     * Orders the nicklist by removing all nicks and then adding them again in
     * right order.
     */
    public final void orderList() {
        int countOfFullList = mNickModel.getSize();

        Object[] opers = new Object[beginningVoiced];
        Object[] voiced = new Object[beginningNormal - beginningVoiced];
        Object[] normal = new Object[countOfFullList - beginningNormal];

        Object[] all = mNickModel.toArray();

        System.arraycopy(all, 0, opers, 0, opers.length);
        System.arraycopy(all, beginningVoiced, voiced, 0, voiced.length);
        System.arraycopy(all, beginningNormal, normal, 0, normal.length);

        Arrays.sort(opers);
        Arrays.sort(voiced);
        Arrays.sort(normal);

        mNickModel.ensureCapacity(countOfFullList);
        int i = 0;
        for (int j = 0; i < beginningVoiced; i++, j++) {
            mNickModel.set(i, opers[j]);
        }
        for (int j = 0; i < beginningNormal; i++, j++) {
            mNickModel.set(i, voiced[j]);
        }
        for (int j = 0; i < countOfFullList; i++, j++) {
            mNickModel.set(i, normal[j]);
        }
    }

    /**
     * Constructor
     * 
     * @param client
     *            An IRC-Client to link the channel to
     * @param name
     *            Name of the channel
     */
    public IRCChannel(IRCClient client, String name) {

        // Set variables
        mIRCClient = client;
        mChannelName = name;

        ActionManager.addActionCallbackListener(ACTIONS.REENABLE_SOUNDBUTTONS, this);

        mSendTextButton.setActionCommand("send");
        mSendTextButton.addActionListener(this);
        mSendTextButton.setText("Send");

        Action sendText = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                mSendTextButton.doClick();
            }
        };
        mInputTextField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "sendText");
        mInputTextField.getActionMap().put("sendText", sendText);

        mSaveLogButton.setActionCommand("copyChatLog");
        mSaveLogButton.addActionListener(this);
        mSaveLogButton.setText("Save Log to File");

        mNickList.setModel(mNickModel);
        JScrollPane memberScroll = new JScrollPane(mNickList);
        memberScroll.setMinimumSize(new Dimension(200, 200));
        memberScroll.setPreferredSize(new Dimension(200, 200));

        mJoinButton.setText("/join #");
        mJoinButton.setActionCommand("sendJoin");
        mJoinButton.addActionListener(this);
        mJoinButton.setMnemonic('J');
        mFunctionButtonPanel.add(mJoinButton);

        mMeButton.setActionCommand("sendAction");
        mMeButton.addActionListener(this);
        mMeButton.setMnemonic('M');
        mFunctionButtonPanel.add(mMeButton);

        mNickButton.setActionCommand("sendNick");
        mNickButton.addActionListener(this);
        mNickButton.setMnemonic('N');
        mFunctionButtonPanel.add(mNickButton);

        mPartButton.setText("/part ");
        mPartButton.setActionCommand("sendPart");
        mPartButton.addActionListener(this);
        mPartButton.setMnemonic('P');
        mFunctionButtonPanel.add(mPartButton);

        mGunfireButton.setActionCommand("sendGunfire");
        mGunfireButton.addActionListener(this);
        mGunfireButton.setMnemonic('G');
        mSoundButtonPanel.setLayout(new BoxLayout(mSoundButtonPanel, BoxLayout.PAGE_AXIS));
        mSoundButtonPanel.add(mGunfireButton);
        mSoundButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        mKnifeButton.setActionCommand("sendKnife");
        mKnifeButton.addActionListener(this);
        mKnifeButton.setMnemonic('K');
        mSoundButtonPanel.add(mKnifeButton);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();

        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;
        c2.insets = new Insets(0, 5, 0, 0);
        c2.gridx = 0;
        c2.gridy = 0;
        c2.weightx = 0.0;
        c2.weighty = 1.0;
        rightPanel.add(memberScroll, c2);

        c2.insets = new Insets(0, 5, 0, 0);
        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 1.0;
        c2.weighty = 0.0;
        rightPanel.add(mFunctionButtonPanel, c2);

        c2.insets = new Insets(10, 0, 10, 0);
        c2.gridx = 0;
        c2.gridy = 2;
        c2.weightx = 0.0;
        c2.weighty = 0.0;
        Image ownerImage;
        ownerImage = ImageFactory.getImage("ownerlogo", DBWrapper.getCurrentGameUser().getOwner().getLogo());
        PanelImageDisplay logo = new PanelImageDisplay(ownerImage, borders.NO, 1, true, 120, 120);
        rightPanel.add(logo, c2);

        // Create JTextPane for output
        mTextPane = new JTextPane();
        mTextPane.setEditable(false);
        mTextPane.setFont(mFont);
        mTextPane.setForeground(Color.blue);

        // textScroll = new IRCScrollPaneBackground(channelContent);
        mTextScrollPane = new IRCScrollPaneBackground(mTextPane);
        mTextScrollPane.setTileMode(false);
        mTextScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mTextScrollPane.setWheelScrollingEnabled(true);
        mTextScrollPane.setAutoscrolls(true);
        mTextScrollPane.getViewport().setAutoscrolls(true);

        mMessageEnterPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.weightx = 0.0;
        mMessageEnterPanel.add(mInputLabel, c);

        c.gridx = 1;
        c.weightx = 1.0;
        mMessageEnterPanel.add(mInputTextField, c);

        c.gridx = 2;
        c.weightx = 0.0;
        mMessageEnterPanel.add(mSendTextButton, c);

        c.gridx = 3;
        c.weightx = 0.0;
        mMessageEnterPanel.add(mSaveLogButton, c);

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();

        c1.fill = GridBagConstraints.BOTH;
        c1.gridx = 1;
        c1.weightx = 1.0;
        c1.weighty = 1.0;
        p.add(mTextScrollPane, c1);

        c1.gridx = 2;
        c1.weightx = 0.0;
        p.add(rightPanel, c1);

        this.setLayout(new BorderLayout());
        this.add(mSoundButtonPanel, BorderLayout.WEST);
        this.add(p, BorderLayout.CENTER);
        this.add(mMessageEnterPanel, BorderLayout.SOUTH);
    }

    /**
     * Get the name of the channel
     * 
     * @return String myName
     */
    public final String getChannelName() {
        return mChannelName;
    }

    /**
     * A shorthand for printing
     * 
     * @param target
     *            channel to print to
     * @param nick
     *            Nickname of the writer
     * @param message
     *            Written Text
     */
    public final void print(String target, String nick, String message) {

        // Find out the short form of the owner name for the logged in user
        String ownerShort = "";
        if ((nick.indexOf("[") != -1) && (nick.indexOf("]-") != -1)) {
            ownerShort = nick.substring(1, nick.indexOf("]"));
            nick = nick.substring(nick.indexOf("]") + 2);
        }

        // Find out the owner image of the user
        Icon userImage = null;
        // Iterator iter = Universe.ownerList.getArrayList().iterator();
        Iterator iter = DBWrapper.getOwner().iterator();
        while (iter.hasNext()) {
            BFOwner o = (BFOwner) iter.next();
            if (o.getShortName() != null) {
                if (o.getShortName().equals(ownerShort)) {
                    userImage = ImageFactory.getIcon("ownerlogo", o.getLogo());
                }
            }
        }

        boolean serverMessage = false;
        boolean actionMessage = false;

        // Find tokens and format output accordingly
        if ((target == "") || (nick == "")) {
            message = "*** " + message;
            serverMessage = true;
        } else if (message.startsWith("\001ACTION")) {
            String message2 = "* " + nick + " " + message.substring(8, message.length() - 1);
            message = message2;
            nick = "";
            actionMessage = true;
        } else if (message.startsWith("ACTION")) {
            String message2 = "* " + nick + " " + message.substring(7, message.length());
            message = message2;
            nick = "";
            actionMessage = true;
        } else if (nick != "") {
            nick = nick + ":";
        }

        // Add the text to document attached to the JTextPane
        // TODO: Insert line correctly to the document (IRCClient)
        MutableAttributeSet attrs = mTextPane.getInputAttributes();
        StyleConstants.setFontFamily(attrs, mFont.getFamily());
        StyleConstants.setFontSize(attrs, mFont.getSize());
        StyleConstants.setItalic(attrs, (mFont.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attrs, (mFont.getStyle() & Font.BOLD) != 0);
        StyledDocument doc = (StyledDocument) mTextPane.getDocument();

        // Create string to insert
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            String DATE_FORMAT = "HH:mm";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);

            if (serverMessage) {
                StyleConstants.setForeground(attrs, RED);
                doc.insertString(doc.getLength() + 1, "[" + sdf.format(cal.getTime()) + "] " + nick + " " + message + "\n", attrs);
            } else if (actionMessage) {
                StyleConstants.setForeground(attrs, PURPLE);
                doc.insertString(doc.getLength() + 1, "[" + sdf.format(cal.getTime()) + "] " + nick + " " + message + "\n", attrs);
            } else {
                // Insert the image if present
                if (userImage != null) {
                    doc.insertString(doc.getLength() + 1, "\n", attrs);
                    Style style = doc.addStyle("StyleName", null);
                    StyleConstants.setIcon(style, userImage);
                    doc.insertString(doc.getLength(), "ignored text", style);
                }

                // Insert the nick and time
                StyleConstants.setForeground(attrs, BLUE);

                doc.insertString(doc.getLength() + 1, "[" + sdf.format(cal.getTime()) + "] " + nick + " ", attrs);

                // Insert the message
                StyleConstants.setForeground(attrs, BLACK);
                doc.insertString(doc.getLength() + 1, message + "\n", attrs);
            }
        } catch (BadLocationException ble) {
            sLogger.warn(ble);
        }

        // Scroll down
        JScrollBar verticalScrollBar = mTextScrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());

        // Play a sound if the incoming string matches the stored tokens
        if (message.indexOf(Internationalization.getString("IRCSound.Gunfire")) != -1) {
            SoundPlayer.playSynchron(SOUNDTYPE.FX, "machinegunfire1");
        } else if (message.indexOf(Internationalization.getString("IRCSound.Knife")) != -1) {
            SoundPlayer.playSynchron(SOUNDTYPE.FX, "bleep3");
        }
    }

    private void enableSoundButtons(boolean value) {
        mGunfireButton.setEnabled(value);
        mKnifeButton.setEnabled(value);

        if (!value) {
            new Reminder(30, ActionManager.getAction(ACTIONS.REENABLE_SOUNDBUTTONS));
        }
    }

    public boolean handleAction(ACTIONS action, ActionObject o) {
        switch (action) {
        case REENABLE_SOUNDBUTTONS:
            enableSoundButtons(true);
            break;

        default:
            break;
        }
        return true;

    } // handleAction

    /**
     * Dealing with Actionevents
     * 
     * @param e
     *            Incoming event
     */
    public final void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        // if ( e.getSource() == enableGun ) {
        // btGunfire.setEnabled( true );
        //            
        // return;
        //        	
        // } // if
        if ("send".equals(cmd)) {
            // send command
            if (!mInputTextField.equals("")) {
                mIRCClient.shipInput(mChannelName, mInputTextField.getText());
                mInputTextField.setText("");
                mInputTextField.requestFocus();
            }
        } else if ("copyChatLog".equals(cmd)) {
            // copyChatLog command
            writeLog();
        } else if ("sendNick".equals(cmd)) {
            // nick command
            String suggestion = "/nick [" + DBWrapper.getCurrentGameUser().getOwner().getShortName() + "]-";
            mInputTextField.setText(suggestion);
            mInputTextField.setCaretPosition(suggestion.length());
            mInputTextField.requestFocus();
        } else if ("sendAction".equals(cmd)) {
            // action command
            mInputTextField.setText("/me ");
            mInputTextField.setCaretPosition(4);
            mInputTextField.requestFocus();
        } else if ("sendPart".equals(cmd)) {
            // part command
            mIRCClient.shipInput(mChannelName, "/part " + mChannelName);
        } else if ("sendJoin".equals(cmd)) {
            // join command
            mInputTextField.setText("/join #");
            mInputTextField.setCaretPosition(7);
            mInputTextField.requestFocus();
        } else if ("sendGunfire".equals(cmd)) {
            // gunFire command
            enableSoundButtons(false);
            mIRCClient.shipInput(mChannelName, "/me " + Internationalization.getString("IRCSound.Gunfire"));
        } else if ("sendKnife".equals(cmd)) {
            // knife command
            enableSoundButtons(false);
            mIRCClient.shipInput(mChannelName, "/me " + Internationalization.getString("IRCSound.Knife"));
        }
    }
}
