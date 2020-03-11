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
import java.util.Iterator;

import de.battleforge.jdo.BFSystem;

/**
 * @author Manuel Umbach
 * 
 * Hier muss noch gerechnet werden !!!!
 */
public class Route {
    /**
     * 
     */
    private ArrayList systems = new ArrayList();

    /**
     * 
     */
    private double hours = 0;

    /**
     * Constructor
     * 
     * @param route
     */
    public Route(ArrayList route) {
        systems = route;
        calculateHours();
    }

    public BFSystem getDestination() {
        if (systems.size() > 0) {
            return (BFSystem) systems.get(systems.size() - 1);
        }
        return null;
    }

    /**
     * 
     * @uml.property name="hours"
     */
    public double getHours() {
        return hours;
    }

    public String getRouteInsert() {
        String sRoute = "";
        for (int i = 0; i < systems.size(); i++) {
            BFSystem s = (BFSystem) systems.get(i);
            if (sRoute.equals("")) {
                // TODO: sRoute = String.valueOf(s.getPKSystem().getKey());
            } else {
                // TODO: sRoute = sRoute + "," + s.getPKSystem().getKey();
            }
        }
        System.out.println("Route: " + sRoute);
        return sRoute;
    }

    public ArrayList getRouteSystems() {
        return systems;
    }

    public int getStops() {
        return systems.size();
    }

    public void addRoute(Route r) {
        addRoute(r.systems);
        calculateHours();
    }

    public void addRoute(ArrayList route) {
        for (int i = 0; i < route.size(); i++) {
            systems.add(route.get(i));
        }

        calculateHours();
    }

    public void addSystem(BFSystem s) {
        systems.add(s);
        calculateHours();
    }

    public void calculateHours() {
        double time = 0;
        for (int i = 0; i < systems.size(); i++) {
            BFSystem s = (BFSystem) systems.get(i);
            if (s.getStarType() != null) {
                time = time + s.getStarType().getJumpSailRecharging();
            } else {
                time = time + 0;
            }
        }
        hours = time;
    }

    public Route cloneRoute() {
        Route r = new Route(new ArrayList());
        for (int i = 0; i < systems.size(); i++) {
            r.addSystem((BFSystem) systems.get(i));
        }
        return r;
    }

    public void outputRoute() {
        String sRoute = "";
        for (int i = 0; i < systems.size(); i++) {
            BFSystem s = (BFSystem) systems.get(i);
            sRoute = sRoute + "; " + s.getName();
        }
        System.out.println("Hours: " + getHours() + " Stopps: " + getStops() + " Route: " + sRoute);
    }

    public ArrayList getBFUnitMovements() {
        Iterator iter = systems.iterator();
        ArrayList al = new ArrayList();
        while (iter.hasNext()) {
            BFSystem s = (BFSystem) iter.next();
            // TODO: BF_UnitMovement pos = new BF_UnitMovement(-1, s, "", "");
            // TODO: al.add(pos);
        }

        return al;
    }
}
