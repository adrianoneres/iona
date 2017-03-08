package com.iona.app.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.iona.app.dao.PatientDAO;
import com.iona.app.model.Patient;
import com.iona.core.bo.GenericBO;
import com.iona.core.util.Message;

public class PatientBO extends GenericBO implements Serializable {

	private static final long serialVersionUID = 3637640869197533219L;
	
	@Inject
	private PatientDAO patientDAO;
	
	@Inject
	private transient Logger log;
	
	public List<Patient> listAll() {
		return patientDAO.listAll();
	}
	
	public boolean create(Patient patient) {
		try {
			patientDAO.create(patient);
			addMessage(Message.CREATE_SUCCESS);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			addMessage(Message.CREATE_ERROR);
			return false;
		}
	}
	
	public boolean update(Patient patient) {
		try {
			patientDAO.update(patient);
			addMessage(Message.UPDATE_SUCCESS);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			addMessage(Message.UPDATE_ERROR);
			return false;
		}
	}
	
	public boolean delete(Patient patient) {
		try {
			patientDAO.delete(patient);
			addMessage(Message.DELETE_SUCCESS);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			addMessage(Message.DELETE_ERROR);
			return false;
		}
	}
	
	public Patient findById(Long id) {
		return patientDAO.findByID(id);
	}

}
