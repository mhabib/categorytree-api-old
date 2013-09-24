/**
 * 
 */
package com.vroozi.categorytree.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Mamoon Habib
 *
 */
@NodeEntity 
public class ContentViewGroup {

	@GraphId private Long id;
	@Indexed(unique = true)
	String contentViewGroupId;
	String name;
	@Indexed
	String unitId;
	@Indexed
	String token;
	String groupType;
	Boolean active;
	
	@RelatedTo(type = "ASSOCIATED")
    Set<ContentView> contentViews;
	
	public ContentViewGroup() {
		contentViews = new HashSet<ContentView>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContentViewGroupId() {
		return contentViewGroupId;
	}
	public void setContentViewGroupId(String contentViewGroupId) {
		this.contentViewGroupId = contentViewGroupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<ContentView> getContentViews() {
		return contentViews;
	}
	public void setContentViews(Set<ContentView> contentViews) {
		this.contentViews = contentViews;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
