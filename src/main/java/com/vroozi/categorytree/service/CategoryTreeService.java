/**
 * 
 */
package com.vroozi.categorytree.service;

import java.util.List;
import java.util.Map;

import com.vroozi.categorytree.model.Category;

/**
 * @author Mamoon Habib
 *
 */
public interface CategoryTreeService {
	void addUpdateContentViewGroup(String id, String unitId, String name, String token, Boolean active);
	void updateProfileGroups(List<String> groupIds, Boolean active);
	void deleteContentViewGroup(List<String> goupIds);
	void addUpdateContentView(String contentViewId, String name, Boolean active);
	void updateContentViews(List<String> contentViewIds, Boolean active);
	void addContentView(String contentViewGroupId, String contentViewId, String contenViewName, List<String> catalogIds);
	void removeContentViews(List<String> cviewIds);
	void addCatalog(String unitId, List<String> catalogIds, Map<String, Integer> matGroups);
	void deleteCatalog(String unitId, String catalogId, Map<String, Integer> oldMatGroups);
	void uploadCategories(String unitId, String baseFile);
	Category getCategoryTree(String unitId, String contentViewGroupToken);
	void addContentViewToGroups(String cviewId, List<String> cvgoupIds);
	void removeContentViewFromGroups(String contentViewId, List<String> cvgoupIds);
	void addCatalogToConentViews(String catalogId, List<String> contentViewIds);
	void removeCatalogFromConentViews(String catalogId, List<String> contentViewIds);
	
}
