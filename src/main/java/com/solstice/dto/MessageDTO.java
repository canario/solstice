package com.solstice.dto;

public class MessageDTO {

	private String message;
	private MessageType type;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MessageDTO(MessageType type, String message, String code) {
		super();
		this.message = message;
		this.type = type;
		this.code = code;
	}

	public MessageDTO() {
		super();
	}

	public MessageDTO(MessageType type, String message) {
		super();
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
