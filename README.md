# The_Yummi_Pizza_Backend
The Spring Boot Backend for The Yummi Pizza Delivery Website.

Frontend link for the app: https://github.com/Parsa33033/The_Yummi_Pizza_Frontend

Admin panel link: http://104.248.254.44/
Website link: http://161.35.28.65/

## Tech Used
1) Spring boot framework using Java programming language
2) Jhipster framework
3) React framework (for admin panel) using Typescript programming language
4) Liquibase with H2database for testing
5) Mysql connection for database manipulation
6) Uses Docker for deployment

## How to Deploy


1) You can change the config from `./src/main/resources/config.application-prod` and `./src/main/resources/config.application-prod` `./src/main/resources/config.application-dev`
    1. For development you have to have java installed and run `TheYummiPizzaBackendApp.java` from `.\src\main\java\com\yummipizza` directory
       and make sure that cors config and sub configs in `jhipster.cors` in `./src/main/resources/config.application-prod` is commented out and `spring.jpa.hibernate-ddl-auto: none`
    2. For production you can chage mysql setting from `./src/main/resources/config.application-prod`
    3. Mailing config can be changed from both `./src/main/resources/config.application-prod` for production and `./src/main/resources/config.application-dev` for development
    4. In order to make account activation and password recovery email work properly you need to set the `jhipster.male.base-url` from `./src/main/resources/config.application-prod` to the frontend app url
        1. Frontend link to github is: https://github.com/Parsa33033/The_Yummi_Pizza_Frontend 
2) Run `mvnw -Pprod clean package -DskipTests` on the project folder
3) Copy `docker-compose.yml` and `Dockerfile` in the target folder
4) Send the target folder on the server
5) Install Docker and Docker compose on your server
6) Run `docker-compose up` command in the build folder


note: make sure to set `spring.jpa.hibernate-ddl-auto` to `update` when deploying for production


## How to Work with
UML Class Diagram of the project:

![UML Class_Diagram](umlclass.jpg?raw=true "UML Class Diagram")

Steps:
1) In order to work with the app you need to specify a Pizzaria record (just one is enough) in the Pizzaria Model and 
make sure that the addressId attribute of the Pizzaria model points to an address. Therefore you need to add 
your Pizzaria address to the Address model, and of course point the addressId attribute of your Pizzaria record in Pizzaria model
to the address you just created

2) Make sure to add at least one of evey FoodType to MenuItem model (you can set to empty. but what is the point?). So there has to be
4 records in the MenuItem model (make sure to use pictures or otherwise you would see empty spaces in the frontend for the pictures of the menu items)

3) In order to add a manager, a user has to register through the admin panel and admin herself/himself has to activate the user from the User management tab in the admin panel

note: whatever address and port used to deploy this app (backend) that address would load the admin panel and working with it is straight forward (for instance, User management tab is in the administrator nav)
  
everything else would be handled from the frontend (like user registration, orders, customer messages and ... . even if you login as a manager you can still add and remove menu items even though you can do it in the admin panel by admin as well) but make sure to make the three steps.

Admin credentials:
1) username: admin
2) password: admin


You can do couple of things in this app:

customer:
1) A customer can register and after registration a activation email would be sent to her/his email where she/he can click on the link given to activate the account
2) A logged in customer or not a registered customer can add to cart and proceed to checkout(you can switch between Dollor and Euro with a button in both main page and cart page)
3) Make an order which gives adds to order list in customer menu on the top right of the page (if the customer is logged in) and then
an email of seccessful order would be sent to the customer
4) Customer can check her/his orders in the order menu on the top right of the page (if customer is logged in)
5) Customer can logout
6) A customer can edit her/his profile so that next time she/he does not need to fill in the info needed in the checkout page. 

manager:
1) If you want to become a manager you have to register in the admin panel but you would not get an activation email because the admin has to activate you herself
2) If you can loggin as the manager your menu panel consists of pages of orders and menus
    1. In the order menu you can see all the orders and you can check the checkbox for each order to confirm that the food has been delivered. by doing so, customer can see that her/his order has been delivered in her/his order page
    2. In the menu section of the app menu you can add or deduct menu items which in turn shows in the main page of the website (so menus get added dynamically by manager or admin from admin panel)
3) A manger can edit her/his profile


## Pros
1) Used multiple design patterns such as dependency injection, DTO patterns, seperation of concerns with service classes and ... for a reusable, maintainable and testable app.
2) Testable
3) Easy to deploy
4) High security
5) Caching is used for latency improvement.

## Cons
1) Use of Jhipster framework lowers flexibility (but enough for a delivery website)
