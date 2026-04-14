---
trigger: always_on
description: Necessary when key changes have been
globs: 
---

# Project Plan for tinker-with-react

The overarching project here, `tinker-with-react`, should be ready to laurch by itself, out of the box.

## Major players
1. The smart bash or Python script to kick off: 
  - checks for required global system software
    - e.g. docker, and you can call your `check-docker.sh` script
    - quit on failure, with a helpful message
    - Once this has been done, we want to be smart and not ask again.  So, save a small text artifact (`state.yaml`) in $PROJECT_ROOT/.temp and write "bootstrap.complete=true".  This will be the start of a properties file to keep our state
    - call on the next major player
2. The builder
  - dependency updating and building
    - mostly just a build script, but don't forget to call on Gradle and Vite/React to verify their packages
3. The docker whisperer
  - Containerizes the subprojects I've built.  The subprojects here are still very small, and it may not seem worth it to containerize them yet, but I want to be able to parallelize their build, and be able to count on docker to keep their logs accessible when I want them, rather than keep terminal windows open
  - Pulls and/or rebuilds the containers for other services we've configured.  Mostly working with `docker-compose.yml`.
    - Postgres container should exist
    - Localstack, even though I haven't found a need for it yet
4. The runner(s)
  - Run React/vite projects with HMR, inside containers
  - If spring boot can be launched in a way that it rebuilds and refreshes, do that there, too.
    - Else, have another tiny helper script ready that can handily stop the container, re-start the build, and start it up again 
5. README.md - should explain all this to a traveler reviewsperson.

## Other cool files
1. An overall `test.py` script should meld the test runner commands for frontend and backend
  - It should provide options to skip either side, if desired
  - Default behavior on fail is to just fail
  - A parameter should allow all tests to press on, at the user's wish.
    - In that case, a summary should be saved immediately after the failing suite, so that it can be relayed as part of the overall test results at the end.
  - Should report how many tests were run per project

## More on docker-compose.yml
For the containers we're creating out of nothing:
- Anticipate that a hot-rodder may just fly into this project and call `docker compose up -d` before reading anything.  
  - Keep an eye out for evidence of that, and be ready with helpful console output. 

## Project-wide advice
- scripts that aren't utterly, trivially small should offer help on the command line.  They should accept `-h` and `--help`.  If there's only like 2 ways to call it, you can probably skip the "EXAMPLES" section.