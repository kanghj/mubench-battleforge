package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_BUILDING" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFBuilding {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="MAINTENANCECOSTS" jdbc-type="DOUBLE"
     */
    private double maintenanceCosts;
    
    /**
     * @jdo.field
     * @jdo.column name="PURCASEPRICE" jdbc-type="DOUBLE"
     */
    private double purcasePrice;
    
    /**
     * @jdo.field
     * @jdo.column name="BUILDINGTIME" jdbc-type="DOUBLE"
     */
    private double buildingTime;
    
    public BFBuilding(){
        
    }

    /**
     * @return the buildingTime
     */
    public double getBuildingTime() {
        return buildingTime;
    }

    /**
     * @return the maintenanceCosts
     */
    public double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the purcasePrice
     */
    public double getPurcasePrice() {
        return purcasePrice;
    }

}
