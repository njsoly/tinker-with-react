#!/bin/bash
set -e

#####################################################
# This file is pure Claude, with my verbal guidance
#
#####################################################

echo "==> Checking Docker/Podman daemon status..."
./check-docker.sh

echo ""
echo "==> Starting Docker Compose services..."
docker-compose up -d

echo ""
echo "==> Services started successfully!"
echo "Use 'docker-compose ps' to check service status"
echo "Use 'docker-compose logs -f' to view logs"
