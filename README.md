# 📊 Microservices-Based CSV Analytics System

A scalable Spring Boot Microservices Project that allows users to:

- Upload CSV files
- Process and store data using Batch Service
- Fetch dataset columns
- Generate chart data for frontend visualization
- Generate multiple charts (dashboard)
- Access all services through API Gateway

--------------------------------------------------

## 🚀 Architecture Overview

Frontend
   ↓
API Gateway (8084)
   ↓
---------------------------------
| Upload Service (8081)         |
| Batch Service (8082)          |
| Analytics Service (8083)      |
---------------------------------
   ↓
MySQL Database
   ↓
Service Registry (Eureka - 8761)

--------------------------------------------------

## 🛠️ Technologies Used

Java 21  
Spring Boot 3  
Spring Cloud (Eureka + Gateway)  
Spring Data JPA  
MySQL  
Maven  
REST APIs  

--------------------------------------------------

## 🔌 Services and Ports

Service Registry → 8761 → http://localhost:8761  
Upload Service → 8081 → http://localhost:8081  
Batch Service → 8082 → http://localhost:8082  
Analytics Service → 8083 → http://localhost:8083  
API Gateway → 8084 → http://localhost:8084  

👉 Frontend should always use:
http://localhost:8084

--------------------------------------------------

## 🌐 API Endpoints (via Gateway)

### 1. Upload CSV
POST http://localhost:8084/batch/process

Body:
form-data  
Key = file (type: File)

Response:
CSV processed successfully

--------------------------------------------------

### 2. Get Dataset Columns
GET http://localhost:8084/chart/columns/{datasetId}

Example:
GET http://localhost:8084/chart/columns/1

Response:
["name", "age", "city", "salary"]

--------------------------------------------------

### 3. Generate Chart
POST http://localhost:8084/chart/generate

Request:
{
  "datasetId": 1,
  "xAxis": "city",
  "yAxis": "salary",
  "chartType": "bar"
}

Response:
{
  "labels": ["Pune", "Mumbai", "Delhi"],
  "values": [10, 20, 15]
}

Chart Types:
bar, line, pie

--------------------------------------------------

### 4. Generate Dashboard (Multiple Charts)
POST http://localhost:8084/chart/dashboard

Request:
[
  {
    "datasetId": 1,
    "xAxis": "city",
    "yAxis": "salary",
    "chartType": "bar"
  },
  {
    "datasetId": 1,
    "xAxis": "department",
    "yAxis": "salary",
    "chartType": "pie"
  }
]

Response:
[
  {
    "labels": ["Pune", "Mumbai", "Delhi"],
    "values": [10, 20, 15]
  },
  {
    "labels": ["IT", "HR", "Sales"],
    "values": [12, 8, 6]
  }
]

--------------------------------------------------

## 🗄️ Database

Database: csv_db  

Configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/csv_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC  
spring.datasource.username=root  
spring.datasource.password=your_password  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  

--------------------------------------------------

## ▶️ How to Run Project

Run services in this order:

1. Start Service Registry
cd service-registry  
mvn spring-boot:run  

Open:
http://localhost:8761  

2. Start Upload Service
cd upload-service  
mvn spring-boot:run  

3. Start Batch Service
cd batch-service  
mvn spring-boot:run  

4. Start Analytics Service
cd analytics-service  
mvn spring-boot:run  

5. Start API Gateway
cd api-gateway  
mvn spring-boot:run  

--------------------------------------------------

## ✅ Verify Services

Open:
http://localhost:8761  

All services should be UP:
UPLOAD-SERVICE  
BATCH-SERVICE  
ANALYTICS-SERVICE  
API-GATEWAY  

--------------------------------------------------

## 🧪 Testing Flow

1. Upload CSV  
POST http://localhost:8084/batch/process  

2. Get Columns  
GET http://localhost:8084/chart/columns/1  

3. Generate Chart  
POST http://localhost:8084/chart/generate  

4. Generate Dashboard  
POST http://localhost:8084/chart/dashboard  

--------------------------------------------------

## 🔄 Project Flow

CSV Flow:
Frontend → Gateway → Batch Service → Database  

Analytics Flow:
Frontend → Gateway → Analytics Service → Database → Chart  

--------------------------------------------------

## ⚠️ Important Notes

- Always use API Gateway (8084)
- Do not call services directly
- MySQL must be running
- Start Eureka first
- Use form-data for file upload
- Key must be "file"

--------------------------------------------------

## ❗ Common Issues

Service not showing → Start Eureka first  
File upload error → Use form-data + file key  
404 error → Check Gateway routes  
DB error → Check MySQL config  
Frontend error → Enable CORS  

--------------------------------------------------

## 👨‍💻 Author

Swapnil Thorat
