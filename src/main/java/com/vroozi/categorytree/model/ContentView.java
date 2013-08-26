/**
 * 
 */
package com.vroozi.categorytree.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.template.Neo4jOperations;

/**
 * @author Mamoon Habib
 *
 */
@NodeEntity
public class ContentView {
	
	@GraphId Long id;
	@Indexed(unique = true)
	String contentViewId;
	String name;
	Boolean active;
	@RelatedToVia(type = "MAPS_WITH")
	Set<CategoryMapping> categoryMappings = new HashSet<CategoryMapping>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContentViewId() {
		return contentViewId;
	}
	public void setContentViewId(String contentViewId) {
		this.contentViewId = contentViewId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Set<CategoryMapping> getCategoryMappings() {
		return categoryMappings;
	}
	public void setCategoryMappings(Set<CategoryMapping> categoryMappings) {
		this.categoryMappings = categoryMappings;
	}
	
	public CategoryMapping addCategoryMapping(Neo4jOperations neoTemplate, Category category) {
		CategoryMapping categoryMapping = null;
		boolean newMapping = true;
        for (CategoryMapping mapping : categoryMappings) {
        	if(mapping.getCategory().equals(category)) {
        		newMapping = false;
        		categoryMapping = mapping;
        		categoryMapping.setCount(categoryMapping.getCount()+1);
        		neoTemplate.save(mapping);
        	}
		}
        if(newMapping) {
        	CategoryMapping mapping = new CategoryMapping(this, category, 1);
        	categoryMappings.add(mapping);
        	
        }
        return categoryMapping;
    }

	public CategoryMapping removeCategoryMapping(Neo4jOperations neoTemplate, Category category) {
		CategoryMapping categoryMapping = null;
        for (CategoryMapping mapping : categoryMappings) {
        	if(mapping.getCategory().equals(category) && mapping.getCount()>0) {
        		mapping.setCount(mapping.getCount()-1);
        		categoryMapping = mapping;
        		neoTemplate.save(mapping);
        	}
		}
        return categoryMapping;
    }

}
