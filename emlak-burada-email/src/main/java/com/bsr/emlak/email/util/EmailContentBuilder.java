package com.bsr.emlak.email.util;

public final class EmailContentBuilder {

	private StringBuilder stringBuilder;

	private static final String GREETING_TEMPLATE = "<p>Welcome %s,</p>\n" ;

	private static final String SIGNATURE_TEMPLATE = "\n<p>Thank you,<p>" +
													 "\n<p>Emlakburada Team</p>\n" ;
	
	public EmailContentBuilder() {
		stringBuilder = new StringBuilder();
	}

	public EmailContentBuilder setGreeting(String userName){
		this.stringBuilder.append(String.format(GREETING_TEMPLATE, userName));
		return this;
	}

	public EmailContentBuilder addBody(String text){
		this.stringBuilder.append(text+"\n");
		return this;
	}

	public EmailContentBuilder addBodyLine(String text){
		this.stringBuilder.append(text+"\n");
		return this;
	}
	
	public String build() {
		this.stringBuilder.append(SIGNATURE_TEMPLATE);
		return this.stringBuilder.toString();
	}

}
