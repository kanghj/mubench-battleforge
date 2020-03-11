package de.battleforge.jdo;

import sun.util.calendar.BaseCalendar.Date;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_SESSION" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFSession {
    
    /**
     * @jdo.field
     * @jdo.column name="LOGIN" jdbc-type="TIMESTAMP"
     */
    private Date login;
    /**
     * @jdo.field
     * @jdo.column name="LOGOUT" jdbc-type="TIMESTAMP"
     */
    private Date logout;    
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="USERID"
     */
    private BFUser user;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="GAMEID"
     */
    private BFGame game;
    
    public BFSession(){
        
    }

    /**
     * @return the game
     */
    public BFGame getGame() {
        return game;
    }

    /**
     * @return the login
     */
    public Date getLogin() {
        return login;
    }

    /**
     * @return the logout
     */
    public Date getLogout() {
        return logout;
    }

    /**
     * @return the user
     */
    public BFUser getUser() {
        return user;
    }

}
