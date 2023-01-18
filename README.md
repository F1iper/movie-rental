# :movie_camera: Movie Rental
This project is a Java implementation of a movie rental system, using:
- MySQL as database
- H2 as database for tests
- Java 17 as the programming language 
- JDBC as the database connector 
- Lombok as a source code generator
- Log4j as a logging framework 
- JUnit and Mockito for testing.

### :wrench: Application is working in command line interface.

The system allows users to perform CRUD operations on movie rental data 
through the use of not-so RESTful API endpoints.

## :bangbang: Requirements
* JDK 17 or higher
* MySQL 8 or higher
* Maven

## :cyclone: Installation
1. Install the latest version of JDK and Maven on your computer if you haven't already.
2. Download or clone the project from the repository.
* Clone the repository:
    **git clone https://github.com/F1iper/movie-rental.git**
3. Open the command line and navigate to the project's root directory.
4. Use the following command to build the project:
    **mvn clean install**
5. Create MySQL database called movierental and update database configuration

- In the directory **/movie-rental/src/resources** rename the file **example.dbconfig.properties** to -> **dbconfig.properties** and edit it
  to match your local database username + password + url
- example: 
```java
# DB Properties
db.url=jdbc:mysql://localhost:3306/movierental
db.username=your_username
db.password=your_password
```

## :arrow_right: Run the project:
**mvn exec:java -Dexec.mainClass="org.movierental.Main"**

## :zap: Usage
You can use the not-so RESTful API endpoints provided by the project to perform CRUD operations on the movie rental data.
Currently CRUD for 
- Company
- Branch
- Staff
- Movie
are working,
rest of the application is in progress

## :bell: Testing
You can run the unit tests for the project using the following command:

**mvn test**

## :hammer: Tools and Libraries
- [Java 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL](https://www.mysql.com/)
- [H2](http://www.h2database.com/html/main.html)
- [JDBC](https://docs.oracle.com/en/database/oracle/oracle-database/19/jdbc/index.html)
- [Lombok](https://projectlombok.org/)
- [Log4j](https://logging.apache.org/log4j/2.x/)
- [JUnit](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

## :recycle: Contribution
Please feel free to submit pull requests and issues. Any contributions are welcome.

## :mailbox_with_mail: Contact Information
In case of any question or suggestion, please contact me at ftimofiejew@gmail.com
