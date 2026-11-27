# Casino Mansion

Beleg im Modul **B42 Softwareengineering und Softwarearchitekturen** (HTW Berlin, WiSe 26/27).

## Projektbeschreibung

Simuliertes Online-Casino als RESTful Microservice-Architektur. Nutzer können Konten anlegen und mit
virtuellem Spielgeld an einem Slot-Spiel teilnehmen. Ziel ist es nicht, Glücksspiel zu verherrlichen,
sondern zu zeigen, dass selbst ein spielerfreundliches Spiel mit geringem Hausvorteil langfristig zu
Verlusten auf Spielerseite führt.

| Service           | Architektur                 | Port | Basis-Pfad               |
|-------------------|-----------------------------|------|--------------------------|
| `banking-service` | Vertical Slice Architecture | 8080 | `/casino/bank/api`       |
| `slots-service`   | Layered Architecture        | 8081 | `/casino/slots/api`      |

Jeder Service hat eine eigene PostgreSQL-Datenbank in einem eigenen Container.

**Tech-Stack:** Java 21, Spring Boot, Maven, PostgreSQL, Docker, Swagger (springdoc), JUnit 5, Mockito

## Installation

Voraussetzung: nur **Docker** (inkl. Docker Compose). Java und Maven werden nicht lokal benötigt,
der Build läuft im Container.

```bash
git clone https://github.com/ybmrz/Casino-Mansion.git
cd Casino-Mansion
docker compose up --build
```

## Nutzung

- Banking Swagger UI: http://localhost:8080/swagger-ui.html
- Slots Swagger UI: http://localhost:8081/swagger-ui.html
- API-Dokumentation: [docs/API-DOCUMENTATION.md](docs/API-DOCUMENTATION.md)
- Banking OpenAPI: [docs/openapi/banking-service.yml](docs/openapi/banking-service.yml)
- Slots OpenAPI: [docs/openapi/slots-service.yml](docs/openapi/slots-service.yml)

Stoppen: `docker compose down` (mit `-v` werden auch die Datenbanken gelöscht).

## Autoren

- Muhammad Reza Aulia Rahman
- 

## Lizenz
