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
	
	public void adicionar(Object obj) throws Exception {
		em.persist(obj);
	}
	
	public void excluir(Object obj) throws Exception {
		em.remove(em.contains(obj) ? obj : em.merge(obj));
	}
	
	public void editar(Object obj) throws Exception{
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
	
	public T buscarPorId(Long id) {
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public T listByField(String field, String value) {
		Query query = em.createQuery("FROM " + clazz.getName() + " WHERE " + field + " = :value");
		query.setParameter("value", value);
		List<T> lista = query.getResultList();
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
}