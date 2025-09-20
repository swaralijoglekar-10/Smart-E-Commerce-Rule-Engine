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
| Product list before running the rule engine|
| :---: |
| <img width="975" height="448" alt="image" src="https://github.com/user-attachments/assets/a82f377a-8455-45da-b31e-e3a609ccaa65" /> |

| Product list after running the rule engine|
| :---: |
| <img width="975" height="437" alt="image" src="https://github.com/user-attachments/assets/354769bb-d5c5-4d4e-905c-f3b0b0989f12" /> | 

| Dashboard |
| :---: |
| <img width="975" height="376" alt="image" src="https://github.com/user-attachments/assets/0dbbef8b-da86-48e5-9263-f21aaaf4fb92" /> |
| <img width="975" height="494" alt="image" src="https://github.com/user-attachments/assets/fa744ad4-3c3c-495c-9814-5c456bce0695" /> |

| Rules |
| :---: |
| <img width="975" height="385" alt="image" src="https://github.com/user-attachments/assets/706f52fa-f63c-49bb-bb83-8f70e93fe1ba" /> |


### How to Run the Project
1.  **Clone the repository:**
    
    git clone https://github.com/swaralijoglekar-10/Smart-E-Commerce-Rule-Engine.git
   
2.  **Configure API Key**: Get a free API key from [ExchangeRate-API](https://www.exchangerate-api.com/).
3.  **Update `application.properties.example`**: Update `src/main/resources/application.properties` to add your currency API key.
    ```properties
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
