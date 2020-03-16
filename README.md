# Test Automation Framework Sample
##### The Test Automation Framework is developed using next technologies:

- Java 8.
- Maven.
- Spring Boot.
- TestNG as an assertion library.
- RestAssured for testing and validation REST APIs.
- Jsch for working with SFTP servers.
- OpenCSV for reading and writing CSV files.
- Awaitility for asynchronous actions.
- Allure for reporting. 
- Ojdbc for Oracle connection.
- JavaFaker for creating pretty random data.

And next plugins :

- Maven surefire plugin.
- Maven compiler plugin.

##TAF Features overview:

1. Failed tests are rerun 2 times (except for the ones which are annotated with @Bug) ;

2. Test annotations.

- Each test method has next mandatory annotations :

```@Group``` - contains the name of the test group(s) to which the test belongs;

```@Tag``` - contains  characteristic tags which describe the test (ex. if the test checks BMDF file layout then the tags on it can be BMDF, BMDF_LAYOUT, BMDF_VALIDATION and so on);

```@Test``` - contains  short test name and its test case ID.

```@Description``` - contains detailed test description. This test description is displayed in the test report.

- Each test method can have next optional annotations :

```@Bug``` - annotates the test methods which determines a bug(s) (even though it's not reproduces right now). The test methods with @Bug annotation will not be rerun more than one time.

```@DeprecatedTest``` - annotates deprecated test methods. The test methods with @DeprecatedTest annotation are excluded from a test run.

```@PendingFix``` - annotates the test methods with a defect in the test. The test methods with @PendingFix annotation are excluded from a test run.

### Test Execution

  Launch examples.

Running tests from command line :
Test cases can be run with next options :

Running test cases by a test group
Running test cases by a tag
Running all existing test cases
 

 General Options:



```-Denv={environment}```  -  environment on which tests should be run.

```-Dgroups={test_group}```  -  tests of which group should be run.

```-Dgroups={tag}```  -  tests with the {tag} will be run.

 

Next command will run all the tests from SMOKE test group on QA environment :

```mvn test -Denv = qa -Dgroups = SMOKE```



Next command will run all the tests which are tagged with #generic_data test group on QA environment :

```mvn test -Denv = qa -Dgroups = #fature_1```



Next command will run all the tests on QA environment :

```mvn test -Denv = qa```


Running tests in IDE :
To define environment on which test(s) should be run set it in <env>{environment}</env> property in maven-surefire-plugin section in pom.xml

Example for QA environment: 

```
<systemPropertyVariables>
       <env>qa</env>
</systemPropertyVariables>
```


By default it's set to INTE environment.

### Reporting

Test result report is generated in target/allure-results folder by default .

All the needed objects and files are attached to the report for the appropriate step (ex. the step 'Add an object to collection' has inserted object in the attachment and so on).


 - To view test result report run next command in terminal :

```allure serve target/allure-results```

 - To generate test result report to {outputFolder} run next command in terminal :

```allure generate target/allure-results o {outputFolder}   --clean```