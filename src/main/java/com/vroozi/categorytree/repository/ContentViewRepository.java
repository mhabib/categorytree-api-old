/**
 * 
 */
package com.vroozi.categorytree.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.vroozi.categorytree.model.ContentView;

/**
 * @author Mamoon Habib
 *
 */
public interface ContentViewRepository extends GraphRepository<ContentView>, RelationshipOperationsRepository<ContentView>{
	ContentView findByContentViewId(String contentViewId);
}
