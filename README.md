# Microservices - TauxChange

## Presentation
Gérer (créer , récupérer , modifier , supprimer) des taux de change entre deux devises. Un taux de change se caractérise 
par {"devise source" : "EUR", "devise destination" : "USD", "taux" : 1.22, "date" : "2021-05-25"} signifiant qu’en date 
du 25 mai 2021, 1 euro vaut 1.22 dollar US.
## Diagramme de classe

![Screenshot](DiagrammeDeClassTauxChange.png)

# Installation
## Set up the Spring Boot Application
```bash
./mvnw package && java -jar target/tauxchange
```
## Set up Docker
```bash
docker build -t springio/tauxchange .
docker run -p 8000:8000 -t springio/tauxchange
```
# Rest API
## Methods

| Methods   | Urls                                    | Actions                                     |
| :--------:|:----------------------------------------| :-------------------------------------------|
| POST      | /taux-change                            | create new Taux Change                      |
| GET       | /taux-change                            | retrieve all Taux Change                    |
| GET       | /taux-change/id/{id}                    | retrieve Taux Change by {id}                |
| GET       | /taux-change/source/{source}            | retrieve Taux Change by {source}            |
| GET       | /taux-change/dest/{dest}                | retrieve Taux Change by {dest}              |
| GET       | /taux-change/date/{date}                | retrieve Taux Change by {date}              |
| GET       | /taux-change/source/{source}/dest/{dest}| retrieve Taux Change by {source} and {dest} |
| PUT       | /taux-change/id/{id}                    | update all Taux Change by {id}              |
| PUT       | /taux-change/id/{id}/date/{date}        | update {date} of Taux Change by {id}        |
| PUT       | /taux-change/id/{id}/taux/{taux}        | update {taux} of Taux Change by {id}        |
| DELETE    | /taux-change                            | delete all Taux Change                      |
| DELETE    | /taux-change/id/{id}                    | delete Taux Change by {id}                  |

## POST
### create new Taux Change
```bash
curl -X POST -H "Content-type: application/json" -d "{\"source\" : \"AUD\", \"dest\" : \"USD\", \"taux\" : 0.7586, \"date\" : \"2021-06-25\"}" "http://localhost:8000/taux-change"
```
{"source":"AUD","dest":"USD","taux":0.7586,"date":"2021-06-25"}

## GET
### retrieve all Taux Change
```bash
curl -X GET "http://localhost:8000/taux-change"
```
[{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-22"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-23"}, ...]
### retrieve Taux Change by {id}
```bash
curl -X GET "http://localhost:8000/taux-change/id/10001"
```
{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-21"}
### retrieve Taux Change by {source}
```bash
curl -X GET "http://localhost:8000/taux-change/source/USD"
```
[{"source":"USD","dest":"GBP","taux":0.72,"date":"2021-06-21"},{"source":"USD","dest":"GBP","taux":0.72,"date":"2021-06-22"},{"source":"USD","dest":"GBP","taux":0.72,"date":"2021-06-23"},{"source":"USD","dest":"GBP","taux":0.72,"date":"2021-06-24"},{"source":"USD","dest":"GBP","taux":0.72,"date":"2021-06-25"}]
### retrieve Taux Change by {dest}
```bash
curl -X GET "http://localhost:8000/taux-change/dest/USD"
```
[{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-22"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-23"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-24"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-25"},{"source":"AUD","dest":"USD","taux":0.76,"date":"2021-06-25"}]
### retrieve Taux Change by {date}
```bash
curl -X GET "http://localhost:8000/taux-change/date/2021-06-25"
```
[{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-25"},{"source":"EUR","dest":"JPY","taux":132.18,"date":"2021-06-25"},{"source":"USD","dest":"GBP","taux":0.72,"date":"2021-06-25"},{"source":"AUD","dest":"USD","taux":0.76,"date":"2021-06-25"}]
### retrieve Taux Change by {source} and {dest}
```bash
curl -X GET "http://localhost:8000/taux-change/source/EUR/dest/USD"
```
[{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-22"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-23"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-24"},{"source":"EUR","dest":"USD","taux":1.19,"date":"2021-06-25"}]
## PUT
### update all Taux Change by {id}
```bash
curl -X PUT -H "Content-type: application/json" -d "{\"source\" : \"AUD\", \"dest\" : \"USD\", \"taux\" : 0.7582, \"date\" : \"2021-06-24\"}" "http://localhost:8000/taux-change/id/10016"
```
{"source":"AUD","dest":"USD","taux":0.7582,"date":"2021-06-24"}
### update {date} of Taux Change by {id}
```bash
curl -X PUT "http://localhost:8000/taux-change/id/10016/date/2020-06-25"
```
{"source":"AUD","dest":"USD","taux":0.76,"date":"2020-06-25"}
### update {taux} of Taux Change by {id}
```bash
curl -X PUT "http://localhost:8000/taux-change/id/10016/taux/0.7582"
```
{"source":"AUD","dest":"USD","taux":0.7582,"date":"2020-06-25"}
## DELETE
### delete all Taux Change
```bash
curl -X DELETE "http://localhost:8000/taux-change"
```
### delete Taux Change by {id}
```bash
curl -X DELETE "http://localhost:8000/taux-change/id/10016"
```