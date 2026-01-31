<h1 align="center">🏪 POS Application</h1>

<p align="center">
  <strong>Modern, Scalable and Secure Point of Sale Backend API</strong>
</p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Spring%20Boot-3.5.9-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/></a>
  <a href="#"><img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Redis-7.0-DC382D?style=for-the-badge&logo=redis&logoColor=white" alt="Redis"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/></a>
</p>

---

## 📋 Table of Contents

- [🎯 About The Project](#-about-the-project)
- [✨ Features](#-features)
- [🏗️ Architecture](#️-architecture)
- [🛠️ Tech Stack](#️-tech-stack)
- [📦 Installation](#-installation)
- [🚀 Running](#-running)
- [📁 Project Structure](#-project-structure)
- [🔐 Security](#-security)
- [📊 API Endpoints](#-api-endpoints)
- [🗺️ Roadmap](#️-roadmap)

---

## 🎯 About The Project

**POS Application** is a comprehensive **Point of Sale** backend API developed for the retail industry. It provides core retail functions such as store management, branch operations, inventory tracking, order management, refund processing, and shift reporting on a modern and secure infrastructure.

### 🎪 Use Cases

- 🏬 **Retail Stores** - Multi-store and branch management
- 🍽️ **Restaurants & Cafes** - Order and payment tracking
- 🛒 **Supermarkets** - Inventory and stock management
- 💊 **Pharmacies** - Product and sales tracking

---

## ✨ Features

<table>
<tr>
<td>

### 🏪 Store Management
- Multi-store support
- Branch-based operations
- Store contact information management

</td>
<td>

### 📦 Inventory Management
- Real-time stock tracking
- Branch-based inventory
- Low stock alerts

</td>
</tr>
<tr>
<td>

### 🛍️ Order Management
- Order creation and tracking
- Multiple payment methods
- Order status tracking

</td>
<td>

### 📊 Reporting
- Shift reports
- Sales analytics
- Performance metrics

</td>
</tr>
<tr>
<td>

### 🔄 Refund Processing
- Easy refund process
- Refund history tracking
- Automatic stock updates

</td>
<td>

### 👥 User Management
- Role-based access control
- Employee and customer management
- Activity logs

</td>
</tr>
</table>

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENT LAYER                              │
│                   (Web, Mobile, POS Terminal)                    │
└───────────────────────────┬─────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                      API GATEWAY LAYER                           │
│              ┌─────────────────────────────┐                     │
│              │     Spring Security         │                     │
│              │     JWT Authentication      │                     │
│              └─────────────────────────────┘                     │
└───────────────────────────┬─────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                     CONTROLLER LAYER                             │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐           │
│  │  Store   │ │  Branch  │ │  Order   │ │ Product  │  ...      │
│  │Controller│ │Controller│ │Controller│ │Controller│           │
│  └──────────┘ └──────────┘ └──────────┘ └──────────┘           │
└───────────────────────────┬─────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                      SERVICE LAYER                               │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐           │
│  │  Store   │ │  Branch  │ │  Order   │ │ Product  │  ...      │
│  │ Service  │ │ Service  │ │ Service  │ │ Service  │           │
│  └──────────┘ └──────────┘ └──────────┘ └──────────┘           │
└───────────────────────────┬─────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                    REPOSITORY LAYER                              │
│              ┌─────────────────────────────┐                     │
│              │     Spring Data JPA         │                     │
│              └─────────────────────────────┘                     │
└───────────────────────────┬─────────────────────────────────────┘
                            │
              ┌─────────────┼─────────────┐
              ▼             ▼             ▼
        ┌──────────┐  ┌──────────┐  ┌──────────┐
        │  MySQL   │  │  Redis   │  │  Future  │
        │ Database │  │  Cache   │  │  (ES/MQ) │
        └──────────┘  └──────────┘  └──────────┘
```

### 🎨 Design Patterns

| Pattern | Usage Area |
|---------|------------|
| **Layered Architecture** | Controller → Service → Repository |
| **Strategy Pattern** | Shift report calculations |
| **Builder Pattern** | Entity and DTO creation (Lombok) |
| **Repository Pattern** | Data access layer |
| **DTO Pattern** | Request/Response separation |
| **Factory Pattern** | Entity creation operations |

---

## 🛠️ Tech Stack

### Backend Framework
| Technology | Version | Description |
|------------|---------|-------------|
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white) | 17 | Main programming language |
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white) | 3.5.9 | Backend framework |
| ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=spring-security&logoColor=white) | 6.x | Security framework |
| ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat-square&logo=spring&logoColor=white) | 3.x | ORM & database operations |

### Database & Cache
| Technology | Version | Description |
|------------|---------|-------------|
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) | 8.0 | Primary database |
| ![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white) | 7.0 | Cache & session management |

### Security & Authentication
| Technology | Version | Description |
|------------|---------|-------------|
| ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=json-web-tokens&logoColor=white) | 0.12.6 | Token-based authentication |
| ![OAuth2](https://img.shields.io/badge/OAuth2-EB5424?style=flat-square&logo=auth0&logoColor=white) | - | OAuth2 client & resource server |
| ![Bouncy Castle](https://img.shields.io/badge/Bouncy%20Castle-1.83-blue?style=flat-square) | 1.83 | Cryptography library |

### Payment Integrations
| Technology | Version | Status |
|------------|---------|--------|
| ![Stripe](https://img.shields.io/badge/Stripe-008CDD?style=flat-square&logo=stripe&logoColor=white) | 28.3.1 | 🔜 Planned |
| ![Razorpay](https://img.shields.io/badge/Razorpay-02042B?style=flat-square&logo=razorpay&logoColor=white) | 1.4.8 | 🔜 Planned |

### DevOps & Tools
| Technology | Description |
|------------|-------------|
| ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white) | Containerization |
| ![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?style=flat-square&logo=docker&logoColor=white) | Multi-container orchestration |
| ![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=flat-square) | Boilerplate code reduction |
| ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white) | Dependency management |

---

## 📦 Installation

### Prerequisites

- ☕ **Java 17** or higher
- 🐳 **Docker** and **Docker Compose**
- 📦 **Maven 3.8+**

### 1️⃣ Clone the Project

```bash
git clone https://github.com/yourusername/pos-application.git
cd pos-application
```

### 2️⃣ Start Databases with Docker

```bash
docker-compose up -d
```

This command will start the following services:
- 🗄️ **MySQL 8.0** - Port: 3306
- ⚡ **Redis 7.0** - Port: 6379

### 3️⃣ Build the Application

```bash
./mvnw clean install -DskipTests
```

---

## 🚀 Running

### Development Environment

```bash
./mvnw spring-boot:run
```

### Production Build

```bash
./mvnw clean package -Pprod
java -jar target/pos-application-0.0.1-SNAPSHOT.jar
```

### Running with Docker

```bash
docker-compose --profile app up -d
```

### Application Access

| Service | URL |
|---------|-----|
| 🌐 API | http://localhost:8080 |
| 📚 Swagger UI | http://localhost:8080/swagger-ui.html *(coming soon)* |
| 🔴 Redis | localhost:6379 |
| 🗄️ MySQL | localhost:3306 |

---

## 📁 Project Structure

```
pos-application/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/egin/
│   │   │   ├── 📄 PosApplication.java          # Main application class
│   │   │   │
│   │   │   ├── 📁 admin/                       # 👨‍💼 Admin module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   ├── 📁 model/
│   │   │   │   └── 📁 exception/
│   │   │   │
│   │   │   ├── 📁 auth/                        # 🔐 Authentication module
│   │   │   │   ├── 📁 config/                  # Security configuration
│   │   │   │   ├── 📁 filter/                  # JWT filter
│   │   │   │   ├── 📁 security/                # Security utilities
│   │   │   │   ├── 📁 service/                 # Auth services
│   │   │   │   └── 📁 utils/                   # Helper classes
│   │   │   │
│   │   │   ├── 📁 branch/                      # 🏢 Branch module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   ├── 📁 common/                      # 🔧 Common components
│   │   │   │   ├── 📁 config/                  # Redis, etc. config
│   │   │   │   ├── 📁 exception/               # Global exception handler
│   │   │   │   └── 📁 model/                   # Base entity, etc.
│   │   │   │
│   │   │   ├── 📁 inventory/                   # 📦 Inventory module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   ├── 📁 order/                       # 🛒 Order module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   ├── 📁 product/                     # 🏷️ Product module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   ├── 📁 refund/                      # 🔄 Refund module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   ├── 📁 shiftReport/                 # 📊 Shift report module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   ├── 📁 store/                       # 🏪 Store module
│   │   │   │   ├── 📁 controller/
│   │   │   │   ├── 📁 service/
│   │   │   │   ├── 📁 repository/
│   │   │   │   └── 📁 model/
│   │   │   │
│   │   │   └── 📁 user/                        # 👤 User module
│   │   │       ├── 📁 controller/
│   │   │       ├── 📁 service/
│   │   │       ├── 📁 repository/
│   │   │       └── 📁 model/
│   │   │
│   │   └── 📁 resources/
│   │       └── 📄 application.properties       # Application configuration
│   │
│   └── 📁 test/                                # 🧪 Test files
│       └── 📁 java/com/egin/
│
├── 📄 docker-compose.yml                       # 🐳 Docker configuration
├── 📄 pom.xml                                  # 📦 Maven dependencies
├── 📄 ANALIZ.md                                # 📋 Project analysis
└── 📄 README.md                                # 📖 This file
```

---

## 🔐 Security

### Authentication Flow

```
┌──────────┐      ┌──────────┐      ┌──────────┐      ┌──────────┐
│  Client  │ ───► │  Login   │ ───► │   JWT    │ ───► │ Protected│
│          │      │ Endpoint │      │  Token   │      │ Resource │
└──────────┘      └──────────┘      └──────────┘      └──────────┘
     │                                    │                 │
     │◄───────── Access Token ───────────┤                 │
     │◄───────── Refresh Token ──────────┤                 │
     │                                                      │
     │─────────── Bearer Token ────────────────────────────►│
```

### Role-Based Access Control (RBAC)

| Role | Permissions |
|------|-------------|
| 🔴 **ADMIN** | Full system access |
| 🟠 **STORE_ADMIN** | Store and branch management |
| 🟡 **CASHIER** | Order and sales operations |
| 🟢 **USER** | Basic read permissions |

### Security Features

- ✅ JWT-based stateless authentication
- ✅ Refresh token mechanism
- ✅ Invalid token tracking
- ✅ Method-level security (@PreAuthorize)
- ✅ Password encryption (BCrypt)
- ✅ CORS configuration

---

## 📊 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/login` | User login |
| `POST` | `/api/auth/register` | User registration |
| `POST` | `/api/auth/refresh` | Token refresh |
| `POST` | `/api/auth/logout` | Logout |

### 🏪 Store
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/stores` | Get all stores |
| `GET` | `/api/stores/{id}` | Get store details |
| `POST` | `/api/stores` | Create store |
| `PUT` | `/api/stores/{id}` | Update store |
| `DELETE` | `/api/stores/{id}` | Delete store |

### 🏢 Branch
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/branches` | Get all branches |
| `GET` | `/api/branches/{id}` | Get branch details |
| `GET` | `/api/stores/{storeId}/branches` | Get branches by store |
| `POST` | `/api/branches` | Create branch |
| `PUT` | `/api/branches/{id}` | Update branch |

### 🛒 Order
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/orders` | Get all orders |
| `GET` | `/api/orders/{id}` | Get order details |
| `POST` | `/api/orders` | Create order |
| `PUT` | `/api/orders/{id}/status` | Update order status |

### 🏷️ Product
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get product details |
| `POST` | `/api/products` | Create product |
| `PUT` | `/api/products/{id}` | Update product |

### 📦 Inventory
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/inventory` | Get inventory list |
| `GET` | `/api/inventory/branch/{branchId}` | Get branch inventory |
| `PUT` | `/api/inventory/{id}` | Update stock |

---

## 🗺️ Roadmap

### ✅ Completed
- [x] Basic CRUD operations
- [x] JWT Authentication
- [x] Role-based Authorization
- [x] Docker Compose configuration
- [x] Redis Cache integration
- [x] Multi-store/branch support

### 🚧 In Progress
- [ ] Swagger/OpenAPI documentation
- [ ] Email notification system
- [ ] Payment integrations (Stripe, Razorpay)

### 🔜 Planned

#### High Priority
| Feature | Priority | Status |
|---------|----------|--------|
| 🧪 Unit Tests | 🔴 High | Planned |
| 🧪 Integration Tests | 🔴 High | Planned |
| 📚 Swagger/OpenAPI | 🔴 High | Planned |
| 📧 Email Notifications | 🟡 Medium | Planned |

#### Medium Priority
| Feature | Priority | Status |
|---------|----------|--------|
| 🔍 Elasticsearch | 🟡 Medium | Planned |
| 📊 Advanced Reporting | 🟡 Medium | Planned |
| 🎫 Discount/Promotion System | 🟡 Medium | Planned |

#### Long Term
| Feature | Priority | Status |
|---------|----------|--------|
| ☸️ Kubernetes | 🟢 Long-term | Planned |
| 📨 Message Queues (Kafka/RabbitMQ) | 🟢 Long-term | Planned |
| 📈 Prometheus & Grafana | 🟢 Long-term | Planned |
| 🔄 CI/CD Pipeline | 🟢 Long-term | Planned |

---


<p align="center">
  <strong>⭐ If you like this project, don't forget to give it a star!</strong>
</p>

<p align="center">
  Made by <a href="https://github.com/AhmetEGIN">Ahmet Egin</a>
</p>
