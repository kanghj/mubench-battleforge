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
package de.battleforge.gui.map.route;

import java.util.ArrayList;

import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.DBWrapper;

/**
 * @author Manuel Umbach
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BuildRoute {

    /**
     * 
     */
    static int COMPTYPE_HOURS = 0;

    /**
     * 
     */
    static int COMPTYPE_STOPS = 1;

    /**
     * 
     */
    ArrayList universe = null;

    /**
     * 
     */
    ArrayList waypoints = new ArrayList();

    /**
     * 
     */
    Route route = null;

    /**
     * 
     */
    BFSystem cDestinationSystem = null;

    /**
     * 
     */
    BFSystem cStartSystem = null;

    int nComparisonType = 0;

    /**
     * 
     */
    private Route tmproute = null;

    private int tests = 0;

    public BuildRoute(ArrayList universe) {
        this.universe = universe;
    }

    public void setComparisonType(int type) {
        nComparisonType = type;
    }

    public void setDestinationSystem(BFSystem system) {
        cDestinationSystem = system;
    }

    public String getInsert() {
        return route.getRouteInsert();
    }

    public void setRoute(String sString) {
    }

    public void setRoute() {
        route = searchRoute();
    }

    /**
     * 
     * @uml.property name="route"
     */
    public Route getRoute() {
        return route;
    }

    public void setStartSystem(BFSystem system) {
        cStartSystem = system;
    }

    public void addWaypoint(BFSystem system) {
        waypoints.add(system);
    }

    private ArrayList getOrderedNeigbours(BFSystem destination, BFSystem actual, Route route) {
        ArrayList a = new ArrayList();

        double nMin = 1;

        BFSystem add = null;

        while (nMin != 0) {
            nMin = 0;
            add = null;

            BFSystem[] actualNeighbour = DBWrapper.getSystemNeighbour(actual);

            for (BFSystem neighbour : actualNeighbour) {
                // for (int i = 0; i <= actualNeighbour.size(); i++) {
                // BFSystem neighbour = (BFSystem)actualNeighbour.get(i);

                if (neighbour == destination) {
                    a = new ArrayList();
                    a.add(neighbour);
                    return a;
                }

                if (checkArrayList(a, neighbour) && checkArrayList(route.getRouteSystems(), neighbour)) {
                    double ncheck = GetDiagonal(neighbour.getX(), neighbour.getY(), neighbour.getZ(), destination.getX(), destination.getY(),
                            destination.getZ());
                    if ((ncheck < nMin) || (nMin == 0)) {
                        add = neighbour;
                        nMin = ncheck;
                    }
                }
            }

            /*
             * for (int i = 0; i <= actual.getMaxNeighbours(); i++) { BFSystem
             * neighbour = actual.getNeighbour(i);
             * 
             * if (neighbour.getPKSystem().getKey() ==
             * destination.getPKSystem().getKey()) { a = new ArrayList();
             * a.add(neighbour); return a; }
             * 
             * if (checkArrayList(a, neighbour) &&
             * checkArrayList(route.getRouteSystems(), neighbour)) { double
             * ncheck = GetDiagonal(neighbour.getXCor(), neighbour.getYCor(),
             * neighbour.getZCor(), destination.getXCor(),
             * destination.getYCor(), destination.getZCor()); if ((ncheck <
             * nMin) || (nMin == 0)) { add = neighbour; nMin = ncheck; } } }
             */
            if (add != null) {
                a.add(add);
            }
        }
        return a;
    }

    public static double GetDiagonal(double nX1, double nY1, double nZ1, double nX2, double nY2, double nZ2) {
        double nTemp = 0;
        double nT1;
        double nT2;
        double nT3;
        nT1 = (nX2 - nX1) * (nX2 - nX1);
        nT2 = (nY2 - nY1) * (nY2 - nY1);
        nT3 = (nZ2 - nZ1) * (nZ2 - nZ1);
        nTemp = Math.sqrt((nT1) + (nT2));
        if (nZ1 == nZ2) {
            return nTemp;
        }
        return Math.sqrt(nTemp + nT3);
    }

    private boolean checkArrayList(ArrayList r, BFSystem cNew) {
        for (int i = 0; i < r.size(); i++) {
            BFSystem s = (BFSystem) r.get(i);
            if (s == cNew) {
                return false;
            }
        }
        return true;
    }

    /** Wenn neue Route besser return TRUE */
    private boolean compareRoute(Route newRoute, Route oldRoute) {
        switch (nComparisonType) {
        case 1:
            return newRoute.getHours() < oldRoute.getHours();
        case 2:
            return newRoute.getStops() < oldRoute.getStops();
        }
        return false;
    }

    /**
     * Berechnet und fasst Teilstücke der Route zusammen
     * 
     * @return Route
     */
    public Route searchRoute() {
        boolean bFirst = true;
        Route route = null;
        boolean bOK = false;

        if (waypoints.size() > 0) {
            route = new Route(new ArrayList());
            for (int i = 0; i <= waypoints.size(); i++) {
                if (bFirst) {
                    Route ro = new Route(new ArrayList());
                    tmproute = null;
                    tests = 0;
                    bOK = searchRoute(ro, (BFSystem) waypoints.get(i), cStartSystem);
                    bFirst = false;
                } else if (waypoints.size() > (i + 1)) {
                    Route ro = new Route(new ArrayList());
                    tmproute = null;
                    tests = 0;
                    bOK = searchRoute(ro, (BFSystem) waypoints.get(i), (BFSystem) waypoints.get(i - 1));
                } else {
                    Route ro = new Route(new ArrayList());
                    tmproute = null;
                    tests = 0;
                    bOK = searchRoute(ro, cDestinationSystem, (BFSystem) waypoints.get(i - 1));
                }
                if (bOK) {
                    route.addRoute(tmproute);
                } else {
                    route = null;
                    return null;
                }
            }
            return route;
        } else {
            Route ro = new Route(new ArrayList());
            tmproute = null;
            tests = 0;
            bOK = searchRoute(ro, cDestinationSystem, cStartSystem);
            System.out.println("Anzahl der Tests: " + tests);
            if (bOK) {
                route = tmproute;
                return route;
            } else {
                route = null;
                return null;
            }
        }
    }

    /**
     * Berechnung der Teilroute wenn Return TRUE strecke in tmproute der Klasse
     * 
     * @param route
     *            Bisherige Route ( Sicherung für Kreisbewegungen )
     * @param destination (
     *            Ziel )
     * @param actual (
     *            Aktuelles System )
     * @return
     */
    private boolean searchRoute(Route r, BFSystem destination, BFSystem actual) {
        ArrayList neighbours = getOrderedNeigbours(destination, actual, r);
        Route r2 = r.cloneRoute();
        if (actual.getName().compareTo("Cholame") == 0) {
            System.out.println("TEST");
        }

        boolean returnOK = false;
        for (int i = 0; i < neighbours.size(); i++) {
            BFSystem neighbour = (BFSystem) neighbours.get(i);
            r2.addSystem(neighbour);

            // r2.outputRoute();
            if (neighbour == destination) {
                if ((tmproute == null) || this.compareRoute(r2, tmproute)) {
                    tmproute = r2;
                    r2.outputRoute();
                }
                tests++;
                return true;
            } else {
                if ((tmproute == null) || this.compareRoute(r2, tmproute)) {
                    boolean bOK = searchRoute(r2, destination, neighbour);
                    if (bOK) {
                        returnOK = true;
                    }
                } else {
                    tests++;
                }
            }
        }

        return returnOK;
    }
}
