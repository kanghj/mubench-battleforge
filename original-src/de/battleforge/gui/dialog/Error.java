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
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import de.battleforge.sound.SoundPlayer;
import de.battleforge.sound.SoundPlayer.SOUNDTYPE;
import de.battleforge.util.BFException;
import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.BFProperties.BFProps;

/**
 * <p>
 * Title: <b>ErrorDialog</b><br>
 * Description: <i>A special dialog to display errors</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public class Error extends AbstractDialog {

    /**
     * List with the exceptions.
     */
    private JList mExceptionList;

    /**
     * the error message
     */
    private JTextArea mMessageTextArea;

    /**
     * the solution for that error
     */
    private JTextArea mSolutionTextArea;

    /**
     * flag wether the app should be closed or not
     */
    private static boolean mExitOnButtonPress;

    /**
     * The current selected exception.
     */
    private BFException mSelectedException;

    /**
     * An array with all exceptions.
     */
    private BFException[] mExceptions;

    /**
     * The action responsible for displaying the log-file.
     */
    private ShowLogFileAction mShowLogFileAction;

    /**
     * The action responsible for displaying the details of the problem.
     */
    private ShowDetailsAction mShowDetailsAction;

    /**
     * The action responsible for closing the dialog.
     */
    private CloseAction mCloseAction;

    /**
     * Our logger.
     */
    private static Logger sLogger = Logger.getLogger(Error.class);

    /**
     * Displays an dialog for occuring errors and provides some informations for
     * solution
     * 
     * @param parent
     *            where the dialog belongs to
     * @param code
     *            the number of the occured error
     * @param exitOnButton
     *            boolean whether the app should close or not
     */
    public Error(Frame parent, Throwable[] throwables) {
        super(parent, "errorDialog.title", OPTIONS.USERDEFINED);

        if (throwables == null) {
            throw new IllegalArgumentException("throwables must not be null!");

        } // if

        setThrowables(throwables);

    } // ErrorDialog

    public Error(Dialog parent, Throwable[] throwables) {
        super(parent, "errorDialog.title", OPTIONS.USERDEFINED);

        if (throwables == null) {
            throw new IllegalArgumentException("throwables must not be null!");

        } // if

        setThrowables(throwables);

    } // ErrorDialog

    @Override
    protected String getImageName() {
        return "dialog_error.png";

    } // getImageName

    @Override
    protected Container createMainPane() {
        mMessageTextArea = createTextArea();
        mSolutionTextArea = createTextArea();

        JPanel contentPane = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;

        contentPane.add(createErrorListScrollPane(), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.insets = new Insets(5, 0, 0, 0);
        c.weighty = 0;

        contentPane.add(new JLabel(Internationalization.getString("errorDialog.label.description")), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 2;
        c.weighty = 0.5;

        contentPane.add(new JScrollPane(mMessageTextArea), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.weighty = 0.0;

        contentPane.add(new JLabel(Internationalization.getString("errorDialog.label.solution")), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 4;
        c.weighty = 0.5;

        contentPane.add(new JScrollPane(mSolutionTextArea), c);

        return contentPane;

    } // createMainPane

    @Override
    protected int getDefaultButtonIndex() {
        return 2;

    } // getDefaultButtonIndex

    @Override
    protected JButton[] getButtons() {
        mShowDetailsAction = new ShowDetailsAction();
        mShowLogFileAction = new ShowLogFileAction();
        mCloseAction = new CloseAction();

        return new JButton[] { new JButton(mShowLogFileAction), new JButton(mShowDetailsAction), new JButton(mCloseAction) };

    } // getButtons

    private void setThrowables(Throwable[] throwables) {
        mExceptions = new BFException[throwables.length];

        for (int i = 0; i < throwables.length; i++) {
            if (throwables[i] instanceof BFException) {
                mExceptions[i] = (BFException) throwables[i];

            } else {
                mExceptions[i] = new BFException(9999, throwables[i]);

            } // if
        } // for

        mExceptionList.setListData(mExceptions);
        mExceptionList.setSelectedIndex(0);

    } // setThrowables

    private JScrollPane createErrorListScrollPane() {
        mExceptionList = new JList();

        mExceptionList.setVisibleRowCount(3);

        mExceptionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mExceptionList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    mSelectedException = mExceptions[mExceptionList.getSelectedIndex()];

                    mMessageTextArea.setText(mSelectedException.getDescription());
                    mSolutionTextArea.setText(mSelectedException.getSolution());
                    mShowDetailsAction.setEnabled(mSelectedException.getCause() != null);

                } // if
            } // valueChanged
        });

        JScrollPane scrollPane = new JScrollPane(mExceptionList);

        return scrollPane;

    } // createErrorListScrollPane

    private JTextArea createTextArea() {
        JTextArea txt = new JTextArea();

        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setEditable(false);

        return txt;

    } // createTextArea

    public static void showDialog(Frame parent, Throwable th, boolean exitOnButton) {
        showDialog(parent, new Throwable[] { th }, exitOnButton);

    } // showDialog

    public static void showDialog(Dialog parent, Throwable th, boolean exitOnButton) {
        showDialog(parent, new Throwable[] { th }, exitOnButton);

    } // showDialog

    public static void showDialog(Dialog parent, Throwable[] throwables, boolean exitOnButton) {
        mExitOnButtonPress = exitOnButton;

        Error dialog = new Error(parent, throwables);

        dialog.setVisible(true);

    }

    public static void showDialog(Frame parent, Throwable[] throwables, boolean exitOnButton) {
        mExitOnButtonPress = exitOnButton;

        Error dialog = new Error(parent, throwables);

        dialog.setVisible(true);

    } // showDialog

    @Override
    public void setVisible(boolean b) {
        if (b) {
            SoundPlayer.play(SOUNDTYPE.VOICE, "0016");

        } // if

        super.setVisible(b);

        if (!b && mExitOnButtonPress) {
            System.exit(1);

        } // if
    } // setVisible

    private class CloseAction extends AbstractAction {

        public CloseAction() {
            super(mExitOnButtonPress ? Internationalization.getString("errorDialog.button.exit") : Internationalization
                    .getString("errorDialog.button.continue"));

        } // CloseAction

        public void actionPerformed(ActionEvent e) {
            dispose();

        } // actionPerformed
    } // end of inner-class CloseAction

    private class ShowLogFileAction extends AbstractAction {

        private ShowLogFileAction() {
            super(Internationalization.getString("errorDialog.button.showLogFile"));

        } // ShowLogFileAction

        public void actionPerformed(ActionEvent e) {
            String title = Internationalization.getString("errorDialog.logfile.title");

            title = MessageFormat.format(title, new Object[] { BFProperties.getProperty(BFProps.LOGFILE) });

            String text = getLogFileAsString();

            String labelText = Internationalization.getString("errorDialog.logfile.label");

            TextDisplay.showDialog(Error.this, title, labelText, text);

        } // actionPerformed

        private String getLogFileAsString() {
            FileInputStream fIn = null;

            try {
                fIn = new FileInputStream(BFProperties.getProperty(BFProps.LOGFILE));

                byte[] buffer = new byte[64 * 1024];

                int count = 0;

                StringBuffer strBuffer = new StringBuffer();

                while (count != -1) {
                    count = fIn.read(buffer);

                    if (count > 0) {
                        strBuffer.append(new String(buffer, 0, count));

                    } // if
                } // while

                return strBuffer.toString();

            } catch (IOException e) {
                sLogger.error(Internationalization.getStringForLog("exception.io"), e);

            } finally {
                if (fIn != null) {
                    try {
                        fIn.close();

                    } catch (IOException e) {
                        sLogger.error(Internationalization.getStringForLog("exception.io"), e);

                    } // try
                } // if
            } // try

            String text = Internationalization.getString("errorDialog.logfile.error.fileNotFound");

            return MessageFormat.format(text, new Object[] { BFProperties.getProperty(BFProps.LOGFILE) });

        }

    }

    private class ShowDetailsAction extends AbstractAction {

        public ShowDetailsAction() {
            super(Internationalization.getString("errorDialog.button.showDetails"));

        } // ShowDetailsAction

        public void actionPerformed(ActionEvent e) {
            String title = Internationalization.getString("errorDialog.details.title");

            title = MessageFormat.format(title, new Object[] { mSelectedException.getMessage() });

            String text = getStackTraceAsString(mSelectedException.getCause());

            String labelText = Internationalization.getString("errorDialog.details.label");

            TextDisplay.showDialog(Error.this, title, labelText, text);

        } // actionPerformed

        private String getStackTraceAsString(Throwable cause) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            cause.printStackTrace(new PrintStream(outputStream));

            return new String(outputStream.toByteArray());

        } // getStackTraceAsString
    } // end of inner-class ShowDetailsAction
} // end of class ErrorDialog
