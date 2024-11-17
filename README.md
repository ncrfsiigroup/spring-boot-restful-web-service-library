<h1>Spring Boot RESTful Web Service for a Library System</h1>
<h2>1. Tools</h2>
For this project the following tools were used:<br><br>
<ul>
  <li>Java JDK version 23.0.1</li>
  <li>Spring Tool Suite 4 (IDE)</li>
  <li>EclEmma Java Code Coverage Plugin for IDE</li>
  <li>SonarQube Plugin for IDE</li>
  <li>Postman tool (used for testing web service)</li>
</ul>
<h2>2. Project Structure</h2>
The structure of the project generated with Spring Boot using Maven is shown in the following image:<br><br>
<img src="https://github.com/user-attachments/assets/a803393e-d393-4bf1-8baa-94b4d29a3f17" width="50%"><br><br>
In the "cl.siigroup.libraryapi" package of "main/java" is the class that launches the application.<br><br>
In the "cl.siigroup.libraryapi.controller" package of "main/java" are the controllers RESTful Web Service running CRUD operations on the AUTHOR, CATEGORY and BOOK tables.<br><br>
In the "cl.siigroup.libraryapi.model" package of "main/java" are the models that define the data that will be used from the fields of the AUTHOR, CATEGORY and BOOK tables.<br><br>
In the "cl.siigroup.libraryapi.repository" package of "main/java" are the interfaces to manage the data of the AUTHOR, CATEGORY and BOOK tables in the database.<br><br>
In the "cl.siigroup.libraryapi.service" package of "main/java" are the services that connect to the repository that manages the data from the AUTHOR, CATEGORY and BOOK tables in the database for the business logic that defines the operations to be used.<br><br>
In the "cl.siigroup.libraryapi.util" package of "main/java" is the class of utilities that execute functionalities such as formatted current date and HTTP request response.<br><br>
In "main/resources" are the application properties that define the server port, configure access to the database and enable the console to use the embedded H2 DB, and there is also the SQL script to create the three tables of the application.<br><br>
In "test/java" are the JUnit tests for the unit tests of the classes created in the application.<br><br>
The diagram that shows the logical representation of the Web application is:<br><br>
<img src="https://github.com/user-attachments/assets/a8ca0fe3-bc94-4a72-afb0-6f44cc4be4de" width="50%">
<h2>3. Spring Boot Dependencies</h2>
The Maven dependencies generated from Spring Boot for the operation of the Java Web application, and are configured in the POM file:<br><br>
<b>Spring Web</b>: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container. Test applications with libraries including JUnit Jupiter, Hamcrest and Mockito.<br><br>

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

<b>Spring Boot DevTools</b>: Provides fast application restarts, LiveReload, and configurations for enhanced development experience.<br>

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
	<optional>true</optional>
</dependency>
```

<b>Spring Data JPA</b>: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.<br>

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

<b>H2 Database</b>: Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.<br>

```
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

<h2>4. Database Structure</h2>
The entity-relationship diagram of the three database tables that represent the library system is as follows:<br><br>
<img src="https://github.com/user-attachments/assets/907bc492-eb5e-4774-8236-459f00ec843b" width="50%"><br><br>
In the case of this particular Web application, the following are assumed:<br>
<ol>
	<li>Each book has only one author.</li>
	<li>Each book has only one category.</li>
</ol>
<h2>5. H2 Database</h2>
Before using the H2 embedded database implementation, you need to prepare the 3 tables by running the sql script found in "main/resources".<br><br>
The steps to follow are:
<ol>
	<li>Run the application with the Spring Tool Suite 4 IDE embedded server.</li>
	<li>Enter the H2 Database console with the suffix "/h2" in the URL.</li>
	<li>Run the sql script.</li>
</ol>
The following images describe those steps:<br><br>
<img src="https://github.com/user-attachments/assets/bd55b669-7e95-48d9-9701-8a460ba29a17" width="50%"><br><br>
<img src="https://github.com/user-attachments/assets/773787a3-048d-438c-a253-5e080701863f" width="50%"><br><br>
<img src="https://github.com/user-attachments/assets/520c0811-70df-4af2-9bd3-628a7e623749" width="50%">
<h2>6. Java Web Application</h2>
After running the application with the Spring Tool Suite 4 IDE embedded server, CRUD operations can be executed using RESTful endpoints.<br>
The Postman tool is used to consume the endpoints:<br><br>
<b>Read authors (GET method)</b>:<br>
http://localhost:8080/api/v1/authors<br><br>
<img src="https://github.com/user-attachments/assets/d87a6afc-01db-43db-a3d4-4fe716adaf18" width="70%"><br><br>
<b>Read categories (GET method)</b>:<br>
http://localhost:8080/api/v1/categories<br><br>
<img src="https://github.com/user-attachments/assets/7ca0e98e-e0f9-4db0-a755-5299383e1624" width="70%"><br><br>
<b>Read books (GET method)</b>:<br>
http://localhost:8080/api/v1/books<br><br>
<img src="https://github.com/user-attachments/assets/e064af88-2c07-4aec-a4e5-62b7cabe0299" width="70%"><br><br>
<b>Create authors (POST method)</b>:<br>
http://localhost:8080/api/v1/authors<br>
Use the following JSON structure:<br>

