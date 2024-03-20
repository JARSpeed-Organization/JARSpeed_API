# Use the official Maven image with JDK 17 to create a build artifact.
FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Optionnel: définir une variable d'environnement pour stocker le dossier où l'application réside dans le conteneur
ARG APP_DIR=/app

# Créer le répertoire de l'application
RUN mkdir -p ${APP_DIR}

# Définir le répertoire de travail dans le conteneur
WORKDIR ${APP_DIR}

# Copier le fichier JAR de l'application dans l'image Docker
# Remplacez 'build/libs/mon-app-0.0.1-SNAPSHOT.jar' par le chemin de votre fichier JAR
COPY target/API-0.0.1-SNAPSHOT.jar ${APP_DIR}/app.jar

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8080

# Définir la commande pour démarrer l'application
CMD ["java", "-jar", "app.jar"]

