/**
 * 
 */
package com.vroozi.categorytree.utils;

import com.vroozi.categorytree.config.AppConfig;

/**
 * @author Mamoon Habib
 *
 */
public class RestServiceUrl {
	AppConfig appConfig;

	public RestServiceUrl(AppConfig appConfig) {
		super();
		this.appConfig = appConfig;
	}
	
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public String getCategoryTreeDbPath() {
        return appConfig.categoryTreeDbPath;
    }

	public String getLiveCatalogIdsPath() {
        return appConfig.liveCatalogIds;
    }

	public String getMatGroupsByCatalogIdsPath() {
		return appConfig.matGroupsByCatalogIds;
	}
	public String getProfilesByCatalogIdsURI() {
		return appConfig.profilesByCatalogIdsURI;
	}
}
