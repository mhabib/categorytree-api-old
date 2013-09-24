package com.vroozi.categorytree.service.rest;

import java.util.List;

import com.vroozi.categorytree.model.ProfileGroup;
import com.vroozi.categorytree.model.ProfileProxy;

public interface ContentViewRestClient {
	List<ProfileProxy> getContentViewsByIds(List<String> contentViewIds);
	ProfileGroup getMasterConentViewGroup(String unitId);
	List<ProfileGroup> getContentViewGroupsByIds(
			List<String> contentViewGroupIds);
}
