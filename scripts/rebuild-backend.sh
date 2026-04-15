#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

show_help() {
    cat << EOF
Usage: $(basename "$0") SERVICE [OPTIONS]

Rebuild and restart a backend container.

ARGUMENTS:
    SERVICE          Service name (resistors or trading)

OPTIONS:
    -h, --help       Show this help message


EOF
}

# Check for help flag first, before validating arguments
for arg in "$@"; do
    if [[ "${arg}" == "-h" || "${arg}" == "--help" ]]; then
        show_help
        exit 0
    fi
done

if [[ $# -lt 1 ]]; then
    echo "Error: SERVICE argument required"
    show_help
    exit 1
fi

SERVICE_NAME="$1"
shift

if [[ "${SERVICE_NAME}" != "resistors" && "${SERVICE_NAME}" != "trading" ]]; then
    echo "Error: SERVICE must be 'resistors' or 'trading'"
    show_help
    exit 1
fi

CONTAINER_NAME="tinker-${SERVICE_NAME}"

cd "${PROJECT_ROOT}"

echo "→ Stopping and removing ${CONTAINER_NAME}..."
docker compose stop "${SERVICE_NAME}" 2>/dev/null || true
docker rm -f "${CONTAINER_NAME}" 2>/dev/null || true

echo "→ Building ${SERVICE_NAME}..."
docker compose build --no-cache "${SERVICE_NAME}"

echo "→ Starting ${SERVICE_NAME}..."
docker compose up -d "${SERVICE_NAME}"

echo "✓ ${SERVICE_NAME} rebuilt and restarted"
