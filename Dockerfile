FROM reg.haier.net/library/jdk:1.8_60

ADD patchservice-0.0.1-SNAPSHOT.jar /application.jar

CMD ["java","-Xms512m","-Xmx512m","-jar","application.jar"]


