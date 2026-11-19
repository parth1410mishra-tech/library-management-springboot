# Library Management REST API (Spring Boot)

Java 17+ and Maven. Data is stored in memory (resets on restart).

## Run
- IntelliJ: open `LibraryManagementApplication.java` and click the green Run button, or
- Terminal: `mvn spring-boot:run`

Server starts at http://localhost:8080

## Endpoints

| Part | Method | URL                | Body        | Success | Failure |
|------|--------|--------------------|-------------|---------|---------|
| b    | POST   | /api/books         | Book JSON   | 201     | -       |
| c    | GET    | /api/books         | -           | 200     | -       |
| c    | GET    | /api/books/{id}    | -           | 200     | 404     |
| d    | PUT    | /api/books/{id}    | Book JSON   | 200     | 404     |
| e    | DELETE | /api/books/{id}    | -           | 200     | 404     |

## Sample JSON (POST / PUT)
```json
{
  "title": "Design Patterns",
  "author": "Erich Gamma",
  "category": "Programming",
  "availableCopies": 4
}
```

## curl examples
```
curl http://localhost:8080/api/books
curl http://localhost:8080/api/books/1
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d "{\"title\":\"Design Patterns\",\"author\":\"Erich Gamma\",\"category\":\"Programming\",\"availableCopies\":4}"
curl -X PUT http://localhost:8080/api/books/1 -H "Content-Type: application/json" -d "{\"title\":\"Clean Code 2\",\"author\":\"Robert C. Martin\",\"category\":\"Programming\",\"availableCopies\":9}"
curl -X DELETE http://localhost:8080/api/books/1
curl -X DELETE http://localhost:8080/api/books/999
```
