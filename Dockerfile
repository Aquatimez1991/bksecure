# Usamos una imagen base de Java (asegúrate de usar la versión correcta, ej. 17 o 21)
FROM openjdk:17-jdk-slim

# Creamos directorio de trabajo
WORKDIR /app

# Copiamos el archivo JAR generado (ajustaremos el nombre luego)
COPY target/*.jar app.jar

# Exponemos el puerto 8081
EXPOSE 8081

# Comando para iniciar la app
ENTRYPOINT ["java", "-jar", "app.jar"]