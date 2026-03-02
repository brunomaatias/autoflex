# AutoFlex API ⚙️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

The **AutoFlex API** is the robust RESTful back-end service powering the [AutoFlex Web](https://github.com/brunomaatias/autoflex-web) application. It handles the core business logic, data persistence, and automated calculations for industrial production planning and inventory management.

## ✨ Core Responsibilities

* **Raw Materials Management:** Endpoints to create, read, update, and delete raw materials, keeping track of stock levels.
* **Product Catalog & BOM:** Manage final products and their Bill of Materials (the specific raw materials and quantities required for manufacturing).
* **Production Calculations:** A dedicated engine that calculates the maximum possible production yield for each product based on current raw material availability.
* **RESTful Architecture:** Clear, predictable, and resource-oriented API endpoints.

## 🛠️ Technologies Used

* **Language:** Java
* **Framework:** Spring Boot (Spring Web, Spring Data JPA)
* **Architecture:** REST API
* **Containerization:** Docker (Ready for containerized deployment)

## 🔗 Front-end Repository

This API serves the AutoFlex React Front-end. To see the complete system in action, check out the client-side repository:
👉 **[AutoFlex Web Interface](https://github.com/brunomaatias/autoflex-web)**

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* Maven (or Gradle, depending on your build tool)
* A relational database running locally or via Docker (e.g., PostgreSQL, MySQL, or H2 for memory testing)

### Installation & Execution

1. Clone the repository:
   ```bash
   git clone [https://github.com/brunomaatias/autoflex-api.git](https://github.com/brunomaatias/autoflex-api.git)

2. Navigate to the project directory: 

    ```bash
    cd autoflex-api  

3. Build the application:
   ```bash
    mvn clean install

4. Run the Spring Boot application:
     ```bash
    mvn spring-boot:run

The API will typically be available at http://localhost:5000.