#!/bin/bash
set -e

################################################################################
# Build Script - Dependencies and Compilation
################################################################################
#
# PURPOSE:
#   Updates dependencies and builds all subprojects (Gradle backends, Vite frontend)
#
# USAGE:
#   ./scripts/build.sh [options]
#
# OPTIONS:
#   -h, --help          Show this help message
#   --skip-deps         Skip dependency updates
#   --backend-only      Build only backend projects
#   --frontend-only     Build only frontend projects
#
################################################################################

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

show_help() {
    sed -n '/^# USAGE:/,/^################################################################################$/p' "$0" | sed 's/^# //;s/^#//'
}

SKIP_DEPS=false
BACKEND_ONLY=false
FRONTEND_ONLY=false

while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            show_help
            exit 0
            ;;
        --skip-deps)
            SKIP_DEPS=true
            shift
            ;;
        --backend-only)
            BACKEND_ONLY=true
            shift
            ;;
        --frontend-only)
            FRONTEND_ONLY=true
            shift
            ;;
        *)
            echo "Unknown option: $1"
            echo "Use -h or --help for usage information"
            exit 1
            ;;
    esac
done

build_gradle_project() {
    local project_name=$1
    local project_path=$2

    echo "→ Building $project_name..."
    cd "$project_path"
    ./gradlew clean build -x test

    echo "  ✓ $project_name built successfully"
    cd "$PROJECT_ROOT"
}

build_vite_project() {
    local project_name=$1
    local project_path=$2

    echo "→ Building $project_name..."
    cd "$project_path"

    if [ ! -d "node_modules" ] || [ "$SKIP_DEPS" = false ]; then
        echo "  • Installing/updating dependencies..."
        npm install
    fi

    echo "  • Building for production..."
    npm run build

    echo "  ✓ $project_name built successfully"
    cd "$PROJECT_ROOT"
}

if [ "$FRONTEND_ONLY" = false ]; then
    echo "Building backend projects..."
    echo ""

    if [ -d "$PROJECT_ROOT/backends/resistors" ]; then
        build_gradle_project "resistors" "$PROJECT_ROOT/backends/resistors"
        echo ""
    fi

    if [ -d "$PROJECT_ROOT/backends/trading" ]; then
        build_gradle_project "trading" "$PROJECT_ROOT/backends/trading"
        echo ""
    fi
fi

if [ "$BACKEND_ONLY" = false ]; then
    echo "Building frontend projects..."
    echo ""

    if [ -d "$PROJECT_ROOT/frontends/tinker-react" ]; then
        build_vite_project "tinker-react" "$PROJECT_ROOT/frontends/tinker-react"
        echo ""
    fi
fi

echo "✓ Build complete!"
