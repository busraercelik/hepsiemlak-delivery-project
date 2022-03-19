package com.bsr.emlak.commons.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advert_address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends BaseEntity {
	private String city;
	private String district;
	private String addressDesc;
}
