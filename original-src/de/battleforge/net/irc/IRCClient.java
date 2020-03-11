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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.IRCEventAdapter;
import org.schwering.irc.lib.IRCEventListener;
import org.schwering.irc.lib.IRCModeParser;
import org.schwering.irc.lib.IRCUser;
import org.schwering.irc.lib.IRCUtil;
import org.schwering.irc.lib.SSLIRCConnection;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.dialog.Property;
import de.battleforge.sound.SoundPlayer;
import de.battleforge.sound.SoundPlayer.SOUNDTYPE;
import de.battleforge.util.BFProperties;
import de.battleforge.util.BFProperties.BFProps;

/**
 * 
 */
public class IRCClient extends JPanel implements ActionListener, ActionCallbackListener {

    private JTextArea myTextArea = new JTextArea();

    private IRCConnection conn = null;

    private JScrollPane textScroll;

    private HashMap<String, IRCChannel> hm = new HashMap<String, IRCChannel>();

    private JTabbedPane tab = new JTabbedPane();

    private JButton btSendText = new JButton();

    private JTextField input = new JTextField();

    private JPanel plEnterMessage = new JPanel();

    private JLabel lbInput = new JLabel("Enter Message:");

    private JButton btDisconnect = new JButton("Disconnect");

    private JPanel connection = new JPanel();

    private JButton btConnect = new JButton("Connect");

    private JButton btJoin = new JButton("Join default channel");

    /**
     * A shorthand for printing
     */
    private void print(String target, String nick, String message) {
        // if no target was specified, the messages goes to serverlog
        // if the target was "server" then it only goes to serverlog
        if ((target == "") || (target == "Server") || (nick == "")) {
            int len = myTextArea.getDocument().getLength();
            try {
                myTextArea.getDocument().insertString(len, message + "\n\r", null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // bring the scrollbar down to follow the text
            myTextArea.setCaretPosition(myTextArea.getDocument().getLength());

            int max = textScroll.getVerticalScrollBar().getMaximum();
            textScroll.getVerticalScrollBar().setValue(max);
        }

        // Messages are printed on the channels they where send to. If there is
        // no target specified, the messages goes to all channels and the server
        // log.
        IRCChannel temp = hm.get(target);
        if (temp != null) {
            temp.print(target, nick, message);
        }
    }

    public IRCConnection getConnection() {
        return conn;
    }

    /**
     * Checks wether a string starts with another string (case insensitive)
     * 
     * @param s1
     *            string1
     * @param s2
     *            string2
     * @return true if s2 starts with s1, otherwise false
     */
    private static boolean startsWith(String s1, String s2) {
        return (s1.length() >= s2.length()) ? s1.substring(0, s2.length()).equalsIgnoreCase(s2) : false;
    }

    /**
     * Adds a channel-tab to the display and changes to it. This is used when a
     * /join command is coming in.
     * 
     * @param name
     */
    private void addChannel(String name) {
        if (conn != null) {
            if (conn.isConnected()) {
                if (hm.get(name) == null) {
                    IRCChannel newChannel = new IRCChannel(this, name);
                    hm.put(name, newChannel);
                    tab.add(newChannel, name);
                    tab.setSelectedIndex(tab.getTabCount() - 1);
                }
            }
        }
    }

    /**
     * Removes a channel-tab from the display and changes to console. This is
     * used when a /part command is coming in.
     * 
     * @param name
     *            channel to remove
     */
    private void removeChannel(String name) {
        if (conn != null) {
            if (conn.isConnected()) {
                tab.remove(hm.get(name));
                hm.remove(name);
            }
        }
    }

    /**
     * Adds a nickname to the nicklist of a given channel. Uses the
     * <code>addNick(int, String)</code> method.
     * 
     * @param chan
     *            The channel's name.
     * @param nicks
     *            The nickname array which is to add.
     */
    public void addNicks(String chan, String[] nicks) {
        int index = indexOfTab(chan);
        addNicks(index, nicks);
    }

    /**
     * Adds a nickname to the nicklist of a given channel.
     * 
     * @param index
     *            The channel's index.
     * @param nicks
     *            The nickname array which is to add.
     */
    public void addNicks(int index, String[] nicks) {
        if (index != -1) {
            Component component = tab.getComponentAt(index);
            IRCChannel myChannel = (IRCChannel) component;
            for (String element : nicks) {
                myChannel.addUserToList(new IRCUser(element, "", ""));
            }
            myChannel.orderList();
        }
    }

    /**
     * Constructor Creates a new IRCConnection instance and starts the thread
     */
    public IRCClient() {
        myTextArea.setEditable(false);
        myTextArea.setLineWrap(true);
        myTextArea.setWrapStyleWord(true);
        myTextArea.setAutoscrolls(true);

        btSendText.setActionCommand("send");
        btSendText.addActionListener(this);
        btSendText.setText("Send");
        btSendText.setEnabled(false);

        Action sendText = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btSendText.doClick();
            }
        };
        input.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "sendText");
        input.getActionMap().put("sendText", sendText);

