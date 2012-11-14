package de.tud.in.middleware.dao.customers;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.tud.in.middleware.customers.Customer;

/**
 * Session Bean implementation class CustomerDAO
 */
@Stateless
@LocalBean
public class CustomerDAO {

	@PersistenceContext
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public CustomerDAO() {
    }
    
    public long getNumberOfCustomers() {
    	long result;
    	Query query = entityManager.createQuery("select count(id) from Customer id");
    	result = (Long) query.getSingleResult();
    	return result;
    }

	public String getCustomerName(long id) {
//		Customer customer = entityManager.find(Customer.class, id);
    	String format = String.format("select name from Customer name where name.id = %d", id);
    	Query query = entityManager.createQuery(format);
    	Customer customer  =  (Customer) query.getSingleResult();    	
    	return customer.getName();
	}

	public long addCustomer(String name) {
    	String format = String.format("insert into Customer (name) VALUES ('%s')", name);
    	Query query = entityManager.createNativeQuery(format);

    	return -1;
	}

}
