package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
