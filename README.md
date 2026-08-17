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
│       ├── main/java/com/automation/      # Main source (empty)
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

Berikut adalah langkah-langkah detail beserta command yang dijalankan dalam pembuatan automation test framework ini:

### Step 1: Inisialisasi Proyek Gradle

Proyek diinisialisasi menggunakan Gradle dengan template `java-application`:

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

**Setelah inisialisasi, verifikasi build awal:**
```bash
./gradlew build
```

Struktur awal menghasilkan folder `app/` dengan `build.gradle` dan source code standar (termasuk `App.java` dan `AppTest.java`).

### Step 2: Bersihkan Template Awal

Hapus file template yang tidak diperlukan:

```bash
rm -rf app/src/main/java/org/example
rm -rf app/src/test/java/org/example
```

### Step 3: Konfigurasi `build.gradle` dengan Semua Dependencies

File `app/build.gradle` dikonfigurasi ulang (replace seluruh isi file) untuk menambahkan semua library yang dibutuhkan. Kemudian compile untuk memastikan dependency ter-download:

```bash
./gradlew compileTestJava
```

```groovy
dependencies {
    // Cucumber (BDD framework untuk Gherkin)
    testImplementation 'io.cucumber:cucumber-java:7.15.0'
    testImplementation 'io.cucumber:cucumber-junit:7.15.0'

    // Selenium WebDriver (untuk Web UI testing)
    testImplementation 'org.seleniumhq.selenium:selenium-java:4.27.0'
    testImplementation 'io.github.bonigarcia:webdrivermanager:5.9.2'

    // REST Assured (untuk API testing)
    testImplementation 'io.rest-assured:rest-assured:5.4.0'

    // JUnit 4 (sebagai test runner)
    testImplementation 'junit:junit:4.13.2'

    // Gson (untuk JSON processing)
    testImplementation 'com.google.code.gson:gson:2.10.1'
}
```

**Mengapa memilih JUnit 4?** Karena Cucumber `@RunWith(Cucumber.class)` berjalan lebih stabil dengan JUnit 4 dibandingkan JUnit 5 untuk proyek ini.

### Step 4: Membuat Folder/Package Terpisah untuk Web dan API

Buat semua direktori yang dibutuhkan dalam satu command:

```bash
# Package untuk API tests
mkdir -p app/src/test/java/com/automation/api/clients
mkdir -p app/src/test/java/com/automation/api/runners
mkdir -p app/src/test/java/com/automation/api/stepdefinitions
mkdir -p app/src/test/java/com/automation/api/utils

# Package untuk Web UI tests
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

**Hasil struktur:**
```
com.automation/
├── api/
│   ├── clients/          -> REST Assured API client
│   ├── runners/          -> Cucumber runner untuk @api
│   ├── stepdefinitions/  -> Step definitions untuk API
│   └── utils/            -> Shared context (ScenarioContext)
└── web/
    ├── pages/            -> Page Object Model classes
    ├── runners/          -> Cucumber runner untuk @web
    ├── stepdefinitions/  -> Step definitions untuk Web
    └── utils/            -> WebDriver factory
```

### Step 5: Membuat Feature Files dengan Gherkin Format

Feature files dibuat di `app/src/test/resources/features/`. Setiap file diawali dengan tag `@api` atau `@web` di awal feature.

**Contoh `api/user.feature`:**
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

**Contoh `web/login.feature`:**
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

**Penting:** Setiap feature file harus diawali dengan tag `@api` atau `@web` agar bisa difilter oleh Gradle tasks.

### Step 6: Implementasi Page Object Model (POM) untuk Web UI

Page Object Model digunakan untuk memisahkan locator HTML dari step definitions.

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

**b. `BasePage.java`** - Parent class untuk semua page objects:
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

**c. Page objects** (`LoginPage`, `ProductsPage`, `CartPage`, dll) menggunakan `@FindBy` annotations untuk locators:
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

### Step 7: Implementasi API Client Pattern

Semua panggilan API REST Assured dikonsolidasi ke dalam satu `ApiClient.java`:

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

**Authentication** dilakukan via header `app-id` pada setiap request.

### Step 8: Implementasi Scenario Context untuk Sharing State

Dalam Cucumber, setiap step adalah method terpisah. Kita perlu menyimpan response dari step "When" agar bisa diakses di step "Then" berikutnya. `ScenarioContext.java` menggunakan `ThreadLocal`:

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

### Step 9: Implementasi Step Definitions

a. **API:** Buat `CommonApiSteps.java` untuk step yang dipakai di banyak feature (GET, POST, PUT, DELETE, status code check). Sisanya di `UserSteps.java` dan `TagSteps.java`.

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

b. **Web:** Buat `WebHooks.java` untuk shared `@Given("the user is on the Demoblaze homepage")` step. Sisanya di `LoginSteps.java`, `ProductSteps.java`, dan `CartSteps.java`.

**Verifikasi compilation setelah semua step definitions dibuat:**
```bash
./gradlew compileTestJava
```

### Step 10: Membuat Cucumber Test Runners

Dua runner dibuat untuk memisahkan eksekusi test:

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

### Step 11: Membuat 2 Gradle Tasks (@api dan @web)

Dua custom tasks dibuat di `build.gradle` dengan class filtering agar hanya runner yang sesuai yang dijalankan:

```groovy
tasks.register('runApiTests', Test) {
    description = 'Runs all Cucumber tests tagged with @api'
    group = 'verification'
    useJUnit()
    testClassesDirs = sourceSets.test.output.classesDirs
    classpath = sourceSets.test.runtimeClasspath
    include '**/ApiTestRunner.class'       // HANYA jalankan ApiTestRunner

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
    include '**/WebTestRunner.class'       // HANYA jalankan WebTestRunner

    systemProperty('cucumber.filter.tags', '@web')
    systemProperty('cucumber.glue', 'com.automation.web.stepdefinitions')
    systemProperty('cucumber.features', 'src/test/resources/features/web')
}
```

**Penting:** Tanpa `include '**/ApiTestRunner.class'`, Gradle akan menjalankan SEMUA test classes (termasuk runner lainnya), sehingga tag filter tidak berfungsi dengan benar.

### Step 12: Jalankan API Tests

Jalankan API tests pertama kali untuk menemukan masalah:

```bash
./gradlew clean runApiTests
```

### Step 13: Konfigurasi Cucumber Reporting (HTML + JSON)

Reporting dikonfigurasi di dua tempat:

**a. Di Cucumber Runner** (`plugin` attribute):
```java
plugin = {
    "pretty",                                              // console output
    "html:build/reports/cucumber/api-cucumber.html",       // HTML report
    "json:build/reports/cucumber/api-cucumber.json"        // JSON report
}
```

**b. Di Gradle `test` task** (untuk `./gradlew test`):
```groovy
test {
    useJUnit()
    systemProperty('cucumber.plugin',
        'pretty, html:build/reports/cucumber/cucumber.html, json:build/reports/cucumber/cucumber.json')
}
```

**Verifikasi report ter-generate:**
```bash
./gradlew clean runApiTests
ls -la app/build/reports/cucumber/
# Harus ada: api-cucumber.html, api-cucumber.json
```

### Step 14: Membuat GitHub Actions Workflow + GitHub Pages Deployment

Buat file `.github/workflows/test-automation.yml` dengan **3 jobs**: 2 untuk testing dan 1 untuk deploy ke GitHub Pages.

**Alur kerja:**
```
run-api-tests ──────┐
                     ├──> deploy-reports ──> GitHub Pages
