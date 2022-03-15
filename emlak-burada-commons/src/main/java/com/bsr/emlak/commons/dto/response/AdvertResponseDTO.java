package com.bsr.emlak.commons.dto.response;

import com.bsr.emlak.commons.entity.Person;
import com.bsr.emlak.commons.entity.Property;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdvertResponseDTO {

	private int advertNo;
	private Property property;
	private String title;
	private Person user; // hem bireysel & kurumsal
	private String[] imageList = new String[5];
	private BigDecimal cost;
	private int duration;
	private boolean shouldHighlighted = false;
	private boolean isReviewed = false;
	private Date createdDate;
	private boolean isActive;

}
