package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.enums.PropertyType;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.zip.InflaterInputStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner")
public class Banner extends BaseEntity{
	private String title;
	private String advertUUID;
	private String phoneNumber;
	private String city;
	private String district;
	@ElementCollection
	private List<String> imageList;
	private LocalDateTime postedDate;
	private Float grossSquareMeter;
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;

	@PrePersist
	@PreUpdate
	public void updateInternalFields() {
		this.createdAt = ObjectUtils.isEmpty(this.createdAt) ? LocalDateTime.now() : this.createdAt;
		this.modifiedAt = LocalDateTime.now();
	}
}
