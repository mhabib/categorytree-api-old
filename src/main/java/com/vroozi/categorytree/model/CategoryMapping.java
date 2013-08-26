/**
 * 
 */
package com.vroozi.categorytree.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Mamoon Habib
 *
 */
@RelationshipEntity(type = "MAPS_WITH")
public class CategoryMapping {
	@GraphId private Long id;
	@StartNode private ContentView contentView;
	@EndNode @Fetch private Category category;
	private Integer count;

	public CategoryMapping() {
	}
	
	public CategoryMapping(ContentView contentView, Category category,
			Integer count) {
		super();
		this.contentView = contentView;
		this.category = category;
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ContentView getContentView() {
		return contentView;
	}
	public void setContentView(ContentView contentView) {
		this.contentView = contentView;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
