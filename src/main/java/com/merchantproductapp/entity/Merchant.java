package com.merchantproductapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//Represents merchant table in database
//One Merchant can have multiple Products (OneToMany relationship)

@Entity
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true)
	private String phone;

	@Column(name = "gst_num")
	private String gstNum;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGstNum() {
		return gstNum;
	}

	public void setGstNum(String gstNum) {
		this.gstNum = gstNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
	    products.add(product);
	    product.setMerchant(this);
	}
	
	public void removeProduct(Product product) {
	    products.remove(product);
	    product.setMerchant(null);
	}
	
	@Override
	public String toString() {
		return "Merchant [id=" + id + ", name=" + name + ", phone=" + phone + ", gstNum=" + gstNum + ", email=" + email + "]";
	}
}
