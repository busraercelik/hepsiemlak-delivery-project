package com.bsr.emlak.commons.dto.response;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponseDTO {
	private String title;
	private String advertUUID;
	private String phoneNumber;
	private String city;
	private String district;
	private List<String> imageList;
	private LocalDateTime postedDate;
	private Float grossSquareMeter;
	private PropertyType propertyType;
}
