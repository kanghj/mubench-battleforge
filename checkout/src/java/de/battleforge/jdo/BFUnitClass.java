package de.battleforge.jdo;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_UNITCLASS" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */
public class BFUnitClass {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field collection-type="collection"
     *            element-type="de.battleforge.jdo.BFUnitClass"
     *            persistence-modifier="persistent" table="BF_UNITCLASS"
     * @jdo.join column="PARENT"
     * @jdo.element column="ID"
     */
    private Set<BFUnitClass> dockingPossibilities = new HashSet<BFUnitClass>();
    
    public BFUnitClass(){
        
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    public Set<BFUnitClass> getDockingPossibilities() {
        return dockingPossibilities;
    }
    
}
