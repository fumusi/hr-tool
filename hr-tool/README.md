# HR Tool

A Spring Boot + Maven HR platform for managing job candidates and their skills.

## Features

- Add, update, delete, and search job candidates
- Add/remove skills for a candidate  
- Search candidates by name or by skill(s)
- H2 in-memory database
- Swagger/OpenAPI UI
- Service-layer and REST API tests
- React TypeScript UI

## Prerequisites

- Java 17+
- Maven 3.6+
- Node.js 16+ and npm (for frontend only)

## Build and Run

### Backend

#### 1. Build and test:
```bash
mvn clean test
```

#### 2. Start the app:
```bash
mvn spring-boot:run
```

The backend will start at `http://localhost:8080`

#### 3. Open Swagger UI:
- http://localhost:8080/swagger-ui.html

#### 4. H2 Console (optional):
- http://localhost:8080/h2-console
- URL: `jdbc:h2:mem:hrtool`
- Username: `sa`
- Password: (leave empty)

### Frontend

#### 1. Install dependencies:
```bash
cd hr-tool-frontend
npm install
```

#### 2. Start the app:
```bash
npm start
```

The frontend will start at `http://localhost:3000`

**Note:** Make sure the backend is running at `http://localhost:8080`

#### 3. Build for production:
```bash
npm run build
```

## API Endpoints

### Add job candidate
```
POST /api/candidates
Content-Type: application/json

Request:
{
  "fullName": "Jane Doe",
  "dateOfBirth": "1990-01-01",
  "contactNumber": "+123456789",
  "email": "jane@example.com",
  "skills": ["Java", "Database design"]
}
```

### Update job candidate
```
PUT /api/candidates/{id}
```

### Add skills to candidate
```
POST /api/candidates/{id}/skills
```

### Remove skill from candidate
```
DELETE /api/candidates/{id}/skills/{skillName}
```

### Delete candidate
```
DELETE /api/candidates/{id}
```

### Get candidate by ID
```
GET /api/candidates/{id}
```

### Search by name
```
GET /api/candidates/search/by-name?name=Jane
```

### Search by skills
```
GET /api/candidates/search/by-skills?skills=Java&skills=Database%20design
```

## Project Structure

```
hr-tool/                          # Backend (Java/Spring Boot)
├── src/main/java/...             # Java source code
├── src/test/java/...             # Test cases
├── pom.xml                        # Maven configuration
└── README.md                      # This file

hr-tool-frontend/                 # Frontend (React/TypeScript)
├── src/
│   ├── components/                # React components
│   ├── api/                       # API client
│   ├── types/                     # TypeScript types
│   ├── App.tsx                    # Main component
│   └── index.tsx                  # Entry point
├── public/
│   └── index.html                 # HTML template
├── package.json                   # npm configuration
├── tsconfig.json                  # TypeScript configuration
└── README.md                      # Frontend documentation
```

## Testing

### Backend Tests
```bash
mvn test
```

Results will show:
- JobCandidateServiceImplTest (3 tests)
- JobCandidateControllerTest (1 test)
- ApiExceptionHandlerTest (2 tests)

### Frontend Tests
```bash
cd hr-tool-frontend
npm test
```

## Documentation

### Backend API Documentation
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/api-docs
- OpenAPI YAML: http://localhost:8080/api-docs.yaml

### Frontend Documentation
See `hr-tool-frontend/README.md` for frontend-specific information.

## Development

### Running Both Backend and Frontend

Terminal 1 - Backend:
```bash
mvn spring-boot:run
```

Terminal 2 - Frontend:
```bash
cd hr-tool-frontend
npm start
```

Then navigate to http://localhost:3000 to access the UI.

## Database

This application uses H2 in-memory database which:
- Requires no external setup
- Persists only during application runtime
- Is perfect for development and testing
- Uses data structure defined in entities

To view database contents:
1. Open H2 Console: http://localhost:8080/h2-console
2. Connect with provided credentials
3. Query tables: job_candidates, skills, job_candidate_skills

## Production Deployment

### Backend
```bash
mvn clean package
java -jar target/hr-tool-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd hr-tool-frontend
npm run build
# Serve the 'build' directory with a static server
```

## License

This project is part of the HR Tool platform.


