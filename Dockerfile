FROM zenika/alpine-kotlin

RUN mkdir /app
WORKDIR /app

ADD . /app

EXPOSE 8080

CMD ./gradlew bootrun
