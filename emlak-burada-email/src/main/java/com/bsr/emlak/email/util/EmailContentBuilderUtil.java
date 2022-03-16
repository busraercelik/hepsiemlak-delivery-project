package com.bsr.emlak.email.util;

public class EmailContentBuilderUtil {
	
	private static String template = "<p>Hoş geldin ${user},</p>\n" ;
	
	private EmailContentBuilderUtil() {
		
	}
	
	public static String build(String userName) {
		return template.replaceAll("\\$\\{*user\\}", userName.split("@")[0]);
	}

}