        plEnterMessage.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(3, 3, 3, 3);

        c.gridx = 0;
        c.weightx = 0.0;
        plEnterMessage.add(lbInput, c);

        c.gridx = 1;
        c.weightx = 1.0;
        plEnterMessage.add(input, c);

        c.gridx = 2;
        c.weightx = 0.0;
        plEnterMessage.add(btSendText, c);

        btConnect.setActionCommand("connect");
        btConnect.addActionListener(this);

        btDisconnect.setActionCommand("disconnect");
        btDisconnect.addActionListener(this);
        btDisconnect.setEnabled(false);

        btJoin.setActionCommand("join");
        btJoin.addActionListener(this);
        btJoin.setEnabled(false);

        connection.add(btConnect);
        connection.add(btJoin);
        connection.add(btDisconnect);

        JPanel serverLog = new JPanel();
        myTextArea.setForeground(Color.RED);
        textScroll = new JScrollPane(myTextArea);
        textScroll.setAutoscrolls(true);
        textScroll.getViewport().setAutoscrolls(true);
        serverLog.setLayout(new BorderLayout());
        serverLog.add(connection, BorderLayout.NORTH);
        serverLog.add(textScroll, BorderLayout.CENTER);
        serverLog.add(plEnterMessage, BorderLayout.SOUTH);

        tab.add(serverLog, "Server");

