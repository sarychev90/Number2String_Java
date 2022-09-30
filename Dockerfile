# Build stage
FROM maven:3.8.6-openjdk-18 AS build
COPY . /home
RUN mvn -f /home/pom.xml clean install 

# Package stage
FROM tomcat:9-jdk8-corretto
ARG WAR_FILE=*.war
COPY --from=build /home/target/${WAR_FILE} /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]