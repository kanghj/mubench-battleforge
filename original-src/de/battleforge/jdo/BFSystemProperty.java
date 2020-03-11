package de.battleforge.jdo;

import sun.util.calendar.BaseCalendar.Date;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_SYSTEMPROPERTY" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFSystemProperty {
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="SYSTEMOWNERID"
     */
    private BFSystemOwner systemOwner;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="BUILDINGID"
     */
    private BFBuilding building;
    
    /**
     * @jdo.field
     * @jdo.column name="ISREADY" jdbc-type="TINYINT"
     */
    private boolean isReady;
    
    /**
     * @jdo.field
     * @jdo.column name="PROGRESS" jdbc-type="INTEGER"
     */
    private int progress;    
    
    /**
     * @jdo.field
     * @jdo.column name="INDUSTRIESLOTS" jdbc-type="INTEGER"
     */
    private int industrieSlots;
    
    /**
     * @jdo.field
     * @jdo.column name="AUTOMATICPRODUCTION" jdbc-type="TINYINT"
     */
    private boolean automaticProduction;
    
    public BFSystemProperty(){
        
    }

    /**
     * @return the automaticProduction
     */
    public boolean isAutomaticProduction() {
        return automaticProduction;
    }

    /**
     * @return the building
     */
    public BFBuilding getBuilding() {
        return building;
    }

    /**
     * @return the industrieSlots
     */
    public int getIndustrieSlots() {
        return industrieSlots;
    }

    /**
     * @return the isReady
     */
    public boolean isReady() {
        return isReady;
    }

    /**
     * @return the progress
     */
    public int getProgress() {
        return progress;
    }

    /**
     * @return the systemOwner
     */
    public BFSystemOwner getSystemOwner() {
        return systemOwner;
    }

}
