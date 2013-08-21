/**
 * 
 */
package com.vroozi.categorytree.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vroozi.categorytree.model.Category;
import com.vroozi.categorytree.model.ContentView;
import com.vroozi.categorytree.model.ContentViewGroup;
import com.vroozi.categorytree.processing.CategoryTreeProcess;
import com.vroozi.categorytree.repository.CategoryRepository;
import com.vroozi.categorytree.repository.ContentViewGroupRepository;
import com.vroozi.categorytree.repository.ContentViewRepository;
import com.vroozi.categorytree.service.rest.CatalogService;
import com.vroozi.categorytree.utils.CategoryParser;

/**
 * @author Mamoon Habib
 *
 */
@Service
public class CategoryTreeServiceImpl implements CategoryTreeService {

    @Autowired
    private Neo4jOperations neoTemplate;

    @Autowired
    private ContentViewGroupRepository cvGroupRepository;
    
	@Autowired
	private ContentViewRepository cvRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	CategoryTreeProcess categoryTreeProcess;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void addUpdateContentViewGroup(String groupId, String unitId, String name,
			String token, Boolean active) {
		ContentViewGroup cvgroup = cvGroupRepository.findByContentViewGroupId(groupId);
		if(cvgroup == null) {
			cvgroup = new ContentViewGroup();
		}
		cvgroup.setContentViewGroupId(groupId);
		cvgroup.setUnitId(unitId);
		cvgroup.setName(name);
		cvgroup.setToken(token);
		cvgroup.setActive(active);
		cvGroupRepository.save(cvgroup);
	}
	
	@Override
	public void updateProfileGroups(List<String> groupIds, Boolean active) {
		for (String groupId : groupIds) {
			ContentViewGroup cvgroup = cvGroupRepository.findByContentViewGroupId(groupId);
			cvgroup.setActive(active);
			cvGroupRepository.save(cvgroup);
		}
	}
	
	@Override
	public void deleteContentViewGroup(List<String> groupIds) {
		for (String groupId : groupIds) {
			ContentViewGroup cvgroup = cvGroupRepository.findByContentViewGroupId(groupId);
//			ContentViewGroup cvgroup = cvGroupRepository.findByPropertyValue("contentViewGroupId", id);
			cvGroupRepository.delete(cvgroup);
		}
	}

	@Override
	public void addUpdateContentView(String contentViewId, String name, Boolean active) {
		ContentView contentView = cvRepository.findByContentViewId(contentViewId);
		if(contentView==null) {
			contentView = new ContentView();
			contentView.setContentViewId(contentViewId);
			contentView.setName(name);
			contentView.setActive(active);
			contentView = cvRepository.save(contentView);
		}
	}
	
	@Override
	public void updateContentViews(List<String> contentViewIds, Boolean active) {
		for (String contentViewId : contentViewIds) {
			ContentView contentView = cvRepository.findByContentViewId(contentViewId);
			if(contentView!=null) {
				contentView.setActive(active);
				cvRepository.save(contentView);
			}
		}
	}
	@Override
	public void removeContentViews(List<String> cviewIds) {
		for (String cviewId : cviewIds) {
			ContentView contentView = cvRepository.findByContentViewId(cviewId);
			cvRepository.delete(contentView);
		}
	}

	@Override
	public void addContentViewToGroups(String contentViewId, List<String> groupIds) {
		ContentView contentView = cvRepository.findByContentViewId(contentViewId);
		for (String groupId : groupIds) {
			ContentViewGroup cvgroup = cvGroupRepository.findByContentViewGroupId(groupId);
			cvgroup.getContentViews().add(contentView);
			cvGroupRepository.save(cvgroup);
		}
	}

	@Override
	public void removeContentViewFromGroups(String contentViewId,
			List<String> groupIds) {
		ContentView contentView = cvRepository.findByContentViewId(contentViewId);
		for (String groupId : groupIds) {
			ContentViewGroup cvgroup = cvGroupRepository.findByContentViewGroupId(groupId);
			cvgroup.getContentViews().remove(contentView);
			cvGroupRepository.save(cvgroup);
		}
	}

	@Override
	public void addCatalogToConentViews(String catalogId,
			List<String> contentViewIds) {

		List<String> catalogIds = new ArrayList<String>(1);
		catalogIds.add(catalogId);
		Map<String, Integer> matGroups = catalogService.getMatGroupsByCatalogIds(catalogIds);
		if(matGroups!=null && matGroups.size()>0) {
			for (String contentViewId : contentViewIds) {
				ContentView contentView = cvRepository.findByContentViewId(contentViewId);
				linkContentViewCategories(contentView, matGroups);
			}
		}
	}

