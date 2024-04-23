package hibernate_person.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hibernate_person.dto.Person;

public class PersonDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("sujit").createEntityManager();
	}
	
	public void savePerson(Person person) {
       EntityManager manager = getEntityManager();
       EntityTransaction transaction = manager.getTransaction();
       transaction.begin();
       manager.persist(person);
       transaction.commit();	  
	}
	
	public Person getPerson(String email) {
		EntityManager manager = getEntityManager();
		Query query = manager.createQuery("SELECT a FROM Person a WHERE a.email = ?1");
		query.setParameter(1, email);
		try {
			Person person = (Person) query.getSingleResult();
			return person;	
		} catch (Exception e) {
			return null;
		}
	}
	
	public Person findById(int id) {
		EntityManager manager = getEntityManager();		
		return manager.find(Person.class,1);
	}
	
	public Person findByPhone(long phone) {
		EntityManager manager = getEntityManager();
		Query query = manager.createQuery("SELECT a FROM Person a WHERE a.phone = ?1");
		query.setParameter(1, phone);
		try {
			Person person = (Person) query.getSingleResult();
			return person;	
		} catch (Exception e) {
			return null;
		}
	}
	
	public void findByName(String name) {
		EntityManager manager = getEntityManager();
		Query query = manager.createQuery("SELECT a FROM Person a WHERE a.name = ?1");
		query.setParameter(1, name);
		try {
			List<Person> list = query.getResultList();
			System.out.println(list);	
		} catch (Exception e) {
		
		}
	}
	
	public int deleteById(int id) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Person person = manager.find(Person.class, id);
		if(person!=null) {
			transaction.begin();
			manager.remove(person);
			transaction.commit();
			return 1;
		}
		else {
			return 0;
		}
	 }
	
	public int deleteByPhone(long phone) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("DELETE FROM Person d WHERE d.phone = ?1");
	    query.setParameter(1, phone);
			int result = query.executeUpdate();
			transaction.commit();
			return result;
	}
	
	public int deleteByEmail(String email) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("delete from Person d where d.email = ?1");
	    query.setParameter(1, email);
	    	int result = query.executeUpdate();
	    	transaction.commit();
	        return result;
	}
	
	public List<Person> findAllPerson() {
		EntityManager manager = getEntityManager();
	    Query query = manager.createQuery("SELECT p FROM Person p");
	    try {
	    	 List<Person> list = (List<Person>)query.getResultList();
	 	     return list;	
		} catch (Exception e) {
			return null;
		}
	}
	
	public int updatePerson(int id) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
        Person person = manager.find(Person.class, id);
        transaction.begin();
    	if (person!=null) {
	     	manager.merge(person);
	    }else {
	    	return 0;
	    }
    	transaction.commit();
    	return 1;
	}
}
