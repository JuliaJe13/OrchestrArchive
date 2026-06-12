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
frontend separately

backend/
├── controller/    # REST endpoints
├── service/       # Business logic
├── model/         # JPA entities
├── repository/    # Data access
├── exception/     # Error handling
└── config/        # Configuration

## Getting Started

### Prerequisites
- Java 21
- Node.js 18+

### Backend
```bash
./mvnw spring-boot:run
```
Backend runs on http://localhost:8080

### Frontend
```bash
cd frontend
npm install
npm run dev
```
Frontend runs on http://localhost:5173

## Third-Party APIs
- **MusicBrainz API** (https://musicbrainz.org/doc/MusicBrainz_API)  
  Used for searching sheet music metadata like composer and title information.
  No API key required.

## API Documentation
Swagger UI: http://localhost:8080/swagger-ui.html