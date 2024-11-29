# Coding Resources Application

A web application for managing and discovering programming resources, built with Spring Boot and MySQL.

## Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Maven (or use the included Maven wrapper)

## Project Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd coding-resources
```

### 2. Database Setup

1. Start MySQL using Docker Compose:
```bash
docker-compose up -d
```

This will:
- Create a MySQL 8.0 container
- Set up the database named `coding_resources`
- Create a user with credentials:
  - Username: `coding_user`
  - Password: `coding_password`

2. Verify MySQL container is running:
```bash
docker ps
```

You should see a container named `coding_resources_mysql` running.

### 3. Initialize Database with Sample Data

1. Wait a few seconds for MySQL to fully start, then run:
```bash
docker exec -i coding_resources_mysql mysql -u coding_user -pcoding_password coding_resources < src/main/resources/data.sql
```

This will populate the database with:
- Programming categories
- Topics
- Sample resources
- Resource categories and topics relationships

2. Verify data insertion:
```bash
docker exec -it coding_resources_mysql mysql -u coding_user -pcoding_password coding_resources
```

Then in MySQL prompt:
```sql
SELECT COUNT(*) FROM resources;
SELECT COUNT(*) FROM categories;
SELECT COUNT(*) FROM topics;
```

### 4. Run the Application

1. Start the Spring Boot application:
```bash
./mvnw spring-boot:run
```

2. Access the application:
- Open your browser and navigate to: `http://localhost:8081`
- The application will be running with sample data loaded

## Application Features

- Browse programming resources by categories
- Search resources by name or topic
- View detailed information about each resource
- Resources are organized by categories like:
  - Programming Languages
  - Web Development
  - Mobile Development
  - DevOps
  - Database
  - Machine Learning
  - Cloud Computing

## Troubleshooting

1. If MySQL connection fails:
```bash
# Check MySQL container logs
docker logs coding_resources_mysql

# Restart MySQL container
docker-compose restart
```

2. If data insertion fails:
```bash
# Connect to MySQL and check tables
docker exec -it coding_resources_mysql mysql -u coding_user -pcoding_password coding_resources

# In MySQL prompt:
SHOW TABLES;
DESCRIBE resources;
DESCRIBE categories;
DESCRIBE topics;
```

## Database Schema

The application uses the following main tables:
- `resources`: Stores programming resources
- `categories`: Resource categories
- `topics`: Resource topics
- `resources_categories`: Maps resources to categories
- `resources_topics`: Maps resources to topics

## Contributing

Feel free to submit issues and enhancement requests!
