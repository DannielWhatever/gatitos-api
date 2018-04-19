## gatitos API

Rest API para adoptar gatitos :octocat:

### Keywords

kotlin - springboot 2 - mongodb - docker - experimental

### Dependencies

- jdk >= 1.8
- mongodb

### How to run?

#### Local
- ./gradlew bootrun

#### Docker

- sudo docker build .
- sudo docker run -p8080:8080 -d 9b715f691fec

### Test service..

- curl http://localhost:8080/commons/cities
