
# 🔗 URL Shortener — Spring Boot + HTML/CSS

A simple and secure URL shortening service built using **Spring Boot**, **JWT-based authentication**, and a clean modular architecture. Users can register/login, shorten URLs, manage them via a dashboard, and redirect securely to the original destination.

---

## 📌 Features

- ✅ User registration & login with JWT authentication  
- 🔐 Secure endpoints via token-based access   
- 🔗 Shorten URLs with unique aliases  
- 📋 View & delete shortened URLs  
- 🚀 Redirect to original URLs  
- 🧪 Ready-to-run with Spring Boot  

---

## 🧭 Application Workflow

![URL Shortener Diagram]([/ProjectWorkflow.png](https://github.com/gowthamreddysomala/urlshortener/blob/master/ProjectWorkfow.png))

---

## 📁 Project Structure

```
src
├── main
│   ├── java
│   │   └── com.example.urlshortener
│   │       ├── config                  # SecurityConfig
│   │       ├── controller              # AuthController, UrlController, etc.
│   │       ├── dto                     # AuthRequest, UrlResponse, UserDto, etc.
│   │       ├── entity                  # User, Url
│   │       ├── filter                  # JwtAuthFilter.java
│   │       ├── repository              # UserRepository, UrlRepository
│   │       ├── service                 # JwtService, UrlService, UserDetailsServiceImpl
│   │       ├── util                    # UrlshortenerApplication
├── test
│   └── java
│       └── com.example.urlshortener
│           └── UrlshortenerApplicationTests.java
├── resources                           # Application properties & static files
```

---

## 🛠️ Technologies Used

- **Java 21+**  
- **Spring Boot 3**  
- **Spring Security + JWT**  
- **Spring Data JPA**  
- **Maven**  
- **HTML/CSS (frontend)**  
- **MariaDB (configurable)**  

---

## ▶️ Getting Started

### Prerequisites
- Java 17 or later
- Maven

### Steps

```bash
# Clone the repository
git clone https://github.com/yourusername/urlshortener.git
cd urlshortener

# Run the application
./mvnw spring-boot:run
```

Access it at: `http://localhost:8080`

---

## 📮 REST API Endpoints

### 🧑‍💻 Auth

| Method | Endpoint             | Description           | Auth |
|--------|----------------------|-----------------------|------|
| POST   | `/api/auth/login`    | Login user            | ❌   |
| POST   | `/api/auth/register` | Register new account  | ❌   |

### 🔗 URL Actions

| Method | Endpoint         | Description              | Auth |
|--------|------------------|--------------------------|------|
| POST   | `/api/url`       | Create short URL         | ✅   |
| GET    | `/api/url`       | Get user’s URLs          | ✅   |
| DELETE | `/api/url/{id}`  | Delete specific short URL| ✅   |

### 🚀 Redirect

| Method | Endpoint          | Description              | Auth |
|--------|-------------------|--------------------------|------|
| GET    | `/{shortUrl}`     | Redirect to original URL | ❌   |

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 🧩 Example DTOs (Data Transfer Objects)

```json
// AuthRequest
{
  "username": "testuser",
  "password": "password123"
}

// UrlCreateRequest
{
  "longUrl": "https://example.com/very/long/url"
}
```

---

## 🗃️ Database Entities

### `User`
- `id`
- `username`
- `password`

### `Url`
- `id`
- `shortUrl`
- `originalUrl`
- `createdAt`
- `userId`

---

## 📬 Contribution Guide

1. Fork this repo  
2. Create a new branch (`git checkout -b feature/feature-name`)  
3. Make your changes  
4. Push the branch (`git push origin feature/feature-name`)  
5. Open a Pull Request  

---

## 👥 Contributors

### 💻 Team Name: **Vibe Coders**

- Gowtham  
- Mojesh  
- Anjali  
- Gopika  
- Swapnil  

---

## 📄 License

This project is NOT licensed.

---

## 🙋‍♂️ Contact

Have feedback or questions? Feel free to reach out or open an issue.

---
