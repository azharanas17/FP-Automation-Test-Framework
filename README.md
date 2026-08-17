# FP-Automation-Test-Framework

init with:
> gradle init --type java-application  --dsl groovy
    Starting a Gradle Daemon (subsequent builds will be faster)

    Enter target Java version (min: 7, default: 21): 17

    Project name (default: automation-test-framework): 

    Select application structure:
    1: Single application project
    2: Application and library project
    Enter selection (default: Single application project) [1..2] 

    Select test framework:
    1: JUnit 4
    2: TestNG
    3: Spock
    4: JUnit Jupiter
    Enter selection (default: JUnit Jupiter) [1..4] 

    Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] 


    > Task :init
    Learn more about Gradle by exploring our Samples at https://docs.gradle.org/9.4.1/samples/sample_building_java_applications.html

    BUILD SUCCESSFUL in 1m 29s
    1 actionable task: 1 executed

> ./gradlew build
    Starting a Gradle Daemon, 1 incompatible Daemon could not be reused, use --status for details
    Calculating task graph as no cached configuration is available for tasks: build

    BUILD SUCCESSFUL in 49s
    7 actionable tasks: 7 executed
    Configuration cache entry stored.