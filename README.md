# 📚 Book Store App

## 🚀 Project Overview
A simple book store application built with **Spring Boot** and **MySQL**.

## 📌 Features
- User authentication (Register/Login)
- CRUD operations for books
- Search and filter books
- Order management

## 🛠️ Tech Stack
- **Backend**: Java, Spring Boot, Spring Data JPA
- **Database**: MySQL
- **Authentication**: Spring Security (JWT)
- **Frontend**: React (future work)

## 📂 System Design Overview
### 1️⃣ **Architecture**
- Monolithic Spring Boot Application
- REST API for frontend communication
- MySQL database for storing data

### 2️⃣ **Database Schema (Basic)**
- **Users Table**: `id`, `name`, `email`, `password`
- **Books Table**: `id`, `title`, `author`, `price`
- **Orders Table**: `id`, `user_id`, `book_id`, `status`

## 🔧 Setup Instructions
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/book-store-app.git
