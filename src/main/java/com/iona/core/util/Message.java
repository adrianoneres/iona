package com.iona.core.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum Message {
	
	CREATE_SUCCESS(FacesMessage.SEVERITY_INFO, "general.success", "general.createSuccess"),
	CREATE_ERROR(FacesMessage.SEVERITY_ERROR, "general.error", "general.createError"),
	
	UPDATE_SUCCESS(FacesMessage.SEVERITY_INFO, "general.success", "general.updateSuccess"),
	UPDATE_ERROR(FacesMessage.SEVERITY_ERROR, "general.error", "general.updateError"),
	
	DELETE_SUCCESS(FacesMessage.SEVERITY_INFO, "general.success", "general.deleteSuccess"),
	DELETE_ERROR(FacesMessage.SEVERITY_ERROR, "general.error", "general.deleteError");
	
	private Severity level;
	private String title;
	private String content;
	
	private Message(Severity level, String title, String content) {
		this.level = level;
		this.title = title;
		this.content = content;
	}

	public Severity getLevel() {
		return level;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
