package com.vroozi.categorytree.service.rest;

import java.util.List;

import com.vroozi.categorytree.model.ContentView;
import com.vroozi.categorytree.model.ProfileProxy;

public interface ContentViewRestClient {
	List<ProfileProxy> getContentViewIdsByCatalogIds(List<String> contentViewIds);
}
