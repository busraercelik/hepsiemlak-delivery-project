package com.bsr.emlak.commons.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advert_address")
public class Address extends BaseEntity {
	private String city;
	private String district;
	private String addressDesc;
}
