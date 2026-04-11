#!/bin/bash
set -e

################################################################################
# Docker/Podman Daemon Detection and Startup Script
# by Claude with my guidance
################################################################################
#
# PURPOSE:
#   This script intelligently detects whether the system is using Docker or
#   Podman (including when Podman masquerades as 'docker' via symlink or
#   docker-compose compatibility), and ensures the appropriate container
#   daemon is running before proceeding with container operations.
#
# PROBLEM IT SOLVES:
#   On systems where Podman is installed as a Docker replacement, the daemon
#   may not be automatically running (particularly the podman.socket systemd
#   service). This script automates the detection and startup process, removing
#   the manual step of starting podman.socket before running docker/docker-compose
#   commands.
#
# FUNCTIONALITY:
#   1. Detects if 'docker' command points to Podman (via symlink or version string)
#   2. For Podman setups:
#      - Checks if podman daemon is accessible
#      - Starts podman.socket via systemctl if needed
#      - Waits with retry logic (up to 10 attempts) for daemon to be ready
#   3. For regular Docker setups:
#      - Verifies Docker daemon is running
#      - Reports error if not accessible
#
# USAGE:
#   Run this script before executing docker-compose or other container commands:
#     ./check-docker.sh && docker-compose up
#
# PREREQUISITES:
#   - Podman systems: systemctl and podman.socket service configured
#   - Docker systems: Docker daemon must be started externally
#
# EXIT CODES:
#   0 = Success (daemon is running)
#   1 = Failure (daemon not accessible or failed to start)
#
################################################################################

is_using_podman() {
    if command -v docker &>/dev/null; then
        local docker_path=$(command -v docker)
        local resolved_path=$(readlink -f "$docker_path" 2>/dev/null)
        
        if [[ "$resolved_path" == *"podman"* ]]; then
            return 0
        fi
        
        docker --version 2>&1 | grep -q "podman"
    else
        return 1
    fi
}

check_and_start_podman() {
    if ! podman info &>/dev/null; then
        echo "Podman daemon not accessible. Starting podman.socket..."
        
        if systemctl --user is-active podman.socket &>/dev/null; then
            echo "Warning: podman.socket is active but podman is not responding. Restarting..."
            systemctl --user restart podman.socket
        else
            systemctl --user start podman.socket
        fi
        
        local max_attempts=10
        local attempt=1
        while [ $attempt -le $max_attempts ]; do
            if podman info &>/dev/null; then
                echo "Podman daemon is now running."
                return 0
            fi
            echo "Waiting for podman daemon to start (attempt $attempt/$max_attempts)..."
            sleep 1
            ((attempt++))
        done
        
        echo "Error: Failed to start podman daemon after $max_attempts attempts."
        return 1
    else
        echo "Podman daemon is already running."
        return 0
    fi
}

check_docker_daemon() {
    if docker info &>/dev/null; then
        echo "Docker daemon is running."
        return 0
    else
        echo "Error: Docker daemon is not running. Please start Docker."
        return 1
    fi
}

if is_using_podman; then
    echo "Detected Podman (masquerading as docker)."
    check_and_start_podman
else
    echo "Detected regular Docker installation."
    check_docker_daemon
fi
