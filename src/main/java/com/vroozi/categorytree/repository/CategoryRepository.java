/**
 * 
 */
package com.vroozi.categorytree.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.vroozi.categorytree.model.Category;

/**
 * @author Mamoon Habib
 *
 */
public interface CategoryRepository extends GraphRepository<Category>, RelationshipOperationsRepository<Category>{
	Category findByCategoryId(String categoryId);
	Category findByCompanyCategoryCode(String companyCategoryCode);
	
	@Query( "start user=node({0}) " +
            " match user-[r:RATED]->movie<-[r2:RATED]-other-[r3:RATED]->otherMovie " +
            " where r.stars >= 3 and r2.stars >= r.stars and r3.stars >= r.stars " +
            " return otherMovie, avg(r3.stars) as rating, count(*) as cnt" +
            " order by rating desc, cnt desc" +
            " limit 10" )
	List<Category> getCategories( String cvGroupToken );
}
