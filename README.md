# How To Run The Server

## To Run Server From Command Line:
```bash
$ mvn package
$ java -jar target/points-api-1.0-SNAPSHOT.jar server src/main/resources/api.yml
```

## To Test
```bash
$ curl http://localhost:8000/users
```
