# QA Automation – API & UI Tests

Automation tests created as part of the QA Automation Engineer technical assignment.

The project contains:

- API automation for Swagger Petstore
- UI automation for Luminor financial reports

## Tech Stack

- Java 21
- Gradle
- JUnit 5
- REST Assured
- Jackson
- Playwright

## Project Structure

```text
src/
└── test/
    └── java/
        └── com/
            └── azad/
                ├── petstore/
                │   ├── api/
                │   ├── config/
                │   ├── data/
                │   └── tests/
                │
                └── luminor/
                    ├── config/
                    ├── pages/
                    └── tests/
```
# API Automation

- API tests use the public Swagger Petstore API. The suite covers the following CRUD flow:

1. Create a pet
2. Retrieve the created pet
3. Update the pet
4. Delete the pet & Verify the pet was deleted

The pet ID returned by the POST /pet response is extracted and reused by the subsequent GET, PUT, and DELETE requests.

The tests are intentionally ordered because they operate on the same pet created during the test run.

# UI Automation

UI automation uses Playwright and covers the financial reports flow on the Luminor website.

The test scenario:

1. Open the Luminor website
2. Accept cookies if displayed
3. Open the hamburger/site menu
4. Open the About Us section
5. Verify the About Us menu contains 11 items
6. Open Financial Reports
7. Verify the 2026 section is expanded
8. Verify the Q2 2026 financial report link is present

The UI test uses the Page Object Model to separate page interactions from test logic.

# Run all tests

```./gradlew clean test```

# Run API tests only 

```./gradlew test --tests '*PetCrudTest'```

# Run UI tests only

```./gradlew test --tests '*FinancialReportsTest'```

```PWDEBUG=1 ./gradlew test --tests '*FinancialReportsTest'``` Debug mode

# Test Report 

```build/reports/tests/test/index.html```




