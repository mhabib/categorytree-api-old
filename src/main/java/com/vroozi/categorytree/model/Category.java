package com.vroozi.categorytree.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Category {
	
	@GraphId Long id;
	@Indexed(unique = true)
	private String categoryId;
	@Indexed
	private String unitId;
	private Long row;
	private String catalogCategoryCode;
    private String startRange;
    private String endRange;
    @Indexed
    private String companyCategoryCode;
    private String companyLabel;
    private String supplierId;
    private Category parent;    
    private List<Category> childs = new ArrayList<Category>();

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
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public Long getRow() {
		return row;
	}
	public void setRow(Long row) {
		this.row = row;
	}
	public String getCatalogCategoryCode() {
		return catalogCategoryCode;
	}
	public void setCatalogCategoryCode(String catalogCategoryCode) {
		this.catalogCategoryCode = catalogCategoryCode;
	}
	public String getStartRange() {
		return startRange;
	}
	public void setStartRange(String startRange) {
		this.startRange = startRange;
	}
	public String getEndRange() {
		return endRange;
	}
	public void setEndRange(String endRange) {
		this.endRange = endRange;
	}
	public String getCompanyCategoryCode() {
		return companyCategoryCode;
	}
	public void setCompanyCategoryCode(String companyCategoryCode) {
		this.companyCategoryCode = companyCategoryCode;
	}
	public String getCompanyLabel() {
		return companyLabel;
	}
	public void setCompanyLabel(String companyLabel) {
		this.companyLabel = companyLabel;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChilds() {
		return childs;
	}
	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}
	
}
