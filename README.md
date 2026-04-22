# tinker-with-react

A polyglot monorepo demonstrating full-stack development with multiple backends (Spring Boot/Kotlin) and frontends (React/TypeScript), all containerized and ready to run out of the box.

___Warning:__ there are many scripts that promise fancy things below, and they are falling into disrepair._\
_I run the `tinker-react` frontend with `npm run dev`, and then the `resistors-api` backend via IntelliJ._


## Quick Start

**Get everything running in one command:**

```bash
./run.sh
```

That's it! The script will:
1. Check system prerequisites (Docker/Podman)
2. Build all projects (Gradle + npm)
3. Build Docker images
4. Start all services with PostgreSQL and LocalStack

**Access your services:**
- React Frontend: http://localhost:5173
- Resistors API: http://localhost:8081
- Trading API: http://localhost:8082
- PostgreSQL: localhost:5432
- LocalStack: http://localhost:4566

## Project Structure

```
tinker-with-react/
├── backends/
│   ├── resistors/        # Spring Boot API for resistor calculations
│   └── trading/          # Spring Boot API for trading (backburner)
├── frontends/
│   └── tinker-react/     # React + TypeScript + Vite frontend
├── scripts/              # Helper scripts
│   ├── build.sh          # Build all projects
│   ├── docker-orchestrate.sh  # Manage containers
│   └── restart-app.sh    # Quick restart individual services
├── run.sh                # Main entry point
├── test.py               # Unified test runner
└── docker-compose.yml    # All service definitions
```

## Usage

### Starting the Project

**First time or full setup:**
```bash
./run.sh
```

**Skip bootstrap if already done:**
```bash
./run.sh --skip-bootstrap
```

**Force rebuild everything:**
```bash
./run.sh --rebuild
```

**Run with tests:**
```bash
./run.sh --tests
```

### Testing

**Run all tests:**
```bash
./test.py
```

**Run only backend tests:**
```bash
./test.py --backend-only
```

**Run only frontend tests:**
```bash
./test.py --frontend-only
```

**Continue on failure (see all results):**
```bash
./test.py --continue
```

### Managing Services

**View logs:**
```bash
docker compose logs -f
docker compose logs -f resistors  # specific service
```

**Restart a service after code changes:**
```bash
./scripts/restart-app.sh resistors
./scripts/restart-app.sh trading
./scripts/restart-app.sh tinker-react
```

**Stop everything:**
```bash
docker compose down
```

**Stop and remove volumes:**
```bash
docker compose down -v
```

### Building

**Build everything:**
```bash
./scripts/build.sh
```

**Build only backends:**
```bash
./scripts/build.sh --backend-only
```

**Build only frontends:**
```bash
./scripts/build.sh --frontend-only
```

## Subprojects

### [resistors](backends/resistors/README.md)
Spring Boot backend for resistor calculations and electronics utilities.

### [trading](backends/trading/README.md)
Spring Boot backend for trading operations (currently on backburner).

### [tinker-react](frontends/tinker-react/README.md)
React + TypeScript frontend with Vite for hot module replacement.

## Technical Details

### Docker/Podman Support

This project works with both regular Docker and Podman installations. The startup scripts automatically detect which is in use.

**On Fedora/Podman systems:**
- Podman socket is enabled to start on boot: `systemctl --user enable podman.socket`
- Scripts automatically start the Podman daemon if needed

**On other systems:**
- Regular Docker daemon should be running
- Scripts verify Docker is accessible before starting services

### Services

All services run in containers with:
- Health checks for reliable startup ordering
- Volume persistence for databases
- Hot reload for development (where supported)
- Isolated networks for security

**Infrastructure:**
- PostgreSQL 17 (database)
- LocalStack (AWS service emulation)

**Applications:**
- Resistors API (port 8081)
- Trading API (port 8082)
- React Frontend (port 5173)

## Development Notes

- All scripts support `-h` or `--help` for detailed usage
- State tracking in `.temp/state.yaml` prevents redundant checks
- Logs are accessible via Docker Compose
- Frontend has HMR (Hot Module Replacement) enabled
- Backend changes require container restart (use `restart-app.sh`)
