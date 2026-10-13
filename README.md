# Iterative Socket Server

A Java client-server application demonstrating **iterative socket programming and client-server communication**.

The project consists of a server and client. The server processes client connections sequentially, handling one request at a time before accepting the next connection. The client can generate multiple requests and measure their turnaround times.

## Project Overview

The purpose of this project was to explore **iterative client-server architecture** and compare sequential request processing with a concurrent server implementation.

The server creates a `ServerSocket`, waits for a client connection, processes the client's request, sends the result back, closes the connection, and then waits for the next client.

Unlike a concurrent server, the iterative server does not create a separate handler thread for each client. Each request must be processed before the server moves on to the next connection.

## Technologies Used

* Java
* Socket Programming
* TCP/IP Networking
* Client-Server Architecture
* Multithreading for Client Testing
* Performance Measurement

## Features

### Iterative Server

The server uses Java's `ServerSocket` and `Socket` classes to establish network communication.

Client connections are handled sequentially. The server receives a connection, reads the request, executes the requested command, sends the output back to the client, and closes the connection before accepting another client.

### System Commands

The client can request several system operations from the server:

1. Date and Time
2. Uptime
3. Memory Use
4. Netstat
5. Current Users
6. Running Processes
7. Exit

The server executes the requested command and sends the output back to the client.

### Client Testing

The client allows the user to select the number of requests to send:

* 1
* 5
* 10
* 15
* 20
* 25

For testing purposes, the client creates a thread for each request and establishes a socket connection for each one.

### Performance Measurement

The client records the turnaround time for each request and calculates the total and average turnaround time after all requests have completed.

## How It Works

The key characteristic of the iterative server is that requests are handled **sequentially rather than concurrently**.

## Client Commands

| Command | Operation           |
| ------- | ------------------- |
| 1       | Date and Time       |
| 2       | System Uptime       |
| 3       | Memory Usage        |
| 4       | Network Connections |
| 5       | Current Users       |
| 6       | Running Processes   |
| 7       | Exit                |

The client validates that the selected command is between 1 and 7 before sending the request to the server.

## Running the Project

### 1. Compile the Java files

```bash
javac Server.java Client.java
```

### 2. Start the Server

```bash
java Server
```

The server will ask for a port number:

```text
Enter port number:
```

Enter the port you want the server to use.

### 3. Start the Client

Open another terminal and run:

```bash
java Client
```

The client will ask for:

```text
Please Enter Network Address:
Please Enter Port:
```

Enter the server's network address and the same port number used by the server.

### 4. Select a Command

The client displays the available commands:

```text
1 - Date and Time
2 - Uptime
3 - Memory Use
4 - Netstat
5 - Current Users
6 - Running Process
7 - Exit Program
```

### 5. Select the Number of Requests

Choose the number of requests to send:

```text
Options: 1, 5, 10, 15, 20, 25
```

The client will create and execute the requested number of client threads and record their turnaround times.

## Performance Results

The client calculates:

* Individual request turnaround time
* Total turnaround time
* Average turnaround time

These measurements can be used to evaluate the behavior of the iterative server when processing multiple requests.

## Iterative vs. Concurrent Server

This project can be compared with the concurrent socket server implementation.

| Feature              | Iterative Server          | Concurrent Server                 |
| -------------------- | ------------------------- | --------------------------------- |
| Client processing    | Sequential                | Concurrent                        |
| Server threads       | No client handler threads | One handler thread per connection |
| Multiple connections | Processed one at a time   | Can be processed simultaneously   |
| Architecture         | Iterative                 | Multithreaded                     |
| Main focus           | Sequential processing     | Concurrent processing             |

The concurrent version creates a `ClientHandler` thread for each incoming connection, whereas this iterative version processes the connection directly inside the server loop.

## Concepts Demonstrated

This project demonstrates:

* Client-server architecture
* TCP socket communication
* Java `ServerSocket`
* Java `Socket`
* Sequential request processing
* Network programming
* Multithreaded client testing
* Turnaround-time measurement
* Performance evaluation

## Project Structure

```text
Iterative-Socket-Server/
│
├── Server.java
├── Client.java
└── README.md
```

## Disclaimer

This project was developed for educational purposes to demonstrate socket programming, iterative server architecture, and client-server communication in Java.
