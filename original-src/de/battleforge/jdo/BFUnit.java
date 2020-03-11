package de.battleforge.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_UNIT" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * @jdo.inheritance strategy="new-table"
 */
public class BFUnit {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="GAMEID"
     */
    private BFGame game;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="UNITTYPID"
     */
    private BFUnitType unitTyp;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="LOGICALUNITID"
     */
    private BFLogicalUnit logicalUnit;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="ROUTEID"
     */
    private BFRoute route;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="ROUTEDETAILSID"
     */  
    private BFRouteDetails routeDetails;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="CURRENTSYSTEMID"
     * @jdo.persistence-modifier="persistent"
     */
    private BFSystem currentSystem;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="CURRENTDOCKINGUNITID"
     * @jdo.persistence-modifier="persistent"
     */
    private BFUnit currentDockingUnit;
    
    /**
      * @jdo.field persistence-modifier="none"
//     * @jdo.field collection-type="collection"
//     *            element-type="de.battleforge.jdo.BFUnit"
//     *            persistence-modifier="persistent" table="BF_UNIT"
//     * @jdo.join column="CURRENTDOCKINGUNITID"
//     * @jdo.element column="ID" embedded-element="true"
     */
    private ArrayList<BFUnit> childUnits;
//  private Set<BFUnit> childUnits = new HashSet<BFUnit>();
    
    public BFUnit(){
        
    }

    /**
     * @return the game
     */
    public BFGame getGame() {
        return game;
    }

    /**
     * @return the logicalUnit
     */
    public BFLogicalUnit getLogicalUnit() {
        return logicalUnit;
    }

    /**
     * @return the owner
     */
    public BFOwner getOwner() {
        return owner;
    }

    /**
     * @return the route
     */
    public BFRoute getRoute() {
        return route;
    }

    /**
     * @return the routeDetails
     */
    public BFRouteDetails getRouteDetails() {
        return routeDetails;
    }

    /**
     * @return the unitName
     */
    public String getName() {
        return name;
    }

    /**
     * @return the unitTyp
     */
    public BFUnitType getUnitTyp() {
        return unitTyp;
    }

    /**
     * @param logicalUnit The logicalUnit to set.
     */
    public void setLogicalUnit(BFLogicalUnit logicalUnit) {
        this.logicalUnit = logicalUnit;
    }

    /**
     * @return Returns the currentDockingUnit.
     */
    public BFUnit getCurrentDockingUnit() {
        return currentDockingUnit;
    }

    /**
     * @return Returns the currentSystem.
     */
    public BFSystem getCurrentSystem() {
        return currentSystem;
    }
       
    /**
     * @return set the current docking unit.
     */
    public void setCurrentDockingUnit(BFUnit unit) {
        if(unit != null) {
            if(currentDockingUnit != null) {
                currentDockingUnit.getChildUnits().remove(this);
                
            } else {
                currentSystem.getUnits().remove(this);
                
            } //if
            
            currentSystem = null;
            currentDockingUnit = unit;
            unit.getChildUnits().add(this);
            
        }
    }    

    /**
     * @return set the current system.
     */
    public void setCurrentSystem( BFSystem system) {
        if(system != null) {
            if(currentDockingUnit != null) {
                currentDockingUnit.getChildUnits().remove(this);
                
            }  else {
                currentSystem.getUnits().remove(this);
                
            } //if

            currentDockingUnit = null;
            currentSystem = system;
            system.getUnits().add(this);
            
        }  
    }
    
    /**
     * @return Returns all docking units.
     */
    public Collection<BFUnit> getChildUnits() {        
        if(childUnits == null) {
            childUnits = new ArrayList(DBWrapper.getChildUnits(this));
        }    
        return childUnits;
    }    
 
    /** 
     * @return returns true if the unit can land on the current system
     */
    public boolean canLandOnCurrentSystem() {
        boolean canLand = false;
        if( getCurrentDockingUnit() != null && getCurrentDockingUnit().getCurrentSystem() != null) {
            canLand = true;
            
        }                       
        return canLand;
    }
    
    /**
     * @return
     */
    public boolean canDocking(BFUnit dockingUnit) {
        boolean canDocking = false;
        
        /*TODO: Abfrage nicht schön. Hier wird noch eine neuen Zuordnungtabelle angelegt in der
         *      hinterlegt wird welche Einheiten an welche Einheit gedockt werden können
         */
        
        if( ("DropShip".equals(dockingUnit.getUnitTyp().getUnitClass().getName()) && 
                "Mech".equals(getUnitTyp().getUnitClass().getName())) ||
                ("Jumpship".equals(dockingUnit.getUnitTyp().getUnitClass().getName()) && 
                        "DropShip".equals(getUnitTyp().getUnitClass().getName())) &&
                        getCurrentDockingUnit() != dockingUnit                        
                ){
            canDocking = true;
        }                               
        
        return canDocking;
    }
}
