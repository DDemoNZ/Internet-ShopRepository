<h1>"Internet-Shop Project"</h1>
<hr>

# Table of Contents
* [Description](#Description)
* [Project structure](#Structure)
* [Start](#Start)
* [For user](#User)
* [For administrator](#Admin)
* [For developer](#Developer)
* [Author](#Author)
<hr>

# <a name="Description"></a>Description
It's simple realization of the web-application - internet shop.
This shop has simple UI which done by CSS and JSP pages(HTML, JSPTL).
Project has: authentic and authorization filter.
Dao layer with two implementation: Lists and JDBC.
Service layer with logic between Dao and Servlets.
View layer with pages.
<hr>

# <a name="Structure"></a>Project Structure
* Java 11
* Maven 4.0.0
* Tomcat 8.5.50
* Servlet 3.1
* MySQL 8.0.17
* Logger log4j 1.2.17
* JSTL 1.2
<hr>
<br>

# <a name="Start"></a>Start
* Click "Inject" button.
* And after you can login as user with login - user and password - user.
* And as admin with login - admin and password - admin.
* Or you can register new user.
<hr>
<br>

# <a name="User"></a>For user
User can:
* watch item list</h5>
* add and delete items into his bucket</h5>
* create order from bucket with items</h5>
* delete his orders</h5>
<hr>
<br>

# <a name="Admin"></a>For administrator
Administrator can: 
* add/delete(manage) items into item list</h5>
* watch and manage users list</h5>
* watch and manage orders list</h5>
<hr>
<br>

# <a name="Developer"></a>For developer
* Open this project in IDE as Maven project
* Install and configure Tomcat:
    - add artifact;
    - add sdk 11;
    - add sdk 11 in project structure;
* Install and configure MySQL:
    - Run script from src/main/resources/init_db.sql in MySQL;
    - In mate/academy/internetshop/factory/FactoryForAllDao.java class use your login and password to create a connection;
* Configure log4j.properties if you want to check log:
    - change path in src/main/resources/log4j.properties to path where you want to create log file;
* Run project
*
* It can be running with SQL or with Lists implementation
* If you want change impl to list
* Change "BucketDao", "UserDao", "ItemDao", "OrderDao" in Factory from "JDBC impl" to "simple impl":
    - change from -> public static BucketDao getBucketDao() { return new BucketDaoJdbcImpl(connection); }
    - to -> public static BucketDao getBucketDao() { return bucketDao == null ? new BucketDaoImpl() : bucketDao; }
<br>

# Author
# [Dmitrii Zinchuk](https://github.com/DDemoNZ)