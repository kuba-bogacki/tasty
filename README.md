# tasty

A Java-based project. This README is a starter template — update the sections below with details specific to this repository.

## Table of contents

- [About](#about)
- [Features](#features)
- [Requirements](#requirements)
- [Build & run](#build--run)
- [Docker](#docker)
- [Configuration](#configuration)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## About

Briefly describe what the tasty project does and its main goals. Replace this paragraph with a concise project description.

Example:

> tasty is a Java library/service for parsing, analyzing, and serving recipes (or replace with your actual project purpose).

## Features

- Short bullet list of main features
- What problem it solves
- Key components or modules

## Requirements

- Java 11+ (adjust to the version your project needs)
- JDK and build tool (Maven or Gradle) installed
- Docker (optional, if you use the included Dockerfile)

## Build & run

If the project uses Maven:

```bash
# from repository root
mvn clean package
java -jar target/your-artifact-name.jar
```

If the project uses Gradle (wrapper recommended):

```bash
# build
./gradlew build
# run (adjust path to jar produced)
java -jar build/libs/your-artifact-name.jar
```

If this is a library, use your preferred build tool to include the package as a dependency.

Replace `your-artifact-name.jar` with the actual artifact name produced by your build.

## Docker

A Dockerfile is present in the repository. Example build & run:

```bash
# build image (adjust tag/name as needed)
docker build -t kuba-bogacki/tasty:latest .
# run container
docker run --rm -p 8080:8080 kuba-bogacki/tasty:latest
```

Adjust the exposed port and run command to match your application.

## Configuration

Document configuration options (environment variables, config files, command-line flags). Example:

- PORT - port the service listens on (default: 8080)
- DATABASE_URL - connection string for the database (if applicable)

## Testing

Run unit and integration tests with your build tool:

Maven:

```bash
mvn test
```

Gradle:

```bash
./gradlew test
```
```

## Contributing

Contributions are welcome. Suggested workflow:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/my-change`
3. Make changes and add tests
4. Run tests locally
5. Open a pull request describing your changes

Please add a clear description of the problem you're solving and include any relevant screenshots or logs.

## License

Add your license here (e.g., MIT, Apache-2.0). If you are not sure, add a LICENSE file in the repository and update this section.

## Contact

Maintainer: kuba-bogacki

Project link: https://github.com/kuba-bogacki/tasty
