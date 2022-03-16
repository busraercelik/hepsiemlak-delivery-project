package com.bsr.emlak.commons.repository;


import com.bsr.emlak.commons.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
}
