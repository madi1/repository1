# Stage 1: Build
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve
# Changed from nginx:jammy to nginx:stable
FROM nginx:stable 
# Ensure 'consultancy-frontend' matches your angular.json outputPath
COPY --from=build /app/dist/consultancy-frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]