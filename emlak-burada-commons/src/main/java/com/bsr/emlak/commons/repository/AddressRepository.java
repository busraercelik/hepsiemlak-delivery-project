package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
