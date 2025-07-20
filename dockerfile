FROM openjdk:11
MAINTAINER MTA taghi.ahadi@gmail.com
COPY target/concurrent-banking-system-0.0.1-SNAPSHOT.jar concurrent-banking-system-1.0.0.jar
ENTRYPOINT ["java","-jar","/concurrent-banking-system-1.0.0.jar"]