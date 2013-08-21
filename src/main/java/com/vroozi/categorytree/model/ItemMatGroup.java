/**
 * 
 */
package com.vroozi.categorytree.model;

import java.util.List;


/**
 * @author Mamoon Habib
 *
 */
public class ItemMatGroup {
	String matGroup;
	int count;
	List<String> contentViewIds;
	
	public String getMatGroup() {
		return matGroup;
	}
	public void setMatGroup(String matGroup) {
		this.matGroup = matGroup;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getContentViewIds() {
		return contentViewIds;
	}
	public void setContentViewIds(List<String> contentViewIds) {
		this.contentViewIds = contentViewIds;
	}
	
}
