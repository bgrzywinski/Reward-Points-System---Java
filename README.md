# Java Project - Reward Points System

A Java application for managing customer reward points, purchases, and redemptions.

## Project Structure

```
src/
├── app/              # Application entry point and menu
├── basic/            # Basic domain classes (Address, Currency, Tier, etc.)
├── business/         # Business logic (Customer, Transaction, Payment, etc.)
└── data/             # Data repository
```

## Features

- Customer management with tier-based rewards
- Purchase transactions that earn points
- Reward redemption system
- Transaction history tracking

## Getting Started

### Prerequisites

- Java JDK 8 or higher

### Running the Application

1. Compile the Java files:
   ```bash
   javac -d bin src/**/*.java
   ```

2. Run the application:
   ```bash
   java -cp bin src.app.Main
   ```

## License

This project is open source and available for educational purposes.