	@Override
	public void removeCatalogFromConentViews(String catalogId,
			List<String> contentViewIds) {
		
		List<String> catalogIds = new ArrayList<String>(1);
		catalogIds.add(catalogId);
		Map<String, Integer> matGroups = catalogService.getMatGroupsByCatalogIds(catalogIds);
		if(matGroups!=null && matGroups.size()>0) {
			for (String contentViewId : contentViewIds) {
				ContentView contentView = cvRepository.findByContentViewId(contentViewId);
				unlinkContentViewCategories(contentView, matGroups);
			}
		}
	}

	@Override
	@Transactional
	public void addContentView(String contentViewGroupId, String contentViewId,
			String contenViewName, List<String> catalogIds) {
		ContentView contentView = cvRepository.findByContentViewId(contentViewId);
		if(contentView==null) {
			contentView = new ContentView();
			contentView.setContentViewId(contentViewId);
			contentView.setName(contenViewName);
			contentView = cvRepository.save(contentView);
		}
		
		ContentViewGroup cvGroup = cvGroupRepository.findByContentViewGroupId(contentViewGroupId);
		cvGroup.getContentViews().add(contentView);
		cvGroupRepository.save(cvGroup);
		
		Map<String, Integer> matGroups = catalogService.getMatGroupsByCatalogIds(catalogIds);
		if(matGroups!=null && matGroups.size()>0) {
			linkContentViewCategories(contentView, matGroups);
		}
	}

	private void linkContentViewCategories(ContentView contentView, Map<String, Integer> matGroups) {
		EndResult<Category> categories = categoryRepository.findAllByPropertyValue("unitId", "");
		for (Category category : categories) {
			if(mapCategory(category, matGroups)) {
				contentView.addCategoryMapping(category);
			}
		}
		contentView = cvRepository.save(contentView);
	}

	private void unlinkContentViewCategories(ContentView contentView, Map<String, Integer> matGroups) {
		EndResult<Category> categories = categoryRepository.findAllByPropertyValue("unitId", "");
		for (Category category : categories) {
			if(mapCategory(category, matGroups)) {
				contentView.removeCategoryMapping(category);
			}
		}
		contentView = cvRepository.save(contentView);
	}
	
	@Override
	public void addCatalog(String unitId, List<String> catalogIds, Map<String, Integer> matGroups) {
		List<String> contentViewIds = catalogService.getProfilesByCatalogIds(unitId, catalogIds); 
		EndResult<Category> categories = categoryRepository.findAllByPropertyValue("unitId", unitId);
		for (Category category : categories) {
			if(mapCategory(category, matGroups)) {
				for (String contentViewId : contentViewIds) {
					ContentView contentView = cvRepository.findByContentViewId(contentViewId);
					contentView.addCategoryMapping(category);
					cvRepository.save(contentView);
				}
			}
		}
	}

//	public void updateCatalog(String unitId, String catalogId, Map<String, Integer> oldMatGroups, Map<String, Integer> matGroups, List<String> contentViewIds) {
//		deleteCatalog(unitId, oldContentViewIds, oldMatGroups);
//		addCatalog(unitId, contentViewIds, matGroups);
//	}
	
	@Override
	public void deleteCatalog(String unitId, String catalogId, Map<String, Integer> oldMatGroups) {
		EndResult<ContentView> contentViews = cvRepository.findAllByPropertyValue("unitId", unitId);
		EndResult<Category> categories = categoryRepository.findAllByPropertyValue("unitId", unitId);
		for (Category category : categories) {
			if(mapCategory(category, oldMatGroups)) {
				for (ContentView contentView : contentViews) {
					contentView.removeCategoryMapping(category);
					cvRepository.save(contentView);
				}
			}
		}
	}

	@Override
	public void uploadCategories(String unitId, String baseFile) {
		categoryRepository.deleteAll();
		categoryTreeProcess.processCategories(unitId, baseFile);
		updateCategoriesPath(unitId);
	}

	public void updateCategoriesPath(String unitId) {
		List<String> catalogIds = catalogService.getLiveCatalogIds(unitId);
		Map<String, Integer> catalogMatGroups = catalogService.getMatGroupsByCatalogIds(catalogIds);
		addCatalog(unitId, catalogIds, catalogMatGroups);
	}

	@Override
	public Category getCategoryTree(String unitId, String contentViewGroupToken) {
		categoryRepository.getCategories("");
		return null;
	}

	private boolean mapCategory(Category category, Map<String, Integer> matGroups) {
		
		for (Entry<String, Integer> matGroupEntry : matGroups.entrySet()) {
			if(CategoryParser.matches(category.getCatalogCategoryCode(), matGroupEntry.getKey())) {
				return true;
			}
		}
		return false;
	}
}
