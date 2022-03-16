package com.bsr.emlak.commons.repository;


import com.bsr.emlak.commons.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
}
