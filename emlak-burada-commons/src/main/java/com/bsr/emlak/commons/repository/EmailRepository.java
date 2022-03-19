package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {

}
