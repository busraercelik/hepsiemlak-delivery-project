package com.bsr.emlak.burada.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdvertRequest {
	private String title;
	private BigDecimal cost;
	private Integer duration;
	private Boolean shouldHighlighted;
	private Boolean isReviewed;
	private Boolean isActive;
	private Long propertyId;
	private Long userId;
}
