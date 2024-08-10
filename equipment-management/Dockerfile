FROM openjdk:17

# Expose the application port
EXPOSE 8080

# Copy the application jar to the container
ADD /target/equipment-management-0.0.1-SNAPSHOT.jar /equipment-management.jar




ENTRYPOINT ["java", "-jar","equipment-management.jar"]