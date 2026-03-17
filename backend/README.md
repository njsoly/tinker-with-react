# Portfolio Backend

Spring Boot backend for the portfolio hub, built with Kotlin using DDD and Hexagonal Architecture.

## Architecture

This is a modular monolith with the following modules:

### Core Modules
- **portfolio-domain**: Shared domain models and utilities
- **portfolio-api**: Main Spring Boot application (entry point)

### Trading Module (Hexagonal Architecture)
- **trading-domain**: Domain logic, entities, value objects, and port definitions
  - `domain/`: Trade, Portfolio, Holding aggregates and value objects
  - `application/`: Use case implementations (TradeManagementService)
  - `port/in/`: Inbound ports (use case interfaces)
  - `port/out/`: Outbound ports (repository interfaces)
  
- **trading-api**: Adapters for the trading domain
  - `adapter/in/web/`: REST controllers
  - `adapter/out/persistence/`: JPA repository implementations
  - `config/`: Spring configuration

## Tech Stack

- **Kotlin** 1.9.22
- **Spring Boot** 3.2.2
- **Gradle** with Kotlin DSL
- **H2 Database** (in-memory for development)
- **JPA/Hibernate**

## Running the Application

```bash
./gradlew :portfolio-api:bootRun
```

The application will start on `http://localhost:8080`

## API Endpoints

### Trading
- `POST /api/trading/trades` - Record a new trade
- `GET /api/trading/trades` - Get all trades
- `GET /api/trading/trades/symbol/{symbol}` - Get trades for a symbol
- `GET /api/trading/portfolio` - Get current portfolio holdings

### H2 Console
- `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:portfoliodb`
  - Username: `sa`
  - Password: (empty)

## Building

```bash
./gradlew build
```

## Testing

```bash
./gradlew test
```

## Domain Model

### Trade Aggregate
Represents a buy or sell transaction with:
- Symbol (stock/crypto ticker)
- Type (BUY/SELL)
- Quantity and Price
- Execution timestamp
- Fees

### Portfolio Aggregate
Tracks holdings across all trades:
- Current positions (holdings by symbol)
- Cost basis calculation
- Realized/unrealized gains
