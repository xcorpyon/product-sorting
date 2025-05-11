# __PRODUCT SORTING__  project

Rest API based on a mavenized java backend project.

Provides an algorithm to sort a list of products based on currently 2 sorting criteria:
- unit sales: multiply unit sales of each product by the request parameter indicated with 'US'
- stock ratio: multiply sizes available of each product by the request parameter indicated with 'SR'

Exposed endpoint: `GET /products`

## External libraries used
- Spring Boot
- Spring MVC
- Lombok
- MapStruct

## Additional considerations about the project
- It is designed using hexagonal architecture.
- It is prepared to sort whatever item, not just products.
- It contains an @Advice to handle bad requests (related to request params).
- It uses immutable objects broadly.
- It contains 1 unit test and 1 integration test.

## Instructions to compile and execute tests
```
mvn verify
```

## Instructions to run the application
```
mvn spring-boot:run
```

## Example of testing the API from command line
```
curl --request GET 'http://localhost:8080/products?sortingCriteriaWeight=SR-1000&sortingCriteriaWeight=US-50' | jq
```
