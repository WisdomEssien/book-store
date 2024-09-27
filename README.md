# Online Book Store
A Demo Project for Online Book Store with some features as below:

- Book Inventory
- Search Books by various criteria
- Shopping Cart
- Checkout 
- Purchase History

## Tech/framework used

- Springboot 3.3.4
- Java 17
- maven 3.9.8
- Lombok 1.18.34
- H2 Database

## Step-By-Step guide to setup project on local system

- Clone project from the git repository using this link [Git Repo](https://github.com/WisdomEssien/book-store.git).

- Open command prompt and navigate to the desired directory. Copy, paste and execute the git command below on the command prompt.
  _You have to have git installed on your system._

```
	git clone -b main https://github.com/WisdomEssien/book-store.git
```

- Once the project is completely downloaded, launch and import project to your favourite IDE as a maven project.
- Run the application.
- Import the Postman Collection shared in the email. There are saved request/responses to test with.
  - You can start from ``ìnventory``
- Launch the database console using this link: [H2 Database Console](http://localhost:7788/h2-console/).
  Copy and paste link directly on the browser if you are experiencing any difficulty redirecting.
- Use the credentials below when prompted to login

```
   - User Name: sa
   - Password:
   - JDBC URL:	jdbc:h2:mem:mydb
   - Driver Class: org.h2.Driver
```

Hope this was helpful to get you started

[wisdom essien](https://github.com/WisdomEssien/book-store.git) © 2024 
