package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_SHIPPROPERTY" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */

public class BFShipProperty {
    
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="UNITTYPID"
     */
    private BFUnitType unitType;
    
    /**
     * @jdo.field
     * @jdo.column name="MAXJUMPCHARGES" jdbc-type="INTEGER"
     */
    private int maxJumpCharges;
    
    /**
     * @jdo.field
     * @jdo.column name="DOCKINGSTATIONS" jdbc-type="INTEGER"
     */
    private int dockingStations;
    
    /**
     * @jdo.field
     * @jdo.column name="TONNAGE" jdbc-type="INTEGER"
     */
    private int tonnage;
    
    /**
     * @jdo.field
     * @jdo.column name="PROPELLANT" jdbc-type="INTEGER"
     */
    private int propellant;
    
    /**
     * @jdo.field
     * @jdo.column name="CHARGE" jdbc-type="INTEGER"
     */
    private int charge;
    
    /**
     * @jdo.field
     * @jdo.column name="CREW" jdbc-type="INTEGER"
     */
    private int crew;
    
    /**
     * @jdo.field
     * @jdo.column name="PASSENGER" jdbc-type="INTEGER"
     */
    private int passenger;
    

    
    public BFShipProperty(){
        
    }



    /**
     * @return the charge
     */
    public int getCharge() {
        return charge;
    }



    /**
     * @return the crew
     */
    public int getCrew() {
        return crew;
    }



    /**
     * @return the dockingStations
     */
    public int getDockingStations() {
        return dockingStations;
    }



    /**
     * @return the maxJumpCharges
     */
    public int getMaxJumpCharges() {
        return maxJumpCharges;
    }



    /**
     * @return the passanger
     */
    public int getPassenger() {
        return passenger;
    }



    /**
     * @return the propellant
     */
    public int getPropellant() {
        return propellant;
    }



    /**
     * @return the tonnage
     */
    public int getTonnage() {
        return tonnage;
    }



    /**
     * @return the unitType
     */
    public BFUnitType getUnitType() {
        return unitType;
    }
    
}
