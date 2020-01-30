<h1>"Internet-Shop Project"</h1>
<br><hr>
<h2>Table of contents</h2>
<ul>
<li><a href="#Desription"><b>Description</b></a></li>
<li><a href="#Structure"><b>Project structure</b></a></li>
<li><a href="#Start"><b>Start</b></a></li>
<li><a href="#forUser"><b>For user</b></a></li>
<li><a href="#forAdmin"><b>For administrator</b></a></li>
<li><a href="#forDeveloper"><b>For developer</b></a></li>
</ul>
<hr>

<h2><a href=#Description></a>Description</h2>
It's simple realization of the web-application - internet shop.
It has simple UI which done by CSS and JSP pages(HTML, JSPTL).
This project has authentic and authorization filter.
It has Dao layer with tro implementation: Lists and JDBC.
Service layer with logic between Dao and Servlets.
View layer with pages.
<hr>


<h2><a href="#Structure"></a>Project Structure</h2>
<ul>
<li>Java 11</li>
<li>Maven 4.0.0</li>
<li>Tomcat 8.5.50</li>
<li>Servlet 3.1</li>
<li>MySQL 8.0.17</li>
<li>Logger log4j 1.2.17</li>
<li>JSTL 1.2</li>
</ul>
<hr>
<br>

<h2><a href=#Start></a>Start</h2>
<h5>1. Click "Inject" button. Or you can register two users and to do the next steps.
<h5>2. Change admin role_id in table user_roles from 1 to 2.
<h5>3. And after you can login as user with login - user and password - user.
<h5>4. And as admin with login - admin and password - admin.
<hr>
<br>

<h2><a href="#forUser"></a>For user</h2>
<h5>User can watch item list</h5>
<h5>Can add and delete items into his bucket</h5>
<h5>Can create order from bucket with items</h5>
<h5>Can delete his orders</h5>
<hr>
<br>

<h2><a href="#forAdmin"></a>For administrator</h2>
<h5>Can add/delete(manage) items into item list</h5>
<h5>Can watch and manage users list</h5>
<h5>Can watch and manage orders list</h5>
<hr>
<br>

<h2><a href="#forDeveloper"></a>For developer</h2>
<h5>1. Open this project in IDE as Maven project</h5>
<h5>2. Install and configure Tomcat</h5>
<h5>3. Install and configure MySQL</h5>
<h5>4. Create "internetshop</h5>
<h5>4. Run script init_db.sql in MySQL</h5>
<h5>5. Configure log4j.properties if you want to check log</h5>
<h5>6. Run project</h5>
<hr>
<h5>It can be running with SQL or with Lists implementation</h5>
<h5>If you want change impl to list</h5>
<h5>Change "BucketDao", "UserDao", "ItemDao", "OrderDao" in Factory from "JDBC impl" to "simple impl"</h5>
<br>

<h2>Author</h2>
<h5><a href="https://github.com/DDemoNZ">Dmitrii Zinchuk</a></h5>