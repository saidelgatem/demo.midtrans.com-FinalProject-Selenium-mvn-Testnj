# TestNG_Maven-demo.midtrans.com

## Technology used:
- Tool: Selenium
- Language: Java
- IDE: Intellij IDEA
- Build tool: Maven
- Framework: TestNG

## Prerequisite:
1. Need to install jdk 19 and configure it into Environment variable
2. Download maven and configure it into Environment variable also

## Run the automation script:
1. Open the project with Intellij IDEA by clicking on pom.xml
2. Let the Intellij IDEA to download the dependencies for the first run
3. After dependencies are downloaded and project loaded successfully, click on Terminal from the bottom bar
4. To run "regression" or "smoke" test, just go to "testng.xml" file and replace the word from "regression" to "smoke" or vice versa
```sh
<groups>
  <run>
     <include name = "regression"></include>
  </run>
</groups>
```
5. Right click on "testng.xml" file and click on "Run" or Type in the terminal:
```sh
mvn clean test
```
6. Selenium will open the browser and start automation.
7. To view report of the automation execution, expand the target->surefire-reports folder and open "index.html" in a browser
```sh
```
## Test run report
![image](https://github.com/saidelgatem/demo.midtrans.com-FinalProject-Selenium-mvn-Testnj/blob/Project1/target/surefire-reports/IntelIJ%20Repport.png)
