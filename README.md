# PoliticianParty

Kurzes Spring-Boot-Quizprojekt: Es wird ein zufälliger Politiker angezeigt und der Nutzer errät die Partei.
Das Frontend basiert auf Thymeleaf, die Persistenz auf Spring Data JPA mit SQLite.


## Voraussetzungen

- JDK 25 installiert
- Maven (oder Maven Wrapper `mvnw` / `mvnw.cmd`)
- politicians.db Datei mit Daten vorhanden

## Projekt starten

```powershell
Set-Location "C:\Users\Henry\IdeaProjects\PoliticianParty"
.\mvnw.cmd spring-boot:run
```

Danach im Browser öffnen:

- `http://localhost:8080/`

## Datenbank (SQLite)

- Verwendete Datei: `politicians.db`
- Beispiel-Datei mit 4 Einträgen: `politicians.db.example4`
- Beispiel-Datei mit 39 Einträgen: `politicians.db.example39`
- JDBC-URL (siehe `src/main/resources/application.properties`):
  - `spring.datasource.url=jdbc:sqlite:politicians.db`

Initiales Schema mit Beispieldaten liegen unter:

- `src/main/resources/sql/schema.sql`
