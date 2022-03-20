package com.bsr.emlak.commons.dto.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class EmailMessageRequestDTO {

	private Long toEmlakUserId;
	private String toEmail;
	private String userName;
	private String subject;
	private String body;
	private LocalDateTime sentTimeStamp;

}
