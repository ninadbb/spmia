
# Spring Microservices POC | [![Build Status](https://www.travis-ci.com/ninadbb/spmia.svg?branch=master)](https://www.travis-ci.com/ninadbb/spmia)
- This project demonstrates Spring Microservices POC.
- EagleEye: The fictitious application, manages software licences used by different organization built using microservices principles.

## Software needed
- Maven
- Docker
- Git  

## Build Project
```
mvn clean package docker:build
```

## Run Project on local
```
docker-compose -f docker/common/docker-compose.yml up
```

## Micro Services
- A Spring Cloud based OAuth2 authentication service that can issue and validate OAuth2 tokens.
- A Spring Cloud Config server that is deployed as Docker container and can manage a services configuration information using a file system or GitHub-based repository.
- A Eureka server running as a Spring-Cloud based service. This service will allow multiple service instances to register with it. Clients that need to call a service will use Eureka to lookup the physical location of the target service.
- A Zuul API Gateway. All of our microservices can be routed through the gateway and have pre, response and post policies enforced on the calls.
- A Zipkin server for visualizing distributed tracing
- A organization service that will manage organization data used within EagleEye.
- A licensing service that will manage licensing data(licenses for organizations) used within EagleEye.
- A Postgres SQL database used to hold the data for these two services.
- A Kafka message bus to transport messages between services.
- A Redis service to act as a distributed cache.

## CI/CD
- Travis used for CI.
- when code pushed to the repo, it will trigger build on the travis CI and push docker build image to the docker hub 
 
### Travis setup to encrypt environment variables
```
#Install Travis CLI
brew install travis

#Run in project directory as those are project specific

#login
travis login --pro --auto
OR
travis login --pro

# Encrypt environement variables
travis encrypt --pro DOCKER_USERNAME="docker_username" --add env.global
```