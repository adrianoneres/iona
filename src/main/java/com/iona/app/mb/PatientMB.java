package com.iona.app.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.iona.app.bo.PatientBO;
import com.iona.app.model.Patient;
import com.iona.app.model.Phone;
import com.iona.core.mb.GenericMB;

@Model
@ViewScoped
public class PatientMB extends GenericMB<Patient> {
	
	private static final long serialVersionUID = -7533442843057982988L;
	
	private Patient patient;
	
	private List<Patient> patients;
	
	private Phone newPhone;
	
	@Inject
	private PatientBO patientBO;
	
	@PostConstruct
	public void clean() {
		Long patientId = (Long) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("patientId");
		if (patientId != null) {
			this.patient = patientBO.findById(patientId);
		} else {
			this.patient = new Patient();
		}
		this.newPhone = new Phone();
		patients = null;
	}

	public String showCreate() {
		clean();
		return CREATE;
	}

	@Transactional
	public String create() {
		boolean result = patientBO.create(patient);
		clean();
		return result ? LIST : null;
	}

	public String showUpdate(Patient patient) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("patientId", patient.getId());
		return UPDATE;
	}
	
	@Transactional
	public String update() {
		boolean result = patientBO.update(patient);
		clean();
		return result ? LIST : null;
	}

	@Transactional
	public String delete(Patient patient) {
		patientBO.delete(patient);
		clean();
		return LIST;
	}
	
	public void addPhone(ActionEvent actionEvent) {
		if (this.patient.getPhones() == null) {
			this.patient.setPhones(new ArrayList<>());
		}
		newPhone.setPatient(patient);
		this.patient.getPhones().add(newPhone);
		this.newPhone = new Phone();
	}
	
	public void removePhone(Phone phone) {
		this.patient.getPhones().remove(phone);
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

	public Phone getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(Phone newPhone) {
		this.newPhone = newPhone;
	}
	
}
