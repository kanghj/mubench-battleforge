package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_POPULATIONSTATUS" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFPopulationStatus {

    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    private BFPopulationStatus() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
