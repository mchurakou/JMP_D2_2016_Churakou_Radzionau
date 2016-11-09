package com.epam.jmp.dr.task17.rest.storage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.epam.jmp.dr.task17.rest.entities.Task;
import com.epam.jmp.dr.task17.rest.entities.User;


public class JPAService {
	
	EntityManager em;
	
	public JPAService()
	{
		EntityManagerFactory emFactory  = Persistence.createEntityManagerFactory("restJPA");
		em = emFactory.createEntityManager();
	}
	
	public List<User> getAllUsers()
	{
		List<User> result = em.createQuery("select u from User u").getResultList();
		return result;
	}
	
	public User getUser(int id)
	{
		User u = em.find(User.class, id);
		return u;
	}
	
	public void addUser(User u)
	{
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	public void deleteUser(int id)
	{
		em.getTransaction().begin();
		em.remove(em.getReference(User.class, id));
		em.getTransaction().commit();
	}
	
	public void updateUser(User u)
	{
		em.getTransaction().begin();
		User pUser = em.find(User.class, u.getId());
		pUser.setMail(u.getMail());
		pUser.setName(u.getName());
		pUser.setSurname(u.getSurname());
		em.getTransaction().commit();
	}
	
	public void addTask(int userId, Task t)
	{
		em.getTransaction().begin();
		User pUser = em.find(User.class, userId);
		pUser.getTasks().add(t);
		em.getTransaction().commit();
	}

}
