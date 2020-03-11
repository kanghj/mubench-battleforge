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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;

import de.battleforge.basics.IntegerField;
import de.battleforge.gui.PanelImageDisplay;
import de.battleforge.gui.PanelImageDisplay.borders;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.jdo.BFJumpship;
import de.battleforge.jdo.BFSystem;

/**
 * <p>
 * Title: <b>PanelRoute</b><br>
 * Description: <i>Route Panel</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * <br>
 * This is the panel to display the waypoints of a route for a BFJumpship.
 * </p>
 * 
 * @author Werner
 * @version 1.0
 */
public class PanelRoute extends JPanel implements ActionListener {

    private static Logger sLogger = Logger.getLogger(PanelRoute.class);

    /**
     * 
     * @uml.property name="jShip"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    private BFJumpship jShip;

    int i = 10;

    boolean bCalRoute = false;

    // components
    JPanel p1;

    // components
    JPanel p2;

    // components
    JPanel p3;

    JLabel lImage;

    JLabel lCaption;

    JLabel l1;

    JLabel l2;

    JLabel l3;

    JLabel l4;

    JLabel l5;

    /**
     * 
     * @uml.property name="t3"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    IntegerField t3;

    JTextField t1;

    JTextField t2;

    JTextField t4;

    JTextField t5;

    JScrollPane sP1;

    JButton jbAddWaypoint;

    JButton jbClearRoute;

    JButton jbCalculateRoute;

    /**
     * 
     * @uml.property name="sign"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    PanelImageDisplay Sign;

    BorderLayout l = new BorderLayout(5, 5);

    JScrollPane myScrollPane = new JScrollPane();

    JTable tblRoute = new JTable();

    /**
     * 
     * @uml.property name="myArrayListControl"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    ModelArrayListControl myArrayListControl;

    public PanelRoute() {
        myArrayListControl = new ModelArrayListControl();

        setTblBFSystem();
        createComponents();
        refreshPanel();

    }

    private void createComponents() {
        lCaption = new JLabel("Momentan ausgewähltes Sprungschiff:");

        // ----- panel p1 and components
        p1 = new JPanel();
        l1 = new JLabel("Name");

        // l1.setPreferredSize(new Dimension(20,40));
        l2 = new JLabel("Typ");

        // l2.setPreferredSize(new Dimension(20,40));
        l3 = new JLabel("Sprungladungen");

        // l3.setPreferredSize(new Dimension(20,40));
        l4 = new JLabel("Ankunftsdatum");

        // l4.setPreferredSize(new Dimension(20,40));
        l5 = new JLabel("Standort");

        // l5.setPreferredSize(new Dimension(20,40));
        t1 = new JTextField();
        t1.setEditable(false);
        t2 = new JTextField();
        t2.setEditable(false);
        t3 = new IntegerField();
        t3.setEditable(false);
        t4 = new JTextField();
        t4.setEditable(false);
        t5 = new JTextField();
        t5.setEditable(false);

        GridLayout lay1 = new GridLayout(5, 2);
        p1.setLayout(lay1);
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l5);
        p1.add(t5);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.setMaximumSize(new Dimension(40, 40));
        sP1 = new JScrollPane();
        sP1.getViewport().add(p1);

        // ----- create p2 and components -----
        p2 = new JPanel();
        lImage = new JLabel("Bildchen vom Schiff");

        BorderLayout lay2 = new BorderLayout();
        p2.setLayout(lay2);
        p2.add(lImage, BorderLayout.NORTH);
        p2.add(sP1, BorderLayout.CENTER);

        // ----- create p3 and components
        p3 = new JPanel();

        GridLayout lay3 = new GridLayout(3, 1);
        p3.setLayout(lay3);
        jbAddWaypoint = new JButton("neuer Wegpunkt");
        jbAddWaypoint.addActionListener(this);
        jbAddWaypoint.setActionCommand("waypoint");
        jbClearRoute = new JButton("neue Route");
        jbClearRoute.addActionListener(this);
        jbClearRoute.setActionCommand("clear");
        jbCalculateRoute = new JButton("Übernehmen");
        jbCalculateRoute.addActionListener(this);
        jbCalculateRoute.setActionCommand("ok");
        jbCalculateRoute.setEnabled(false);
        p3.add(jbAddWaypoint);
        p3.add(jbClearRoute);
        p3.add(jbCalculateRoute);

        // ----- add table -----
        myScrollPane.setPreferredSize(new Dimension(400, 100));
        myScrollPane.getViewport().add(tblRoute);

        // ----- picture -----
        Sign = new PanelImageDisplay(ImageFactory.getImage("units/dropships", "leopard.png"), borders.NO, 0, false, 0, 0);
        Sign.setPreferredSize(new Dimension(120, 100));
        Sign.setMaximumSize(new Dimension(120, 100));

        // --- add components ----
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gbl);

        // ----- LAYOUT -----
        // top,left,bottom,right
        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 6;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        this.add(lCaption, c);

        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        this.add(Sign, c);

        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        this.add(p1, c);

        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 2.0;
        c.weighty = 1.0;
        this.add(myScrollPane, c);

        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 1;
        c.weightx = 0.0;
        this.add(p3, c);
    }

    // initialisiert die Tabelle tblBFSystem
    private void setTblBFSystem() {
        String[] columnHeader = { "System", "Owner", "Ankunftsdatum" };

        // String[] columnData = {"getSystem", "getSOwner",
        // "getJumpSailRecharging"};
        String[] columnData = { "getSystemName", "getOwnerName", "getArrivalDate" };

        myArrayListControl.setHeaderAndColumn(columnHeader, columnData);

        // myArrayListControl.setData(jShip.getRoute().getRouteBFSystem());
        // myArrayListControl.setData(jShip.getUnitMovementsHist());
        tblRoute = new JTable(myArrayListControl);
        tblRoute.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void refreshPanel() {
        // Universe.alBFJumpship
        if ((jShip != null) && (t1 != null) && (t2 != null) && (t3 != null) && (t4 != null) && (t5 != null)) {
            t1.setText(jShip.getName());
            t2.setText(jShip.getUnitTyp().getName());
            t3.setInt(jShip.getJumpCharges());
            // t4.setText(jShip.getCurrentPosition(BF_User.getCurrentUser().getGameRoundDate()).getArrivalDate());
            // t5.setText(jShip.getCurrentPosition(BF_User.getCurrentUser().getGameRoundDate()).getSystem().getSystem());
        }
    }

    // ****** setter and getter ******
    public void setBFJumpship(BFJumpship j) {
        jShip = j;
        // alRoute = jShip.getRoute();
        // refresh(jShip.getUnitMovementsHist());

        // setTblBFSystem();
        // createComponents();
        refreshPanel();
    }

    /**
     * 
     * @uml.property name="system"
     */
    public void setSystem(BFSystem s) {
    }

