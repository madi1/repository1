# Stage 1: Build (Switching to alpine here speeds up your Azure DevOps/GitHub builds)
FROM node:20-alpine AS build 
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve
FROM nginx:alpine
# Copy the compiled files from the build stage
COPY --from=build /app/dist/angular/browser/ /usr/share/nginx/html/
# Copy your custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
Use code with caution.
