# Stage 1: Build (Using an updated LTS Node version)
FROM node:20-jammy as build
WORKDIR /app

# Install dependencies using 'npm ci' for faster, consistent builds
COPY package*.json ./
RUN npm ci

# Copy source and build
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve with Nginx (Optimized for performance)
FROM nginx:jammy

# IMPORTANT: Replace 'your-actual-app-name' with the name in your angular.json
COPY --from=build /app/dist/your-actual-app-name /usr/share/nginx/html

# (Optional) Copy a custom nginx config to handle Angular routing
# COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
