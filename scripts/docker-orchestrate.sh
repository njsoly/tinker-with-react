#!/bin/bash
set -e

################################################################################
# Docker Orchestration Script
################################################################################
#
# PURPOSE:
#   Manages container images for our custom services and external dependencies
#
# USAGE:
#   ./scripts/docker-orchestrate.sh [options]
#
# OPTIONS:
#   -h, --help          Show this help message
#
################################################################################

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

show_help() {
    sed -n '/^# USAGE:/,/^################################################################################$/p' "$0" | sed 's/^# //;s/^#//'
}

while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            echo "Unknown option: $1"
            echo "Use -h or --help for usage information"
            exit 1
            ;;
    esac
done

cd "${PROJECT_ROOT}"

echo "→ Pulling external service images..."
# Pull with full registry paths to avoid interactive prompts
docker pull docker.io/postgres:17-alpine 2>/dev/null || true
docker pull docker.io/localstack/localstack:latest 2>/dev/null || true

echo "→ Building custom application containers..."
docker compose build --no-cache

echo "✓ Docker orchestration complete!"
