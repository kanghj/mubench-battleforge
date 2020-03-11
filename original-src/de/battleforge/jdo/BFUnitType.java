package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_UNITTYPE" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */

public class BFUnitType {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="UNITCLASSID"
     */
    private BFUnitClass unitClass;
    
    /**
     * @jdo.field
     * @jdo.column name="CONSTRUCTIONTIME" jdbc-type="INTEGER"
     */
    private int constructionTime;
    
    /**
     * @jdo.field
     * @jdo.column name="PURCASEPRICE" jdbc-type="DOUBLE"
     */
    private double purcasePrice;
    
    /**
     * @jdo.field
     * @jdo.column name="MAINTENANCECOSTS" jdbc-type="DOUBLE"
     */
    private double maintenanceCosts;
    
    /**
     * @jdo.field
     * @jdo.column name="NEEDEDINDUSTRIESLOTS" jdbc-type="INTEGER"
     */
    private int neededIndustrieSlots;
    
    public BFUnitType(){
        
    }

    /**
     * @return the constructionTime
     */
    public int getConstructionTime() {
        return constructionTime;
    }

    /**
     * @return the maintenanceCosts
     */
    public double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    /**
     * @return the neededIndustrieSlots
     */
    public int getNeededIndustrieSlots() {
        return neededIndustrieSlots;
    }

    /**
     * @return the purcasePrice
     */
    public double getPurcasePrice() {
        return purcasePrice;
    }

    /**
     * @return the unitClass
     */
    public BFUnitClass getUnitClass() {
        return unitClass;
    }

    /**
     * @return the unitTypName
     */
    public String getName() {
        return name;
    }
    
}
