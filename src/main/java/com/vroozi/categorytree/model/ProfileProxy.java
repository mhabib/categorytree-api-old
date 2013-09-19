/**
 * 
 */
package com.vroozi.categorytree.model;

import java.util.List;

/**
 * @author Mamoon Habib
 *
 */
public class ProfileProxy {
	private int profileId;

    private String profileName;
    private String profileDesc;
    private String createdOn;
    private String createdOnView;
    private String createdBy;
    private String createdByName;
    private boolean active;
    private String unitId;
    private int productRating;
    private List<String> associatedCatalogs;
    private boolean isSupplierAssociated;
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileDesc() {
		return profileDesc;
	}
	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedOnView() {
		return createdOnView;
	}
	public void setCreatedOnView(String createdOnView) {
		this.createdOnView = createdOnView;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public int getProductRating() {
		return productRating;
	}
	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}
	public List<String> getAssociatedCatalogs() {
		return associatedCatalogs;
	}
	public void setAssociatedCatalogs(List<String> associatedCatalogs) {
		this.associatedCatalogs = associatedCatalogs;
	}
	public boolean isSupplierAssociated() {
		return isSupplierAssociated;
	}
	public void setSupplierAssociated(boolean isSupplierAssociated) {
		this.isSupplierAssociated = isSupplierAssociated;
	}
    
    
}
