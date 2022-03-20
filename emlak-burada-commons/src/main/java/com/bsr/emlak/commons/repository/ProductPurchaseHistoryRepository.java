package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.ProductPurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPurchaseHistoryRepository extends JpaRepository<ProductPurchaseHistory, Long> {
}
