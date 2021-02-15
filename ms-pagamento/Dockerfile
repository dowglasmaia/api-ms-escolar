FROM openjdk:11

EXPOSE 8082

ADD target/ms-pagamento-SNAPSHOT.jar ms-pagamento.jar

ENTRYPOINT [ "java","-jar","ms-pagamento.jar"]