# How To Run The Server

## To initialize the db:
Must run at least once to create tables. Subsequent runs will recreate.
```bash
$ mvn package
$ java -jar target/points-api-1.0-SNAPSHOT.jar reset-db src/main/resources/api.yml
```

## To Run Server From Command Line:
```bash
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
$ curl -X POST localhost:8000/users/matt.wedgwood/memberships -d '{"program":"aadvantage","memberId":"12345678"}' -H 'CONTENT-TYPE: application/json' -i
```
### List user's memberships
```bash
$ curl http://localhost:8000/usera/matt.wedgwood/memberships
```
### Test auth success
```bash
$ curl -H 'CONTENT-TYPE: application/json' -d '{"name": "points"}' http://localhost:8000/users
$ curl -v -u points:letmein localhost:8000/auth/tokens
```
### Test auth failure
```bash
$ curl -v -u admin:admin localhost:8000/auth/tokens
```
