package com.beta.replyservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyMessage {

	private final String message;

	private final String data;

	public ReplyMessage(String message, String data) {
		this.message = message;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}
}