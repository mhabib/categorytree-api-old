/**
 * 
 */
package com.vroozi.categorytree.service.rest;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vroozi.categorytree.model.ContentView;
import com.vroozi.categorytree.model.ProfileProxy;
import com.vroozi.categorytree.utils.RestServiceUrl;

/**
 * @author Mamoon Habib
 *
 */
@Component
public class ContentViewRestServiceImpl implements ContentViewRestClient {

	private final Logger LOGGER = LoggerFactory.getLogger(CatalogServiceRestClientImpl.class);

    @Autowired
    RestServiceUrl restServiceUrl;
    
	@Override
	public List<ProfileProxy> getContentViewIdsByCatalogIds(
			List<String> contentViewIds) {
		try {
        	String contentViewIdStr = StringUtils.join(contentViewIds, ',');
        	ProfileProxy[] cviewArray =  new RestTemplate().getForObject(restServiceUrl.getProfilesByProfileIdsPath(), 
        			ProfileProxy[].class, contentViewIdStr);
        	List<ProfileProxy> contentViews = Arrays.asList(cviewArray);
        	return contentViews;
        } catch(RestClientException rse) {
        	LOGGER.error("Error retrieving content views by ids!", rse);
        } catch(Exception exp) {
        	LOGGER.error("Error retrieving content views by ids!", exp);
        }
        return null;
	}

}
