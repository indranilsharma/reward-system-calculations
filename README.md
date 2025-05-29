# Spring Boot reward-system-calculations

# Purpose
This application is designed to calculate customer reward points based on their spending behavior. It simulates a loyalty program where customers earn points for each transaction, encouraging higher spending by offering tiered rewards.

# Reward Rules
The reward system is structured to incentivize spending:
No points for the first $50 of a transaction.
1 point per dollar for the amount between $50 and $100.
2 points per dollar for the amount over $100.

## Tech Stack
Java 17
Build systems:Maven
H2 Inmemory databse
Spring Boot 3.5
Mockito 5.2

## How to Run 
No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can find jar inside target directory
```
 reward-system-0.0.1-SNAPSHOT.jar.original

        mvn spring-boot:run 

## About the Service
The service is just a reward systems calculations REST service. It uses an in-memory database (H2) to store the data. 

Here is what this little application demonstrates: 

* Full integration with the latest **Spring-Boot** Framework: 
* Packaging as a single jar with embedded container (tomcat 8): No need to install a container separately 
* Writing a RESTful service using annotation


# Here is the endpoint you can call:

```
http://localhost:8080/api/rewards

```

### Create a reward system resouces
POST (http://localhost:8080/api/rewards)
Accept: application/json
Content-Type: application/json

[
  {
    "customerId": "C001",
    "amount": 120,
    "date": "2025-03-15"
  },
  {
    "customerId": "C002",
    "amount": 100,
    "date": "2025-05-15"
  }
]


<img width="537" alt="image" src="https://github.com/user-attachments/assets/4bc7b4fa-a4e2-4829-a613-9eebd6b35a31" />

### To view your H2 in-memory datbase

The 'test' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8080/h2-console
Default username is 'sa' with a blank password.
JDBC URL: jdbc:h2:mem:rewardsdb
