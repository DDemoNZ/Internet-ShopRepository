<h1>"Internet-Shop Project"</h1>
<br><hr>
# Table of contents
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
It has simple UI which done by CSS and JSP pages(HTML, JSPTL).
This project has authentic and authorization filter.
It has Dao layer with tro implementation: Lists and JDBC.
Service layer with logic between Dao and Servlets.
View layer with pages.
<hr>


# <a name="Strucure"></a>Project Structure
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
* 1. Click "Inject" button. Or you can register two users and to do the next steps.
* 2. Change admin role_id in table user_roles from 1 to 2.
* 3. And after you can login as user with login - user and password - user.
* 4. And as admin with login - admin and password - admin.
<hr>
<br>

# <a name="User"></a>For user
* User can watch item list</h5>
* Can add and delete items into his bucket</h5>
* Can create order from bucket with items</h5>
* Can delete his orders</h5>
<hr>
<br>

# <a name="Admin"></a>For administrator
* Can add/delete(manage) items into item list</h5>
* Can watch and manage users list</h5>
* Can watch and manage orders list</h5>
<hr>
<br>

# <a name="Developer"></a>For developer
* 1. Open this project in IDE as Maven project
* 2. Install and configure Tomcat
* 3. Install and configure MySQL
* 4. Run script init_db.sql in MySQL
* 5. Configure log4j.properties if you want to check log
* 6. Run project
<hr>
* It can be running with SQL or with Lists implementation
* If you want change impl to list
* Change "BucketDao", "UserDao", "ItemDao", "OrderDao" in Factory from "JDBC impl" to "simple impl"
<br>

<h2>Author</h2>
# [Dmitrii Zinchuk](https://github.com/DDemoNZ)