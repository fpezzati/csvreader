# CsvReader
Readers define format of .csv file: you have a row reader, composed by at least one reader. Row reader split .csv row, each reader read its string snippet. 

Flow is organized in tasks. Each task allows an input and an output. Task order is crucial, task's output may become input for the next one.

## Esercizio 1
Left uncompleted, see the todo below.

## Esercizio 2
rest apis trivial schema:

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
Fix `UtentiReaderIT`, a couple of tests broke because of object equality.

Write the 'writer' component that will pick `UtentiReader` output and put into a .csv file.

Generate an API description using openapi3 instead using tables in md.