Avanti Gadgets eCommerce Java Web Application
--------------

# Installation
+ Download zip or clone the project
+ Create MySQL database with schema name [DB_NAME]
+ Import data from self-contained file `avanti_store_db.sql`
+ Make sure Main class in application properties is `com.avanti.ecommerce.EcommerceApplication`
+ Update `application.properties` files for configuration. Replace [DB_NAME] with the database created in step-2, in `spring.datasource.url=jdbc:mysql://localhost:3306/[DB_NAME]?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false`
+ Update database username and password according to your MySQL configuration in `spring.datasource.username=root` and `spring.datasource.password=root`
+ At last, run the project.

# Users
+ Admin - bhagattamu@gmail.com
+ Normal User - bhagat@gmail.com
  
  Password for both of them is `testpassword`

# How to use application

+ Use admin user to manage category, manage product, and manage orders
+ Use normal user to order items.


