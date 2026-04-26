package com.merchantproductapp.dao;

import com.merchantproductapp.entity.Merchant;

import com.merchantproductapp.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

//DAO uses EntityManager to perform CRUD operations
//Uses HibernateUtil to reuse EntityManagerFactory (singleton)

public class MerchantDao {
	
	//save merchant
	public Merchant saveMerchant(Merchant m) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		
		em.persist(m);
		et.commit();
		em.close();
		return m;
		
	}
	
	//update merchant
	public Merchant updateMerchant(Merchant m) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		
		Merchant db=em.find(Merchant.class, m.getId()); //fetch
		
		if(db!=null) {
			db.setName(m.getName()); 
			db.setPhone(m.getPhone());
			db.setGstNum(m.getGstNum());
			db.setEmail(m.getEmail());
			db.setPassword(m.getPassword());
		}
		et.commit();
		em.close();
		return db;
	}
	
	// find by id
	public Merchant findMerchantById(int id) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		Merchant m=em.find(Merchant.class, id);
		em.close();
		return m;
	}
	
	//delete merchant
	public boolean deleteMerchant(int id) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
	    Merchant m = em.find(Merchant.class, id);
	    
	    if (m != null) {
		    em.remove(m);
		    et.commit();
		    em.close();
		    return true;
	   }
	    em.close();
	    return false;
	}
	
	//login by email
	public Merchant loginByEmail(String email, String password) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		TypedQuery<Merchant> q = em.createQuery("select m from Merchant m where m.email = :email and m.password = :pass", Merchant.class);
		q.setParameter("email", email);
		q.setParameter("pass", password);
		
		try {
			Merchant db= q.getSingleResult();
			return db;
		}
		catch(NoResultException e) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	//login by phone
	public Merchant loginByPhone(String phone, String password) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		TypedQuery<Merchant> q=em.createQuery("select m from Merchant m where m.phone= :ph and m.password= :pass", Merchant.class);
		q.setParameter("ph", phone);
		q.setParameter("pass", password);
		
		try {
			Merchant db= q.getSingleResult();
			return db; 
		}
		catch(NoResultException e) {
			return null;
		}
		finally {
			em.close();
		}
	}
}
