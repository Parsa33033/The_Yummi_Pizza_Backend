FROM openjdk:8
WORKDIR /usr/src/yummipizza/
COPY ./target .
EXPOSE 80
CMD ["java", "-jar", "the-yummi-pizza-backend-0.0.1-SNAPSHOT.jar"]
