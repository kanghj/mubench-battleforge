package de.battleforge.jdo;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_SYSTEMDESCRIPTION" 
 * @jdo.datastore-identity strategy="native" column="ID"
 * 
 */

public class BFSystemDescription {
    
    /**
     * @jdo.field
     * @jdo.column name="DESCRIPTION" jdbc-type="LONGVARCHAR"
     */
    private String description;
    
    /**
     * @jdo.field default-fetch-group="false"
     * @jdo.column name="SYSTEMID"
     */
    private BFSystem system;
    
    /**
     * @jdo.field
     * @jdo.column name="LANGUAGECODE" jdbc-type="VARCHAR" length=2
     */
    private String languageCode;
    
    public BFSystemDescription(){
        
    }

    /**
     * @return the languageCode
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     */
    public void setDescription(String value) {
        description = value;
    }

    /**
     * @return the system
     */
    public BFSystem getSystem() {
        return system;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setSystem(BFSystem system) {
        this.system = system;
    }
}
