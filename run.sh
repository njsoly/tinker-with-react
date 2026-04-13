#!/bin/bash
set -e

################################################################################
# Main Project Launcher for tinker-with-react
################################################################################
#
# PURPOSE:
#   Single entry point to start the entire development environment.
#   Handles prerequisites, dependencies, building, and running all services.
#
# USAGE:
#   ./run.sh [options]
#
# OPTIONS:
#   -h, --help          Show this help message
#   --skip-bootstrap    Skip bootstrap checks (assume already done)
#   --skip-build        Skip build steps
#   --rebuild           Force rebuild of all containers
#   --tests             Run tests after starting services
#
################################################################################

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
STATE_DIR="$PROJECT_ROOT/.temp"
STATE_FILE="$STATE_DIR/state.yaml"

show_help() {
    sed -n '/^# USAGE:/,/^################################################################################$/p' "$0" | sed 's/^# //;s/^#//'
}

big_echo() {
    local message="$1"
    local msg_len=${#message}
    local total_width=$((msg_len + 12))
    local border=$(printf '═%.0s' $(seq 1 $total_width))
    local padded_msg=$(printf "%*s%s%*s" 6 "" "$message" 6 "")

    echo ""
    echo "$border"
    echo "$padded_msg"
    echo "$border"
    echo ""
}

ensure_state_dir() {
    if [ ! -d "$STATE_DIR" ]; then
        mkdir -p "$STATE_DIR"
        echo "Created state directory: $STATE_DIR"
    fi
}

check_bootstrap_complete() {
    if [ -f "$STATE_FILE" ] && grep -q "bootstrap.complete=true" "$STATE_FILE" 2>/dev/null; then
        return 0
    else
        return 1
    fi
}

mark_bootstrap_complete() {
    ensure_state_dir
    if ! grep -q "bootstrap.complete=true" "$STATE_FILE" 2>/dev/null; then
        echo "bootstrap.complete=true" >> "$STATE_FILE"
        echo "Bootstrap marked as complete in $STATE_FILE"
    fi
}

run_bootstrap() {
    big_echo "BOOTSTRAP: Checking System Prerequisites"

    echo "→ Checking Docker/Podman..."
    if ! "$PROJECT_ROOT/check-docker.sh"; then
        echo ""
        echo "✗ Docker/Podman check failed. Please install Docker or Podman and try again."
        exit 1
    fi

    echo ""
    echo "✓ All prerequisites satisfied"
    mark_bootstrap_complete
}

run_build() {
    big_echo "BUILD: Dependencies and Compilation"

    "$PROJECT_ROOT/scripts/build.sh"
}

run_docker() {
    big_echo "DOCKER: Container Management"

    local args=()
    if [ "$REBUILD" = true ]; then
        args=(--rebuild)
    fi
    "$PROJECT_ROOT/scripts/docker-orchestrate.sh" "${args[@]}"
}

run_services() {
    big_echo "SERVICES: Starting Development Environment"

    docker compose up -d

    echo ""
    echo "✓ All services started successfully!"
    big_echo "READY TO USE"
    echo "Services running:"
    docker compose ps

}

SKIP_BOOTSTRAP=false
SKIP_BUILD=false
REBUILD=false
RUN_TESTS=false

while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            show_help
            exit 0
            ;;
        --skip-bootstrap)
            SKIP_BOOTSTRAP=true
            shift
            ;;
        --skip-build)
            SKIP_BUILD=true
            shift
            ;;
        --rebuild)
            REBUILD=true
            shift
            ;;
        --tests)
            RUN_TESTS=true
            shift
            ;;
        *)
            echo "Unknown option: $1"
            echo "Use -h or --help for usage information"
            exit 1
            ;;
    esac
done

echo ""
echo "╔═══════════════════════════════════════════════════════════════╗"
echo "║          tinker-with-react Development Environment            ║"
echo "╚═══════════════════════════════════════════════════════════════╝"
echo ""

if [ "$SKIP_BOOTSTRAP" = false ]; then
    if check_bootstrap_complete; then
        echo "✓ Bootstrap previously completed"
    else
        run_bootstrap
    fi
fi

if [ "$SKIP_BUILD" = false ]; then
    run_build
fi

if [ "$RUN_TESTS" = true ]; then
    big_echo "TESTS: Running Test Suites"
    "$PROJECT_ROOT/test.py"
fi

run_docker
run_services

big_echo "Project is ready! Happy coding!"
