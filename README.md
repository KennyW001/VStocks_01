# Stock Trading Simulator

An end-to-end stock trading simulator built independently using **Java, JavaFX, PostgreSQL, and the Yahoo Finance API**. This was one of my first major software engineering projects and was developed entirely as a solo project.

The goal of the project was to create a simulated stock trading environment where users could create accounts, view real-time market data, manage a virtual portfolio, and simulate buying and selling stocks.

## Project Overview

The Stock Trading Simulator combines a graphical user interface, external financial data, and persistent database storage into a single application.

The current version is a **JavaFX application designed using Scene Builder**. It retrieves stock market information through API calls and presents the data through charts and other GUI components.

Users can create and manage accounts, with their information stored in a **PostgreSQL database**. The database keeps track of information such as account details, available virtual funds, stocks owned, and portfolio data.

## Features

* **User Account System**

  * Create and manage user accounts
  * Store user information in PostgreSQL
  * Persistent account data between sessions

* **Virtual Stock Portfolio**

  * Track available virtual funds
  * Track owned stocks
  * Store stock quantities and portfolio information
  * Simulate stock ownership and transactions

* **Real-Time Market Data**

  * Retrieves current stock information through the Yahoo Finance API
  * Displays market data within the application
  * Uses API data to populate stock charts and other visualizations

* **Graphical User Interface**

  * Built with JavaFX
  * UI designed with Scene Builder
  * Graphs and visual components for displaying stock information
  * Interactive application interface

* **Database Integration**

  * PostgreSQL database
  * Persistent storage of user and portfolio information
  * Java application communicates directly with the database

## Technologies Used

| Technology            | Purpose                         |
| --------------------- | ------------------------------- |
| **Java**              | Core application logic          |
| **JavaFX**            | Graphical user interface        |
| **Scene Builder**     | GUI design and layout           |
| **PostgreSQL**        | User and portfolio data storage |
| **Yahoo Finance API** | Real-time stock market data     |

## Development Process

This project was one of my first major projects in software engineering and was developed completely independently.

The original goal was to build the simulator as a **Spring Boot web application**. During development, I determined that building the full web application architecture would significantly increase the complexity of the project. I therefore shifted the implementation toward a JavaFX desktop application using Scene Builder.

This change allowed me to focus on the core functionality of the simulator while continuing to work with technologies such as APIs, databases, user accounts, and financial data.

The project provided hands-on experience with:

* Object-oriented programming in Java
* GUI application development
* REST/API integration
* Database design and SQL
* Connecting Java applications to PostgreSQL
* Persistent user data
* Working with external real-time data
* Application architecture and project organization
* Debugging and adapting a project when the original approach proved impractical

## Current Status

**Current status: Functional prototype / ongoing development**

The application currently has a working JavaFX interface, database-backed account system, portfolio data storage, stock market data retrieval, and graphical stock data visualization.

The project is still a work in progress, with additional functionality and improvements planned for future development.

## What I Learned

Developing this project independently gave me experience with the full development process, from designing the initial idea to implementing a working application and database.

One of the most valuable lessons was learning how to adapt when the original technical approach was not practical for the current scope of the project. Instead of abandoning the project, I changed the implementation from a Spring Boot web application to a JavaFX desktop application and continued developing the core trading functionality.

Working with this API was tricky at first since I was constrained to a limited amount of times I could ping the data, unless I get the paid package. Working under this limitation gave me insight into how to create and manage a system with limited resources.

This project served as an early foundation for my experience working with **Java, databases, APIs, graphical interfaces, and end-to-end application development**.

## Disclaimer
This application is a stock market simulation and does not execute real trades or handle real money. Market data is provided for simulation and educational purposes.
