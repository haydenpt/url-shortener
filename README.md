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
* POST /shorten: Takes a long URL and returns a short URL.
* GET /{shortURL}: Redirects to the original URL.
* GET /stats/{shortURL} (optional): Fetches analytics for a URL.

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

# 3. URL Generation Strategy

* Hashing: Hash the original URL using MD5/SHA-256, then take the first 6-8 characters.

    `Example: SHA-256("https://example.com") → 1a2b3c`


# 4. Database Design
  - ...
  - ...


### References:
https://medium.com/javarevisited/day-1-high-level-system-design-series-url-shortening-d28888d71084