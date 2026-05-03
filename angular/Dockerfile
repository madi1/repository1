# Stage 1: Build
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve
FROM nginx:stable

# The new builder puts files in dist/angular/browser
# We use /* to ensure we grab the CONTENTS, not the folder itself
COPY --from=build /app/dist/angular/browser/ /usr/share/nginx/html/

# Copy your custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
