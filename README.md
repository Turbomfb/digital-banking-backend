
# DIGITAL BANKING

## Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: 3.6.0 or higher
- **Database**: PostgreSQL 12+
- **Redis**: Redis server running (locally, Docker, or remote)

## Quick Start

### 1. Clone Repository
```bash
git clone https://github.com/Turbomfb/digital-banking/
cd digital-banking
```

### 2. Setup Database
```bash
# PostgreSQL
createdb digital-banking-dev
```

### 3. Setup Redis

You need a running Redis instance for caching and other features.

- **Option A: Run Redis locally**

  ```bash
  redis-server
  ```

- **Option B: Run Redis via Docker**

  ```bash
  docker run -d --name redis-container -p 6379:6379 redis
  ```

### 4. Configure Application

```bash
# Copy and edit configuration
cp src/main/resources/application.properties
```

Edit `application.properties` with your database and Redis credentials:

```properties
# PostgreSQL settings
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/digital-banking-dev}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}

# Redis settings
spring.redis.host=${REDIS_HOST:localhost}
spring.redis.port=${REDIS_PORT:6379}
spring.redis.timeout=${REDIS_TIMEOUT:60000} # timeout in milliseconds
```

### 5. Run the Application

#### Backend API
```bash
# Install dependencies and run
mvn clean install
mvn spring-boot:run

# Application will start on http://localhost:8098
```

## API Access

- **Base URL**: `http://localhost:8098/api/v1`
- **Health Check**: `http://localhost:8098/actuator/health`
- **API Documentation**: `http://localhost:8098/swagger-ui.html`

## Environment Variables

```sh
  DB_URL=jdbc:postgresql://localhost:5432/digital-banking-dev
  DB_USERNAME=postgres
  DB_PASSWORD=postgres

  REDIS_HOST=localhost
  REDIS_PORT=6379
  REDIS_TIMEOUT=60000

  JAVA_OPTS="-Xmx512m -Xms256m"
```
