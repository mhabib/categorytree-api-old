package com.vroozi.categorytree.model;

import java.io.Serializable;
import java.util.List;

public class ProfileGroup implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
	private String groupName;
	private ProfileGroupType groupType;
	private String token;
	private String createdOn;
	private String createdBy;
	private String createdByName;
	private boolean active;
	private String unitId;
	private String companyId;
	private List<Integer> associatedProfiles;
    private boolean isSupplierAssociated;

    public boolean isSupplierAssociated() {
        return isSupplierAssociated;
    }

    public void setSupplierAssociated(boolean supplierAssociated) {
        isSupplierAssociated = supplierAssociated;
    }
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public ProfileGroupType getGroupType() {
		return groupType;
	}
	public void setGroupType(ProfileGroupType groupType) {
		this.groupType = groupType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<Integer> getAssociatedProfiles() {
		return associatedProfiles;
	}
	public void setAssociatedProfiles(List<Integer> associatedProfiles) {
		this.associatedProfiles = associatedProfiles;
	}
	
	
	
	
	
}
