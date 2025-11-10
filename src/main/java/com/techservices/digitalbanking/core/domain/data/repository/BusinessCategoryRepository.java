package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.BusinessCategory;
import com.techservices.digitalbanking.core.domain.enums.BusinessCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {

    Optional<BusinessCategory> findByCode(String code);

    Optional<BusinessCategory> findByName(String name);

    Optional<BusinessCategory> findByCategoryType(BusinessCategoryType categoryType);

    List<BusinessCategory> findByIsActiveTrueOrderByDisplayOrderAsc();

    Optional<BusinessCategory> findByCategoryTypeAndIsActiveTrueOrderByDisplayOrderAsc(BusinessCategoryType categoryType);

    @Query("SELECT c FROM BusinessCategory c LEFT JOIN FETCH c.items WHERE c.id = :id")
    Optional<BusinessCategory> findByIdWithItems(@Param("id") Long id);

    @Query("SELECT c FROM BusinessCategory c LEFT JOIN FETCH c.items WHERE c.code = :code")
    Optional<BusinessCategory> findByCodeWithItems(@Param("code") String code);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
