package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_OWNERTYPE" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFOwnerType {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="SHORTNAME" jdbc-type="VARCHAR" length="10"
     */
    private String shortName;
    
    public BFOwnerType(){
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

}
