package de.battleforge.jdo;


/**
 * 
 * 
 * @author kotzbrocken2
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_STARTYPE" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */

public class BFStarType {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="JUMPSAILRECHARGING" jdbc-type="INTEGER"
     */
    private double jumpSailRecharging;
    
    /**
     * @jdo.field
     * @jdo.column name="DISTANCETOSUN" jdbc-type="DOUBLE"
     */
    private double distanceToSun;
    
    /**
     * @jdo.field
     * @jdo.column name="TRAVELTIME" jdbc-type="DOUBLE"
     */
    private double travelTime;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="COLORID"
     */
     private BFColor color;
    
    private BFStarType() {
    }

    /**
     * @return the color
     */
    public BFColor getColor() {
        return color;
    }

    /**
     * @return the distanceToSun
     */
    public double getDistanceToSun() {
        return distanceToSun;
    }

    /**
     * @return the jumpSailRecharging
     */
    public double getJumpSailRecharging() {
        return jumpSailRecharging;
    }

    /**
     * @return the starType
     */
    public String getName() {
        return name;
    }

    /**
     * @return the travelTime
     */
    public double getTravelTime() {
        return travelTime;
    }
    
}
