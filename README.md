# __PRODUCT SORTING__  project

Rest API based on a mavenized java backend project.

Provides an algorithm to sort a list of products based on currently 2 weighing criteria:
- adds the sales of each product
- adds all the stock items of each product

Exposed endpoint with no parameters or other requirements: `GET /`

## Instructions to compile and execute tests
```
mvn verify
```

## Instructions to run the application taking advantage of Spring Boot
```
mvn spring-boot:run
```

## Example of testing the API from command line
```
curl --request GET http://localhost:8080 | jq
```
