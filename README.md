# DevOps Learning Spring Boot (Java 21)

This repository is a small, production-style Spring Boot project intended for a DevOps learning journey:

- **Stage 1**: Git, GitHub
- **Stage 2**: Maven build, unit testing, GitHub Actions CI
- **Stage 3**: Docker, Docker Compose
- **Stage 4**: Kubernetes (ConfigMap, Secret, Deployment, Service, Ingress, HPA)
- **Stage 5**: Helm
- **Stage 6**: Continuous Deployment

The application itself is intentionally simple:

- **REST API** for managing "notes"
- **In-memory storage** (no database)
- Clean package separation: controller / service / repository / model
- Constructor injection
- JUnit tests
- No authentication

## Requirements

- Java 21
- Maven 3.9+

## Run locally (Maven)

```bash
mvn test
mvn spring-boot:run
```

App will start on `http://localhost:8080`.

## API quick check

```bash
curl http://localhost:8080/api/notes
```

## Build a runnable JAR

```bash
mvn clean package
java -jar target/devops-learning-springboot-0.1.0.jar
```

## Docker (Stage 3)

```bash
docker build -t devops-learning-springboot:local .
docker run -p 8080:8080 devops-learning-springboot:local
```

## Docker Compose (Stage 3)

```bash
docker compose up --build
```

## Kubernetes (Stage 4)

Manifests live in `k8s/`. Apply them once you have a cluster:

```bash
kubectl apply -f k8s/
```

> The manifests are intentionally minimal starter templates to evolve as you learn (Ingress/HPA require cluster add-ons).

