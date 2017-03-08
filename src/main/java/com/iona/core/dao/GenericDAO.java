package com.iona.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDAO<T> implements Serializable {
	
	private static final long serialVersionUID = -4785403212963162671L;

	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> clazz;
	
	public GenericDAO(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public void create(Object obj) throws Exception {
		em.persist(obj);
	}
	
	public void delete(Object obj) throws Exception {
		em.remove(em.contains(obj) ? obj : em.merge(obj));
	}
	
	public void update(Object obj) throws Exception{
		em.merge(obj);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		Query query = em.createQuery("FROM " + clazz.getName());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(String orderBy) {
		Query query = em.createQuery("FROM " + clazz.getName() + " ORDER BY " + orderBy);
		return query.getResultList();
	}
	
	public T findByID(Long id) {
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public T findByField(String field, String value) {
		Query query = em.createQuery("FROM " + clazz.getName() + " WHERE " + field + " = :value");
		query.setParameter("value", value);
		List<T> lista = query.getResultList();
		if (lista != null && lista.size() > 0) {
			//TODO show a warn in console if returns more than one register
			return lista.get(0);
		}
		return null;
	}
}