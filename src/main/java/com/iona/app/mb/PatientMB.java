package com.iona.app.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.iona.app.bo.PatientBO;
import com.iona.app.model.Patient;
import com.iona.core.mb.GenericMB;

@Model
@ViewScoped
public class PatientMB extends GenericMB<Patient> {
	
	private static final long serialVersionUID = -7533442843057982988L;
	
	private Patient patient;
	
	private List<Patient> patients;
	
	@Inject
	private PatientBO patientBO;
	
	@PostConstruct
	public void clean() {
		this.patient = new Patient();
		patients = null;
	}

	public String showCreate() {
		clean();
		return CREATE;
	}

	@Transactional
	public String create() {
		patientBO.create(patient);
		clean();
		return LIST;
	}

	public String showUpdate(Patient patient) {
		this.patient = patient;
		return UPDATE;
	}
	
	@Transactional
	public String update() {
		patientBO.update(patient);
		clean();
		return LIST;
	}

	@Transactional
	public String delete(Patient patient) {
		patientBO.delete(patient);
		clean();
		return LIST;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Patient> getPatients() {
		if (patients == null) {
			patients = patientBO.listAll();
		}
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	
}
