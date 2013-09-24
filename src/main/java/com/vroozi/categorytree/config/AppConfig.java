package com.vroozi.categorytree.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vroozi.categorytree.utils.RestServiceUrl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.vroozi.categorytree")
public class AppConfig {

    @Value("${neo4j.db.path}")
    public String categoryTreeDbPath;

    @Value("${liveCatalogIds}")
    public String liveCatalogIds;
    
    @Value("${matGroupsByCatalogIds}")
	public String matGroupsByCatalogIds;

    @Value("${profilesByCatalogIdsURI}")
	public String profilesByCatalogIdsURI;

    @Value("${profilesByProfileIdsPath}")
	public String profilesByProfileIdsPath;

    @Value("${masterProfilePath}")
	public String masterProfilePath;

    @Value("${profileGroupsByIdsPath}")
	public String profileGroupsByIdsPath;
    
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();

        Resource[] resources = new ClassPathResource[] {
            new ClassPathResource("categorytree.properties")
        };
        ppc.setLocations(resources);
        ppc.setIgnoreResourceNotFound(false);
        ppc.setIgnoreUnresolvablePlaceholders(false);

        return ppc;
    }

    @Bean(name="restServiceUrl")
    public RestServiceUrl getRestServiceUrl() {
        return new RestServiceUrl(this);
    }

}
