# Instrukcja uruchomienia aplikacji

## Wymagania wstępne

1. **Java Development Kit (JDK)**
2. **Maven**
3. **Node.js i npm**
4. **Angular CLI**
5. **PostgreSQL**

## Kroki do uruchomienia

### 1. Konfiguracja bazy danych PostgreSQL

1. Należy stworzyć bazę danych o nazwie `club_career`.
2. Kod DDL jest zawarty w pliku w projekcie o nazwie `kodBazyDanych.sql`.

### 2. Konfiguracja aplikacji Spring Boot

1. W pliku `application.properties` ustawić odpowiednio:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/club_career
   spring.datasource.username=twoj_uzytkownik
   spring.datasource.password=twoje_haslo
   ```

### 3. Uruchomienie aplikacji Spring Boot

1. Za pomocą Mavena lub IntelliJ uruchomić aplikację:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

### 4. Uruchomienie aplikacji Angular

1. Przejść do folderu `frontend` i uruchomić następujące komendy:
   ```sh
   npm install
   ng serve
   ```

## Adresy serwerów

- **Backend** będzie dostępny pod adresem: `http://localhost:8080`
- **Frontend** będzie dostępny pod adresem: `http://localhost:4200`
