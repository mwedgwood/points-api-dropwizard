# How To Run The Server

## To Run Server From Command Line:
```bash
$ mvn package
$ java -jar target/points-api-1.0-SNAPSHOT.jar server src/main/resources/api.yml
```

## To Access metrics
```bash
http://localhost:8001
```

## To Test

### Get list of all users
```bash
$ curl http://localhost:8000/users
```
### Get user with id
```bash
$ curl http://localhost:8000/users/1
```
### Create user
```bash
$ curl -H 'CONTENT-TYPE: application/json' -d '{"name": "matt.wedgwood"}' http://localhost:8000/users
```
### Create membership and assign to user
```bash
$ curl -X POST localhost:8000/users/owen.tran/memberships -d '{"program":"aadvantage","memberId":"12345678"}' -H 'CONTENT-TYPE: application/json' -i
```
