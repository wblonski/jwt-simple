# Getting Started

Przykład prostej implementacji JWT security w Spring Boot, Postgres container + Hibernate, Maven . 
Lobok jest używany marginalnie ponieważ wywołuje konflikty z Hibernate.

Po uruchomieniu aplikacja nasłuchuje na porcie 8080. Można uruchomić testy integracyjne z pliku testów. 
Można też testować dowolnym clientem HTTP. 


##  Rejestracja nowego użytkownika
#### Request:

POST http://localhost:8080/api/v1/auth/register
Content-Type: application/json

{
"firstname": "jan",
"lastname": "kowalski",
"email": "jan.kowalski@domain.com",
"password": "password",
"role": "ADMIN",
"mfaEnabled": false
}

#### Response:
[empty body]

 Error respone:

{
  "timestamp": "2025-09-08T05:31:40.777+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/api/v1/admin"
}

## Sprawdzenie, czy działa autentykacja użytkownika
#### Request:
POST http://localhost:8080/api/v1/auth/authenticate
Content-Type: application/json

{
"email": "jan.kowalski@domain.com",
"password": "password"
}
#### Response:
{
"accessToken": "eyJhbGc...",
"refreshToken": "eyJhbGc...",
"mfaEnabled": false
}

## Odświeżenie tokena użytkownika

#### Request:
POST http://localhost:8080/api/v1/auth/refresh-token
Content-Type: application/x-www-form-urlencoded

#### Response:
[empty body]


## Weryfikacja użytkownika tokenem
#### Request:
POST http://localhost:8080/api/v1/auth/verify
Content-Type: application/json

{
"email": "jan.kowalski@domain.com",
"code": "eyJhbGciOi..."
}

#### Response:
{
"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW4ua293YWxza2lAZG9tYWluLmNvbSIsImlhdCI6MTc1NzMwOTQ1MCwiZXhwIjoxNzU3Mzk1ODUwfQ.u4Vz5ejP2bRJtXXTVAcyRdsYxQ28xCrhOEjGj7Z9tNs",
"mfaEnabled": true
}
