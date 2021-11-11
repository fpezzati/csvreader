# CsvReader

## Esercizio 1
Svolto parzialmente nel progetto.

## Esercizio 2
Definizione delle api rest:

| metodo | url | mime payload | esempio payload |
|:------:|:----|:-------------|:----------------|
| POST | /regione/provincia/{codice_comune} | application/json | {data_nascita, sesso, comune} |
| POST | /regione/provincia | application/json | {codice_comune} |
| GET | /regione/provincia/{codice_comune} | | |
| GET | /regione/ | | |

### Suggerimento per la paginazione

| metodo | url | mime payload | esempio payload |
|:------:|:----|:-------------|:----------------|
| GET | /regione/?provincia=FI&offset=0&limit=10 | | |