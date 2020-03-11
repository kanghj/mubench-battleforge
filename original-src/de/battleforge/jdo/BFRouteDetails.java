package de.battleforge.jdo;

import sun.util.calendar.BaseCalendar.Date;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_ROUTEDETAILS" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFRouteDetails {
        
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="ROUTEID"
     */
    private BFRoute route;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="SYSTEMID"
     */
    private BFSystem system;
    
    /**
     * @jdo.field
     * @jdo.column name="ISCALCULATED" jdbc-type="TINYINT"
     */
    private boolean isCalculated;
    
    public BFRouteDetails(){
        
    }

    /**
     * @return the route
     */
    public BFRoute getRoute() {
        return route;
    }

    /**
     * @return the system
     */
    public BFSystem getSystem() {
        return system;
    }

}