## Steps to run:
Step 1: Pull the project and update the pom.xml file.
Step 2: Configure the MySQL database in the application.properties file.
Step 3: Start the Spring Boot application.
Step 4: Access the application using the specified port, for example:
http://localhost:9091/

## Architecture description:
The following layered architecture is used in the Spring Boot application:
Controller -> Service -> Repository

The design patterns listed below are used to calculate the product price.

## Design patterns used and justification:
Factory Design Pattern:
Used to obtain the appropriate processor object.

Strategy Design Pattern:
Used to compute the final price in PricingRuleProcessor based on the processor passed at runtime.

Chain of Responsibility Pattern:
Used to iterate through pricing rules based on priority and apply each rule to the original price.
   
## Assumptions made:
Percentage Discount:
The calculated percentage amount is subtracted from the original price.

Flat Discount:
A fixed amount is subtracted from the original price.

Bulk Pricing:
If the given quantity meets the defined condition, the specified percentage is subtracted from the original price.

Category Promotion:
If the product category matches the defined condition, the specified percentage is subtracted from the original price.
