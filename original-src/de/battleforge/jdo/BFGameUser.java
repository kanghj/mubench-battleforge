package de.battleforge.jdo;

import sun.util.calendar.BaseCalendar.Date;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_GAMEUSER" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFGameUser {
    
    /**
     * @jdo.field
     * @jdo.column name="ROUNDDATE" jdbc-type="DATE"
     */
    private Date roundDate;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="GAMEID"
     */
    private BFGame game;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="USERID"
     */
    private BFUser user;
    
    public BFGameUser(){
        
    }

    
    @Override
    public String toString() {
        return game.toString();
        
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
     * @return the roundDate
     */
    public Date getRoundDate() {
        return roundDate;
    }


    /**
     * @return the user
     */
    public BFUser getUser() {
        return user;
    }
    
}
