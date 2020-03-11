package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_STARTAREA" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFStartArea {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    public BFStartArea(){
        
    }

    /**
     * @return the startArea
     */
    public String getName() {
        return name;
    }

}
