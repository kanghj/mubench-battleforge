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

import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * <p>
 * Title: <b>PanelDiplomacyOwner</b><br>
 * Description: <i>The panel for the single owner diplomacy</i><br>
 * Copyright: Copyright (c) 2005<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author christian.bartel
 * @version 1.1
 */
public class ChangeDiplomacyStatus extends AbstractDialog {
    /**
     * Constructor
     */
    public ChangeDiplomacyStatus(Frame parent) {
        super(parent, "Diplomacy.DialogTitle", OPTIONS.USERDEFINED);

    } // ChangeDiplomacyStatus

    public ChangeDiplomacyStatus(Dialog parent) {
        super(parent, "Diplomacy.DialogTitle", OPTIONS.USERDEFINED);

    } // ChangeDiplomacyStatus

    @Override
    protected Container createMainPane() {
        JPanel panel = new JPanel();
        JComboBox cbStatus = new JComboBox();
        JTextArea taDesc = new JTextArea();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 2;
        c.weightx = 0.0;
        c.weighty = 1.0;
        cbStatus.addItem("Verbündet");
        cbStatus.addItem("Neutral");
        cbStatus.addItem("Verfeindet");
        panel.add(cbStatus, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        JLabel indicator = new JLabel();
        indicator.setBackground(Color.GREEN);
        indicator.setOpaque(true);
        indicator.setPreferredSize(new Dimension(10, 10));
        panel.add(indicator, c);

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 3;
        taDesc.setEnabled(false);
        JScrollPane sp = new JScrollPane(taDesc);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(200, 1));
        panel.add(sp, c);

        c.gridx = 4;
        c.gridy = 0;
        JRadioButton forum = new JRadioButton("Forum", true);
        JRadioButton all = new JRadioButton("All members", false);
        JRadioButton leader = new JRadioButton("Unit leader", false);
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(forum);
        bgroup.add(all);
        bgroup.add(leader);
        JPanel radioButtons = new JPanel();
        radioButtons.setLayout(new GridLayout(3, 1));
        radioButtons.add(forum);
        radioButtons.add(all);
        radioButtons.add(leader);
        panel.add(radioButtons, c);

        return panel;
    } // createMainPane

    @Override
    protected JButton[] getButtons() {
        return new JButton[] { new JButton(), getCancelButton() };

    }

    @Override
    protected int getDefaultButtonIndex() {
        return 1;
    }

    @Override
    protected String getImageName() {
        return "dialog_diplomacy.png"; //$NON-NLS-1$

    } // getImageName
}