        this.setLayout(new BorderLayout());
        this.add(tab);
        ActionManager.addActionCallbackListener(ACTIONS.SHUTDOWN, this);
    }

    /**
     * 
     * 
     */
    private void connect() {
        conn = null;

        String host = BFProperties.getProperty(BFProps.IRC_SERVER);
        int port = BFProperties.getInt(BFProps.IRC_PORT);
        String pass = BFProperties.getProperty(BFProps.IRC_PASSWORD);
        String nick = BFProperties.getProperty(BFProps.IRC_NICKNAME);
        String user = BFProperties.getProperty(BFProps.IRC_USER);
        String name = BFProperties.getProperty(BFProps.IRC_NAME);
        boolean ssl = false;

        if ((host.equals("")) || (nick.equals(""))) {
            Property dialog = new Property(JFrame.getFrames()[0]);
            dialog.setVisible(true);

            if (BFProperties.getBoolean(BFProps.PLAY_MUSIC)) {
                SoundPlayer.play(SOUNDTYPE.MUSIC, "mechw2splash", SoundPlayer.LOOP_CONTINUOUSLY);
            } else {
                SoundPlayer.stop(SOUNDTYPE.MUSIC, "mechw2splash");
            }
        }
        if (!((host.equals("")) || (nick.equals("")))) {
            try {
                if (!ssl) {
                    conn = new IRCConnection(host, new int[] { port }, pass, nick, user, name);
                } else {
                    conn = new SSLIRCConnection(host, new int[] { port }, pass, nick, user, name);
                }
                conn.addIRCEventListener(new Listener());
                conn.setPong(true);
                conn.setDaemon(false);
                conn.setColors(false);
                conn.connect();
                SoundPlayer.play(SOUNDTYPE.VOICE, "0003");
            } catch (IOException exc) {
                print("", "", "Error while trying to connect!\n" + exc.getMessage());
                btConnect.setEnabled(true);
                btDisconnect.setEnabled(false);
            }
        } else {
            print("", "", "\nPlease check connection parameters!\nServername or Nickname is missing...\n");
            btConnect.setEnabled(true);
            btDisconnect.setEnabled(false);
        }
    }

    /**
     * Parses the input and sends it to the IRC server
     */
    public void shipInput(String target, String input) {
        if ((input == null) || (input.length() == 0)) {
            return;
        }

        // http://www.irchelp.org/irchelp/rfc/ctcpspec.html

        if (startsWith(input, "/TARGET")) {
            target = input.substring(8);
            return;
        } else if (startsWith(input, "/JOIN")) {
            target = input.substring(6);
            addChannel(target);
            input = input.substring(1);
            conn.send(input);
        } else if (startsWith(input, "/PART")) {
            input = input.substring(1);
            conn.send(input);
        } else if (startsWith(input, "/ME")) {
            input = "\001ACTION " + input.substring(4) + "\001";
            conn.doPrivmsg(target, input);
            print(target, conn.getNick(), input);
        } else if (startsWith(input, "/")) {
            input = input.substring(1);
            conn.send(input);
            // print(target, conn.getNick(), input);
        } else {
            // input = "[" + BF_User.getCurrentUser().getOwner().getOwnerShort()
            // + "] " + input;
            conn.doPrivmsg(target, input);
            print(target, conn.getNick(), input);
        }
    }

    /**
     * Returns the index of a channel in the tabs.
     * 
     * @param title
     *            The title which is to search.
     * @return The index of a channel or <code>-1</code> if nothing is found.
     */
    public int indexOfTab(String title) {
        return tab.indexOfTab(title);
    }

    /**
     * 
     */
    public final void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("connect")) {
            btConnect.setEnabled(false);
            myTextArea.setText("Connection is being established... ");
            connect();
        } else if (cmd.equals("disconnect")) {
            conn.doQuit();
        } else if (cmd.equals("send")) {
            shipInput("", input.getText());
            input.setText("");
            input.requestFocus();
        } else if (cmd.equals("join")) {
            shipInput("", "/JOIN " + BFProperties.getProperty(BFProps.IRC_CHANNEL));
            btJoin.setEnabled(false);
        }
    }

    /**
     * Treats IRC events. The most of them are just printed
     */
    public class Listener extends IRCEventAdapter implements IRCEventListener {
        public void onRegistered() {
            print("", "", "Connected");
            btDisconnect.setEnabled(true);
            btSendText.setEnabled(true);

            if (BFProperties.getProperty(BFProps.IRC_CHANNEL) != "") {
                shipInput("", "/JOIN " + BFProperties.getProperty(BFProps.IRC_CHANNEL));
            }
        }

        public void onDisconnected() {
            SoundPlayer.playSynchron(SOUNDTYPE.VOICE, "0017");
            print("", "", "Disconnected");
            btConnect.setEnabled(true);
            btDisconnect.setEnabled(false);
            btSendText.setEnabled(false);
            btJoin.setEnabled(false);
            while (tab.getTabCount() > 1) {
                tab.removeTabAt(tab.getTabCount() - 1);
            }
            hm.clear();
        }

        public void onError(String msg) {
            print("", "", "Error: " + msg);

            Iterator iter = hm.values().iterator();
            while (iter.hasNext()) {
                IRCChannel temp = (IRCChannel) iter.next();
                print(temp.getChannelName(), "", "Error: " + msg);
            }
        }

        public void onError(int num, String msg) {
            print("", "", "Error #" + num + ": " + msg);

            Iterator iter = hm.values().iterator();
            while (iter.hasNext()) {
                IRCChannel temp = (IRCChannel) iter.next();
                print(temp.getChannelName(), "", "Error: #" + num + ": " + msg);
            }
        }

        public void onInvite(String chan, IRCUser u, String nickPass) {
            print(chan, "", u.getNick() + " invites " + nickPass);
        }

        public void onJoin(String chan, IRCUser u) {
            print(chan, "", u.getNick() + " joins");

            IRCChannel temp = hm.get(chan);
            if (temp != null) {
                if (!u.getNick().equals(conn.getNick())) {
                    temp.addUserToList(u);
                }
            }
        }

        public void onKick(String chan, IRCUser u, String nickPass, String msg) {
            print(chan, "", u.getNick() + " kicks " + nickPass);
        }

        public void onMode(IRCUser u, String nickPass, String mode) {
            print("", "", "Mode: " + u.getNick() + " sets modes " + mode + " " + nickPass);
        }

        public void onMode(IRCUser u, String chan, IRCModeParser mp) {
            print(chan, "", u.getNick() + " sets mode: " + mp.getLine());
        }

        public void onNick(IRCUser u, String nickNew) {
            print("", "", "Nick: " + u.getNick() + " is now known as " + nickNew);

            // TODO: Put the new name to the userlists!

            Iterator iter = hm.values().iterator();
            while (iter.hasNext()) {
                IRCChannel temp = (IRCChannel) iter.next();
                print(temp.getChannelName(), "", "Nick: " + u.getNick() + " is now known as " + nickNew);
            }
        }

        public void onNotice(String target, IRCUser u, String msg) {
            print(target, "", target + "> " + u.getNick() + " (notice): " + msg);
        }

        public void onPart(String chan, IRCUser u, String msg) {
            print(chan, "", u.getNick() + " parts " + chan);
            (hm.get(chan)).removeUserFromList(u.getNick());

            if (u.getNick().equals(conn.getNick())) {
                removeChannel(chan);
            }

            if (chan.equals(BFProperties.getProperty(BFProps.IRC_CHANNEL))) {
                btJoin.setEnabled(true);
            }

        }

        public void onPrivmsg(final String chan, final IRCUser u, final String msg) {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {

                    public void run() {
                        print(chan, u.getNick(), msg);

                    }

                });
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void onQuit(IRCUser u, String msg) {
            print("", "", "Quit: " + u.getNick());

            Iterator iter = hm.values().iterator();
            while (iter.hasNext()) {
                IRCChannel temp = (IRCChannel) iter.next();
                temp.removeUserFromList(u.getNick());
                print(temp.getChannelName(), "", "Quit: " + u.getNick());
            }
        }

        public void onReply(int num, String value, String msg) {
            if (num == IRCUtil.RPL_NAMREPLY) { // who -> add nicks to nicklist

                StringTokenizer stValue = new StringTokenizer(value);
                stValue.nextToken(); // jump over the first (it is our name)
                stValue.nextToken(); // jump over the second (it is a '@',
                                        // '*' or '=')

                String chan = stValue.nextToken();
                int index = indexOfTab(chan);
                if (index != -1) {
                    StringTokenizer stNicks = new StringTokenizer(msg);
                    String[] nicks = new String[stNicks.countTokens()];
                    for (int i = 0; stNicks.hasMoreTokens(); i++) {
                        nicks[i] = stNicks.nextToken();
                    }
                    addNicks(chan, nicks);
                }
                print("Server", "", "Reply #" + num + ": " + value + " " + msg);
            }
        }

        public void onTopic(String chan, IRCUser u, String topic) {
            print(chan, "", u.getNick() + " changes topic into: " + topic);
        }
    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case SHUTDOWN:
            if (conn != null) {
                conn.doQuit();
                conn.close();
            }
            break;

        default:
            break;
        }
        return true;
    }
}
