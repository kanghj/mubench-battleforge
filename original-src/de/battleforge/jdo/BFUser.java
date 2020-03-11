package de.battleforge.jdo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_USER" 
 * @jdo.datastore-identity strategy="identity" column="ID"
 */
public class BFUser {
    
    /**
     * @jdo.field
     * @jdo.column name="USER" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="USERPASSWORD" jdbc-type="VARCHAR" length="255"
     */
    private String password;
    
    /**
     * @jdo.field collection-type="collection"
     *            element-type="de.battleforge.jdo.BFGame"
     *            persistence-modifier="persistent" table="BF_GAMEUSER"
     * @jdo.join column="ID"
     * @jdo.element column="GAMEID"
     */
    private Set<BFGame> games = new HashSet<BFGame>();
    
    //private BFGame currentGame;
    
    public BFUser(){
        
    }
    
    
    public void setName(String n) {
        name = n;
        
    }
    
    public void setPassword(String pwd) {
        password = pwd;
        
    }


    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * @return the username
     */
    public String getName() {
        return name;
    }
    
    
    public Collection<BFGame> getGames() {
        return games;
        
    }
    
    public boolean getAdminFlag(){
        return true;
    }
}
