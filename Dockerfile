FROM gradle:8.1-jdk17-alpine

WORKDIR /app

COPY . .

RUN gradle clean build -x test

ENTRYPOINT ["java", "-jar", "build/libs/fastfood-tech-challenge-1-v1.0.0.jar"]

EXPOSE 8080/tcp
