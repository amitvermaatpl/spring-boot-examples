# Start with a base image containing Java runtime: A docker image can use another image available in the docker registry as its base or parent image. # 
FROM openjdk:8-jdk-alpine

# Add Maintainer Info [Not mandatory]
LABEL maintainer="amitverma75741@gmail.com"

# Add a volume pointing to /tmp #
VOLUME /tmp

# The application's jar file #
ARG JAR_FILE=target/hello-docker-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container #
ADD ${JAR_FILE} hello-docker-app.jar

#ADD target/hello-docker-0.0.1-SNAPSHOT.jar hello-docker-app.jar

# Entry Point: Run the jar file, This is where you configure how the application is executed inside the container. #
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /hello-docker-app.jar" ]