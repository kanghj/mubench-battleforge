package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_ROUTE" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFRoute {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    /**
     * @jdo.field
     * @jdo.column name="ISOFFICIALROUTE" jdbc-type="TINYINT"
     */
    private boolean isOfficialRoute;    
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="STARTSYSTEMID"
     */
    private BFSystem startSystem;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="DESTINATIONSYSTEMID"
     */
    private BFSystem destinationSystem;
    
    public BFRoute(){
        
    }

    /**
     * @return the destinationSystem
     */
    public BFSystem getDestinationSystem() {
        return destinationSystem;
    }

    /**
     * @return the isOfficialRoute
     */
    public boolean isOfficialRoute() {
        return isOfficialRoute;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the startSystem
     */
    public BFSystem getStartSystem() {
        return startSystem;
    }

}
