/**
 * 
 */
package com.vroozi.categorytree.service.rest;

import java.util.List;
import java.util.Map;

/**
 * @author Mamoon Habib
 *
 */
public interface CatalogService {
	
	List<String> getLiveCatalogIds(String unitId);
	Map<String, Integer> getMatGroupsByCatalogIds(List<String> catalogId);
	List<String> getContentViewIdsByCatalogIds(String unitId, List<String> catalogIds);
}
