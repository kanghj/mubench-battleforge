package de.battleforge.jdo;

import java.util.HashSet;
import java.util.Set;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_LOGICALUNITSTRUCTURE" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFLogicalUnitStructure {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="PARENTLOGICALUNITSTRUCTUREID"
     */
    private BFLogicalUnitStructure parentLogicalUnitStructure;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERTYPEID"
     */
    private BFOwnerType ownerTyp;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="OWNERID"
     */
    private BFOwner owner;
    
    /**
     * @jdo.field collection-type="collection"
     *            element-type="de.battleforge.jdo.BFLogicalUnitStructure"
     *            persistence-modifier="persistent" table="BF_LOGICALUNITSTRUCTURE"
     * @jdo.join column="PARENTLOGICALUNITSTRUCTUREID"
     * @jdo.element column="ID"
     */
    private Set<BFLogicalUnit> childLogicalUnitStructures = new HashSet<BFLogicalUnit>();
    
    public BFLogicalUnitStructure(){
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the owner
     */
    public BFOwner getOwner() {
        return owner;
    }

    /**
     * @return the ownerTyp
     */
    public BFOwnerType getOwnerTyp() {
        return ownerTyp;
    }

    /**
     * @return the parentLogicalUnitStructur
     */
    public BFLogicalUnitStructure getParentLogicalUnitStructur() {
        return parentLogicalUnitStructure;
    }

    /**
     * @return Returns the childLogicalUnitStructures.
     */
    public Set<BFLogicalUnit> getChildLogicalUnitStructures() {
        return childLogicalUnitStructures;
    }    

}
