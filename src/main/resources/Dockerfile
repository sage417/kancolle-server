FROM openjdk:8u102-jre
VOLUME /tmp
ADD kancolle-server-@project.version@.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]