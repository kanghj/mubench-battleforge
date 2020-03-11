package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_JUMPSHIP" persistence-capable-superclass="de.battleforge.jdo.BFUnit"
 * @jdo.datastore-identity strategy="native" column="UNITID"
 * @jdo.inheritance strategy="new-table"
 */
public class BFJumpship extends BFUnit {

    /**
     * @jdo.field
     * @jdo.column name="JUMPCHARGES" jdbc-type="INTEGER"
     */
    private int jumpCharges;
    
    /**
     * @jdo.field
     * @jdo.column name="X" jdbc-type="INTEGER"
     */
    private int x;
    
    /**
     * @jdo.field
     * @jdo.column name="Y" jdbc-type="INTEGER"
     */
    private int y;
    
    /**
     * @jdo.field
     * @jdo.column name="Z" jdbc-type="INTEGER"
     */
    private int z;
    
    public BFJumpship(){
        
    }

    /**
     * @return the jumpCharges
     */
    public int getJumpCharges() {
        return jumpCharges;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }
    
}
