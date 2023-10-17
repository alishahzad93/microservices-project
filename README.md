# microservices-project
Currency conversion project using microservices, spring cloud, Resilience4j, Eureka and JPA
URLs to hit the microservices:
Eureka: http://localhost:8761/
Currency Exchange: http://localhost:8000/currency-exchange/from/USD/to/INR/
Currency Conversion: http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/30
API Gateway: http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/30

Docker Images:
Currency Excange Service: docker.io/alishahzad93/ccmp-currency-exchange-service:0.0.1-SNAPSHOT 
