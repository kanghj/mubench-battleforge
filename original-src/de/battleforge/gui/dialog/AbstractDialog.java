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
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionObject;
import de.battleforge.action.BFAction;
import de.battleforge.gui.util.GlassPaneConsumeAllEvents;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>AbstractDialog</b><br>
 * Description: <i>An abstract dialog</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author kotzbrocken2
 * @version 1.0
 */
public abstract class AbstractDialog extends JDialog implements ActionCallbackListener {

    protected static enum OPTIONS {
        OK, OK_CANCEL, USERDEFINED

    } // enum

    public AbstractDialog(Dialog parent, String title, OPTIONS optionType) {
        super(parent, Internationalization.getString(title), true);

        init(optionType);

    } // AbstractDialog

    /**
     * 
     * 
     * @param parent
     *            where the dialog belongs to
     * @param code
     *            the number of the occured error
     * @param exitOnButton
     *            boolean whether the app should close or not
     */
    public AbstractDialog(Frame parent, String title, OPTIONS optionType) {
        super(parent, Internationalization.getString(title), true);

        init(optionType);

    } // AbstractDialog

    public boolean handleAction(ACTIONS action, ActionObject o) {
        switch (action) {
        case OK:
        case CANCEL:
            dispose();

        } // switch

        return true;

    }

    private void init(OPTIONS optionType) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        setContentPane(createContentPane(optionType));
        setGlassPane(new GlassPaneConsumeAllEvents());

        pack();

        setLocationRelativeTo(getParent());

    } // init

    private Container createContentPane(OPTIONS optionType) {
        JPanel contentPane = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 5, 0);
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 2;

        Component imgLabel = createImageLabel();

        contentPane.add(imgLabel, c);

        c.gridx = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 1;

        contentPane.add(createMainPane(), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.weighty = 0.0;

        contentPane.add(createButtonPanel(optionType), c);

        Dimension maxSize = contentPane.getMaximumSize();
        Dimension minSize = contentPane.getMinimumSize();
        Dimension prefSize = contentPane.getPreferredSize();

        maxSize.height = imgLabel.getPreferredSize().height;
        minSize.height = imgLabel.getPreferredSize().height;
        prefSize.height = imgLabel.getPreferredSize().height;

        contentPane.setMaximumSize(maxSize);
        contentPane.setMinimumSize(minSize);
        contentPane.setPreferredSize(prefSize);

        return contentPane;

    } // createContentPane

    protected JLabel createImageLabel() {
        JLabel imageLabel = new JLabel(ImageFactory.getIcon(getImageName()));

        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return imageLabel;

    } // createImageLabel

    private Container createButtonPanel(OPTIONS optionType) {
        JPanel buttonPanel = new JPanel();

        switch (optionType) {
        case OK:
            buttonPanel.setLayout(new BorderLayout());
            JButton okButton = getOKButton();

            buttonPanel.add(okButton);

            getRootPane().setDefaultButton(okButton);

            break;

        case OK_CANCEL:
            buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));
            okButton = getOKButton();

            buttonPanel.add(okButton);
            buttonPanel.add(getCancelButton());

            getRootPane().setDefaultButton(okButton);

            break;

        case USERDEFINED:
            JButton[] buttons = getButtons();

            buttonPanel.setLayout(new GridLayout(1, buttons.length, 5, 0));

            for (JButton button : buttons) {
                buttonPanel.add(button);

            } // for

            int index = getDefaultButtonIndex();

            if (index > -1) {
                getRootPane().setDefaultButton(buttons[index]);

            }
        } // switch

        return buttonPanel;

    } // createButtonPanel

    protected int getDefaultButtonIndex() {
        throw new RuntimeException("Operation must be implemented by subclasses");

    } // getDefaultButtonIndex

    protected JButton[] getButtons() {
        throw new RuntimeException("Operation must be implemented by subclasses");

    } // getButtons

    protected JButton getOKButton() {
        return new JButton(new BFAction(ACTIONS.OK, this));

    } // getOKButton

    protected JButton getCancelButton() {
        return new JButton(new BFAction(ACTIONS.CANCEL, this));

    } // getCancelButton

    protected abstract String getImageName();

    protected abstract Container createMainPane();

    protected void executeAsync(AsynchronousRunnable run) {
        new Thread(run).start();

    }

    protected abstract class AsynchronousRunnable implements Runnable {

        private AbstractDialog mParent;

        private Runnable mSwitchRunnable;

        protected AsynchronousRunnable(AbstractDialog parent) {
            mParent = parent;

            mSwitchRunnable = new Runnable() {
                private Cursor oldCursor = null;

                public void run() {
                    if (oldCursor == null) {
                        oldCursor = mParent.getCursor();

                        mParent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    } else {
                        mParent.setCursor(oldCursor);

                        oldCursor = null;

                    } // if

                    mParent.getGlassPane().setVisible(!mParent.getGlassPane().isVisible());

                }
            };
        } // AsynchronousRunnable

        public final void run() {
            switchGlassPaneOnParent();

            execute();

            switchGlassPaneOnParent();

        }

        private void switchGlassPaneOnParent() {
            try {
                SwingUtilities.invokeAndWait(mSwitchRunnable);
            } catch (Exception e) {
                // do nothing

            } // try
        }

        protected abstract void execute();

    }
} // end of class AbstractDialog
