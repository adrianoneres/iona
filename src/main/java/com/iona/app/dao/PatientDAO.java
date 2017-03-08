package com.iona.app.dao;

import java.io.Serializable;

import com.iona.app.model.Patient;
import com.iona.core.dao.GenericDAO;

public class PatientDAO extends GenericDAO<Patient> implements Serializable {

	private static final long serialVersionUID = -2550821410178966913L;
	
	public PatientDAO() {
		super(Patient.class);
	}
	
}
