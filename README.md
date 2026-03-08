### TaskManager Full-Stack Application
A comprehensive task management system featuring a Spring Boot 4.0 backend and a React 19 frontend. This project implements secure JWT authentication and role-based access control.

### Project Structure
 -taskmanager/taskmanager: The Java backend containing the REST API.
 
 -taskmanager-frontend: The React.js frontend application.

 ### Tech Stack
Backend: Java 25, Spring Boot 4.0.3, Spring Security, MySQL, JWT (JSON Web Token), Lombok.

Frontend: React 19.2.4, Axios for API calls, React Router DOM for navigation.

### API Endpoints

## Authentication (/api/v1/auth)

POST /register: Register a new user account.

POST /login: Authenticate and receive a JWT.

### Task Management (/api/v1/tasks)
All endpoints below require JWT Authentication-
POST /: Create a new task.
GET /: Retrieve all tasks for the logged-in user.
GET /{id}: Retrieve a specific task by ID.
PUT /{id}: Update an existing task.
DELETE /{id}: Delete a task.

### Admin Operations (/api/v1/admin)
Requires ROLE_ADMIN authority.
GET /users: List all registered users.
GET /tasks: List all tasks across the entire system.

### 📝 Features
(i) JWT Security: Secure access to task resources using token-based authentication.
(ii) Role-Based Access: Specialized administrative views to monitor all users and tasks.
(iii) Full CRUD: Complete management lifecycle for personal tasks.
(iv) Responsive Frontend: Modern React interface built with version 19.
