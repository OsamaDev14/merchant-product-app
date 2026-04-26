package com.merchantproductapp.service;

import com.merchantproductapp.dao.MerchantDao;
import com.merchantproductapp.entity.Merchant;

//Service layer contains business logic
//It acts as a bridge between Controller and DAO

public class MerchantService {

    MerchantDao dao = new MerchantDao();
    
    public Merchant saveMerchant(Merchant m) {
    	
        if (m.getEmail() == null || m.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        
        if (m.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        return dao.saveMerchant(m);
    }

    public Merchant updateMerchant(Merchant m) {
        return dao.updateMerchant(m);
    }

    public boolean deleteMerchant(int id) {
        return dao.deleteMerchant(id);
    }

    public Merchant findMerchantById(int id) {
        return dao.findMerchantById(id);
    }

    public Merchant loginByEmail(String email, String password) {
    	
    	if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
    		throw new IllegalArgumentException("Invalid credentials");
        }
        return dao.loginByEmail(email, password);
    }

    public Merchant loginByPhone(String phone, String password) {
    	if (phone == null || phone.isEmpty() || password == null || password.isEmpty()) {
    		throw new IllegalArgumentException("Invalid credentials");
        }
        return dao.loginByPhone(phone, password);
    }
}
