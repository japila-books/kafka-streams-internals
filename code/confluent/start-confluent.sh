#!/bin/zsh

# Pull the latest versions of the service containers
docker-compose pull

docker compose -f docker-compose.yml up

