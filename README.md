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
