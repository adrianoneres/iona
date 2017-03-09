package com.iona.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.iona.app.model.Patient;
import com.iona.core.dao.GenericDAO;

public class PatientDAO extends GenericDAO<Patient> implements Serializable {

	private static final long serialVersionUID = -2550821410178966913L;
	
	public Patient findByID(Long id) {
		Query query = em.createQuery("SELECT p FROM Patient p LEFT JOIN FETCH p.phones WHERE p.id = :id");
		query.setParameter("id", id);
		List<Patient> list = query.getResultList();
		if (list != null && list.size() > 0) {
			//TODO show a warn in console if returns more than one register
			return list.get(0);
		}
		return null;
	}
	
	public PatientDAO() {
		super(Patient.class);
	}
	
}
