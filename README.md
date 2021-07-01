# Microservices - TauxChange

## Presentation

## Diagramme de classe

![Screenshot](DiagrammeDeClassTauxChange.png)

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

# Exemple
## POST
### create new Taux Change
curl -X POST -H "Content-type: application/json" -d "{\"source\" : \"AUD\", \"dest\" : \"USD\", \"taux\" : 0.7586, \"date\" : \"2021-06-25\"}" "http://localhost:8000/taux-change"

## GET
### retrieve all Taux Change
curl -X GET "http://localhost:8000/taux-change"
### retrieve Taux Change by {id}
curl -X GET "http://localhost:8000/taux-change/id/10001"
### retrieve Taux Change by {source}
curl -X GET "http://localhost:8000/taux-change/source/EUR"
### retrieve Taux Change by {dest}
curl -X GET "http://localhost:8000/taux-change/dest/USD"
### retrieve Taux Change by {date}
curl -X GET "http://localhost:8000/taux-change/date/2021-05-25"
### retrieve Taux Change by {source} and {dest}
curl -X GET "http://localhost:8000/taux-change/source/EUR/dest/USD"

## PUT
### update all Taux Change by {id}
curl -X PUT -H "Content-type: application/json" -d "{\"source\" : \"AUD\", \"dest\" : \"USD\", \"taux\" : 0.7582, \"date\" : \"2021-06-24\"}" "http://localhost:8000/taux-change/id/10016"
### update {date} of Taux Change by {id}
curl -X PUT "http://localhost:8000/taux-change/id/10016/date/2020-06-25"
### update {taux} of Taux Change by {id}
curl -X PUT "http://localhost:8000/taux-change/id/10016/taux/0.7582"

## DELETE
### delete all Taux Change
curl -X DELETE "http://localhost:8000/taux-change"
### delete Taux Change by {id}
curl -X DELETE "http://localhost:8000/taux-change/id/10016"