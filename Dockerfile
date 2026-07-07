# ------------------------------------------------------------------------------
# Docker image build recipe (Stage 3).
#
# Purpose:
# - Produces a small runtime image by using a multi-stage build.
# - Works well in CI pipelines and Kubernetes deployments.
# ------------------------------------------------------------------------------

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy build files first (better Docker layer caching)
COPY pom.xml .
COPY src ./src

RUN mvn -q -DskipTests=false test
RUN mvn -q -DskipTests package

# Runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Environment variables are a common configuration mechanism in containers.
ENV JAVA_OPTS=""

COPY --from=build /workspace/target/devops-learning-springboot-0.1.0.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

