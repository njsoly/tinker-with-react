#!/bin/bash
set -e

################################################################################
# Application Restart Helper
################################################################################
#
# PURPOSE:
#   Quickly rebuild and restart a specific containerized application
#
# USAGE:
#   ./scripts/restart-app.sh <service-name>
#
# EXAMPLES:
#   ./scripts/restart-app.sh resistors-api
#   ./scripts/restart-app.sh trading-api
#   ./scripts/restart-app.sh tinker-react
#
################################################################################

if [ $# -eq 0 ]; then
    echo "Usage: $0 <service-name>"
    echo ""
    echo "Available services:"
    echo "  - resistors-api"
    echo "  - trading-api"
    echo "  - tinker-react"
    exit 1
fi

SERVICE=$1
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

cd "${PROJECT_ROOT}"

echo "→ Stopping ${SERVICE}..."
docker compose stop "${SERVICE}"

echo "→ Rebuilding ${SERVICE}..."
docker compose build --no-cache "${SERVICE}"

echo "→ Starting ${SERVICE}..."
docker compose up -d "${SERVICE}"

echo "✓ ${SERVICE} restarted successfully!"
echo ""
