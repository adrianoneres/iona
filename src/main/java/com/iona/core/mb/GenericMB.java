package com.iona.core.mb;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.iona.core.util.Message;

public abstract class GenericMB<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected static final String LIST = "list";
	protected static final String ADD = "create";
	protected static final String EDIT = "update";
	
	@Inject
	protected transient Logger log;
	
	private ResourceBundle rb = ResourceBundle.getBundle("messages");
	
	public abstract void clean();
	
	public abstract void list();
	
	public abstract String showCreate();
	
	public abstract String create();
	
	public abstract String showUpdate(T t);
	
	public abstract String update();
	
	public abstract String delete(T t);
	
	public String cancel() {
		return LIST;
	}
	
	protected void adicionarMensagem(Message msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage(msg.getLevel(), 
				rb.getString(msg.getTitle()), rb.getString(msg.getContent())));
	}

}