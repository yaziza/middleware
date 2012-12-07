package de.tud.in.middleware.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public OrderDAO(){
		
	}
}
