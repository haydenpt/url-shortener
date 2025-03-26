#!/bin/bash

# Build the Docker images
echo "Building urlshortener image..."
docker build -t urlshortener:latest ./urlshortener

echo "Building urlanalytics image..."
docker build -t urlanalytics:latest ./url-analytics

# Run docker-compose
echo "Starting services with docker-compose..."
docker-compose up -d