run-web-tests ──────┘
```

**Job 1 & 2** berjalan paralel untuk menjalankan tests. **Job 3** (`deploy-reports`) menunggu keduanya selesai, lalu:
- Download artifacts dari kedua test jobs
- Generate `index.html` sebagai landing page
- Deploy ke GitHub Pages

GitHub Pages selalu men serve `index.html` sebagai halaman utama. Sehingga perlu adanya `index.html` sebagai landing page, agar user bisa memilih mau melihat report API atau Web UI.

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

Setelah menjalankan tests, reports akan di-generate di:
- **HTML Report**: `app/build/reports/cucumber/`
  - API: `api-cucumber.html`
  - Web: `web-cucumber.html`
- **JSON Report**:
  - API: `api-cucumber.json`
  - Web: `web-cucumber.json`

---

## CI/CD & GitHub Pages Deployment

### Workflow Structure

GitHub Actions workflow (`.github/workflows/test-automation.yml`) memiliki **3 jobs**:

```
run-api-tests ──────┐
                     ├──> deploy-reports ──> GitHub Pages
run-web-tests ──────┘
```

1. **`run-api-tests`** - Menjalankan API tests, upload report sebagai artifact
2. **`run-web-tests`** - Menjalankan Web UI tests, upload report sebagai artifact
3. **`deploy-reports`** - Download kedua reports, generate `index.html`, deploy ke GitHub Pages

### Trigger

Workflow berjalan pada:
- **Pull Request** ke branch `main`
- **Manual trigger** via `workflow_dispatch`

### Setup GitHub Pages

Agar reports bisa diakses via GitHub Pages, lakukan langkah berikut:

1. Buka repository di GitHub
2. Go to **Settings** > **Pages**
3. Di bagian **Source**, pilih **GitHub Actions**
4. Push code atau trigger workflow secara manual
5. Reports akan tersedia di: `https://<username>.github.io/<repo-name>/`

### Landing Page (index.html)

Karena GitHub Pages membutuhkan `index.html` sebagai entry point, workflow secara otomatis generate landing page yang berisi link ke kedua reports:

```
pages/
├── index.html            # Landing page dengan link ke kedua reports
├── api-cucumber.html     # API test report
├── api-cucumber.json     # API test report (JSON)
├── web-cucumber.html     # Web UI test report
└── web-cucumber.json     # Web UI test report (JSON)
```

Landing page menampilkan dua kartu:
- **API Tests** (`@api`) - link ke `api-cucumber.html`
- **Web UI Tests** (`@web`) - link ke `web-cucumber.html`

---

## Design Patterns

### Page Object Model (POM)
Setiap halaman web diwakili oleh sebuah class dengan:
- `@FindBy` annotated WebElement locators
- Methods yang meng encapsulate page interactions
- `BasePage` sebagai parent class dengan shared WebDriver dan WebDriverWait

### API Client Pattern
REST Assured calls dikonsolidasi ke `ApiClient.java` dengan:
- Pre-configured base URL dan authentication headers
- Methods GET, POST, PUT, DELETE
- App-ID dikirim via header (`63a804408eb0cb069b57e43a`)

### Scenario Context Pattern
`ScenarioContext.java` menggunakan `ThreadLocal` untuk sharing state antar step definitions secara thread-safe.
