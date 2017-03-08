package com.iona.core.bo;


import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.iona.core.util.Message;

public class GenericBO implements Serializable {
	
	private static final long serialVersionUID = 1792907801748772608L;
	
	private ResourceBundle rb = ResourceBundle.getBundle("messages");
	
	protected void addMessage(Message msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage(msg.getLevel(), 
				rb.getString(msg.getTitle()), rb.getString(msg.getContent())));
	}

}