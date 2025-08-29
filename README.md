# 📌 Credit Card Management System

A Spring Boot 3.x application for managing credit cards and transactions.  
It provides REST APIs to:  

- Add and fetch credit cards  
- View card limits (total, used, available)  
- Record transactions  
- Fetch transactions with pagination and sorting  

Built with **Spring Boot, Spring Security, Spring Data JPA, H2 Database**, and tested using **JUnit + Mockito + MockMvc**.

---

## ⚡ Features
- Manage credit card details  
- Transaction management with limit enforcement  
- Validation for card number, CVV, expiry date  
- Exception handling with structured error responses  
- In-memory H2 database with web console  
- Basic Authentication (username/password)  
- Pagination and sorting for listing APIs  
- Unit & integration tests  

---

## 🏗️ Tech Stack
- **Java 17+**  
- **Spring Boot 3.x**  
- **Spring Data JPA**  
- **Spring Security (Basic Auth)**  
- **H2 Database**  
- **JUnit 5 + Mockito + MockMvc**  

---

## 📂 Project Structure
src/main/java/com/example/creditcard  
├── config  
|   ├── AuditorAwareImpl.java  
│   └── SecurityConfig.java  
├── controller  
│   ├── CreditCardController.java  
│   └── TransactionController.java  
├── dto  
│   ├── CreditCardDto.java  
│   ├── LimitInfoDto.java  
│   └── TransactionDto.java  
├── exception  
│   ├── GlobalExceptionHandler.java  
│   ├── LimitExceededException.java  
│   └── ResourceNotFoundException.java  
├── model  
│   ├── CreditCard.java  
│   └── Transaction.java  
├── repository  
│   ├── CreditCardRepository.java  
│   └── TransactionRepository.java  
├── service  
│   ├── CreditCardService.java  
│   └── TransactionService.java  
└── CreditCardApplication.java  


src/test/java/com/example/creditcard  
├── controller  
│   ├── CreditCardControllerTest.java  
│   └── TransactionControllerTest.java  
├── service  
    ├── CreditCardServiceTest.java  
    └── TransactionServiceTest.java 

---

## ⚙️ Getting Started

### 1. Clone the repository
git clone https://github.com/ArjunBhogavimath/creditcard.git  
cd creditcard  

### 2. Build the project
mvn clean install  

### 3. Run the application
mvn spring-boot:run  

The app starts on:  
http://localhost:8080  

---

## 🔑 Authentication
This project uses **Basic Authentication**.  
Use the following credentials for API access:

- Username: admin  
- Password: admin123  

In Postman, set Authorization → Basic Auth.  

---

## 🗂️ API Endpoints

### Credit Card APIs
POST   /api/v1/cards                          → Add a new credit card  
GET    /api/v1/cards/{id}                     → Fetch credit card by ID  
GET    /api/v1/cards?page=0&size=5&sort=cardHolderName,asc → List cards (paginated)  
GET    /api/v1/cards/{id}/limit               → Get card limit info  

### Transaction APIs
POST   /api/v1/transactions                   → Make a new transaction  
GET    /api/v1/transactions/card/{cardId}?page=0&size=5   → List transactions by card  

---

## 🗄️ Database (H2)
Access the H2 Console at:  

http://localhost:8080/h2-console  

Settings:  
- JDBC URL: jdbc:h2:mem:creditcarddb  
- Username: creditcard  
- Password: (leave empty)  

---

## 🧪 Running Tests
Run all tests with:  
mvn test  

Tests include:  
- Service Layer: Pure unit tests using Mockito  
- Controller Layer: MockMvc tests with Spring Security  

---

## 📸 Sample Postman Request

Add Credit Card  
POST http://localhost:8080/api/v1/cards  

Body:  
{
  "cardHolderName": "John Doe",
  "cardNumber": "1234567812345678",
  "expiryDate": "12/29",
  "cvv": "123",
  "totalLimit": 10000
}

---

## 🚀 Future Enhancements
- JWT authentication instead of Basic Auth  
- External DB (PostgreSQL/MySQL)  
- Docker support  
- CI/CD pipeline  

---


✍️ **Developed by Mallikarjunaiah B M**  
🔗 [LinkedIn](https://www.linkedin.com/in/mallikarjunaiah-b-m-1331a319a/) | [vpmallikarjuna03@gmail.com](mailto:vpmallikarjuna03@gmail.com)
