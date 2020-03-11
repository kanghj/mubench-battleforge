package de.battleforge.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.jpox.store.OID;

import de.battleforge.util.Internationalization;

public abstract class DBWrapper {

    private static PersistenceManagerFactory sCurrentPMFactory;

    private static PersistenceManager sCurrentPM;

    private static BFGameUser sCurrentGameUser;

    private static HashMap<BFSystem, BFSystemOwner> sSystemOwner = new HashMap<BFSystem, BFSystemOwner>();

    private static Collection<BFSystem> sSystems;

    private static Collection<BFUnit>  sUnitsForSystemOnly;
    
    private DBWrapper() {
        // do nothing

    } // DBWrapper

    public static BFGameUser getCurrentGameUser() {
        return sCurrentGameUser;

    }
    
    private static BFGameUser getCurrentGameUserTest(BFUser u) {
        Query q = sCurrentPM.newQuery(BFGameUser.class, "this.game == g && this.user == u");
        
        q.declareParameters("de.battleforge.jdo.BFGame g, de.battleforge.jdo.BFUser u");
        
        u.getGames();
        Iterator iter = ((HashSet)u.getGames()).iterator();
        BFGame game = (BFGame)iter.next();
        
        List result = (List) q.execute(game,u);

        return (BFGameUser) result.get(0);

    }
    
    public static BFUser doLogin(String user, String pwd) {
        Properties properties = new Properties();

        properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.jpox.PersistenceManagerFactoryImpl");
        properties.setProperty("javax.jdo.option.ConnectionDriverName", "net.sf.soapjdbc.SoapJdbcDriver");
        properties.setProperty("javax.jdo.option.ConnectionURL", "jdbc:soap://http://www.battleforge.de/soapJdbcGate/jdbc-gate.php");
        properties.setProperty("javax.jdo.option.ConnectionUserName", user);
        properties.setProperty("javax.jdo.option.ConnectionPassword", pwd);
        properties.setProperty("javax.jdo.option.NontransactionalWrite", "false");
        properties.setProperty("javax.jdo.option.NontransactionalRead", "true");
        properties.setProperty("javax.jdo.option.Multithreaded", "true");
        properties.setProperty("org.jpox.autoCreateSchema", "false");
        properties.setProperty("org.jpox.validateTables", "false");
        properties.setProperty("org.jpox.validateConstraints", "false");
        properties.setProperty("org.jpox.connectionPoolingType", "DBCP");
        properties.setProperty("org.jpox.cache.collections", "true");
        properties.setProperty("org.jpox.cache.collections.lazy", "false");
        properties.setProperty("org.jpox.maxFetchDepth", "-1");
        properties.setProperty("org.jpox.cache.level2", "true");
        
        sCurrentPMFactory = JDOHelper.getPersistenceManagerFactory(properties);

        try {
            sCurrentPM = sCurrentPMFactory.getPersistenceManager();

            // Query q = sCurrentPM.newQuery(BFGame.class);
            //            
            // Object r = q.execute();

//            BFUser u = new BFUser();
//
//            u.setName("Test0815");
//            u.setPassword("Test2");
//
//            Transaction tx = sCurrentPM.currentTransaction();
//
//            try {
//                tx.begin();
//
//                Object object = sCurrentPM.makePersistent(u);
//                
//                tx.commit();
//                
//            } catch (Exception e) {
//                // Handle the exception
//                e.printStackTrace();
//                
//            } finally {
//                if (tx.isActive()) {
//                    tx.rollback();
//                }
//            }
            Query query = sCurrentPM.newQuery(BFUser.class, "name.equals(\"" + user + "\") && password.equals(\"" + pwd + "\")");

            List result = (List) query.execute();

            sCurrentGameUser = getCurrentGameUserTest((BFUser) result.get(0));
            
            return (BFUser) result.get(0);

        } catch (JDODataStoreException ex) {
            sCurrentPMFactory.close();

            sCurrentPM = null;

            sCurrentPMFactory = null;

            return null;

        } // try
    }

    public static void doLogout() {
    }

    
    public static PersistenceManager getCurrentPM() {
        return sCurrentPM;
        
    }
    
    public static BFLogicalUnit[] getLogicalUnits(BFOwner owner) {
        Query q = sCurrentPM.newQuery(BFLogicalUnit.class, "this.owner == o && this.parentLogicalUnit == null");

        q.declareParameters("de.battleforge.jdo.BFOwner o");
        
        Collection<BFLogicalUnit> units = (Collection<BFLogicalUnit>) q.execute(owner);
        
        return units.toArray(new BFLogicalUnit[units.size()]);
        
    }
    
