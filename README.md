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
ComuniItalianiReader is quite ok. Some doubt about how other components benefit from what he read. 

UtentiReader has a complete read feature. What is missing now is how to get a codemap from ComunitItalianiReader and use to produce a statistic report.

Maybe an interface managing two parameters, <I>, <O>?

ComuniItalianiReader pick a <I> with a file name and put codemap in <O>.

UtentiReader pick a <I> with a codemap and a file name. Codemap was filed in by ComuniItalianiReader in the previous step.

There could be a third component that is in charge to pick statisticalmap UtentiReader provided and write it down in a file.

Oh, this is a reminder: look at https://editor.swagger.io/ to generate an API description instead using tables in md.