# Discord Clone - Backend

This repository contains the backend API for a **Discord clone** application, built with **Spring Boot**. It powers the core functionalities of the app, including direct messaging (DM) with real-time communication via WebSocket.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)

## Features

- **Direct Messaging (DM):** Real-time one-to-one messaging using WebSocket protocol.
- **User Management:** APIs for user registration and authentication (planned).
- **WebSocket Implementation:** Supports real-time message delivery in direct messaging.
- **Scalability:** Built with extensibility and scalability in mind to handle additional features.

## Tech Stack

- **Backend Framework:** Spring Boot
- **WebSocket:** For real-time communication in DM conversations
- **Database:** (e.g., MySQL) – configurable in application properties

## Getting Started

### Prerequisites

- **Java 11** or higher
- **Maven** (for building the project)
- A database (e.g., MySQL, PostgreSQL)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/rudrasama/discwords-backend.git
   cd discwords-backend
   ```

2. Install dependencies and build the project:

   ```bash
   mvn clean install
   ```

### Configuration

1. Set up your database and update the configurations in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/discord_clone
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

### Usage

1. Start the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

2. The backend server will start on [http://localhost:8080](http://localhost:8080).

