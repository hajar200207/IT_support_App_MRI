FROM openjdk:17
# Expose the application port
EXPOSE 8080
# Copy the application jar to the container
ADD /target/equipment-management.jar /app.jar
ENTRYPOINT ["java", "-jar","app.jar"]