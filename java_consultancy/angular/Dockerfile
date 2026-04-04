# Use the official Node.js image as the base image
FROM node:14 as build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY angular/package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application source code
COPY angular/ .

# Build the Angular application
RUN npm run build --prod

# Use an Nginx image to serve the app
FROM nginx:alpine

# Copy built files from the previous step
COPY --from=build /app/dist/your-angular-app-name /usr/share/nginx/html

# Expose the port on which Nginx listens
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]