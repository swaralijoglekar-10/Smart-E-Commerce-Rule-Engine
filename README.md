# Smart E-commerce Product Catalog

### Project Overview
This project is a sophisticated e-commerce product catalog application built using Spring Boot. It features a dynamic, rule-based engine that automatically manages product statuses and integrates with a third-party API to handle currency conversions. The application is designed to show how a simple product list can be transformed into an intelligent, automated system.

### Key Features
* **Dynamic Rule Engine**: The core of this application is a rule engine that processes products based on rules stored in the database. This allows business logic to be updated without changing the application's code.
    * **Stock-Based Rules**: Products are automatically assigned a status like `LOW_STOCK` or `HIGH_STOCK` based on their stock levels.
    * **API-Driven Rules**: The application connects to a real-time currency exchange API to convert prices for products that meet specific criteria (e.g., converting high-priced products from INR to USD).

* **User Interface**: A clean, responsive web interface is built with Thymeleaf and Bootstrap, providing pages for:
    * **Product Management**: A list of products with their statuses and currencies. You can also add new products via a simple form.
    * **Dynamic Rules**: A page to view all the active business rules that govern the system.
    * **Execution Dashboard**: A dashboard that provides a summary of product statuses and a log of the rule engine's actions.

### Screenshots
| Product List |
| :---: |
| <img width="975" height="437" alt="image" src="https://github.com/user-attachments/assets/60e79a81-9a0d-4c79-a97b-690a13118f45" /> | 

| Dashboard |
| :---: |
| <img width="975" height="376" alt="image" src="https://github.com/user-attachments/assets/e08d77cb-6a1e-4669-9a3e-e1ea8667eef1" /> |
| <img width="975" height="494" alt="image" src="https://github.com/user-attachments/assets/364d90a3-5685-42b1-b27c-315d2a4e76bb" /> |

| Rules |
| :---: |
| <img width="975" height="385" alt="image" src="https://github.com/user-attachments/assets/dfd417c7-97be-4ece-8457-2df095796ca1" /> |


### How to Run the Project
1.  **Clone the repository:**
    
    git clone https://github.com/swaralijoglekar-10/Smart-E-Commerce-Rule-Engine.git
   
2.  **Configure API Key**: Get a free API key from [ExchangeRate-API](https://www.exchangerate-api.com/).
3.  **Update `application.properties.example`**: Copy the provided example file to `src/main/resources/application.properties` and add your currency API key.
    ```properties
    # ... other properties
    api.currency.key= MY_API_KEY
    ```
4.  **Run the application**: Use your IDE (e.g., IntelliJ IDEA) or Maven to run the `SmartEcommerceApplication.java` file.
    ```bash
    mvn spring-boot:run
    ```
    The application will be available at `http://localhost:8080`.

### Technologies Used
* **Backend**: Java, Spring Boot, Spring Data JPA
* **Database**: MySQL
* **Frontend**: Thymeleaf, Bootstrap
* **Tools**: Lombok, Maven, External Currency API (ExchangeRate-API)
