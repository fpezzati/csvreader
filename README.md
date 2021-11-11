# CsvReader

## Esercizio 1
Svolto parzialmente nel progetto.

## Esercizio 2
Definizione delle api rest:
1- POST /regione/provincia/{codice_comune} mime: application/json {data_nascita, sesso, comune}
2- POST /regione/provincia mime: application/json {codice_comune}
3- GET  /regione/provincia/{codice_comune}
4- GET  /regione/

### Suggerimento per la paginazione
4- GET  /regione/?provincia=FI&offset=0&limit=10