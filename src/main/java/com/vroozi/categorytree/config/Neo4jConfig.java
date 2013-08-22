package com.vroozi.categorytree.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.JtaTransactionManagerFactoryBean;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("com.vroozi.categorytree.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {
	
	private final Logger LOGGER = LoggerFactory.getLogger(Neo4jConfig.class);
	
    @Bean
    public GraphDatabaseService graphDatabaseService() {
    	Properties properties = new Properties();
    	InputStream is = Neo4jConfig.class.getResourceAsStream("/categorytree.properties"); 
    	try {
			properties.load(is);
			
	    	String categoryTreeDbPath = properties.getProperty("neo4j.db.path");
    		GraphDatabaseService graphDbService = new GraphDatabaseFactory().
			    newEmbeddedDatabaseBuilder( categoryTreeDbPath ).
//			    setConfig( GraphDatabaseSettings.node_keys_indexable, "id, cvGroupId, cviewId" ).
//			    setConfig( GraphDatabaseSettings.relationship_keys_indexable, "relProp1,relProp2" ).
//			    setConfig( GraphDatabaseSettings.node_auto_indexing, "true" ).
//			    setConfig( GraphDatabaseSettings.relationship_auto_indexing, "true" ).
			    newGraphDatabase();
    		return graphDbService;
		} catch (IOException e) {
			LOGGER.error("Failed to load properties", e);
		}
    	
    	return null;
    }
    
    @Override
    @Bean
    public PlatformTransactionManager neo4jTransactionManager() throws Exception {
        return new JtaTransactionManagerFactoryBean(graphDatabaseService()).getObject();
    }
    
    @Bean
    public WrappingNeoServerBootstrapper neo4jWebServer() {
        WrappingNeoServerBootstrapper server = new WrappingNeoServerBootstrapper((GraphDatabaseAPI)graphDatabaseService());
        server.start();
        return server;
    }
}