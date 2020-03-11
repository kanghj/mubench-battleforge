package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_PRODUCTION" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFProduction {
    
    /**
     * @jdo.field
     * @jdo.column name="USEDINDUSTRIESLOTS" jdbc-type="INTEGER"
     */
    private int usedIndustrieSlots;
    
    /**
     * @jdo.field
     * @jdo.column name="PIPELINENUMBER" jdbc-type="INTEGER"
     */
    private int pipelineNumber;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="UNITID"
     */
    private BFUnit unit;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @j do.column name="SYSTEMPROPERTYID"
     */
    private BFSystemProperty systemPropertyID;
    
    public BFProduction(){
        
    }

    /**
     * @return the pipelineNumber
     */
    public int getPipelineNumber() {
        return pipelineNumber;
    }

    /**
     * @return the systemPropertyID
     */
    public BFSystemProperty getSystemPropertyID() {
        return systemPropertyID;
    }

    /**
     * @return the unit
     */
    public BFUnit getUnit() {
        return unit;
    }

    /**
     * @return the usedIndustrieSlots
     */
    public int getUsedIndustrieSlots() {
        return usedIndustrieSlots;
    }

}
