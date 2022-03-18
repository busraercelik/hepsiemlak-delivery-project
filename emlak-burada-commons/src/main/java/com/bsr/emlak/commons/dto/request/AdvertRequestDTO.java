package com.bsr.emlak.commons.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdvertRequestDTO {
	private String title;
	private String description;
	private Long propertyId;
	private List<String> imageList;
	private BigDecimal cost;
	private Integer duration;
	private Boolean shouldHighlighted;
	private Boolean isReviewed;
	private String phoneNumber;
}
