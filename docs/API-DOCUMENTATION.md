# Casino-Mansion API-Dokumentation

Diese Dokumentation beschreibt den aktuellen Projektstand. Sie unterscheidet klar zwischen
implementierten Funktionen und geplanten, noch nicht verfügbaren REST-Endpunkten.

## Überblick

| Service | Basis-URL lokal | Zuständigkeitsbereich |
|---|---|---|
| Banking | `http://localhost:8080/casino/bank/api` | Nutzer und Geldtransaktionen |
| Slots | `http://localhost:8081/casino/slots/api` | Slot-Spiel und Spielauswertung |

Die Ports und Basis-Pfade sind in `docker-compose.yml` beziehungsweise in den jeweiligen
`application.yml`-Konfigurationen festgelegt.

## Aktueller Implementierungsstand

- Beide Spring-Boot-Anwendungen sind konfiguriert und starten einen Webserver.
- Die PostgreSQL-Datenbanken werden getrennt provisioniert.
- Noch sind keine REST-Controller implementiert.
- `springdoc-openapi` ist in beiden Services eingebunden.
- Swagger UI ist unter `/swagger-ui.html` erreichbar.
- Die OpenAPI-Basis wird typischerweise unter `/v3/api-docs` bereitgestellt.
- Der Banking-Service enthält das JPA-Modell `User` und Paketstrukturen für Nutzer-
  und Transaktionsfunktionen.
- Der Slots-Service enthält die geplanten Paketstrukturen für Controller, Services,
  Modelle, Repositories, Views und einen Banking-Client.

## Gemeinsame Konventionen

### Datenformat

- Request- und Response-Bodies verwenden JSON.
- Zeichen werden UTF-8-kodiert übertragen.
- Datumswerte sollen nach ISO 8601 dargestellt werden.
- Inhalte werden mit `Content-Type: application/json` gesendet und empfangen.

### HTTP-Statuscodes

| Status | Bedeutung |
|---|---|
| `200 OK` | Operation erfolgreich ausgeführt |
| `201 Created` | Neue Ressource erstellt |
| `204 No Content` | Operation erfolgreich, ohne Response-Body |
| `400 Bad Request` | Request ungültig oder Pflichtdaten fehlen |
| `404 Not Found` | Angeforderte Ressource existiert nicht |
| `409 Conflict` | Operation kollidiert mit dem bestehenden Zustand |
| `422 Unprocessable Entity` | Daten sind fachlich nicht verarbeitbar |
| `500 Internal Server Error` | Unerwarteter interner Fehler |

### Fehlerformat

Ein projektweiter Fehlervertrag ist noch nicht implementiert. Für neue Controller wird
RFC 7807 `application/problem+json` empfohlen:

```json
{
  "type": "https://example.org/problems/validation-error",
  "title": "Bad Request",
  "status": 400,
  "detail": "Beschreibung des konkreten Fehlers",
  "instance": "/casino/bank/api/..."
}
```

### Authentifizierung

Derzeit ist keine Authentifizierung oder Autorisierung implementiert. Die API darf daher
nicht als geschützte öffentliche Schnittstelle betrachtet werden.

## Bekanntes Datenmodell

### Banking: `User`

Das JPA-Modell `de.htwberlin.casino.banking.model.user.User` enthält folgende Felder:

| Feld | Typ | Regeln |
|---|---|---|
| `id` | Ganzzahl | Von der Datenbank generiert |
| `firstName` | String | Nicht `null`, nicht leer und maximal 50 Zeichen |
| `lastName` | String | Nicht `null`, nicht leer und maximal 50 Zeichen |

Beispiel einer möglichen JSON-Darstellung:

```json
{
  "id": 1,
  "firstName": "Max",
  "lastName": "Mustermann"
}
```

Diese Darstellung entspricht noch keinem implementierten REST-Endpunkt.

## Geplante API-Bereiche

- **Banking / Nutzer:** Nutzer erstellen, lesen, aktualisieren und löschen.
- **Banking / Transaktionen:** Kontobewegungen und Transaktionshistorie.
- **Slots:** Spiele starten und Ergebnisse verarbeiten oder abrufen.

Vor der Implementierung müssen Pfade, HTTP-Methoden, Request-Felder und fachliche Regeln
gemeinsam festgelegt werden. Danach sind die OpenAPI-Dateien um konkrete Pfade und
`operationId`-Werte zu ergänzen.

## OpenAPI-Dateien

- [Banking-Service OpenAPI](openapi/banking-service.yml)
- [Slots-Service OpenAPI](openapi/slots-service.yml)

## Lokales Starten

```bash
docker compose up --build
```

Stoppen:

```bash
docker compose down
```

Mit `docker compose down -v` werden auch die Datenbankvolumes gelöscht.
