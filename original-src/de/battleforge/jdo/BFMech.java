package de.battleforge.jdo;

/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_MECH" persistence-capable-superclass="de.battleforge.jdo.BFUnit
 * @jdo.datastore-identity strategy="native" column="UNITID"
 * @jdo.inheritance strategy="new-table"
 */
public class BFMech extends BFUnit {
    
      
    public BFMech(){
    }


}
