package com.vroozi.categorytree.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${neo4j.db.path}")
    public String categoryTreeDbPath;

    @Bean
    public GraphDatabaseService graphDatabaseService() {
    		GraphDatabaseService graphDbService = new GraphDatabaseFactory().
			    newEmbeddedDatabaseBuilder( "D:/categoryDb" ).
//			    setConfig( GraphDatabaseSettings.node_keys_indexable, "id, cvGroupId, cviewId" ).
//			    setConfig( GraphDatabaseSettings.relationship_keys_indexable, "relProp1,relProp2" ).
//			    setConfig( GraphDatabaseSettings.node_auto_indexing, "true" ).
//			    setConfig( GraphDatabaseSettings.relationship_auto_indexing, "true" ).
			    newGraphDatabase();
    	return graphDbService;
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