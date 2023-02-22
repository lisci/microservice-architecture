# Spring Gateway Microservice Architecture
### Description

This is a Microservices project which goal is give a simple example how to implement gateway pattern. It's intent is to showcase the limitations of using blocking calls in a distributed environment such as that of microservices.


The approach used for the implementation of this pattern is through Spring Cloud which gives us the possibility to use Spring Cloud Gateway.\
For blocking calls, however, OpenFeign is used which allows us to integrate a non-reactive strategy, so for each REST call made on the various endpoints, a thread will be assigned as a resource, making the implementation of this strategy onerous in the long term. Until the REST call resolves, the thread will not be freed. Suppose we have a spike in requests in a certain time slot. Using this strategy you will hit a bottleneck very soon if resources are limited.

Being a microservices project, it will include:
* Performance monitoring system of the various services distributed through Zipkin
* Registration system of services distributed via Eureka, necessary for the Gateway
* Basic security via Gateway
* Health monitoring system and various metrics via Grafana
* Use of Docker containers for each architectural service

## Architecture
![architecture image](https://github.com/lisci/microservice-architecture/blob/testing/documentation/NoReactiveArchitecture.svg?raw=true)

## Notes
This is a work in progress project developed in the free time

