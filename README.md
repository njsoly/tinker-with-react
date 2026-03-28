# tinker_with_react

The original point of this repo was to try out React as a frontend.

Now, I'm thinking of turning it into a monorepo with React as one frontend of possibly several, and multiple backends.  

It'd be great to get a Python backend in here, but I'm more familiar with Spring Boot, so I may start with one of those in order to start on some solid  ground.

## Modules-to-be

### [resistors](backends/resistors/README.md)

A Spring Boot backend for resistors.

### [trading](backends/trading/README.md)

A Spring Boot backend for trading (backburner).

### [tinker-react](tinker-react/README.md)

The React frontend.

## Development Setup

### Docker/Podman

This project works with both regular Docker and Podman installations. The startup scripts automatically detect which is in use.

**On Fedora/Podman systems:**
- Podman socket is enabled to start on boot: `systemctl --user enable podman.socket`
- The scripts will automatically start the Podman daemon if needed

**On other systems:**
- Regular Docker daemon should be running
- The scripts verify Docker is accessible before starting services

**Startup Scripts:**
- `./check-docker.sh` - Detects and verifies Docker/Podman daemon is running
- `./start-services.sh` - Checks daemon status and starts docker-compose services

The IntelliJ run configuration automatically checks daemon status before launching services.
