package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner")
public class Banner extends BaseEntity{

	private String advertUUID;
	private String title;
	private String phoneNumber;

	private String city;
	private String district;

	@ElementCollection
	private List<String> imageList;
	private Float grossSquareMeter;

	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;
	private BigDecimal cost;
}
