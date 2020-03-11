package de.battleforge.jdo;



/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_SYSTEMOWNER" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFSystemOwner {
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="SYSTEMID"
     */
    private BFSystem system;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="GAMEID"
     */
    private BFGame game;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;
 
    private BFSystemOwner() {
    }

    /**
     * @return the game
     */
    public BFGame getGame() {
        return game;
    }

    /**
     * @return the owner
     */
    public BFOwner getOwner() {
        return owner;
    }

    /**
     * @return the system
     */
    public BFSystem getSystem() {
        return system;
    }

}
