/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techservices.digitalbanking.core.domain.data.model.BusinessCategoryItem;

@Repository
public interface BusinessCategoryItemRepository extends JpaRepository<BusinessCategoryItem, Long> {

	List<BusinessCategoryItem> findByCategoryId(Long categoryId);

	List<BusinessCategoryItem> findByCategoryIdAndIsActiveTrueOrderByDisplayOrderAsc(Long categoryId);

	Optional<BusinessCategoryItem> findByCategoryIdAndCode(Long categoryId, String code);

	@Query("SELECT i FROM BusinessCategoryItem i WHERE i.category.code = :categoryCode AND i.code = :itemCode")
	Optional<BusinessCategoryItem> findByCategoryCodeAndItemCode(@Param("categoryCode") String categoryCode,
			@Param("itemCode") String itemCode);

	boolean existsByCategoryIdAndCode(Long categoryId, String code);
}
