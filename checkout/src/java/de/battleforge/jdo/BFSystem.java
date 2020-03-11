package de.battleforge.jdo;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 
 * 
 * @author kotzbrocken2
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_SYSTEM"
 * @jdo.datastore-identity strategy="native" column="ID"
// * @jdo.fetch-group name="hallo" fields="owner"
 */
public class BFSystem {

    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="X" jdbc-type="INTEGER"
     */
    private int x;

    /**
     * @jdo.field
     * @jdo.column name="Y" jdbc-type="INTEGER"
     */
    private int y;

    /**
     * @jdo.field
     * @jdo.column name="Z" jdbc-type="INTEGER"
     */
    private Integer z;
    
    /**
     * @jdo.field
     */
    private Integer population;
    
    /**
     * @jdo.field
     * @jdo.column name="RESOURCES"
     */
    private Integer resources;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="POPULATIONSTATUSID"
     * @jdo.persistence-modifier="persistent"
     */
    private BFPopulationStatus populationStatus;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="STARTYPEID"
     * @jdo.persistence-modifier="persistent"
     */
    private BFStarType starType;
    
    /**
     * @jdo.field
     * @jdo.column name="CAPITAL" jdbc-type="VARCHAR" length="255"
     */
    private String capital;
    
    /**
     * @jdo.field
     * @jdo.column name="TEMPERATURE" jdbc-type="INTEGER"
     */
    private Integer temperature;
    
    /**
     * @jdo.field
     * @jdo.column name="POSITION" jdbc-type="INTEGER"
     */
    private Integer position;
    
    /**
     * @jdo.field
     * @jdo.column name="MOONS" jdbc-type="INTEGER"
     */
    private Integer moons;
    
    /**
     * @jdo.field
     * @jdo.column name="GRAVITATION" jdbc-type="DOUBLE"
     */
    private Double gravitation;
    
    /**
     * @jdo.field
     * @jdo.column name="WATERPERCENTAGE" jdbc-type="INTEGER"
     */
    private Integer waterPercentage;
    
    /**
     * @jdo.field
     * @jdo.column name="LIFEFORM" jdbc-type="VARCHAR" length="255"
     */
    private String lifeForm;
    
    /**
     * @jdo.field
     * @jdo.column name="MAINPLANET" jdbc-type="INTEGER"
     */
    private Integer mainPlanet;
    
    /**
     * @jdo.field
     * @jdo.column name="SYSTEMIMAGENAME" jdbc-type="VARCHAR" length="255"
     */
    private String systemImageName;
    
    /**
     * @jdo.field persistence-modifier="none"
//     *  collection-type="map" key-type="de.battleforge.jdo.BFGame" value-type="de.battleforge.jdo.BFOwner"
//     *            persistence-modifier="none" table="BF_SYSTEMOWNER"
//     * @jdo.join column="SYSTEMID"
//     * @jdo.key column="GAMEID"
//     * @jdo.value column="OWNERID"
     */
    private BFOwner owner;
    
    /**
     * @jdo.field persistence-modifier="none"
     */
    private Collection<BFUnit> units;
   
    
    private BFSystem() {
        units = new ArrayList<BFUnit>();
        
    }
    
    public String getName() {
        return name;
        
    } // getName
    
    @Override
    public String toString() {
        return "System: " + getName();
    }

    /**
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @return the gravitation
     */
    public Double getGravitation() {
        return gravitation;
    }

    /**
     * @return the lifeForm
     */
    public String getLifeForm() {
        return lifeForm;
    }

    /**
     * @return the mainPlanet
     */
    public Integer getMainPlanet() {
        return mainPlanet;
    }

    /**
     * @return the moons
     */
    public Integer getMoons() {
        return moons;
    }

    /**
     * @return the population
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * @return the populationStatus
     */
    public BFPopulationStatus getPopulationStatus() {
        return populationStatus;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @return the resources
     */
    public Integer getResources() {
        return resources;
    }

    /**
     * @return the starType
     */
    public BFStarType getStarType() {
        return starType;
    }

    /**
     * @return the systemImageName
     */
    public String getSystemImageName() {
        return systemImageName;
    }

    /**
     * @return the temperature
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * @return the waterPercentage
     */
    public Integer getWaterPercentage() {
        return waterPercentage;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the z
     */
    public Integer getZ() {
        return z;
    }
    
//    public BFOwner getOwner(BFGame game) {
//        System.out.println(getName());
//        return (BFOwner) owner.get(game);
//    }
//
    
    public Collection<BFUnit> getUnits() {
        return units;

    }

    public void setOwner(BFOwner o) {
        owner = o;
        
    }
    
    public BFOwner getOwner() {
        return owner;

    }
}
