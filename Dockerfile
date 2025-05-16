# Etapa 1: Construir la aplicación con Maven
# Etapa 1: Construir la aplicación con Maven
# Cambia esta línea:
# FROM maven:3.8.5-openjdk-17 AS build
# Por esta (o una similar con JDK 21):
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copiar solo el pom.xml primero para aprovechar el cache de Docker si las dependencias no cambian
COPY pom.xml .
# Descargar dependencias (esto se cachea si pom.xml no cambia)
RUN mvn dependency:go-offline -B
# Copiar el resto del código fuente
COPY src ./src
# Construir el JAR ejecutable
RUN mvn package -DskipTests

# Etapa 2: Crear la imagen final a partir de una imagen JRE ligera
# Asegúrate que la JRE también sea de Java 21 si usas JDK 21 para compilar
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Exponer el puerto en el que la aplicación Spring Boot corre (por defecto 8080)
EXPOSE 8085
# Copiar el JAR construido desde la etapa de 'build'
COPY --from=build /app/target/*.jar app.jar
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]