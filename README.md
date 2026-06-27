# OrchestrArchive

A web application for managing sheet music collections for orchestras and ensembles.

## Description
OrchestrArchive allows orchestras to manage their sheet music library.
Users can organize pieces by category, track individual voice parts,
and search for music information using the MusicBrainz API.

## Architecture
- **Backend:** Spring Boot 4, Java 21, Spring Data JPA
- **Database:** H2 (development)
- **Frontend:** React with TypeScript (Vite)
- **API Documentation:** Swagger UI available at `/swagger-ui.html`

### Project Structure
OrchestrArchive/
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── api/
│   │   └── types.ts
├── src/main/java/
│   ├── controller/
│   ├── service/
│   ├── model/
│   ├── repository/
│   ├── exception/
│   └── config/
├── Dockerfile
└── docker-compose.yml

## Getting Started

### Option 1: Docker (recommended)
**Prerequisites:** Docker Desktop

```bash
# 1. Build backend
./mvnw package -DskipTests

# 2. Build frontend
cd frontend && npm run build && cd ..

# 3. Start everything
docker compose up
```
App runs on http://localhost

### Option 2: Local Development
**Prerequisites:** Java 21, Node.js 20+

```bash
# Backend
./mvnw spring-boot:run
```
Backend: http://localhost:8080

```bash
# Frontend (separate terminal)
cd frontend
npm install
npm run dev
```
Frontend: http://localhost:5173

## Third-Party APIs
- **MusicBrainz API** (https://musicbrainz.org/doc/MusicBrainz_API)  
  Used for searching sheet music metadata like composer and title information.
  No API key required.

## API Documentation
Swagger UI: http://localhost:8080/swagger-ui.html

## Running Tests
```bash
mvn test
```
