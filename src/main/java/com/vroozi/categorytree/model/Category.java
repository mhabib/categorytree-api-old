package com.vroozi.categorytree.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Category {
	
	@GraphId Long id;
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

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
			
		if(obj instanceof Category) {
			Category category2 = (Category) obj;
			return unitId==null ? category2.getUnitId() == null: unitId.equals(category2.getUnitId()) 
					&& catalogCategoryCode==null ? category2.getCatalogCategoryCode() == null: catalogCategoryCode.equals(category2.getCatalogCategoryCode())
					&& companyCategoryCode==null ? category2.getCompanyCategoryCode() == null: companyCategoryCode.equals(category2.getCompanyCategoryCode())
					&& supplierId==null ? category2.getSupplierId() == null: supplierId.equals(category2.getSupplierId());
		}
		
		return false;
	}
	
}
