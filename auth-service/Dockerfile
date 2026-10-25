# Étape 1 : Utiliser une image de base Java 21
FROM eclipse-temurin:21-jre-jammy

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR compilé (généré par Maven dans le dossier target)
COPY target/*.jar app.jar

# Étape 4 : Exposer le port du service (8084)
EXPOSE 8084

# Étape 5 : Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
