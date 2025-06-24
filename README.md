# sftp-server-springboot
Building SFTP Server using Java Spring Boot

# 📁 SFTP Web Interface with Spring Boot & Thymeleaf

This project is a simple yet functional web-based SFTP interface built using **Spring Boot** and **Thymeleaf**. It provides a clean starting point for secure file operations over SFTP within a web application.

## ✨ Features

- 🔐 Secure access using **Spring Security**
- 📂 Browse server-side folders and view file lists
- 📄 Each file includes action icons:
  - **Delete**
  - **Preview**
  - **Download**
- ⬆️ Upload button for adding new files (placed outside the file grid)

## 🛠️ Tech Stack

- **Backend**: Java + Spring Boot
- **Frontend**: Thymeleaf templating engine
- **Security**: Spring Security (basic authentication or custom login)

## 📦 Getting Started

1. Clone this repository:

   ```bash
   git clone https://github.com/your-username/sftp-springboot.git

2. Build and run:
   ./mvnw spring-boot:run
   
3. Access the app at http://localhost:8080
4. Access using SFTP Tools such as FileZila or WinScp
   Credential :
     user      : user1
     password  : pass123

Folder structure: 

![image](https://github.com/user-attachments/assets/ce821bd3-4d94-44a5-b34d-3c40d39a849e)


License :
This project is shared openly to support learning and development. Feel free to fork and enhance it. See LICENSE file for details.
