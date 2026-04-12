#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

SERVICE_NAME="tinker-react"
LOG_TAIL_LINES=200

show_help() {
    cat << EOF
Usage: $(basename "$0") [OPTIONS]

Rebuild and restart the tinker-react frontend container.

OPTIONS:
    -h, --help       Show this help message
    -l, --logs       Show logs after starting (tail mode)


EOF
}

SHOW_LOGS=false

while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            show_help
            exit 0
            ;;
        -l|--logs)
            SHOW_LOGS=true
            shift
            ;;
        *)
            echo "Unknown option: $1"
            show_help
            exit 1
            ;;
    esac
done

cd "$PROJECT_ROOT"

echo "→ Stopping and removing ${SERVICE_NAME}..."
docker compose stop "${SERVICE_NAME}" 2>/dev/null || true
docker rm -f "${SERVICE_NAME}" 2>/dev/null || true

echo "→ Building and starting ${SERVICE_NAME}..."
docker compose up -d --build "${SERVICE_NAME}"

if [[ "$SHOW_LOGS" == true ]]; then
    sleep 1
    docker logs -f --tail "${LOG_TAIL_LINES}" "${SERVICE_NAME}"
else
    echo "→ Container status:"
    docker ps --filter "name=${SERVICE_NAME}" --format "table {{.Names}}\t{{.Image}}\t{{.Status}}\t{{.Ports}}"
    echo ""
    echo "✓ ${SERVICE_NAME} rebuilt and restarted"
fi
