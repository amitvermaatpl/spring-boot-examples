package com.example.demo.rule;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.marshalling.impl.ProtobufMessages.KnowledgeBase;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class DSLExample {
	
	private static KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    private static Collection<KiePackage> pkgs;
    private static InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    private static StatefulKnowledgeSession ksession;
 
    public static void main(final String[] args) {    	
    	initDrools();
    	initMessageObject();
    	fireRules();    		
    }
 
    private static void initDrools(){
    try{
    		
    	Class.forName("oracle.jdbc.driver.OracleDriver"); //com.ibm.db2.jcc.DB2Driver
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "ebor", "password");
        
        String sql="SELECT rule_descp FROM rule";        
        Statement sta = conn.createStatement();
        ResultSet rs = sta.executeQuery(sql);

        String myRule=null;
        while (rs.next()) {
        	myRule=rs.getString(1);
        }
        sta.close();
    	Resource myResource = ResourceFactory.newReaderResource((Reader) new StringReader(myRule));
        kbuilder.add(myResource, ResourceType.DRL);

        // Check the builder for errors, VALIDATION of rules //
        if ( kbuilder.hasErrors() ) {
            System.out.println( kbuilder.getErrors().toString() );
            throw new RuntimeException( "Error in rule, Unable to compile drl\"." );
        } 
        // get the compiled packages (which are serializable)
        pkgs = kbuilder.getKnowledgePackages();
 
        // add the packages to a knowledgebase (deploy the knowledge packages).
        kbase.addPackages( pkgs ); 
        ksession = (StatefulKnowledgeSession) kbase.newStatelessKieSession();
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
 
    private static void fireRules(){
        ksession.fireAllRules();
    }
 
    private static void initMessageObject() {
		Arrays.asList(
				new Asset(1, "x1", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "1",15000),
				new Asset(2, "x1", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "1",17000),
				new Asset(10, "x1", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2",0),
				new Asset(11, "x1", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2",0),
				new Asset(3, "x1", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "1",19000),
				new Asset(12, "x1", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "1",0),
				new Asset(19, "x2", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2",0),
				new Asset(20, "x2", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2",0),
				new Asset(21, "x2", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "2",0),
				new Asset(28, "x2", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2",0),
				new Asset(29, "x2", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2",0),
				new Asset(30, "x2", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "2",0)
		).forEach(ksession::insert);
    }

	/*public static final void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		final KieSession knowledgeSession =  kContainer.newKieSession("ksession-rules");;
	    try {



			Arrays.asList(
					new Asset(1, "x1", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "1",15000),
					new Asset(2, "x1", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "1",17000),
					new Asset(10, "x1", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2",0),
					new Asset(11, "x1", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2",0),
					new Asset(3, "x1", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "1",19000),
					new Asset(12, "x1", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "1",0),
					new Asset(19, "x2", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2",0),
					new Asset(20, "x2", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2",0),
					new Asset(21, "x2", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "2",0),
					new Asset(28, "x2", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2",0),
					new Asset(29, "x2", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2",0),
					new Asset(30, "x2", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "2",0)
			).forEach(knowledgeSession::insert);




		
			// 5 - fire the rules
			knowledgeSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			knowledgeSession.dispose();
        }
	}*/
}