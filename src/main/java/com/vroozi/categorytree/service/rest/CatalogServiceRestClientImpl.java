/**
 * 
 */
package com.vroozi.categorytree.service.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vroozi.categorytree.utils.RestServiceUrl;

/**
 * @author Mamoon Habib
 *
 */
@Component
public class CatalogServiceRestClientImpl implements CatalogService {
    private final Logger LOGGER = LoggerFactory.getLogger(CatalogServiceRestClientImpl.class);

    @Autowired
    RestServiceUrl restServiceUrl;

    @Override
	public List<String> getLiveCatalogIds(String unitId) {
        String[] catalogIdsString = new String[0];
        List<String> catalogIdList = new ArrayList<String>();
        try {
            catalogIdsString =  new RestTemplate().getForObject(restServiceUrl.getLiveCatalogIdsPath(), String[].class, unitId, false);
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching profile group based on group token");
            return null;
        }
        if (catalogIdsString!=null && catalogIdsString.length>0) {
            for (String catalogId: catalogIdsString) {
                catalogIdList.add(catalogId);
            }
        }

        if (catalogIdList.isEmpty()) {
            return null;
        } else {
            return catalogIdList;
        }
    }
    
	@Override
	public Map<String, Integer> getMatGroupsByCatalogIds(List<String> catalogIds) {
		try {
			String catalogIdStr = StringUtils.join(catalogIds, ',');
			Map<String, Integer> matGroups =  new RestTemplate().getForObject(restServiceUrl.getMatGroupsByCatalogIdsPath(), Map.class, catalogIdStr);
			return matGroups;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching catalog mat groups.", e);
            return null;
        }
	}

	@Override
	public List<String> getContentViewIdsByCatalogId(String unitId, String catalogId) {
		try {
        	String[] cviewArray =  new RestTemplate().getForObject(restServiceUrl.getProfilesByCatalogIdsURI(), String[].class, unitId, catalogId);
        	List<String> contentViewIds = Arrays.asList(cviewArray);
        	return contentViewIds;
        } catch(RestClientException rse) {
        	LOGGER.error("Error retrieving content view ids!", rse);
        } catch(Exception exp) {
        	LOGGER.error("Error retrieving content view ids!", exp);
        }
        return null;
	}
}
