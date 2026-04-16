# Fullstack Project (Angular 3 + Java Microservices)

This folder is a lightweight wrapper project to run the existing Angular frontend and Java microservice backend as one fullstack app.

## Structure
- `../angular` : Angular frontend app
- `../microservices` : Spring Boot backend app

## Run backend
1. Open terminal in `microservices`
2. `mvn clean spring-boot:run`
3. Backend listens on `http://localhost:8080`

## Run frontend
1. Open terminal in `angular`
2. `npm install` (if first run)
3. `npm start`
4. Frontend listens on `http://localhost:4200`

## Verify
- Access `http://localhost:4200` and see payload from backend in UI.
- Backend endpoint: `GET http://localhost:8080/api/consultancy` returns JSON:
  - `payload`: `my name is AI`
  - `message`: `Welcome to Madi Consultancy!`
  - `status`: `Backend Connected`

## Optional: Run both using one command
(Requires Node.js installed globally with `concurrently`):

```bash
cd fullstack
npm init -y
npm install --save-dev concurrently
npx concurrently "npm --prefix ../microservices -- run spring-boot" "npm --prefix ../angular -- start"
```

> The backend is a Java Maven project; there is no Node `npm run spring-boot` by default. Use `mvn spring-boot:run` in the `microservices` folder.