    public void actionPerformed(ActionEvent e) {
        // String cmd = e.getActionCommand();
        //        
        // if (cmd.equals("waypoint")) {
        // alRoute.addSystem(this.system);
        // refresh(alRoute.getBFUnitMovements());
        // jbCalculateRoute.setEnabled(true);
        // } else if (cmd.equals("clear")) {
        // i = 10;
        // alRoute = new Route(new ArrayList());
        // alRoute.addSystem(jShip.getCurrentSystem(BF_User.getCurrentUser().getGameRoundDate()));
        // refresh(alRoute.getBFUnitMovements());
        // jbCalculateRoute.setEnabled(true);
        // } else if (cmd.equals("ok")) {
        // BuildRoute br = new BuildRoute(new ArrayList());
        // br.setComparisonType(2);
        // br.setStartSystem(jShip.getCurrentSystem(BF_User.getCurrentUser().getGameRoundDate()));
        //            
        // /*TODO: sind Start- und Zielsystem gleich und es existieren keine
        // Wegpunkte,
        // * muss die Routenberechnung noch angepasst werden
        // **/
        // Route ro = null;
        // if(alRoute.getRouteBFSystem().size()>1){
        // int c = alRoute.getRouteBFSystem().size() - 1;
        // for (int d = 1; d < c; d++) {
        // br.addWaypoint((BFSystem) alRoute.getRouteBFSystem().get(d));
        // }
        // br.setDestinationSystem((BFSystem)
        // alRoute.getRouteBFSystem().get(c));
        // ro = new Route(br.searchRoute().getRouteBFSystem());
        // }else{
        // //br.setDestinationSystem(jShip.getCurrentSystem(Main.bfUser.getGameRoundDate()));
        // ArrayList helper = new ArrayList();
        // ro = new Route(helper);
        // }
        // jShip.setRoute(ro);
        // refresh(jShip.getUnitMovementsHist());
        // jbCalculateRoute.setEnabled(false);
        //            
        // // Damit die Karte aktualisiert wird
        // //Tools.setSelectedBFJumpship(jShip);
        //            
        // firePropertyChange( "route", true, false );
        // firePropertyChange( "newWayPoints", myArrayListControl.getData(),
        // null );
        //            
        // }
    }

    public BFJumpship getJumpship() {
        return jShip;

    }
}
