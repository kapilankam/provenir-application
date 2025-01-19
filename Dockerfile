### Use a slim runtime image for production
#FROM openjdk:17-jdk-slim
#
#RUN apt-get update && apt-get install -y python3 python3-pip \
#    && pip3 install spacy \
#    && python3 -m spacy download en_core_web_md
## Set the working directory in the runtime image
#WORKDIR /app
#
## Copy the built artifact from the builder stage
#COPY build/libs/provenir-0.0.1-SNAPSHOT.jar provenir-0.0.1-SNAPSHOT.jar
#COPY scripts /app/scripts
## Expose the application port
#EXPOSE 8080
#
## Define the command to run the application
#CMD ["java", "-jar", "provenir-0.0.1-SNAPSHOT.jar"]


FROM maven:3.8.4-openjdk-17-slim as base
RUN apt-get update && apt-get install -y python3 python3-pip \
    && pip3 install spacy \
    && python3 -m spacy download en_core_web_md

FROM base
WORKDIR /app

# Copy the built artifact from the builder stage
COPY build/libs/provenir-0.0.1-SNAPSHOT.jar provenir-0.0.1-SNAPSHOT.jar
COPY scripts /app/scripts
# Expose the application port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "provenir-0.0.1-SNAPSHOT.jar"]
