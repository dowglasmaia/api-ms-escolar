FROM openjdk:11

EXPOSE 8083

ADD target/ms-auth-SNAPSHOT.jar ms-auth.jar

ENTRYPOINT [ "java","-jar","ms-auth.jar"]