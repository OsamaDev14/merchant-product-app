# 🛒 Merchant Product Management System

---

## 📌 Project Overview

Java-based backend-focused console application using Hibernate (JPA) and MySQL to manage merchants and products.
It features full CRUD operations, secure authentication, JPA mappings, and a layered architecture (Controller-Service-DAO).

---

## 🛠️ Tech Stack

* Java
* Hibernate (JPA)
* MySQL
* Maven

---

## 🏗️ Architecture

```text
Controller → Service → DAO → Database
```

* **Controller** → Handles user input (CLI menu)
* **Service** → Business logic & validation
* **DAO** → Database operations using Hibernate
* **Entity** → JPA mapping

---

## ✨ Features

### 👤 Merchant

* Register new merchant
* Update merchant details
* Delete merchant
* Login using Email & Password
* Login using Phone & Password

### 📦 Product

* Add product (linked to merchant)
* Update product
* Delete product
* View products by merchant
* Search products by brand
* Search products by category

---

## 🔐 Security Features

* Login-based access control
* Only logged-in users can manage products
* Merchant-specific data access

---

## 🧠 Key Concepts Used

* Hibernate ORM
* JPA Annotations (`@Entity`, `@OneToMany`, `@ManyToOne`)
* Bidirectional Mapping
* JPQL Queries
* Layered Architecture
* Exception Handling

---

## 📸 Screenshots

> Add screenshots in `/screenshots` folder


\

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/OsamaDev14/merchant-product-app.git
```

### 2. Configure Database

Update `persistence.xml`:

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/your_db"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="your_password"/>
```

### 3. Run Application

Run:

```
MerchantProductController.java
```
---

## 👨‍💻 Author

**Osama**
Java Backend Developer

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
