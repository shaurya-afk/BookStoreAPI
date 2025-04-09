# Backend Developer Assignment – RESTful API Task (Zynetic)

## Task Overview
RESTful API for managing a list of books.

## Tech Stack
- **Language**: Java
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **ORM**: Spring Data Jpa
- **Testing**: JUnit
- **Authentication**: JWT

## Endpoints
### Book Management
- **POST** `/books` - Create a new book.
- **GET** `/books` - Retrieve all books.
- **GET** `/books/id/{id}` - Retrieve a book by its ID.
- **PUT** `/books/id/{id}` - Update a book by its ID.
- **DELETE** `/books/id/{id}` - Delete a book by its ID.

### Filtering
- **GET** `/books/filter/author/{author}` - Retrieve books by author.
- **GET** `/books/filter/category/{category}` - Retrieve books by category.
- **GET** `/books/filter/rating/{rating}` - Retrieve books with a minimum rating.

### Search
- **GET** `/books/search/title/{title}` - Search books by title.

### Authentication
- **POST** `/auth/login` - Authenticate user and return JWT token.
- **POST** `/auth/register` - Register a new user.

## Setup
1. Clone the repository.
```bash
git clone https://github.com/shaurya-afk/BookStoreAPI.git
```
1. Navigate to the project directory.
2. Run the application using `mvn spring-boot:run`.
3. Ensure PostgreSQL is running and the database is configured in `application.properties`.
4. Use Postman or any API client to test the endpoints.
5. You can also test the endpoints using the provided under tests.

> In the static folder is the postman endpoints

## Dockerization
1. Build the Docker image.
```bash
    mvn clean package
```
2. Run the Docker container.
```bash
    docker build -t book-store-api .
    docker run -p 8080:8080 book-store-api
```