    public static BFJumpship[] getJumpships(BFOwner owner) {
        Query q = sCurrentPM.newQuery(BFJumpship.class, "this.owner == o");

        q.declareParameters("de.battleforge.jdo.BFOwner o");        
        Collection<BFJumpship> jumpships = (Collection<BFJumpship>) q.execute(owner);
        
        return jumpships.toArray(new BFJumpship[jumpships.size()]);
        
    }
    
    public static BFMech[] getMechs(BFOwner owner) {
        Query q = sCurrentPM.newQuery(BFMech.class, "this.owner == o");

        q.declareParameters("de.battleforge.jdo.BFOwner o");        
        Collection<BFMech> mechs = (Collection<BFMech>) q.execute(owner);
        
        return mechs.toArray(new BFMech[mechs.size()]);
        
    }
    
    public static BFDropship[] getDropships(BFOwner owner) {
        Query q = sCurrentPM.newQuery(BFDropship.class, "this.owner == o");

        q.declareParameters("de.battleforge.jdo.BFOwner o");
        Collection<BFDropship> dropship = (Collection<BFDropship>) q.execute(owner);
        
        return dropship.toArray(new BFDropship[dropship.size()]);
        
    }
    
    public static Collection<BFOwner> getOwner() {
        Query q = sCurrentPM.newQuery(BFOwner.class);

        return (Collection<BFOwner>) q.execute();

    }
    
    public static Collection<BFOwnerType> getOwnerType() {
        Query q = sCurrentPM.newQuery(BFOwnerType.class);

        return (Collection<BFOwnerType>) q.execute();

    }
    
    public static Collection<BFLogicalUnitStructure> getLogicalUnitStructure(BFOwner owner) {
//        Query q = sCurrentPM.newQuery(BFLogicalUnitStructure.class, "this.owner == o || this.owner == null");
//        q.declareParameters("de.battleforge.jdo.BFOwner o");

        Query q = sCurrentPM.newQuery(BFLogicalUnitStructure.class);
        
        return (Collection<BFLogicalUnitStructure>) q.execute(owner);
        
    }
    
    public static BFSystem[] getSystemNeighbour(BFSystem system){
        int x = system.getX();
        int y = system.getY();        

        //TODO: check it
        Query q = sCurrentPM.newQuery(BFSystem.class, "this.x <= x && this.y <= y ");
        
        q.declareParameters("int x, int y");
        
        Collection<BFSystem> systems = (Collection<BFSystem>) q.execute(x,y);

        return (BFSystem[])systems.toArray();
    }
    
    public static BFSystem[] getAllSystems() {
        if ( sSystems == null ) {
            Query q = sCurrentPM.newQuery(BFSystem.class);
            
            sSystems = (Collection<BFSystem>) q.execute();
            
            q = sCurrentPM.newQuery(BFSystemOwner.class, "this.game == g");
            
            q.declareParameters("de.battleforge.jdo.BFGame g");
            
            for (BFSystemOwner sysOwner : (Collection<BFSystemOwner>) q.execute(sCurrentGameUser.getGame())) {
                sysOwner.getSystem().setOwner(sysOwner.getOwner());
                sysOwner.getOwner().addSystem(sysOwner.getSystem());
                
            } // for
        }
        
        return sSystems.toArray(new BFSystem[sSystems.size()]);
        
    }
    
//    public static BFOwner getSystemOwner(BFSystem system) {
//        BFSystemOwner systemOwner = sSystemOwner.get( system );
//        
//        if ( systemOwner == null ) {
//            Query q = sCurrentPM.newQuery(BFSystemOwner.class, "this.game == g");
//            
//            q.declareParameters("de.battleforge.jdo.BFGame g");
//            
//            Collection<BFSystemOwner> systems = (Collection<BFSystemOwner>) q.execute(sCurrentGameUser.getGame());
//            
//            //System.out.println(system.getName());
//            
//            //systemOwner = systems.toArray(new BFSystemOwner[systems.size()])[0];
//            
//            for (BFSystemOwner sysOwner : systems) {
//                sSystemOwner.put( sysOwner.getSystem(), systemOwner );
//                
//            } // for
//        }
//
////        systemOwner = sSystemOwner.get( system );
//        
//        return systemOwner.getOwner();
//        
//        
//    }
    
    public static BFSystem[] getAllSystems(BFOwner o) {
        Query q = sCurrentPM.newQuery(BFSystem.class, "this.owner == o");
        
        q.declareParameters("de.battleforge.jdo.BFOwner o");
        
        Collection<BFSystem> systems = (Collection<BFSystem>) q.execute(sCurrentGameUser.getOwner());
                
        return systems.toArray(new BFSystem[systems.size()]);
        
    }
    
