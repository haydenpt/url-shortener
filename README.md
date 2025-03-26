# URL Shortener Project

## 1. Requirements

### Functional Requirements:
* Convert long URLs into short URLs. 
* Retrieve the original URL when a short URL is accessed.
* Handle high read traffic efficiently (many people accessing short links). 
* Handle custom short URLs. 
* Track click analytics. 
* Expire URLs after a certain period (optional).

### Non-functional Requirements:
* High availability: Users should always be able to access URLs.
* Low latency: URL redirection should be fast.
* Scalability: Should handle millions of requests per second.
* Consistency vs. Availability: Prioritize availability over strict consistency.

## 2. High-Level Design

### API Layer:
* **POST** /shorten: Takes a long URL and returns a short URL.
* **GET** /{shortURL}: Redirects to the original URL.

### Database/Storage:

* Store the mapping between short URLs and long URLs.
* Store click analytics data.
* Store user data.
* Support read-heavy workloads efficiently by using MongoDB.
* Use Kafka to handle high write traffic for analytics data.

### Encoding System:
* Generate a short unique identifier using Base62 encoding.
* Use hashing with salt (SHA-256) to create a unique key with minimal collision.

### Cache Layer:
* Use Redis or Memcached to store frequently accessed URLs.

### Load Balancer & Web Servers:
* Nginx Load Balancer to distribute traffic.

### Analytics System:
* Track the number of clicks, IP addresses, user agents, geolocation.

## 3. URL Generation Strategy

* Hashing: Hash the original URL using MD5/SHA-256, then take the first 6-8 characters.

    `Example: SHA-256("https://example.com") → 1a2b3c`

## 4. Database Design
* **NoSQL Database** (e.g., MongoDB or Cassandra) for URL creation and access:

  * Handles the high-throughput, write-heavy operations required for shortening URLs and redirecting users quickly.
  * Optimized for low-latency, fast access to data, and scalability.
  * Relational Database (e.g., PostgreSQL or MySQL) for Analytics:


* **PostgreSQL** to store user data, click analytics, and URL statistics:
  * Stores data for tracking and analyzing the URL shortening system (e.g., number of accesses, user interactions, click analytics).
  * Uses SQL queries to run reports and detailed analysis on usage patterns.


* **Message Queue** to queue the write requests:
  * Acts as a buffer between the URL shortening (NoSQL) database and the relational database.
  * Ensures that data is written to both systems in an asynchronous manner without blocking the URL shortening process.
  * Decouple the write-heavy operations (NoSQL) from analytics processing (relational DB), ensuring system reliability and scalability.


### References:
https://medium.com/javarevisited/day-1-high-level-system-design-series-url-shortening-d28888d71084


## 5. How to Run
### Prerequisites
* Ensure you have Docker installed on your system.
* Ensure you have Maven installed and configured in your system's PATH.
* Steps to Run
* Clone the Repository

### Build the Project:
```bash
    cd url-shortener
    mvn clean install
    cd ../url-analytics
    mvn clean install
```

### Go back to the root directory:
```bash
    cd ..
```

### Build and Run the Docker Containers:
```bash
    ./build_and_run.sh
```

Access the Application:  
* URL Shortener Service: http://localhost:8080
* **POST** /shorten: Takes a long URL and returns a short URL.
* **GET** /{shortURL}: Redirects to the original URL.
