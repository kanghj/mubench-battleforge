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
package de.battleforge.gui.map.system;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.map.PanelOwnerInformationMap;
import de.battleforge.gui.map.units.PanelBuildUnits;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFBuilding;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.BFSystemProperty;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.util.Internationalization;

/**
 * <p>
 * Title: <b>PanelSystemInformation</b><br>
 * Description: <i>Information about the chosen system</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * <br>
 * Information about the system is displayed here.
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class PanelSystemInformation extends JPanel implements ActionCallbackListener {

    /**
     * The System to display infos about
     */
    private BFSystem s = null;

    /**
     * Label Position
     */
    private JLabel lbPopulation = new JLabel();

    /**
     * The position
     */
    private JLabel lbPopulationValue = new JLabel();

    /**
     * The startype
     */
    private JLabel lbStartypeValue = new JLabel();

    /**
     * Label Startype
     */
    private JLabel lbStartype = new JLabel();

    /**
     * Label mooncount
     */
    private JLabel lbPopulationStatus = new JLabel();

    /**
     * The mooncount
     */
    private JLabel lbPopulationStatusValue = new JLabel();

    /**
     * The temperature
     */
    private JLabel lbTemperatureValue = new JLabel();

    /**
     * Label temperatur
     */
    private JLabel lbTemperature = new JLabel();

    /**
     * Label traveltime
     */
    private JLabel lbTraveltime = new JLabel();

    /**
     * Traveltime
     */
    private JLabel lbTraveltimeValue = new JLabel();

    /**
     * Image of the system
     */
    private PanelImageDisplay pSystemImage;

    /**
     * Image of the owner
     */
    private PanelImageDisplay pOwnerImage;

    /**
     * The tabpane
     */
    private JTabbedPane tp = new JTabbedPane();

    /**
     * Panel for the system options (buildings)
     */
    private PanelSystemOptions mySystemOptions = new PanelSystemOptions();

    /**
     * Panel for the systems history
     */
    private PanelSystemHistory mySystemHistory = new PanelSystemHistory();

    /**
     * Panel for owner information
     */
    private PanelOwnerInformationMap myOwnerInformation = new PanelOwnerInformationMap();

    /**
     * Panel for balance information
     */
    private PanelSystemBalance mySystemBalance = new PanelSystemBalance();

    /**
     * Panel for building Units
     */
    private PanelBuildUnits myIndustry = new PanelBuildUnits(DBWrapper.getCurrentGameUser().getOwner());

    /**
     * Constructor
     */
    public PanelSystemInformation() {
        super(new BorderLayout());
        Image systemImage = ImageFactory.getImage("systems", "Terra.png");
        Image ownerImage = ImageFactory.getImage("ownerlogo", "IceHellion.png");
        pSystemImage = new PanelImageDisplay(systemImage, borders.LINE, 0, true, 76, 76);
        pOwnerImage = new PanelImageDisplay(ownerImage, borders.NO, 0, true, 76, 76);

        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 0, 10, 5);
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        leftPanel.add(pSystemImage, c);

        c.insets = new Insets(0, 0, 10, 15);
        c.gridx = 1;
        c.gridy = 0;
        leftPanel.add(pOwnerImage, c);

        JPanel sysDetail = new JPanel(new GridLayout(5, 2, 5, 0));
        sysDetail.add(lbStartype);
        sysDetail.add(lbStartypeValue);
        sysDetail.add(lbPopulation);
        sysDetail.add(lbPopulationValue);
        sysDetail.add(lbPopulationStatus);
        sysDetail.add(lbPopulationStatusValue);
        sysDetail.add(lbTraveltime);
        sysDetail.add(lbTraveltimeValue);
        sysDetail.add(lbTemperature);
        sysDetail.add(lbTemperatureValue);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 15);
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(sysDetail, c);

        this.add(leftPanel, BorderLayout.WEST);

        tp.setTabPlacement(SwingConstants.BOTTOM);
        tp.add(mySystemOptions, Internationalization.getString("SystemTabs.Options"));
        tp.add(myIndustry, Internationalization.getString("SystemTabs.Factory"));
        tp.add(mySystemBalance, Internationalization.getString("SystemTabs.Balance"));
        tp.add(mySystemHistory, Internationalization.getString("SystemTabs.History"));
        tp.add(myOwnerInformation, Internationalization.getString("SystemTabs.Owner"));
        this.add(tp, BorderLayout.CENTER);

        this.setTexts();
        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);
    }

    /**
     * Set the system
     * @param sys
     * System to display infos about
     */
    public final void setSystem(BFSystem sys) {
        // sets the system and repaints
        s = sys;
        setTexts();

        // Enable/Disabel industry tab
        boolean industryPresent = false;
        for (BFSystemProperty b : DBWrapper.getBuildingList(s) ) {
            BFBuilding building = b.getBuilding();
            if ("Industry".equals(building.getName())) {
                industryPresent = true;
            }
        }
        if ((!industryPresent) && (tp.getSelectedIndex() == 1)) {
            tp.setSelectedIndex(0);
        }
        tp.setEnabledAt(1, industryPresent);

        this.repaint();
    }

    /**
     * Setting the strings according to language and choosen System
     */
    public final void setTexts() {
        // Setting the labels
        lbPopulation.setText(Internationalization.getString("SystemDetails.Population") + ": ");
        lbPopulationStatus.setText(Internationalization.getString("SystemDetails.PopulationStatus") + ": ");
        lbStartype.setText(Internationalization.getString("SystemDetails.StartypeLabel") + ": ");
        lbTraveltime.setText(Internationalization.getString("SystemDetails.TravelTime") + ": ");
        lbTemperature.setText(Internationalization.getString("SystemDetails.Temperature") + ": ");

        if (s != null) {
            // Setting the values
            pSystemImage.setImage(ImageFactory.getImage("systems", s.getSystemImageName() + ".png"));
            // pOwnerImage.setImage(ImageFactory.getImage("ownerlogo",
            // s.getOwner().getLogo()));
            // pOwnerImage.setImage(ImageFactory.getImage("ownerlogo",
            // DBWrapper.getSystemOwner(s).getLogo()));
            pOwnerImage.setImage(ImageFactory.getImage("ownerlogo", s.getOwner().getLogo()));
            lbPopulationValue.setText(s.getPopulation() + "");
            lbPopulationStatusValue.setText(s.getPopulationStatus().getName());
            lbStartypeValue.setText(s.getStarType().getName());
            lbTraveltimeValue.setText("jhsjh");
            lbTemperatureValue.setText(s.getTemperature() + "");
        }
    }

    /**
     * Handle actions
     */
    public boolean handleAction(ACTIONS action, ActionObject o) {
        switch (action) {
        case CHANGE_CURRENT_SYSTEM:
            setSystem((BFSystem) o.getObject());
            break;

        default:
            break;
        }
        return true;
    }
}
