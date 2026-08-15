# Petstore API Automation

API automation tests for Swagger Petstore.

## Tech Stack

- Java 21
- Gradle
- JUnit 5
- RestAssured
- Jackson

## Tests

The suite covers:

1. Create a pet
2. Retrieve the created pet
3. Update the pet
4. Delete the pet
5. Verify the pet was deleted

The pet ID is extracted from the POST response and reused by
the subsequent GET, PUT and DELETE operations.
The CRUD tests are intentionally ordered because they operate on the same pet created during the test run. 
The ID returned by POST is reused by subsequent operations.

## Run tests (both UI and API)

```bash
./gradlew clean test
```

# Test Report

After execution, the Gradle HTML report is available at:

```build/reports/tests/test/index.html```

# To run specific tests

```./gradlew test --tests '*FinancialReportsTest'```
```./gradlew test --tests '*PetCrudTest'```

# To run UI Tests in Debug mode

```PWDEBUG=1 ./gradlew test --tests '*FinancialReportsTest'```


