package de.battleforge.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_LOGICALUNIT"
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFLogicalUnit {

    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;

    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="LOGICALUNITSTRUCTUREID"
     */
    private BFLogicalUnitStructure logicalUnitStructure;

    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;

    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="PARENTLOGICALUNITID"
     */
    private BFLogicalUnit parentLogicalUnit;

    /**
      * @jdo.field persistence-modifier="none"
//     * @jdo.field collection-type="collection"
//     *            element-type="de.battleforge.jdo.BFLogicalUnit"
//     *            persistence-modifier="persistent" table="BF_LOGICALUNIT"
//     * @jdo.join column="PARENTLOGICALUNITID"
//     * @jdo.element column="ID"
     */
    private ArrayList<BFLogicalUnit> childLogicalUnits;
    
//    private Set<BFLogicalUnit> childLogicalUnits = new HashSet<BFLogicalUnit>();

    /**
       * @jdo.field persistence-modifier="none"
//     * @jdo.field collection-type="collection"
//     *            element-type="de.battleforge.jdo.BFUnit"
//     *            persistence-modifier="persistent" table="BF_UNIT"
//     * @jdo.join column="LOGICALUNITID"
//     * @jdo.element column="ID"
     */
    private ArrayList<BFUnit> units;
//    private Set<BFUnit> units = new HashSet<BFUnit>();


    public BFLogicalUnit() {
       
    }

    /**
     * @return the logicalUnitStructur
     */
    public BFLogicalUnitStructure getLogicalUnitStructure() {
        return logicalUnitStructure;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the parentLogicalUnit
     */
    public BFLogicalUnit getParentLogicalUnit() {
        return parentLogicalUnit;
    }
       
    public void setParentLogicalUnit(BFLogicalUnit parentLogicalUnit) {
        this.parentLogicalUnit = parentLogicalUnit;
    }

    /**
     * @return Returns the owner.
     */
    public BFOwner getOwner() {
        return owner;
    }

    public void setOwner(BFOwner owner) {
        this.owner = owner;
    }

    /**
     * @return logical unit childs
     */
    public Collection<BFLogicalUnit> getChildLogicalUnits() {
        if(childLogicalUnits == null) {
            childLogicalUnits = new ArrayList(DBWrapper.getLogicalUnitChilds(this));
        }    
        return childLogicalUnits;
        
    }

    /**
     * @return Returns the units.
     */
    public Collection<BFUnit> getUnits() {
        if(units == null) {
            units = new ArrayList(DBWrapper.getUnitsForLogicalUnit(this));
        }    
        return units;
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogicalUnitStructure(BFLogicalUnitStructure logicalUnitStructure) {
        this.logicalUnitStructure = logicalUnitStructure;
    }
    
    
    
}