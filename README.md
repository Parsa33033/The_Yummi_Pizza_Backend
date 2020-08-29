# The_Yummi_Pizza_Backend
The Spring Boot Backend for The Yummi Pizza Delivery Website.

## Tech Used
1) Spring boot framework using Java programming language
2) Jhipster framework
3) React framework (for admin panel) using Typescript programming language
4) Liquibase with H2database for testing
5) Mysql connection for database manipulation

## How to Deploy

## How to Work with
UML Class Diagram of the project:

![UML Class_Diagram](umlclass.jpg?raw=true "UML Class Diagram")

Steps:
1) In order to work with the app you need to specify a pizzaria (just one is enough) and 
make sure the the addressId attribute of the pizzaria model points to an address. Therefore you need to add 
your pizzaria address to the Address model, and of course point the addressId attribute of your pizzaria record in Pizzaria model
to the address you just created

2) Make sure to add at least one of evey FoodType to MenuItem model. So there has to be
4 records in the MenuItem model (make sure to use pictures or otherwise you would see empty spaces in the frontend for the pictures of the menu items)

3) In order to add a manager, a user has to register through the admin panel and admin herself/himself has to activate the user from the User management tab in the admin panel

note: whever address and port used to deploy this app (backend) that address would load the admin panel and working with it is straight forward (for instance, User management tab is in the administrator nav)
  
everything else would handle from the frontend but make sure to make the three steps.

## Pros
1) used multiple design patterns such as dependency injection, DTO patterns, seperation of concerns with service classes and ... for a reusable, maintainable and testable app.
2) Testable
3) easy to deploy
4) secure

## Cons
1) use of Jhipster framework lowers flexibility (but enough for a delivery website)
