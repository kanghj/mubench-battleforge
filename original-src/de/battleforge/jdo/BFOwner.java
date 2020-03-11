package de.battleforge.jdo;

import java.util.ArrayList;


/**
 * 
 * @author kotzbrocken2
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_OWNER" persistence-modifier="persistence-capable" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFOwner {

    /**
     * @jdo.field
     * @jdo.column jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="SHORTNAME" jdbc-type="VARCHAR" length="10"
     W*/
    private String shortName;

    /**
    * @jdo.field default-fetch-group="true"
     *@jdo.column name="COLORID"
     */
    private BFColor color;
    
    /**
     * @jdo.field
     * @jdo.column jdbc-type="VARCHAR" length="255"
     */
    private String logo;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="OWNERTYPEID"
     */
    private BFOwnerType ownerType;
    
    /**
     * @jdo.field default-fetch-group="true"
     * @jdo.column name="STARTAREAID"
     */
    private BFStartArea startArea;
    
//    /**
//     * @jdo.field default-fetch-group="true"
//     *            collection-type="collection"
//     *            element-type="de.battleforge.jdo.BFSystem"
//     *            persistence-modifier="persistent"
//     *            mapped-by="owner"
//     */
//    private Set systems = new HashSet();
    
//    /**
//     * @jdo.field default-fetch-group="true"
//     *            collection-type="collection"
//     *            element-type="de.battleforge.jdo.BFSystem"
//     *            persistence-modifier="persistent" table="BF_SYSTEMOWNER"
//     * @jdo.join column="OWNERID"
//     * @jdo.element column="ID"
//     */
//    private Set<BFSystem> systems = new HashSet<BFSystem>();

    /**
     * @jdo.field persistence-modifier="none"
     */
    private ArrayList<BFSystem> systems = new ArrayList<BFSystem>();
    
    private BFOwner() {
    }
    
    
    @Override
    public String toString() {
        return "Owner: " + name;
        
    }


//    public Set getSystems() {
//        return systems;
//        
//    }

    // TODO: anschauen
    public BFSystem[] getSystems(){
        return systems.toArray(new BFSystem[systems.size()]);
//        return DBWrapper.getAllSystems(this);
        
    }
    
    // TODO: anschauen
    public boolean getHasSystems(){
        return true;
        
//        return DBWrapper.getAllSystems(this).length > 0;
        
    }

    /**
     * @return the color
     */
    public BFColor getColor() {
        return color;
    }


    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @return the nameShort
     */
    public String getShortName() {
        return shortName;
    }


    /**
     * @return the ownerType
     */
    public BFOwnerType getOwnerType() {
        return ownerType;
    }


    /**
     * @return the startArea
     */
    public BFStartArea getStartArea() {
        return startArea;
    }


    public void addSystem(BFSystem system) {
        systems.add(system);
        
    }
}
