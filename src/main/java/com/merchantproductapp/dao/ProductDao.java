package com.merchantproductapp.dao;

import java.util.List;

import com.merchantproductapp.entity.Merchant;
import com.merchantproductapp.entity.Product;

import com.merchantproductapp.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

//DAO class for Product entity
//Handles all database operations related to Product using Hibernate

public class ProductDao {
	
	//add product to merchant
	public Product addProduct(Product p, Merchant m) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		
		Merchant dbMerchant=em.find(Merchant.class, m.getId());
		
		if(dbMerchant!=null) {
			dbMerchant.addProduct(p); // handles both sides
			
			//em.persist(p);
		    et.commit();
		    em.close();
		    return p;
		}
		em.close();
		return null;
	}
	
	
	//find products by merchant id
	public List<Product> findProductByMerchantId(int mid){
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		TypedQuery<Product> q=em.createQuery("select p from Product p where p.merchant.id = :id", Product.class);
		q.setParameter("id", mid);
		List<Product> list = q.getResultList();
		em.close();
		return list;
	}
	
	//Update product
	public Product updateProduct(Product p) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		
		Product db=em.find(Product.class, p.getId()); //fetch
		if(db!=null) {
			db.setName(p.getName());
			db.setBrand(p.getBrand());
			db.setCategory(p.getCategory());
			db.setCost(p.getCost());
			et.commit();
			em.close();
			return db;
		}
		em.close();
		return null;
	}
	
	// delete product
	public boolean deleteProduct(int id) {
		
		EntityManager em = HibernateUtil.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    et.begin();
	    
	    Product p = em.find(Product.class, id);

	    if (p != null) {
	        em.remove(p);
	        et.commit();
	        em.close();
	        return true;
	    }
	    em.close();
	    return false;
	}
	
	//find by product id
	public Product findById(int id) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		Product p=em.find(Product.class, id);
		em.close();
	    return p;
	}
	
	//find by brand
	public List<Product> findProductByBrand(String br){
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		TypedQuery<Product> q = em.createQuery("select p from Product p where p.brand = :brand", Product.class);
		q.setParameter("brand", br);
		List<Product> list = q.getResultList();
		em.close();
		return list;
	}
	
	//find by category
	public List<Product> findProductByCategory(String category){
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		TypedQuery<Product> q=em.createQuery("select p from Product p where p.category= :catg", Product.class);
		q.setParameter("catg", category);
		List<Product> list = q.getResultList();
		em.close();
		return list;
	}
}
