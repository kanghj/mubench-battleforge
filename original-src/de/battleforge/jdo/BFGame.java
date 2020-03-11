package de.battleforge.jdo;

import java.util.HashSet;
import java.util.Set;

import sun.util.calendar.BaseCalendar.Date;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_GAME"
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFGame {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String mName;
    
    /**
     * @jdo.field
     * @jdo.column name="ROUNDDATE" jdbc-type="DATE"
     */
    private Date mRoundDate;

    /**
     * @jdo.field collection-type="collection"
     *            element-type="de.battleforge.jdo.BFOwner"
     *            persistence-modifier="persistent" table="BF_GAMEUSER"
     * @jdo.join column="OWNERID"
     * @jdo.element column="ID"
     */
    private Set<BFOwner> owner = new HashSet<BFOwner>();
    
    public BFGame(){
        
    }
    
    
    public Date getRoundDate() {
        return mRoundDate;

    }
    
    
    public String getName() {
        return mName;

    }
    
    @Override
    public String toString() {
        return getName();
        
    }
}
