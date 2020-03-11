package de.battleforge.jdo;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.jdo.Extent;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import de.battleforge.net.HTTPReader;
import de.battleforge.util.BFProperties;


public class TestJDO {


    public static void main( String[] args ) throws Exception {
        
        File dir = new File( System.getProperty( "user.home" ) + File.separator + "Battleforge" );

        BFProperties.loadUserProperties( dir.toString() );
        
        new HTTPReader(null);

        long startTime = System.currentTimeMillis();
        
        //DefaultTypeMappingImpl.getSingletonDelegate().register(String.class, new QName( "http://www.w3.org/2001/XMLSchema","ur-type"), new UrTypeSerializerFactory(), new UrTypeDeserializerFactory())

//        BFListSystem systemList = new BFListSystem();
//
//        try {
//            systemList.refresh();
//        
//        } catch (BFException e2) {
//            // TODO Automatisch erstellter Catch-Block
//            e2.printStackTrace();
//        }
//        
//        BFListOwner ownerList = new BFListOwner();
//        
//        try {
//            ownerList.refresh();
//            
//        } catch (BFException e1) {
//            // TODO Automatisch erstellter Catch-Block
//            e1.printStackTrace();
//        }
//
//        System.err.println( "old-time: " + (System.currentTimeMillis() - startTime) + " ms." );

//        ProxyHelper ph = new ProxyHelper( Forward.class );
//
//        Forward service = (Forward) ph.bind();
//        
//        try {
//            SoapArray array = service.executeQuery("SELECT * FROM bf_systems" );
//        
//        } catch (SoapException e1) {
//            // TODO Automatisch erstellter Catch-Block
//            e1.printStackTrace();
//        }
//        
//        System.out.println("time1: " + (System.currentTimeMillis() - startTime) + " ms");
//
//        startTime = System.currentTimeMillis();

//        try {
//            SoapForward soapCall = (SoapForward)
//                SoapServices.getDefault().createStartpoint(
//                        "http://localhost/soapJdbcGate/jdbc-gate.php",
//                        new Class[]{SoapForward.class}, // remote service interface
//                        "urn:GoogleSearch", // endpoint name
//                        SoapStyle.IBMSOAP,
//                        "" // SOAPAction
//                );
//            
//            soapCall.doLogin("Werner","Ba9cW5uZC48Lo");
//            
//            //Map[] resultSetMetadata = soapCall.exe("select * from BF_SYSTEMS");
//            
//            //String databaseProductName = soapCall.getDatabaseProductName();
//
//            Object res = soapCall.executeQuery("select * from BF_SYSTEMS", true);
//            //Object res = soapCall.execute("select * from BF_SYSTEMS", true);
//            //Object res = soapCall.execute("insert into BF_SYSTEMS (systemid, system ,x_kor, y_kor) values( 99999, 'Torte', 1, 1 )", true);
//            //Object res = soapCall.execute("delete from BF_SYSTEMS where systemid=99999", true);
//            
//            System.out.println("call returned: " + res);
//
//        } catch (RemoteException e1) {
//            // TODO Automatisch erstellter Catch-Block
//            e1.printStackTrace();
//        }
//        
//        System.out.println("time2: " + (System.currentTimeMillis() - startTime) + " ms");

        Properties properties = new Properties();

        properties.setProperty( "javax.jdo.PersistenceManagerFactoryClass", "org.jpox.PersistenceManagerFactoryImpl" );
        properties.setProperty( "javax.jdo.option.ConnectionDriverName", "net.sf.soapjdbc.SoapJdbcDriver" );
        properties.setProperty( "javax.jdo.option.ConnectionURL", "jdbc:soap://http://localhost:100/soapJdbcGate/jdbc-gate.php" );
//        properties.setProperty( "javax.jdo.option.ConnectionURL", "jdbc:soap://http://www.battleforge.de/soapJdbcGate/jdbc-gate.php" );
//        properties.setProperty( "javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.Driver" );
//        properties.setProperty( "javax.jdo.option.ConnectionURL", "jdbc:mysql://localhost/www_battleforge_de_testgame" );
//        properties.setProperty( "javax.jdo.option.ConnectionDriverName", "org.hsqldb.jdbcDriver" );
//        properties.setProperty( "javax.jdo.option.ConnectionURL",
//                "jdbc:hsqldb:C:/Dokumente und Einstellungen/dirk.ziegenbalg/Battleforge/HSQLDB/bfdb" );
      properties.setProperty( "javax.jdo.option.ConnectionUserName", "Werner" );
      properties.setProperty( "javax.jdo.option.ConnectionPassword", "Ba9cW5uZC48Lo" );
//        properties.setProperty( "javax.jdo.option.ConnectionUserName", "root" );
//        properties.setProperty( "javax.jdo.option.ConnectionPassword", "" );
//        properties.setProperty( "javax.jdo.option.ConnectionUserName", "sa" );
//        properties.setProperty( "javax.jdo.option.ConnectionPassword", "" );
        properties.setProperty( "javax.jdo.option.NontransactionalWrite", "false" );
        properties.setProperty( "javax.jdo.option.NontransactionalRead", "true" );
        properties.setProperty( "javax.jdo.option.Multithreaded", "true" );
        properties.setProperty( "org.jpox.autoCreateSchema", "false" );
//        properties.setProperty( "org.jpox.autoCreateColumns", "true" );
//        properties.setProperty( "org.jpox.autoCreateTables", "true" );
        properties.setProperty( "org.jpox.validateTables", "false" );
        properties.setProperty( "org.jpox.validateConstraints", "true" );
        properties.setProperty("org.jpox.connectionPoolingType", "DBCP");
        properties.setProperty("org.jpox.cache.collections.lazy", "true");
        properties.setProperty("org.jpox.cache.level2", "true");

        PersistenceManagerFactory pmfactory = JDOHelper.getPersistenceManagerFactory( properties );
        PersistenceManager pm = pmfactory.getPersistenceManager();
        
        pmfactory.getDataStoreCache().pinAll(BFUser.class, true);
        
        try {
            Extent e = pm.getExtent( BFUser.class, true );

            Query q = pm.newQuery( e, "name.equals(\"Torte\")");

            Collection c = (Collection) q.execute();
            
            System.out.println(c);

            BFUser u = (BFUser) c.iterator().next();
            
           //BFUser u = new BFUser();

            Transaction tx = pm.currentTransaction();
            
            try {
                tx.begin();
                            
                u.setName("Wänä");
                u.setPassword("Brösel0815");

                Object object = pm.makePersistent(u);
                
                tx.commit();
                
//                tx.begin();
//                
//                u.setName("Peng");
//
//                object = pm.makePersistent(u);
//                
//                tx.commit();

            } catch (Exception ex) {
                // Handle the exception
                ex.printStackTrace();
                
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            e = pm.getExtent( BFUser.class, true );

            q = pm.newQuery( e );

            c = (Collection) q.execute();
            
            System.out.println(c);

            e = pm.getExtent( BFUser.class, true );

//            q = pm.newQuery( e );

            c = (Collection) q.execute();

        } catch (JDODataStoreException ex) {
            ex.printStackTrace();

        } // try
        
        //boolean b = con.createStatement().execute("CREATE TABLE `DELETEME1144865595874`");

        ArrayList<BFSystem> systems = new ArrayList<BFSystem>();
        ArrayList<BFOwner> owner = new ArrayList<BFOwner>();
        
        pmfactory.getDataStoreCache().pinAll(BFOwner.class, true);
        pmfactory.getDataStoreCache().pinAll(BFSystem.class, true);

        //Extent e = pm.getExtent( BFSystem.class, true );
        Extent e = pm.getExtent( BFOwner.class, true );
//        Query q2 = pm.newQuery( e1 ,"this.name == \"Neutral\"");
        Query q = pm.newQuery( e);
//        Query q2 = pm.newQuery( e1 ,"this.name == \"ComStar\"");
        Object ob1 =  q.execute();
//        Extent e = pm.getExtent( BFJumpship.class, true );
//        Extent e = pm.getExtent( BFLogicalUnit.class, true );
//        Query q = pm.newQuery( e );

        long start = System.currentTimeMillis();

        System.err.println( "Startup-time: " + (System.currentTimeMillis() - startTime) + " ms." );

        Collection c = (Collection) q.execute();

        Iterator iter = c.iterator();
        
        int counter = 0;
        while(iter.hasNext()){
            BFOwner ow = (BFOwner)iter.next();
            
//            Set se = ow.getSystems();
//            System.out.println(se.size());
//            String st = ow.getName();
//            se=se;
        }
                
//        while( iter.hasNext()){
//            BFLogicalUnit lu = (BFLogicalUnit)iter.next();
//            BFLogicalUnitStructure lus = lu.getLogicalUnitStructure();
//            System.out.println("BFLogicalUnit: " + lu.getName());
////            System.out.println("BFLogicalUnitStructure: " + lus.getName());
////            System.out.println(lus.getOwnerTyp().getName());
//            
//            Set<BFLogicalUnit> lu1 = lu.getChildLogicalUnits();
//            Iterator i1 = lu1.iterator();
//            while(i1.hasNext()){
//                BFLogicalUnit lu2 = (BFLogicalUnit)i1.next();
//                System.out.println("Child: " + lu2.getName());
//            }    
//            
//            Set<BFUnit> lu3 = lu.getUnits();
//            Iterator i2 = lu3.iterator();
//            while(i2.hasNext()){
//                BFUnit lu4 = (BFUnit)i2.next();
//                System.out.println("BFUnit: " + lu4.getName());
//            }    
//        }
//        
//        
//        Query qu = pm.newQuery(BFLogicalUnit.class, "this.owner == myowner && this.parentLogicalUnit == null");
//        qu.declareParameters("de.battleforge.jdo.BFOwner myowner");
//        Iterator iter2 =  ((Collection)ob1).iterator();
//        BFOwner ow = (BFOwner)iter2.next();
//        Object ob =  qu.execute(ow);
//        ob = ob;

        
        
//        while ( iter.hasNext() ) {
////            BFSystem system = (BFSystem) iter.next();
//            BFJumpship o = (BFJumpship) iter.next();
//
////            system.getOwner();
////            Set<BFSystem> systems2 = o.getSystems();
//            
//  //          counter += systems2.size();
//
////            System.err.println( system + "\n" );
////            System.err.println( system.getOwner() + "\n" );
//
////            systems.add( system );
//    //        owner.add( o );
//            
//            System.out.println("o: " + o.getName());
//            System.out.println("o: " + o.getOwner().getName());
//
//        }

        //System.err.println( systems.size() );
        System.err.println( counter );
        System.err.println( "query-fetch-time: " + (System.currentTimeMillis() - start) + " ms." );
        
        start = System.currentTimeMillis();
        
        e = pm.getExtent( BFSystem.class, true );
        q = pm.newQuery( e );

        c = (Collection) q.execute();

        iter = c.iterator();
        
        while ( iter.hasNext() ) {
            BFSystem system = (BFSystem) iter.next();

//            system.getOwner();
            
//            System.err.println( system + "\n" );

            systems.add( system );

        }

        System.err.println( systems.size() );
        System.err.println( "query-fetch-time: " + (System.currentTimeMillis() - start) + " ms." );

        pm.close();

        System.err.println( "total-time: " + (System.currentTimeMillis() - startTime) + " ms." );

    }
}
