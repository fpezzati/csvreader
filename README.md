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

## TODO
File Readers are ok. What is missing now is a couple of Collectors: one to build the code map and one to build statistic doc.

Once both Collectors are finished, I have to develop a writer to produce the required .csv.

Oh, this is a reminder: look at https://editor.swagger.io/ to generate an API description instead using tables in md.