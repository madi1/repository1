# Stage 1: Build
FROM node:20-jammy as build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve
FROM nginx:jammy
# Adjust 'consultancy-frontend' to match your angular.json 'outputPath'
COPY --from=build /app/dist/consultancy-frontend /usr/share/nginx/html

# REQUIRED: Copy the config to handle Angular routing & internal "ping"
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
