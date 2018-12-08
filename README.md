# Future Gadget Parking Lot

The Future Gadget Parking Lot Application is a web-based application which provides the ability to create, retrieve, and rate parking lot tickets. The current version provides support for
the tracking of multiple parking lots and pricing schemes.

Future Gadget Parking Lot is a Maven based Project utilising the Java Spring framework with Resteasy Jax-rs services which consume and produce JSON payloads.

## Getting Started

First, clone the Future Gadget Parking Lot repository from Github to a location of your choice.

`$ git clone https://github.com/Ne0kropolis/FutureGadgetParkingLot`

next, using your preferred IDE or maven-enabled CLI run the following maven goal:

`mvn tomcat7:run`

verify the application is running by visiting

`http://localhost:8080/tickets/all`

## Builds & Goals

### Maven

The project can be built using typical maven build goals such as

`mvn clean compile package`

### Flyway

If using an external database for flyway integration the flyway:migrate goal can be run with the appropriate versioning files to build the base schema and insert dummy testing data

`mvn flyway:migrate`

In the event of a database corruption the flyway:clean goal can be used to undo an remaining version migration issues

`mvn flyway:clean`

### Tomcat

An embedded tomcat server can be run with the tomcat7:run goal

`mvn tomcat7:run`

### Automated Testing

Automated tests can be triggered with mvn:test

`mvn:test`

These include unit tests for the domain Entities, DAOs, Services, and Controllers.

### CI

https://travis-ci.com/Ne0kropolis/FutureGadgetParkingLot

### Codestyle Checks

Caodestyle checks can be run with

`mvn Checkstyle:Checkstyle`

and

`mvn pmd:check`

Checkstyle currently runs the Sun default codestyle checkstyle.xml schemas

# HttpRequests

This section contains a comprehensive list of the possible application URI's and requests.

## Pricing

### @GET

`http://localhost:8080/pricing/all`

retrieves a list of all pricing objects from the database

`http://localhost:8080/pricing/{id}`

retrieves a single pricing object with a given pricing Id.

`http://localhost:8080/pricing/scheme/{id}`

retrieves a list of all pricing objects with a given pricing scheme number.

### @POST

`http://localhost:8080/pricing/create/json`

creates a pricing object from a given json object
e.g



 	"pricingId": 1,
 	"pricingSchemeNumber": 1,
 	"duration": 10,
 	"granularity": "M",
 	"price": 10
 

creates a list of pricing objects from a given json.
 
`http://localhost:8080/pricing/create/list/json`

### @PUT

updates a pricing object from a given json.

`http://localhost:8080/pricing/update/json`

### @Delete

deletes a pricing object with a given id from the database.

`http://localhost:8080/pricing/delete/{id}`

## Lot

### @GET

`http://localhost:8080/lot/all`

retrieves a list of all lot objects from the database

`http://localhost:8080/lot/{id}`

retrieves a single lot object with a given pricing Id.

### @POST

`http://localhost:8080/lot/create/json`

creates a lot object from a given json object
e.g



 	"lotId": 100,
 	"pricingSchemeNumber": 1,
 	"lotName": "Mulholland Drive",
 	"lotAddress": "Hollywood",
 	"lotCapactiy": 100
 

creates a list of lot objects from a given json.
 
`http://localhost:8080/lot/create/list/json`

### @PUT

updates a lot object from a given json.

`http://localhost:8080/lot/update/json`

updates the capacity of a lot with a given id

`http://localhost:8080/lot/query/capacity?id=value&capacity=value`

### @Delete

deletes a lot object with a given id from the database.

`http://localhost:8080/pricing/delete/{id}`

## Ticket

Ticket times are processed as Timestamps. A ticket can be created without a time_out or price parameter. These tickets may have their prices calculated individually or as a batch at a later time.

### @GET

`http://localhost:8080/tickets/all`

retrieves a list of all ticket objects from the database.

`http://localhost:8080/tickets/{id}`

retrieves a single ticket object with a given pricing Id.

`http://localhost:8080/tickets/outstanding`

retrieves a list of all tickets from the database and processes them for outstanding fees.

`http://localhost:8080/tickets/outstanding/{id}`

retrieves a single ticket from the database and process it for any outstanding fee.

### @POST

`http://localhost:8080/tickets/create/json`

creates a ticket object from a given json object
e.g


    "ticketId": 1,
	"lotId": 100,
	"timeIn": 1542909631000,
	"timeOut": 1542911911000,
	"lost": false
 

creates a list of ticket objects from a given json.
 
`http://localhost:8080/tickets/create/list/json`

### @PUT

updates a ticket object from a given json.

`http://localhost:8080/tickets/update/json`


### @Delete

deletes a ticket object with a given id from the database.

`http://localhost:8080/pricing/delete/{id}`

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgements

The following resources were utilised in the making of this project:

- https://www.mkyong.com/spring/curl-post-json-data-to-spring-rest/

- https://www.baeldung.com/java-date-difference

- https://spring.io/

- http://www.programming-free.com/2014/01/spring-mvc-40-restful-web-services.html

- https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-controller

- https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#spring-web

- https://www.mkyong.com/webservices/jax-rs/integrate-jackson-with-resteasy/

- https://medium.com/salisuwy/building-a-spring-boot-rest-api-part-iii-integrating-mysql-database-and-jpa-81391404046a

- https://howtodoinjava.com/best-practices/how-you-should-unit-test-dao-layer/

- http://www.vogella.com/tutorials/JUnit/article.html

- https://www.baeldung.com/persistence-layer-with-spring-and-hibernate

- https://www.baeldung.com/simplifying-the-data-access-layer-with-spring-and-java-generics

- https://www.mkyong.com/spring/maven-spring-jdbc-example/

- https://www.journaldev.com/2552/spring-rest-example-tutorial-spring-restful-web-services

- https://www.linkedin.com/learning/restful-service-with-jax-rs-2-0?u=26889562

- https://www.tutorialspoint.com/spring/spring_jdbc_framework.htm

