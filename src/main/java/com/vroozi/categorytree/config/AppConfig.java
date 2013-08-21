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

    @Value("${liveCatalogIds}")
    public String liveCatalogIds;
    
    @Value("${matGroupsByCatalogId}")
	public String matGroupsByCatalogId;

    @Value("${matGroupsByCatalogIds}")
	public String matGroupsByCatalogIds;

    @Value("${profilesByCatalogIdsURI}")
	public String profilesByCatalogIdsURI;

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
