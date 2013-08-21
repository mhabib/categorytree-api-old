/**
 * 
 */
package com.vroozi.categorytree.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.vroozi.categorytree.model.ContentViewGroup;

/**
 * @author Mamoon Habib
 *
 */
public interface ContentViewGroupRepository extends GraphRepository<ContentViewGroup>, RelationshipOperationsRepository<ContentViewGroup>{
	ContentViewGroup findByContentViewGroupId(String contentViewGroupId);
}
