
This project contains API that calculates the net payable amount for a given bill according to the customers.

Technologies that I have use to build the application:
1. Spring Boot
2.ORM:JPA,Hibernate
3. Spring Security: JDBC Role based authentication and authorization.
   I have created 4 user with different roles like Employer, Affiliate, Customer.
4. Logging Framework:sl4j
5. Build Automation Tool: Maven
6. Encryption: Bcrypt Password Encoder
7. Datatabase: MySQL

Created the MVC Application with :
1. Controller Layer
2. Service Layer
3. Data Access Layer

Model Classes are:
1. User
2. Role
3. Billing
4. Product

Defined required attributes in each model class.
 The API provides services to calculate billing amount according to the user.
Data flow:
1. When a user login to the system, extract the information about the user and redirect to the users profile page after extracting the user role information.
2. From Controller layer, it will redirect to the service layer. Created the 3 different service logic to calculate the billing amount . According to the logged in user's role, it will redirect to particular service layer and update the database.

About Implementation
This application has been using SpringBoot as it provides a wide variety of features that aid development and maintainence. Some features that were utilised were: Spring Security, Spring Data/JPA and starters.

This program and instructions have been tested on following versions on Windows laptop.

Apache Maven 4.0.0
Java version: 1.8.0_131

How to run?
Import the project into Eclipse IDE. Run the application as Spring boot app and open the localhost running on the port no. specified in the project(default localhost:8080)

Logic:
If(loggedinuser==employer)
then redirect to employers service layer and calculate the amount based on 30% discount for all products except grocery and update the database.
if(loggeedinuser==affiliate)
then redirect to customers service layer and calculate the amount based on 10% discount for all products except grocery and update the database.
if(loggedinuser==customer)
then redirect to Customers service layer and then check the condition whether the customer is using services more than 2 year based on the registereddate, if it is true then calculate the amount based on 5% discount for all products except grocery and update the database.
else calculate the products rate except grocery and divide it by 100 and multiply the integer part with 5. Then we will get the discount amount and after calculating the total amount update it to the database.(Check the service layer of Customer).


