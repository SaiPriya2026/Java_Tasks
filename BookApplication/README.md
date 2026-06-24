###### **Book Application (Spring Boot)**



A RESTful API built with Spring Boot to manage books, including file and image uploads, soft delete functionality, and standardized API responses.



\# Features

\- \*Standard Response Wrapper\*\* using `ApiResponse<T>` (status, message, data).

\- \*RESTful Endpoints\*\* for CRUD operations.

\- \*DTO Pattern\*\* (`BookDTO`) for clean separation between entity and API layer.

\- \*File \& Image Uploads\*\* stored in project `/uploads` directory.

\- \*Soft Delete\*\* using `active` flag instead of hard delete.

\- \*Dual Endpoints for Files/Images\*\*:

&#x20; - JSON response with Base64 (`/image/{id}`, `/file/{id}`)

&#x20; - Direct rendering (`/image/{id}/view`, `/file/{id}/view`)



\---



\# Project Structure



BookApplication/

├── Controller/

│    └── BookController.java

├── Service/

│    └── BookService.java

├── Repository/

│    └── BookRepository.java

├── Entity/

│    └── Book.java

├── Model/

│    └── BookDTO.java

├── Response/

│    └── ApiResponse.java







\---



\#API Endpoints



\# Books

\- `POST /api/v1/books/` → Add new book (multipart form data)

\- `GET /api/v1/books/name/{bookName}` → Get book by name

\- `PUT /api/v1/books/{id}` → Update book

\- `DELETE /api/v1/books/{id}` → Soft delete book



\# Files \& Images

\- `GET /api/v1/books/image/{id}` → Get image (Base64 JSON)

\- `GET /api/v1/books/image/{id}/view` → View image directly

\- `GET /api/v1/books/file/{id}` → Get file (Base64 JSON)

\- `GET /api/v1/books/file/{id}/view` → Download/view file directly



\---



\# Tech Stack

\- \*Java 17+

\- \*Spring Boot

\- \*Spring Data JPA

\- \*Lombok

\- \*Maven











