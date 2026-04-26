package com.merchantproductapp.controller;

import java.util.List;
import java.util.Scanner;

import com.merchantproductapp.entity.Merchant;
import com.merchantproductapp.entity.Product;
import com.merchantproductapp.service.MerchantService;
import com.merchantproductapp.service.ProductService;

//Handles user interaction using menu-driven approach
//Calls Service layer for business logic

public class MerchantProductController {
	static Merchant loggedInUser = null;
	
	static Scanner sc=new Scanner(System.in);
	
	static MerchantService mservice = new MerchantService();
	static ProductService pservice = new ProductService();
	
	
	public static void main(String[] args) {
		
		System.out.println("===== Merchant Product App Started =====");
		boolean running = true;

		while(running) {
			
			try {
				System.out.println("\n===== MENU =====");
				System.out.println("1. Save Merchant");
				System.out.println("2. Update Merchant");
				System.out.println("3. Delete Merchant");
				System.out.println("4. Find Merchant By Id");
				System.out.println("5. Login by Email & Password");
				System.out.println("6. Login by Phone & Password");
				System.out.println("7. Add Product");
				System.out.println("8. Update Product");
				System.out.println("9. Delete Product");
				System.out.println("10. Find Products");
				System.out.println("11. Find Products by Brand");
				System.out.println("12. Find Products by Category");
				System.out.println("13. Logout");
				System.out.println("0. Exit");
				
				System.out.println("Enter choice: ");
				int choice = Integer.parseInt(sc.nextLine());
				
				switch(choice) {
				
				case 0: System.out.println("Thank you! Exiting...");
						running=false;
				break;
					
				case 1: saveMerchant();
				break;
				case 2: updateMerchant();
				break;
				case 3: deleteMerchant();
				break;
				case 4: findMerchantById();
				break;
				case 5: loginByEmail();
				break;
				case 6: loginByPhone();
				break;
				case 7: addProduct();
				break;
				case 8: updateProduct();
				break;
				case 9: deleteProduct();
				break;
				case 10: findProductByMerchantId();
				break;
				case 11: findProductByBrand();
				break;
				case 12: findProductByCategory();
				break;
				case 13: logout();
				break;
				
				default:
					System.out.println("Invalid choice");
				}
			}
			catch (Exception e) {
				System.err.println("Invalid input! Try again."); }
		}
		sc.close();
	}
	
	
	//===================Merchant=================
	
	//save Merchant
	private static void saveMerchant() {
		
		Merchant m=new Merchant();
		
		System.out.print("Enter name: ");
		m.setName(sc.nextLine());
		
		System.out.print("Enter phone: ");
		m.setPhone(sc.nextLine());
		
		System.out.print("Enter GST: ");
		m.setGstNum(sc.nextLine());
		
		System.out.print("Enter email: ");
		m.setEmail(sc.nextLine());
		
		System.out.print("Enter password: ");
		m.setPassword(sc.nextLine());
		
		Merchant db=mservice.saveMerchant(m);
		System.out.println("Saved with id: " + db.getId());
	}
	
	//Update Merchant
	private static void updateMerchant() {
		
		if (loggedInUser == null) {
	        System.err.println("Please login first");
	        return;
	    }
	    
	    Merchant existing = mservice.findMerchantById(loggedInUser.getId());
	    
	    if (existing == null) {
	        System.err.println("Merchant not found");
	        return;
	    }
	    
		System.out.print("Enter name: ");
		existing.setName(sc.nextLine());

        System.out.print("Enter phone: ");
        existing.setPhone(sc.nextLine());

        System.out.print("Enter GST: ");
        existing.setGstNum(sc.nextLine());

        System.out.print("Enter email: ");
        existing.setEmail(sc.nextLine());

        System.out.print("Enter password: ");
        existing.setPassword(sc.nextLine());
		
		Merchant db=mservice.updateMerchant(existing);
		 
		System.out.println("Updated: " + db);
	}
	
	//delete Merchant
	private static void deleteMerchant() {
		
		if (loggedInUser == null) {
	        System.err.println("Please login first");
	        return;
	    }

	    if (mservice.deleteMerchant(loggedInUser.getId())) {
	        System.out.println("Merchant deleted successfully");
	        loggedInUser = null;
	    }
	    else
	        System.err.println("Merchant not found");
	}
	
	//find Merchant By Id
	private static void findMerchantById() {
		
		System.out.println("Enter Merchant ID:");
		int id = Integer.parseInt(sc.nextLine());
		
		Merchant m=mservice.findMerchantById(id);
		
		if(m!=null)
			System.out.println(m);
		else
			System.err.println("Merchant not found. Please check ID.");
	}
	
