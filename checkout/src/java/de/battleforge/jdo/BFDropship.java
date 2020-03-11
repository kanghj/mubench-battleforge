package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_DROPSHIP" persistence-capable-superclass="de.battleforge.jdo.BFUnit
 * @jdo.datastore-identity strategy="native" column="UNITID"
 * @jdo.inheritance strategy="new-table"
 */
public class BFDropship extends BFUnit {
    
    /**
     * @jdo.field
     * @jdo.column name="MECH" jdbc-type="INTEGER"
     */
    private int mech;
    
    /**
     * @jdo.field
     * @jdo.column name="TANK" jdbc-type="INTEGER"
     */
    private int tank;
    
    /**
     * @jdo.field
     * @jdo.column name="INFANTRY" jdbc-type="INTEGER"
     */
    private int infantry;
    
    /**
     * @jdo.field
     * @jdo.column name="CHARGE" jdbc-type="INTEGER"
     */
    private int charge;
    
    public BFDropship(){
        
    }

    /**
     * @return the charge
     */
    public int getCharge() {
        return charge;
    }

    /**
     * @return the infantry
     */
    public int getInfantry() {
        return infantry;
    }

    /**
     * @return the mech
     */
    public int getMech() {
        return mech;
    }

    /**
     * @return the tank
     */
    public int getTank() {
        return tank;
    }
}
