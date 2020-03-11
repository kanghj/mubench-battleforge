package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_DIPLOMACY" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFDiplomacy {
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="GAMEID"
     */
    private BFGame game;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID1"
     */
    private BFOwner owner1;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID2"
     */
    private BFOwner owner2;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="STATEID"
     */
    private BFDiplomacyState state;
    
    public BFDiplomacy(){
        
    }

    /**
     * @return the game
     */
    public BFGame getGame() {
        return game;
    }

    /**
     * @return the owner1
     */
    public BFOwner getOwner1() {
        return owner1;
    }

    /**
     * @return the owner2
     */
    public BFOwner getOwner2() {
        return owner2;
    }

    /**
     * @return the state
     */
    public BFDiplomacyState getState() {
        return state;
    }
}
