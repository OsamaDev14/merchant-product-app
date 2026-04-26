package com.merchantproductapp.service;

import java.util.List;

import com.merchantproductapp.dao.ProductDao;
import com.merchantproductapp.entity.Merchant;
import com.merchantproductapp.entity.Product;
//Service layer for Product entity
//Contains business logic and validation before calling DAO

public class ProductService {

    ProductDao dao = new ProductDao();

    public Product addProduct(Product p, Merchant loggedInUser) {
    	
    	if (loggedInUser == null) {
    		throw new IllegalArgumentException("Please login first");
        }
    	
    	if (p == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (p.getName() == null || p.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
    	
    	if (p.getCost() <= 0) {
    		throw new IllegalArgumentException("Invalid cost");
        }
    	
        return dao.addProduct(p,loggedInUser);
    }
    
    public Product findById(int id) {
        return dao.findById(id);
    }
    
    public List<Product> findProductByMerchantId(int mid) {
        return dao.findProductByMerchantId(mid);
    }


    public Product updateProduct(Product p) {
        return dao.updateProduct(p);
    }


    public boolean deleteProduct(int id) {
        return dao.deleteProduct(id);
    }

    public List<Product> findProductByBrand(String brand) {
        return dao.findProductByBrand(brand);
    }

    public List<Product> findProductByCategory(String category) {
        return dao.findProductByCategory(category);
    }
}