	//login By Email
	private static void loginByEmail() {
		System.out.println("Enter email");
		String email = sc.nextLine();
		
		System.out.println("Enter password:");
        String password = sc.nextLine();
        
        loggedInUser = mservice.loginByEmail(email, password);

        if (loggedInUser != null)
            System.out.println("Login Success: " + loggedInUser.getName());
        else
            System.out.println("Invalid credentials");
	}
	
	//login By Phone
	private static void loginByPhone() {
		
		System.out.println("Enter phone");
		String phone = sc.nextLine();

        System.out.println("Enter password:");
        String password = sc.nextLine();
        
        loggedInUser = mservice.loginByPhone(phone, password);

        if (loggedInUser != null)
            System.out.println("Login Success: " + loggedInUser.getName());
        else
            System.out.println("Invalid credentials");
	}
	
	
	//===================Product=================
	
	//add Product
	private static void addProduct() {
		
		if (loggedInUser == null) {
	        System.err.println("Please login first");
	        return;
	    }

	    Product p = new Product();

	    System.out.print("Enter Product Name: ");
	    p.setName(sc.nextLine());

	    System.out.print("Enter Brand: ");
	    p.setBrand(sc.nextLine());

	    System.out.print("Enter Category: ");
	    p.setCategory(sc.nextLine());

	    System.out.print("Enter Cost: ");
	    p.setCost(Double.parseDouble(sc.nextLine()));

	    Product db = pservice.addProduct(p, loggedInUser);

	    if (db != null)
	        System.out.println("Product added with id: " + db.getId());
	    
	    else
			System.err.println("Failed to add product");
	}
	
	//update Product
	private static void updateProduct() {
		
		
		if (loggedInUser == null) {
			System.err.println("Please login first");
			return;
		}
		
		System.out.print("Enter Product ID: ");
		int id = Integer.parseInt(sc.nextLine());
		
		Product existing = pservice.findById(id);
		
		// Security check
        if (existing == null || existing.getMerchant().getId() != loggedInUser.getId()) {
            System.out.println("Unauthorized or product not found");
            return;
        }
		
		System.out.print("Enter product name: ");
		existing.setName(sc.nextLine());

        System.out.print("Enter brand: ");
        existing.setBrand(sc.nextLine());

        System.out.print("Enter category: ");
        existing.setCategory(sc.nextLine());

        System.out.print("Enter cost: ");
        existing.setCost(Double.parseDouble(sc.nextLine()));
		
		Product db=pservice.updateProduct(existing);
		
		if(db!=null) 
			System.out.println("Updated:" +db);
		else
			System.err.println("Product not found");
	}
	
	//delete Product
	private static void deleteProduct() {
		
		if (loggedInUser == null) {
			System.err.println("Please login first");
			return;
		}
		
	    System.out.println("Enter Product ID:");
	    int id = Integer.parseInt(sc.nextLine());
	    
	    Product p = pservice.findById(id);

        if (p == null || p.getMerchant().getId() != loggedInUser.getId()) {
            System.out.println("Unauthorized or product not found");
            return;
        }

	    if (pservice.deleteProduct(id))
	        System.out.println("Product deleted successfully");
	    else
	        System.err.println("Failed to delete");
	}
	
	//find Product By Merchant Id
	private static void findProductByMerchantId() {
		
		if (loggedInUser == null) {
		    System.err.println("Please login first  to continue");
		    return;
		}

		List<Product> list = pservice.findProductByMerchantId(loggedInUser.getId());
		
		if(!list.isEmpty()) {
			for(Product p : list) {
				System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Brand: " + p.getBrand() + ", Cost: " + p.getCost()
				+ " | Merchant: " + p.getMerchant().getName());
			}
		}
		else
			System.err.println("No products found");
	}
	
	//find Product By Brand
	private static void findProductByBrand() {
		
		System.out.println("Enter brand:");
		String brand = sc.nextLine();

		List<Product> list =pservice.findProductByBrand(brand);
		
		if(!list.isEmpty()) {
			for(Product p : list) {
				System.out.println(p);
			}
		}
		else
			System.err.println("No products found");
	}
	
	//find Product By Category
	private static void findProductByCategory() {
		
		System.out.println("Enter category:");
		String category = sc.nextLine();

		List<Product> list =pservice.findProductByCategory(category);
		
		if(!list.isEmpty()) {
			for(Product p : list) {
				System.out.println(p);
			}
		}
		else
			System.err.println("No products found");
	}
	
	//Logout
	private static void logout() {
	    if (loggedInUser != null) {
	        System.out.println("Logged out: " + loggedInUser.getName());
	        loggedInUser = null;
	    } else {
	        System.err.println("No user is logged in");
	    }
	}
}
