#!/bin/bash
set -e

#####################################################
# This file is pure Claude, with my verbal guidance
#
#####################################################

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
