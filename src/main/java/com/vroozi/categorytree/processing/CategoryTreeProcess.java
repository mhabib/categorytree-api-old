/**
 * 
 */
package com.vroozi.categorytree.processing;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;

import com.vroozi.categorytree.model.Category;
import com.vroozi.categorytree.repository.CategoryRepository;
import com.vroozi.categorytree.service.rest.CatalogService;

/**
 * @author Mamoon Habib
 *
 */
@Component
public class CategoryTreeProcess {
	private static final Logger LOGGER = getLogger(CategoryTreeProcess.class.getName());
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	CatalogService catalogService;
	
	public void processCategories(String unitId, String baseFile) {
		List<CSVReader> csvReaders = new ArrayList<CSVReader>();
		try {
			csvReaders.add(openReader(baseFile, ".validated.success.1"));
			csvReaders.add(openReader(baseFile, ".validated.success.2"));
			csvReaders.add(openReader(baseFile, ".validated.success.3"));
		
			Map<String, Category> categoryMap = new HashMap<String, Category>();
			String columns[] = null;
			for (CSVReader csvReader : csvReaders) {
	            while((columns = csvReader.readNext()) != null){
	            	Category category = mapValues(columns, unitId, categoryMap);
	            	categoryMap.put(category.getCompanyCategoryCode(), category);
	            }
			}
			categoryRepository.save(categoryMap.values());
			
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	protected CSVReader openReader(String baseFile, String fileExt) throws IOException {
		return new CSVReader(new InputStreamReader(new FileInputStream(baseFile + fileExt), "UTF8"), (char)1);
	}
	 
	private Category mapValues(String [] columns, String unitId, Map<String, Category> categoryMap){
			
		Category category = new Category();
		//Supplier_Id, Catalog_Category_Code, Company_category_code, Company_label, Level 1, Level 2, Level 3
		//-- ROW_NUMBER	VENDOR_NUMBER	UNSPSC_CODE	COMPANY_CATEGORY_ID	COMPANY_CATEGORY_LABEL	LEVEL_1	LEVEL_2	LEVEL_3
		category.setSupplierId(columns[1]);
		category.setCatalogCategoryCode(columns[2]);
		category.setCompanyCategoryCode(columns[3]);
		category.setCompanyLabel(columns[4]);
		
		if(columns[5] != null && columns[5].length() > 0){
			Category parent = categoryMap.get(columns[5]);
			parent.getChilds().add(category);
			category.setParent(parent);
		}

		if(columns[6] != null && columns[6].length() > 0){
			Category parent = categoryMap.get(columns[6]);
			parent.getChilds().add(category);
			category.setParent(parent);
		}
			
		if(columns[7] != null && columns[7].length() > 0){
			Category parent = categoryMap.get(columns[7]);
			parent.getChilds().add(category);
			category.setParent(parent);
		}

		category.setStartRange(columns[8]);
		category.setEndRange(columns[9]);

		category.setUnitId(unitId);
		return category;
	}
}
