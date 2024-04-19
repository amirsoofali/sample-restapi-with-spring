This is a Spring Boot application that provides APIs.

Prerequisites
Before running this application, ensure that you have the following prerequisites installed on your system:

Java Development Kit (JDK) 17 or later
Apache Maven
Git

Getting Started
Follow these steps to run the Spring Boot application:

Clone the repository to your local machine:

Clone the repository to your local machine:

git clone https://github.com/amirsoofali/spring-test.git

Navigate to the project directory:

cd spring-test

Build the application using Maven:

mvn clean package
Run the application:

java -jar target/tasks-0.0.1-SNAPSHOT.jar
The application will start running on http://localhost:8080.
Accessing APIs via Swagger
You can test the application's APIs using Swagger UI. Follow these steps:

Open your web browser and go to the following URL:
http://localhost:8080/swagger-ui/index.html
Swagger UI will be displayed, showing a list of available APIs along with their descriptions and parameters.
Explore the available APIs, test them by providing input parameters, and execute requests directly from the Swagger UI.