```
{
    "nameAuthor":"Julio Verne"
}
```

<img src="https://github.com/user-attachments/assets/191d4723-aa8f-4815-acc3-e33b5315f3af" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/a3877fd4-fb64-4da9-9b62-6faf9bd29f38" width="70%"><br><br>
<b>Create categories (POST method)</b>:<br>
http://localhost:8080/api/v1/categories<br>
Use the following JSON structure:<br>

```
{
	"nameCategory":"Aventuras"
}
```

<img src="https://github.com/user-attachments/assets/fdcc6bdf-3464-41c7-9cd3-fce50c51b23f" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/d3fe229c-df83-4984-a177-d276d9cedec2" width="70%"><br><br>
<b>Create books (POST method)</b>:<br>
http://localhost:8080/api/v1/books<br>
Use the following JSON structure:<br>

```
{
	"title":"Viaje al centro de la Tierra",
	"publisher":"Lexus Ediciones",
	"authorId": {
		"id":1
	},
	"categoryId": {
		"id":1
	}
}
```

<img src="https://github.com/user-attachments/assets/9fe5c6c3-5583-42f8-b9e9-a7f2dbf1c081" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/efbff786-ee90-41fa-8b8d-18c696536a5c" width="70%"><br><br>
<b>Update authors (PUT method)</b>:<br>
http://localhost:8080/api/v1/authors/1<br>
Use the following JSON structure:<br>

```
{
    "nameAuthor":"Donna Tartt"
}
```

<img src="https://github.com/user-attachments/assets/5ab6fb3c-2c32-4f01-aaa8-5e5e3d0ab96a" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/a11b7db0-facc-4ef5-98d3-8411ec3a9402" width="70%"><br><br>
<b>Update categories (PUT method)</b>:<br>
http://localhost:8080/api/v1/categories/1<br>
Use the following JSON structure:<br>

```
{
	"nameCategory":"Misterios"
}
```

<img src="https://github.com/user-attachments/assets/0903a702-1cf6-4200-a7b1-cfb36b943179" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/93cc4eef-90db-4613-b10d-0d6dceb03b82" width="70%"><br><br>
<b>Update books (PUT method)</b>:<br>
http://localhost:8080/api/v1/books/1<br>
Use the following JSON structure:<br>

```
{
	"title":"El secreto",
	"publisher":"Lumen"
}
```

<img src="https://github.com/user-attachments/assets/25cce9e2-9498-460f-ba45-84363c8f1bbc" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/79686dda-c93b-4e2a-9aab-fcff59b0dbe8" width="70%"><br><br>
<b>Delete authors (DELETE method)</b>:<br>
http://localhost:8080/api/v1/authors/1<br><br>
<img src="https://github.com/user-attachments/assets/c157d283-670c-4fb7-9138-fc77988e6227" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/32d236f6-6f0b-47b4-b361-34c33a2f6042" width="70%"><br><br>
<b>Delete categories (DELETE method)</b>:<br>
http://localhost:8080/api/v1/categories/1<br><br>
<img src="https://github.com/user-attachments/assets/72c2533e-f7f8-4a5e-a40e-8654eea5bebd" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/84d7a1ef-777a-4485-8759-ad52d966b5d4" width="70%"><br><br>
<b>Delete books (DELETE method)</b>:<br>
http://localhost:8080/api/v1/books/1<br><br>
<img src="https://github.com/user-attachments/assets/b25b0933-0771-47ef-88fc-4a5ae81dc341" width="70%"><br><br>
<img src="https://github.com/user-attachments/assets/9a9c2821-116e-4ecd-8724-4970f53ffb74" width="70%"><br><br>
<h2>7. Code Coverage and Code Quality</h2>
Code coverage was executed in the Web application using the clEmma Java Code Coverage plugin. The result is the following:<br><br>
<img src="https://github.com/user-attachments/assets/3113eb52-17f0-4c34-8015-b503f26ca004"><br><br>
The quality code was executed in the Web application using the SonarQube plugin. The result is the following:<br><br>
<img src="https://github.com/user-attachments/assets/b38b7b9a-9624-434d-a86d-d55a11a909ae">
