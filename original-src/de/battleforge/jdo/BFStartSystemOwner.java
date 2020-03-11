package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_STARTSYSTEMOWNER" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFStartSystemOwner {
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="SYSTEMID"
     */
    private BFSystem system;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="STARTAREAID"
     */
    private BFStartArea startArea;
    
    public BFStartSystemOwner(){
        
    }

    /**
     * @return the owner
     */
    public BFOwner getOwner() {
        return owner;
    }

    public void setOwner(BFOwner o) {
        owner = o;
    }

    public void setSystem(BFSystem s) {
        system = s;
    }

    /**
     * @return the startArea
     */
    public BFStartArea getStartArea() {
        return startArea;
    }

    /**
     * @return the system
     */
    public BFSystem getSystem() {
        return system;
    }

}
