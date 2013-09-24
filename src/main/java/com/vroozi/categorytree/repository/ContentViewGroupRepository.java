/**
 * 
 */
package com.vroozi.categorytree.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.data.repository.query.Param;

import com.vroozi.categorytree.model.ContentViewGroup;

/**
 * @author Mamoon Habib
 *
 */
public interface ContentViewGroupRepository extends GraphRepository<ContentViewGroup>, RelationshipOperationsRepository<ContentViewGroup>{
	ContentViewGroup findByContentViewGroupId(String contentViewGroupId);
	@Query( " START cvgroup=node:__types__(className=\"com.vroozi.categorytree.model.ContentViewGroup\")"
			+ " where HAS(cvgroup.groupType) and cvgroup.active = true "
			+ " and cvgroup.unitId={unitId} and cvgroup.groupType={groupType}"
			+ " return cvgroup")
	Iterable<ContentViewGroup> findFromUnitIdAndGroupType(@Param("unitId") String unitId,@Param("groupType") String groupType);
	
	Iterable<ContentViewGroup> findByUnitIdAndGroupTypeAndActive(String unitId,String groupType, Boolean active);

}
