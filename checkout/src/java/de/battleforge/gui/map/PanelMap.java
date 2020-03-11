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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.map.system.PanelSystemInformation;
import de.battleforge.gui.tabbedpane.CloseableTabsTabbedPane;
import de.battleforge.gui.tabbedpane.TabCloseEvent;
import de.battleforge.gui.tabbedpane.TabCloseListener;
import de.battleforge.gui.tabbedpane.TabCloseVetoException;
import de.battleforge.jdo.BFJumpship;
import de.battleforge.jdo.BFSystem;
import de.battleforge.sound.SoundPlayer;
import de.battleforge.sound.SoundPlayer.SOUNDTYPE;

/**
 * <p>
 * Title: <b>PanelMap</b><br>
 * Description: <i>The Panel for the map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * </p>
 * 
 * @author Meldric
 * @version 1.0
 */
public class PanelMap extends JPanel implements ChangeListener, MouseWheelListener, ActionCallbackListener, TabCloseListener {

    /**
     * The mappanel
     */
    private JPanel mapPanel = new JPanel();

    /**
     * The panel on the top
     */
    private JPanel topPanel = new JPanel();

    /**
     * The zoomslider
     */
    private JSlider jSliderZoom = new JSlider();

    /**
     * The horizontal splitpane
     */
    private JSplitPane horSplitPane = null;

    /**
     * The vertical splitpane
     */
    // private JSplitPane verSplitPane = null;
    /**
     * SysDetail
     */
    PanelSystemList mySysDetail = new PanelSystemList();

    /**
     * 2D view
     */
    Panel2DView my2DView = new Panel2DView();

    /**
     * Route
     */
    private PanelRoute myRoute = new PanelRoute();

    private CloseableTabsTabbedPane mTabbedPane;

    private HashMap<BFJumpship, PanelRoute> mOpenDropShipTabs = new HashMap<BFJumpship, PanelRoute>();

    /**
     * Constructor
     */
    public PanelMap() {
        BorderLayout l = new BorderLayout();
        this.setLayout(l);
        jSliderZoom.setOrientation(SwingConstants.VERTICAL);
        jSliderZoom.setMajorTickSpacing(5);
        jSliderZoom.setMinorTickSpacing(1);
        jSliderZoom.setMinimum(1);
        jSliderZoom.setMaximum(20);
        jSliderZoom.setValue(4);
        jSliderZoom.setInverted(true);
        jSliderZoom.setPaintTicks(true);
        // jSliderZoom.setSnapToTicks(true);
        jSliderZoom.addChangeListener(this);
        jSliderZoom.addMouseWheelListener(this);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BorderLayout());
        sliderPanel.add(jSliderZoom, BorderLayout.CENTER);

        mapPanel.setLayout(new BorderLayout());
        mapPanel.add(my2DView, BorderLayout.CENTER);
        mapPanel.add(sliderPanel, BorderLayout.WEST);

        horSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapPanel, mySysDetail);
        horSplitPane.setOneTouchExpandable(true);
        horSplitPane.setContinuousLayout(false);
        horSplitPane.setResizeWeight(1.0);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(horSplitPane, BorderLayout.CENTER);

        mTabbedPane = new CloseableTabsTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        mTabbedPane.addTabCloseListener(this);

        PanelSystemInformation system = new PanelSystemInformation();
        // system.setSystem( Tools.getSystemByString( "Terra" ) );

        mTabbedPane.addTab("System", system, false);

        add(topPanel, BorderLayout.CENTER);
        add(mTabbedPane, BorderLayout.SOUTH);

        // verSplitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT, topPanel,
        // myRoute );
        // verSplitPane.setOneTouchExpandable( true );
        // verSplitPane.setContinuousLayout( false );
        // verSplitPane.setResizeWeight( 1.0 );

        // this.add(panelGeneralInfo, BorderLayout.NORTH);
        // this.add( verSplitPane, BorderLayout.CENTER );

        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);
        ActionManager.addActionCallbackListener(ACTIONS.OPEN_JUMPSHIP_TAB, this);

        my2DView.addMouseWheelListener(this);

        // TODO This hack updates the panel
        myRoute.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if ("route".equals(evt.getPropertyName())) {
                    my2DView.setZoom(my2DView.getZoom());

                } else if ("newWayPoints".equals(evt.getPropertyName())) {
                    my2DView.setWayPoints((ArrayList) evt.getNewValue());

                } /* if */
            }

        });

        // TODO This hack is used for adding a waypoint to the route
        my2DView.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if ("waypoint".equals(evt.getPropertyName())) {
                    myRoute.setSystem((BFSystem) evt.getNewValue());
                    myRoute.actionPerformed(new ActionEvent(this, -1, "waypoint"));
                }
            }
        });
    }

    /**
     * Set Position of horizontal Splitpane to the left
     */
    public final void setHorizontalSplitLeft() {
        horSplitPane.setDividerLocation(horSplitPane.getInsets().left);
    }

    /**
     * Set Position of horizontal Splitpane to the right
     */
    public final void setHorizontalSplitRight() {
        horSplitPane.setDividerLocation(horSplitPane.getSize().width - horSplitPane.getInsets().right - horSplitPane.getDividerSize());
    }

    /**
     * MouseWheel moved
     * 
     * @param mwe
     *            MouseWheelEvent
     */
    public final void mouseWheelMoved(MouseWheelEvent mwe) {
        jSliderZoom.setValue(jSliderZoom.getValue() + mwe.getWheelRotation());
        if ((jSliderZoom.getValue() == jSliderZoom.getMaximum()) || (jSliderZoom.getValue() == jSliderZoom.getMinimum())) {
            SoundPlayer.play(SOUNDTYPE.FX, "bleep3");

        }
    }

    /**
     * Slider (Zoom) changed
     * 
     * @param ce
     *            ChangeEvent
     */
    public final void stateChanged(ChangeEvent ce) {
        my2DView.setZoom(jSliderZoom.getValue());
    }

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case CHANGE_CURRENT_SYSTEM:
            mTabbedPane.setTitleAt(0, ((BFSystem) object.getObject()).getName());

            break;

        case OPEN_JUMPSHIP_TAB:
            PanelRoute route = mOpenDropShipTabs.get(object.getObject());

            if (route == null) {
                route = new PanelRoute();

                BFJumpship ship = (BFJumpship) object.getObject();

                // route.setJumpship( ship );

                mTabbedPane.addTab(ship.getName(), route);
                mTabbedPane.setSelectedComponent(route);

                mOpenDropShipTabs.put(ship, route);

            } else {
                mTabbedPane.setSelectedComponent(route);
            }
        }
        return true;
    }

    public void tabWillBeClosed(TabCloseEvent e) throws TabCloseVetoException {

    }

    public void tabClosed(TabCloseEvent e) {
        PanelRoute route = (PanelRoute) e.getComponent();

        mOpenDropShipTabs.remove(route.getJumpship());

    }
}
