#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

SERVICE_NAME="tinker-react"

show_help() {
    cat << EOF
Usage: $(basename "$0") [OPTIONS]

Rebuild and restart the tinker-react frontend container.

OPTIONS:
    -h, --help       Show this help message


EOF
}

while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            echo "Unknown option: $1"
            show_help
            exit 1
            ;;
    esac
done

cd "${PROJECT_ROOT}"

echo "→ Stopping and removing ${SERVICE_NAME}..."
docker compose stop "${SERVICE_NAME}" 2>/dev/null || true
docker rm -f "${SERVICE_NAME}" 2>/dev/null || true

echo "→ Building ${SERVICE_NAME}..."
docker compose build --no-cache "${SERVICE_NAME}"

echo "→ Starting ${SERVICE_NAME}..."
docker compose up -d "${SERVICE_NAME}"

echo "✓ ${SERVICE_NAME} rebuilt and restarted"
