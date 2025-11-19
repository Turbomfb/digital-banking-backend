/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techservices.digitalbanking.core.domain.data.model.FAQ;
import com.techservices.digitalbanking.core.domain.enums.ProductType;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {

	List<FAQ> findByDelFlgFalse();

	List<FAQ> findByProductAndDelFlgFalse(ProductType product);

	List<FAQ> findByWebEnableTrueAndDelFlgFalse();

	List<FAQ> findByMobileEnableTrueAndDelFlgFalse();

	@Query("SELECT f FROM FAQ f WHERE f.delFlg = false AND " + "(:product IS NULL OR f.product = :product) AND "
			+ "(:webEnable IS NULL OR f.webEnable = :webEnable) AND "
			+ "(:mobileEnable IS NULL OR f.mobileEnable = :mobileEnable)")
	List<FAQ> findByFilters(@Param("product") ProductType product, @Param("webEnable") Boolean webEnable,
			@Param("mobileEnable") Boolean mobileEnable);
}
