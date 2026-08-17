# FP-Automation-Test-Framework

Automation test framework for Web UI and API testing using Java, Cucumber, Selenium WebDriver, and REST Assured.

---

## Table of Contents

- [Tools & Libraries](#tools--libraries)
- [Project Structure](#project-structure)
- [Step-by-Step Build Process](#step-by-step-build-process)
- [Test Scenarios](#test-scenarios)
- [How to Run Tests](#how-to-run-tests)
- [Test Reports](#test-reports)
- [CI/CD](#cicd)
- [Design Patterns](#design-patterns)

---

## Tools & Libraries

| Tool/Library | Version | Purpose |
|---|---|---|
| Java | 17 | Programming language |
| Gradle | 9.4.1 | Build tool & dependency management |
| Cucumber | 7.15.0 | BDD test framework (Gherkin format) |
| Selenium WebDriver | 4.27.0 | Web UI automation |
| WebDriverManager | 5.9.2 | Automatic ChromeDriver management |
| REST Assured | 5.4.0 | API testing |
| JUnit 4 | 4.13.2 | Test runner |
| Gson | 2.10.1 | JSON processing |
| GitHub Actions | - | CI/CD pipeline |

---

## Project Structure

```
automation-test-framework/
├── .github/
│   └── workflows/
│       └── test-automation.yml            # GitHub Actions CI workflow
├── app/
│   ├── build.gradle                       # Build configuration & dependencies
│   └── src/
│       └── test/
│           ├── java/com/automation/
│           │   ├── api/                   # === API TEST PACKAGE ===
│           │   │   ├── clients/
│           │   │   │   └── ApiClient.java
│           │   │   ├── runners/
│           │   │   │   └── ApiTestRunner.java
│           │   │   ├── stepdefinitions/
│           │   │   │   ├── ApiHooks.java
│           │   │   │   ├── CommonApiSteps.java
│           │   │   │   ├── TagSteps.java
│           │   │   │   └── UserSteps.java
│           │   │   └── utils/
│           │   │       └── ScenarioContext.java
│           │   └── web/                   # === WEB UI TEST PACKAGE ===
│           │       ├── pages/
│           │       │   ├── BasePage.java
│           │       │   ├── LoginPage.java
│           │       │   ├── ProductsPage.java
│           │       │   ├── ProductDetailPage.java
│           │       │   ├── CartPage.java
│           │       │   └── CheckoutPage.java
│           │       ├── runners/
│           │       │   └── WebTestRunner.java
│           │       ├── stepdefinitions/
│           │       │   ├── WebHooks.java
│           │       │   ├── LoginSteps.java
│           │       │   ├── ProductSteps.java
│           │       │   └── CartSteps.java
│           │       └── utils/
│           │           └── DriverFactory.java
│           └── resources/features/
│               ├── api/
│               │   ├── user.feature       # User CRUD API tests
│               │   └── tag.feature        # Tag API tests
│               └── web/
│                   ├── login.feature      # Login UI tests
│                   ├── products.feature   # Browsing UI tests
│                   └── cart.feature       # Cart + checkout UI tests
├── .gitignore
├── gradle.properties
├── settings.gradle
└── README.md
```

---

## Step-by-Step Build Process

Here are the detailed steps and commands executed in building this automation test framework:

### Step 1: Initialize Gradle Project

The project is initialized using Gradle with the `java-application` template:

```bash
gradle init --type java-application --dsl groovy
```

**Interactive prompts:**
```
Enter target Java version (min: 7, default: 21): 17
Project name (default: automation-test-framework): [Enter]
Select application structure (default: Single application project): [Enter]
Select test framework (default: JUnit Jupiter): [Enter]
Generate build using new APIs and behavior? (default: no): [Enter]
```

**After initialization, verify the initial build:**
```bash
./gradlew build
```

The initial structure creates an `app/` folder with `build.gradle` and standard source code (including `App.java` and `AppTest.java`).

### Step 2: Clean Initial Template

Remove unnecessary template files:

```bash
rm -rf app/src/main/java/org/example
rm -rf app/src/test/java/org/example
```

### Step 3: Configure `build.gradle` with All Dependencies

The `app/build.gradle` file is reconfigured (replace entire file contents) to add all required libraries. Then compile to ensure dependencies are downloaded:

```bash
./gradlew compileTestJava
```

```groovy
dependencies {
    // Cucumber (BDD framework for Gherkin)
    testImplementation 'io.cucumber:cucumber-java:7.15.0'
    testImplementation 'io.cucumber:cucumber-junit:7.15.0'

    // Selenium WebDriver (for Web UI testing)
    testImplementation 'org.seleniumhq.selenium:selenium-java:4.27.0'
    testImplementation 'io.github.bonigarcia:webdrivermanager:5.9.2'

    // REST Assured (for API testing)
    testImplementation 'io.rest-assured:rest-assured:5.4.0'

    // JUnit 4 (as test runner)
    testImplementation 'junit:junit:4.13.2'

    // Gson (for JSON processing)
    testImplementation 'com.google.code.gson:gson:2.10.1'
}
```

**Why JUnit 4?** Because Cucumber's `@RunWith(Cucumber.class)` runs more stably with JUnit 4 than JUnit 5 for this project.

### Step 4: Create Separate Folders/Packages for Web and API

Create all required directories in one command:

```bash
# Package for API tests
mkdir -p app/src/test/java/com/automation/api/clients
mkdir -p app/src/test/java/com/automation/api/runners
mkdir -p app/src/test/java/com/automation/api/stepdefinitions
mkdir -p app/src/test/java/com/automation/api/utils

# Package for Web UI tests
mkdir -p app/src/test/java/com/automation/web/pages
mkdir -p app/src/test/java/com/automation/web/runners
mkdir -p app/src/test/java/com/automation/web/stepdefinitions
mkdir -p app/src/test/java/com/automation/web/utils

# Feature files
mkdir -p app/src/test/resources/features/api
mkdir -p app/src/test/resources/features/web

# GitHub Actions
mkdir -p .github/workflows
```

**Resulting structure:**
```
com.automation/
├── api/
│   ├── clients/          -> REST Assured API client
│   ├── runners/          -> Cucumber runner for @api
│   ├── stepdefinitions/  -> Step definitions for API
│   └── utils/            -> Shared context (ScenarioContext)
└── web/
    ├── pages/            -> Page Object Model classes
    ├── runners/          -> Cucumber runner for @web
    ├── stepdefinitions/  -> Step definitions for Web
    └── utils/            -> WebDriver factory
```

### Step 5: Create Feature Files with Gherkin Format

Feature files are created in `app/src/test/resources/features/`. Each file starts with a `@api` or `@web` tag at the beginning of the feature.

**Example `api/user.feature`:**
```gherkin
@api
Feature: User API Operations on DummyAPI
  As an API consumer
  I want to perform CRUD operations on users

  Scenario: Get list of users
    Given the API base URL is set
    When I send a GET request to "/user?limit=1"
    Then the response status code should be 200
    And the response should contain a user with valid data

  Scenario: Create a new user
    Given the API base URL is set
    When I send a POST request to "/user/create" with body:
      """
      {
        "firstName": "John",
        "lastName": "Doe",
        "email": "johndoe@example.com",
        "phone": "+1234567890"
      }
      """
    Then the response status code should be 200
    And the response should contain the created user with firstName "John"
```

**Example `web/login.feature`:**
```gherkin
@web
Feature: Login Functionality on Demoblaze
  As a user
  I want to log in to my account

  Background:
    Given the user is on the Demoblaze homepage

  Scenario: Successful login with valid credentials
    When the user clicks the login link
    And the user enters username "testuser123" and password "Test@1234"
    And the user clicks the login button
    Then the user should be logged in successfully
```

**Important:** Each feature file must start with a `@api` or `@web` tag so it can be filtered by Gradle tasks.

### Step 6: Implement Page Object Model (POM) for Web UI

Page Object Model is used to separate HTML locators from step definitions.

**a. `DriverFactory.java`** - Thread-safe WebDriver management:
```java
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--no-sandbox",
                "--disable-dev-shm-usage", "--disable-gpu",
                "--window-size=1920,1080");
            driver.set(new ChromeDriver(options));
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
```

**b. `BasePage.java`** - Parent class for all page objects:
```java
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
```

**c. Page objects** (`LoginPage`, `ProductsPage`, `CartPage`, etc.) use `@FindBy` annotations for locators:
```java
public class LoginPage extends BasePage {
    @FindBy(id="name")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(xpath="//button[contains(text(),'Log in')]")
    private WebElement loginButton;

    public void login(String username, String password) {
        clickLoginLink();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
```

### Step 7: Implement API Client Pattern

All REST Assured API calls are consolidated into a single `ApiClient.java`:

```java
public class ApiClient {
    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = "63a804408eb0cb069b57e43a";

    private RequestSpecification getRequest() {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .header("app-id", APP_ID)
            .header("Content-Type", "application/json");
    }

    public Response get(String endpoint) {
        return getRequest().when().get(endpoint).then().extract().response();
    }

    public Response post(String endpoint, String body) {
        return getRequest().body(body).when().post(endpoint).then().extract().response();
    }

    public Response put(String endpoint, String body) {
        return getRequest().body(body).when().put(endpoint).then().extract().response();
    }

    public Response delete(String endpoint) {
        return getRequest().when().delete(endpoint).then().extract().response();
    }
}
```

**Authentication** is done via the `app-id` header on every request.

### Step 8: Implement Scenario Context for Sharing State

In Cucumber, each step is a separate method. We need to store the response from the "When" step so it can be accessed in the subsequent "Then" step. `ScenarioContext.java` uses `ThreadLocal`:

```java
public class ScenarioContext {
    private static final ThreadLocal<Response> response = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setResponse(Response res) { response.set(res); }
    public static Response getResponse() { return response.get(); }
    public static void setUserId(String id) { userId.set(id); }
    public static String getUserId() { return userId.get(); }
}
```

### Step 9: Implement Step Definitions

a. **API:** Create `CommonApiSteps.java` for steps used across multiple features (GET, POST, PUT, DELETE, status code check). The rest go in `UserSteps.java` and `TagSteps.java`.

```java
// CommonApiSteps.java - shared steps
public class CommonApiSteps {
    @Given("the API base URL is set")
    public void theApiBaseUrlIsSet() { ... }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        Response res = apiClient.get(endpoint);
        ScenarioContext.setResponse(res);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expected) {
        assertEquals(expected, ScenarioContext.getResponse().getStatusCode());
    }
}
```

b. **Web:** Create `WebHooks.java` for the shared `@Given("the user is on the Demoblaze homepage")` step. The rest go in `LoginSteps.java`, `ProductSteps.java`, and `CartSteps.java`.

**Verify compilation after all step definitions are created:**
```bash
./gradlew compileTestJava
```

### Step 10: Create Cucumber Test Runners

Two runners are created to separate test execution:

```java
// ApiTestRunner.java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/api",
    glue = {"com.automation.api.stepdefinitions"},
    plugin = {"pretty", "html:build/reports/cucumber/api-cucumber.html",
              "json:build/reports/cucumber/api-cucumber.json"},
    tags = "@api"
)
public class ApiTestRunner {}

// WebTestRunner.java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/web",
    glue = {"com.automation.web.stepdefinitions"},
    plugin = {"pretty", "html:build/reports/cucumber/web-cucumber.html",
              "json:build/reports/cucumber/web-cucumber.json"},
    tags = "@web"
)
public class WebTestRunner {}
```

### Step 11: Create 2 Gradle Tasks (@api and @web)

Two custom tasks are created in `build.gradle` with class filtering so only the appropriate runner is executed:

```groovy
tasks.register('runApiTests', Test) {
    description = 'Runs all Cucumber tests tagged with @api'
    group = 'verification'
    useJUnit()
    testClassesDirs = sourceSets.test.output.classesDirs
    classpath = sourceSets.test.runtimeClasspath
    include '**/ApiTestRunner.class'       // ONLY run ApiTestRunner

    systemProperty('cucumber.filter.tags', '@api')
    systemProperty('cucumber.glue', 'com.automation.api.stepdefinitions')
    systemProperty('cucumber.features', 'src/test/resources/features/api')
}

tasks.register('runWebTests', Test) {
    description = 'Runs all Cucumber tests tagged with @web'
    group = 'verification'
    useJUnit()
    testClassesDirs = sourceSets.test.output.classesDirs
    classpath = sourceSets.test.runtimeClasspath
    include '**/WebTestRunner.class'       // ONLY run WebTestRunner

    systemProperty('cucumber.filter.tags', '@web')
    systemProperty('cucumber.glue', 'com.automation.web.stepdefinitions')
    systemProperty('cucumber.features', 'src/test/resources/features/web')
}
```

**Important:** Without `include '**/ApiTestRunner.class'`, Gradle will run ALL test classes (including other runners), so the tag filter will not work correctly.

### Step 12: Run API Tests

Run API tests for the first time to discover issues:

```bash
./gradlew clean runApiTests
```

### Step 13: Configure Cucumber Reporting (HTML + JSON)

Reporting is configured in two places:

**a. In Cucumber Runner** (`plugin` attribute):
```java
plugin = {
    "pretty",                                              // console output
    "html:build/reports/cucumber/api-cucumber.html",       // HTML report
    "json:build/reports/cucumber/api-cucumber.json"        // JSON report
}
```

**b. In Gradle `test` task** (for `./gradlew test`):
```groovy
test {
    useJUnit()
    systemProperty('cucumber.plugin',
        'pretty, html:build/reports/cucumber/cucumber.html, json:build/reports/cucumber/cucumber.json')
}
```

**Verify reports are generated:**
```bash
./gradlew clean runApiTests
ls -la app/build/reports/cucumber/
# Should contain: api-cucumber.html, api-cucumber.json
```

### Step 14: Create GitHub Actions Workflow + GitHub Pages Deployment

Create `.github/workflows/test-automation.yml` with **3 jobs**: 2 for testing and 1 for deploying to GitHub Pages.

**Workflow:**
```
run-api-tests ──────┐
                     ├──> deploy-reports ──> GitHub Pages
run-web-tests ──────┘
```

**Jobs 1 & 2** run in parallel to execute tests. **Job 3** (`deploy-reports`) waits for both to complete, then:
- Downloads artifacts from both test jobs
- Generates `index.html` as the landing page
- Deploys to GitHub Pages

GitHub Pages always serves `index.html` as the main page. Therefore, an `index.html` landing page is needed so users can choose whether to view the API or Web UI report.

---

## Test Scenarios

### Web UI Tests (Target: https://www.demoblaze.com/)

| Feature | Scenario | Tag |
|---|---|---|
| `login.feature` | Successful login with valid credentials | @web |
| `login.feature` | Failed login with invalid credentials | @web |
| `login.feature` | Failed login with empty credentials | @web |
| `products.feature` | Verify products are displayed on homepage | @web |
| `products.feature` | View product details | @web |
| `products.feature` | Navigate to a specific product by name | @web |
| `cart.feature` | Add a product to cart | @web |
| `cart.feature` | View cart contents | @web |
| `cart.feature` | Complete checkout process | @web |

### API Tests (Target: https://dummyapi.io/docs)

| Feature | Scenario | Tag |
|---|---|---|
| `user.feature` | Get list of users | @api |
| `user.feature` | Get a single user by ID | @api |
| `user.feature` | Create a new user | @api |
| `user.feature` | Update an existing user | @api |
| `user.feature` | Delete a user by creating and deleting | @api |
| `user.feature` | Get user with invalid ID | @api |
| `tag.feature` | Get list of tags | @api |
| `tag.feature` | Get tags with page parameter | @api |

---

## How to Run Tests

### Prerequisites
- Java 17 or higher
- Google Chrome browser (for Web UI tests)
- Internet connection

### Run All Tests
```bash
./gradlew test
```

### Run Only API Tests (tag: @api)
```bash
./gradlew runApiTests
```

### Run Only Web UI Tests (tag: @web)
```bash
./gradlew runWebTests
```

### Run Tests via JUnit Runner directly
```bash
# API tests
./gradlew test --tests "com.automation.api.runners.ApiTestRunner"

# Web tests
./gradlew test --tests "com.automation.web.runners.WebTestRunner"
```

---

## Test Reports

After running tests, reports are generated in:
- **HTML Report**: `app/build/reports/cucumber/`
  - API: `api-cucumber.html`
  - Web: `web-cucumber.html`
- **JSON Report**:
  - API: `api-cucumber.json`
  - Web: `web-cucumber.json`

---

## CI/CD & GitHub Pages Deployment

### Workflow Structure

The GitHub Actions workflow (`.github/workflows/test-automation.yml`) has **3 jobs**:

```
run-api-tests ──────┐
                     ├──> deploy-reports ──> GitHub Pages
run-web-tests ──────┘
```

1. **`run-api-tests`** - Runs API tests, uploads report as artifact
2. **`run-web-tests`** - Runs Web UI tests, uploads report as artifact
3. **`deploy-reports`** - Downloads both reports, generates `index.html`, deploys to GitHub Pages

### Trigger

The workflow runs on:
- **Push** to branch `main`
- **Pull Request** to branch `main`
- **Manual trigger** via `workflow_dispatch`

### Setup GitHub Pages

To make reports accessible via GitHub Pages, follow these steps:

1. Open the repository on GitHub
2. Go to **Settings** > **Pages**
3. Under **Source**, select **GitHub Actions**
4. Push code or trigger the workflow manually
5. Reports will be available at: `https://azharanas17.github.io/FP-Automation-Test-Framework/`

### Landing Page (index.html)

Since GitHub Pages requires `index.html` as the entry point, the workflow automatically generates a landing page with links to both reports:

```
pages/
├── index.html            # Landing page with links to both reports
├── api-cucumber.html     # API test report
├── api-cucumber.json     # API test report (JSON)
├── web-cucumber.html     # Web UI test report
└── web-cucumber.json     # Web UI test report (JSON)
```

The landing page displays two cards:
- **API Tests** (`@api`) - link to `api-cucumber.html`
- **Web UI Tests** (`@web`) - link to `web-cucumber.html`

---