    public static Collection getAllUnitsForSystems() {
        if(sUnitsForSystemOnly == null) {
            Query q = sCurrentPM.newQuery(BFUnit.class, "this.game == g && this.currentDockingUnit == null");
            q.declareParameters("de.battleforge.jdo.BFGame g");
            sUnitsForSystemOnly = (Collection<BFUnit>) q.execute(sCurrentGameUser.getGame());
            
            for( BFUnit unit :sUnitsForSystemOnly){
                
               if(unit.getCurrentSystem() != null) {
                   unit.getCurrentSystem().getUnits().add(unit);
                   
               } //if                 
            } //for            
        } //if
        
        return sUnitsForSystemOnly;
        
    }

    public static Collection<BFStartArea> getStartArea() {
        Query q = sCurrentPM.newQuery(BFStartArea.class);

        return (Collection<BFStartArea>) q.execute();
    }

    public static Collection<BFUnit> getDockingUnits(BFUnit unit) {
        Query q = sCurrentPM.newQuery(BFUnit.class, "this.currentDockingUnit == unit");
        
        q.declareParameters("de.battleforge.jdo.BFUnit unit");
        
        return (Collection<BFUnit>) q.execute(unit);
        
    }
    
    public static BFSystemDescription getSystemDescription(BFSystem system, String lc){
        Query q = sCurrentPM.newQuery(BFSystemDescription.class, "this.system == s && this.languageCode == lc");

        q.declareParameters("de.battleforge.jdo.BFSystem s, java.lang.String lc");
        
        Collection<BFSystemDescription> description = (Collection<BFSystemDescription>) q.execute(system, lc);
        
        if(description.size() > 0){
           return (BFSystemDescription)description.iterator().next();
           
        } //if
        
        return null;
    }

    public static Collection<BFUnit> getChildUnits(BFUnit unit) {
        Query q = sCurrentPM.newQuery(BFUnit.class, "this.currentDockingUnit == unit");
        
        q.declareParameters("de.battleforge.jdo.BFUnit unit");
        
        return new ArrayList<BFUnit>((Collection<BFUnit>) q.execute(unit));

    }

    /**
     * Returns the owner for a system for a given year
     * @param sa
     * @param s
     * @return
     */
    public static BFStartSystemOwner getOwnerForStartArea(BFStartArea sa, BFSystem s) {
        Query q = sCurrentPM.newQuery(BFStartSystemOwner.class, "this.startArea == sa && this.system == s");

        q.declareParameters("de.battleforge.jdo.BFStartSystemOwner sa, de.battleforge.jdo.BFSystem s");
        Collection<BFStartSystemOwner> owner = (Collection<BFStartSystemOwner>) q.execute(sa, s);

        return (BFStartSystemOwner)owner.toArray()[0];
    }
    
    public static Collection<BFSystemProperty> getBuildingList(BFSystem s) {
        BFSystemOwner so = getSystemOwner(s);
        
        Query q = sCurrentPM.newQuery(BFSystemProperty.class, "this.systemOwner == systemowner");
  
        q.declareParameters("de.battleforge.jdo.BFSystemOwner systemowner");
        
        Collection<BFSystemProperty> c = (Collection<BFSystemProperty>) q.execute(so);
        return c;

    }
    
    private static BFSystemOwner getSystemOwner(BFSystem system){
        Query q = sCurrentPM.newQuery(BFSystemOwner.class, "this.system == s");

        q.declareParameters("de.battleforge.jdo.BFSystem s");
        
        Collection<BFSystemOwner> sysOwner = (Collection<BFSystemOwner>) q.execute(system);
        
        if(sysOwner.size() > 0){
           return (BFSystemOwner)sysOwner.iterator().next();
           
        } //if
        
        return null;
    }
    
    public static Collection<BFLogicalUnit> getLogicalUnitChilds(BFLogicalUnit lo){
        Query q = sCurrentPM.newQuery(BFLogicalUnit.class, "this.parentLogicalUnit == l");

        q.declareParameters("de.battleforge.jdo.BFLogicalUnit l");
        
        return (Collection<BFLogicalUnit>) q.execute(lo);
    }
    
    public static Collection<BFUnit> getUnitsForLogicalUnit(BFLogicalUnit lo){
        Query q = sCurrentPM.newQuery(BFUnit.class, "this.logicalUnit == l");

        q.declareParameters("de.battleforge.jdo.BFLogicalUnit l");
        
        return (Collection<BFUnit>) q.execute(lo);
    }
    
    
}
