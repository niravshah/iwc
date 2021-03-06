# Infinity Works

## FSA API Integration - Food Hygiene Rating Distribution By Authority 

---

### Running the Application - Using IDE

* The project has been implemented using Spring Boot and comes with a runnable main class - FSAIntegrationApplication. 
* On running the main method in this class, you should see the below line in the terminal

```
    Started FSAIntegrationApplication in 3.333 seconds (JVM running for 3.712)
```


* You should now be able to access the GUI through a browser using the below url 

```
    http://localhost:7070
```

---

### Running the Application - Using Executable Jar

The project can also be compiled into an executable jar as below. The 'mvn package' task creates the jar in the 'target' folder which can be run using the below command

```
    java -jar target/iwc-0.0.1-SNAPSHOT.jar
```

---

### Dependency Management

Dependency management is done using maven. UI Dependencies are managed using Web Jars through maven.

---

### Test Coverage

* Test coverage is provided using the Spring Boot Test Framework (SpringJUnit4ClassRunner) and Mockito (MockitoJUnitRunner). 
* Mocks are implemented using Mockito. Tests are written using BDD and AssertJ assertion library.
* FSA API mocks are implemented using Spring Rest Template support.
* Services and Repositories are covered using Unit Tests with Mocks
* Controllers are covered using end to end Integration Tests (without any mocking)
 
---

### REST API

The application currently exposes two REST endpoints

1 **Authorities Endpoint** (/api/authorities) - This endpoint accepts GET requests and exposes a list of all available authorities to the client in JSON format.

2 **Establishment Stats Endpoint** (/api/authority/{id}/stats) - Given an Authority ID, this endpoint exposes the FSA Hygiene Rating Distribution for that Authority in JSON format. This API internally implements paging to make paged requests to the FSA API as the number of Establishments can be quite large

Integration with the FSA API is done using Spring REST Template and Jackson Object Mappers

---

### Caching

* All calls to the external FSA API are cached using a basic Caching strategy defined in CacheConfiguration
* Authorities and Establishment responses are cached for 5 minutes.
* Cache is implemented using Google Guava
 
---

### User Interface

* The UI has been implemented using AngularJS and Spring Thymleaf templates.
* The UI starts with querying the REST API for 'Auhtorities' (Endpoint 1) above which is exposed as a dropdown available for the user to select an authority.
* Once the user selects an Authority, the UI makes a call to the 'Establishment Stats Endpoint' (Endpoint 2) above to get the Food Hygiene Rating Distribution for that Authority.
* The result is displayed as a Pie Chart (implemented using Angular Highcharts) and a Table format.

---

### Comments

This code tries to follow the paradigm of "Self Documenting Code" and Test Driven code explanations prescribed by Rob Martin in his Clean Code book and hence does not have a lot of explicit comments.
However, some of the more complicated methods Establishment Service are commented for further explanation.

---

### Author

Nirav Shah, 2016 - shah.nirav@me.com