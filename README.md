# ConjurCloud Spring Boot Demo

This project demonstrates how to integrate a Spring Boot application with CyberArk Conjur Cloud using the official [conjur-sdk-springboot](https://mvnrepository.com/artifact/com.cyberark/conjur-sdk-springboot) version `2.1.2`.

## 📦 Features

- Secure secret retrieval using CyberArk Conjur
- Spring Boot 3.5.3 with Java 17
- PostgreSQL DataSource example using injected secrets
- Built-in retry logic and error handling
- Clean architecture with `SecretProvider` abstraction
- Test configuration with mock secrets
- Docker support for containerized deployment

## 🛠 Technologies

- Java 17
- Spring Boot
- CyberArk Conjur SDK
- Maven
- Docker

## 🧰 How to Use

### 1. Compile the project

```bash
mvn clean package
```

### 2. Run locally

```bash
java -jar target/ConjurCloud-0.0.1-SNAPSHOT.jar
```

Access: `http://localhost:8080/test`

### 3. Run with Docker

```bash
docker build -t conjurcloud-app .
docker run -p 8080:8080 conjurcloud-app
```

## 🔐 Secrets Configuration

Secrets like `dev/db/username` and `dev/db/password` must exist in your Conjur Cloud account and be accessible by the authenticated identity running the application.

You can configure Conjur authentication via environment variables or the Conjur SDK auto-configuration.

## 🧪 Testing

The project includes a `TestSecretConfig` to mock secrets during tests. Run tests with:

```bash
mvn test
```

## 📄 License

This project is for demonstration purposes and follows standard CyberArk SDK usage guidelines.
