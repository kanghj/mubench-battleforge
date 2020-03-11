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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.jgoodies.animation.Animation;
import com.jgoodies.animation.Animations;
import com.jgoodies.animation.Animator;
import com.jgoodies.animation.animations.BasicTextAnimation;
import com.jgoodies.animation.components.BasicTextLabel;

import de.battleforge.gui.StartBrowserLabel;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.util.BFConstant;
import de.battleforge.util.Internationalization;
import de.battleforge.util.Tools;

/**
 * This class represents a Info-Dialog where all neccessary things should be
 * displayed.
 * 
 * @author Meldric
 * @author kotzbrocken2
 */
public class Info extends AbstractDialog {

    private Animator mAnimator;

    /**
     * 
     * 
     * @param parent
     *            where the dialog belongs to
     */
    public Info(JFrame parent) {
        super(parent, "infoDialog.title", OPTIONS.USERDEFINED);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                mAnimator.start();

            }

            @Override
            public void windowClosed(WindowEvent e) {
                mAnimator.stop();

            }
        });
    } // InfoDialog

    @Override
    protected String getImageName() {
        return "dialog_info.png";

    } // getImageName

    @Override
    protected Container createMainPane() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        tabbedPane.add(createInfoPanel(), Internationalization.getString("infoDialog.tab.information"));
        tabbedPane.add(createPanelAuthor(), Internationalization.getString("infoDialog.tab.author"));
        tabbedPane.add(createPanelUsedSoftware(), Internationalization.getString("infoDialog.tab.usedSoftware"));
        tabbedPane.add(createPanelThanks(), Internationalization.getString("infoDialog.tab.thanks"));

        panel.add(tabbedPane);

        System.out.println(tabbedPane.getUI().getTabBounds(tabbedPane, 0));
        System.out.println(tabbedPane.getUI().getTabBounds(tabbedPane, 1));
        System.out.println(tabbedPane.getUI().getTabBounds(tabbedPane, 2));
        System.out.println(tabbedPane.getUI().getTabBounds(tabbedPane, 3));

        Rectangle tabBounds = tabbedPane.getUI().getTabBounds(tabbedPane, 3);

        Dimension prefSize = panel.getPreferredSize();

        prefSize.width = tabBounds.x + tabBounds.width;

        panel.setPreferredSize(prefSize);

        return panel;

    } // createMainPane

    private Container createInfoPanel() {
        return new JPanel(new BorderLayout());

    } // createInfoPanel

    /**
     * create the panel for used software
     * 
     * @return JPanel usedSoftware
     */
    private Container createPanelUsedSoftware() {
        Box panel = new Box(BoxLayout.Y_AXIS);

        panel.setOpaque(false);

        String url = "http://java.sun.com";

        panel.add(new StartBrowserLabel(ImageFactory.getIcon("software", "java.jpg"), url, url));

        url = "http://www.eclipse.org";

        panel.add(new StartBrowserLabel(ImageFactory.getIcon("software", "eclipse.jpg"), url, url));

        url = "http://www.mysql.com";

        panel.add(new StartBrowserLabel(ImageFactory.getIcon("software", "mysql.jpg"), url, url));

        url = "http://hsqldb.sourceforge.net";

        panel.add(new StartBrowserLabel(ImageFactory.getIcon("software", "hsqldb.jpg"), url, url));

        url = "http://www.php.net";

        panel.add(new StartBrowserLabel(ImageFactory.getIcon("software", "php.jpg"), url, url));

        url = "http://moepii.sourceforge.net";

        panel.add(new StartBrowserLabel(ImageFactory.getIcon("software", "irclib.jpg"), url, url));

        JScrollPane scrollpane = new JScrollPane(panel);

        scrollpane.getVerticalScrollBar().setUnitIncrement(panel.getComponent(0).getPreferredSize().height);

        return scrollpane;

    } // createPanelUsedSoftware

    /**
     * create the panel for thanks
     * 
     * @return JPanel usedSoftware
     */
    private JPanel createPanelThanks() {
        JPanel thanks = new JPanel(new BorderLayout());

        JTextArea text = new JTextArea(Tools.getTextResource(BFConstant.ABOUT_THANKS));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setOpaque(false);
        text.setBorder(null);

        JScrollPane thanksScroll = new JScrollPane(text);
        thanksScroll.setBorder(null);

        thanks.add(thanksScroll);

        return thanks;

    } // createPanelThanks

    /**
     * create the panel for used software
     * 
     * @return JPanel usedSoftware
     */
    private JPanel createPanelAuthor() {
        JPanel panel = new JPanel(new BorderLayout());

        BasicTextLabel lbl = new BasicTextLabel("");
        lbl.setFont(new Font("dialog", Font.BOLD, 24));

        List<Animation> animations = new LinkedList<Animation>();

        Color color1 = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        Color color2 = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        Color color3 = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        Color color4 = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

        animations.add(BasicTextAnimation.defaultSpace(lbl, 3000, "Christian Bartel", color1));
        animations.add(BasicTextAnimation.defaultSpace(lbl, 3000, "Werner Kewenig", color2));
        animations.add(BasicTextAnimation.defaultSpace(lbl, 3000, "Manuel Umbach", color3));
        animations.add(BasicTextAnimation.defaultSpace(lbl, 3000, "Dirk Ziegenbalg", color4));

        Animation animation = Animations.sequential(animations);

        mAnimator = new Animator(Animations.repeat(Float.MAX_VALUE, animation), 30);

        panel.add(lbl);

        return panel;

    } // createPanelAuthor

    @Override
    protected int getDefaultButtonIndex() {
        return 1;

    } // getDefaultButtonIndex

    @Override
    protected JButton[] getButtons() {
        return new JButton[] { getLicenseButton(), getOKButton() };

    } // getUserDefinedButtonPanel

    private JButton getLicenseButton() {
        return new JButton(new ShowLicenseAction());

    } // getLicenseButton

    private class ShowLicenseAction extends AbstractAction {

        private ShowLicenseAction() {
            super(Internationalization.getString("infoDialog.button.license"));

        } // ShowLicenseAction

        public void actionPerformed(ActionEvent e) {
            String title = Internationalization.getString("infoDialog.button.license");

            TextDisplay dialog = new TextDisplay(Info.this, title, null, Tools.getTextResource(BFConstant.ABOUT_GPL));

            dialog.setVisible(true);

        } // actionPerformed
    } // end of inner-class ShowLicenseAction
} // end of class InfoDialog
