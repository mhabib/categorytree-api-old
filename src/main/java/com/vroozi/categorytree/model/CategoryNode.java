/**
 * 
 */
package com.vroozi.categorytree.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Category tree node to returned in get category tree web service calls
 * 
 * @author Mamoon Habib
 *
 */
public class CategoryNode {
	
	private Long id;
	private String categoryId;
	private Long nodeId;
	private String catalogCategoryCode;
    private String companyCategoryCode;
    private String nodeTitle;
    private Set<CategoryNode> childNodes = new HashSet<CategoryNode>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public String getCatalogCategoryCode() {
		return catalogCategoryCode;
	}
	public void setCatalogCategoryCode(String catalogCategoryCode) {
		this.catalogCategoryCode = catalogCategoryCode;
	}
	public String getCompanyCategoryCode() {
		return companyCategoryCode;
	}
	public void setCompanyCategoryCode(String companyCategoryCode) {
		this.companyCategoryCode = companyCategoryCode;
	}
	public String getNodeTitle() {
		return nodeTitle;
	}
	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}
	public Set<CategoryNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(Set<CategoryNode> childNodes) {
		this.childNodes = childNodes;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
			
		if(obj instanceof CategoryNode) {
			CategoryNode category2 = (CategoryNode) obj;
			return id.equals(category2.getId());
//			return catalogCategoryCode==null ? category2.getCatalogCategoryCode() == null: catalogCategoryCode.equals(category2.getCatalogCategoryCode())
//					&& companyCategoryCode==null ? category2.getCompanyCategoryCode() == null: companyCategoryCode.equals(category2.getCompanyCategoryCode());
		}
		
		return false;
	}

}
