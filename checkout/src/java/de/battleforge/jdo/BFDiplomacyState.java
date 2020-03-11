package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_DIPLOMACYSTATE" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFDiplomacyState {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    public BFDiplomacyState(){
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
