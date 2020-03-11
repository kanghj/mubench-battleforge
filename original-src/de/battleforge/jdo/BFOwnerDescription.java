package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_OWNERDESCRIPTION" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */

public class BFOwnerDescription {
    
    /**
     * @jdo.field
     * @jdo.column name="DESCRIPTION" jdbc-type="LONGVARCHAR"
     */
    private String description;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;
    
    /**
     * @jdo.field
     * @jdo.column name="COUNTRYCODE" jdbc-type="VARCHAR" length="255"
     */
    private String countryCode;
    
    public BFOwnerDescription(){
        
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the owner
     */
    public BFOwner getOwner() {
        return owner;
    }

}
