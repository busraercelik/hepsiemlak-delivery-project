package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner")
public class Banner extends BaseEntity {

	private String advertUUID;
	private String title;
	private String phoneNumber;

	private String city;
	private String district;

	@OneToMany(
			fetch = FetchType.EAGER,
			targetEntity = Document.class,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JoinColumn(name ="banner_id", referencedColumnName = "id")
	private List<Document> images;

	private Float grossSquareMeter;

	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;
	private BigDecimal cost;
}
