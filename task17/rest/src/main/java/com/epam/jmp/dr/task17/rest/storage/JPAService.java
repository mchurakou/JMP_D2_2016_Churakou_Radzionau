package com.epam.jmp.dr.task17.rest.storage;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.epam.jmp.dr.task17.rest.entities.SuperUser;
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
	
	public List<SuperUser> getAllSUsers()
	{
		List<SuperUser> result = em.createQuery("select u from User u").getResultList();
		return result;
	}
	
	public SuperUser getSUser(int id)
	{
		SuperUser u = em.find(SuperUser.class, id);
		return u;
	}
	
	public void addSUser(SuperUser u)
	{
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	public void deleteSUser(int id)
	{
		em.getTransaction().begin();
		em.remove(em.getReference(SuperUser.class, id));
		em.getTransaction().commit();
	}
	
	public void updateSUser(SuperUser u)
	{
		em.getTransaction().begin();
		SuperUser pUser = em.find(SuperUser.class, u.getId());
		pUser.setMail(u.getMail());
		pUser.setName(u.getName());
		pUser.setSurname(u.getSurname());
		pUser.setType(u.getType());
		em.getTransaction().commit();
	}
	
	public List<Task> getAllTasks(int userId)
	{
		//List<Task> result = em.createQuery("select u.tasks from User u where u.id = :id", Task.class).setParameter("id", userId).getResultList();
		return getUser(userId).getTasks();
	}
	
	public Task getTask(int id)
	{
		Task t = em.find(Task.class, id);
		return t;
	}
	
	public void updateTask(Task t)
	{
		em.getTransaction().begin();
		Task pTask = em.find(Task.class, t.getId());
		pTask.setCreationDate(t.getCreationDate());
		pTask.setDeadLine(t.getDeadLine());
		pTask.setName(t.getName());
		pTask.setDescription(t.getDescription());
		em.getTransaction().commit();
	}
	
	public void deleteTask(int userId, int taskId)
	{
		/*em.getTransaction().begin();
		em.remove(em.getReference(Task.class, id));
		em.getTransaction().commit();*/
		/*em.getTransaction().begin();
		em.createQuery("delete from Task t where t.id = :id").setParameter("id", id).executeUpdate();
		em.getTransaction().commit();*/
		
		em.getTransaction().begin();
		User pUser = em.find(User.class, userId);
		Iterator<Task> i = pUser.getTasks().iterator();
		while(i.hasNext())
		{
			Task t = i.next();
			if(t.getId() == taskId)
			{
				i.remove();
			}
		}
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
