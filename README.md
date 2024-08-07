# Instrukcja uruchomienia aplikacji

## Wymagania wstępne

1. **Java Development Kit (JDK) 17**
2. **Maven**
3. **Node.js i npm**
4. **Angular CLI**
5. **PostgreSQL**
6. **Intelij**

## Kroki do uruchomienia

### 1. Konfiguracja bazy danych PostgreSQL

1. Należy stworzyć lokalnie bazę danych opartą na PostgreSQL o nazwie `club_career`.
      ```sh
   create database club_career;
   ```
2. Wywołać na utworzonej bazie kod SQL, który jest zawarty w pliku w projekcie o nazwie `kodBazyDanych.sql`.

### 2. Konfiguracja aplikacji Spring Boot

1. W pliku `application.properties` ustawić odpowiednio:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/club_career
   spring.datasource.username=twoj_uzytkownik
   spring.datasource.password=twoje_haslo
   ```
   gdzie użytkownik ma wszystkie potrzebne uprawnienia 

### 3. Uruchomienie aplikacji Spring Boot

1. Za pomocą  IntelliJ uruchomić aplikację:

### 4. Uruchomienie aplikacji Angular

1. Przejść do folderu `frontend` w konsoli i wywołać następujące komendy:
   ```sh
   npm install
   npm start
   ```

## Adresy serwerów

- **Backend** będzie dostępny pod adresem: `http://localhost:8080`
- **Frontend** będzie dostępny pod adresem: `http://localhost:4200`
