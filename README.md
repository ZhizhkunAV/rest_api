# Reqres API Testing Automation Project
<img  src="media/logo/reqresin.jpg">

## :man_student:: Content:

- <a href="#tools"> Technologies and tools</a>
- <a href="#checking"> Implemented checks</a>
- <a href="#console"> Running tests from the terminal</a>
- <a href="#jenkins"> Build in Jenkins</a>
- <a href="#allureReport"> Allure report</a>
- <a href="#allure"> Integration with Allure TestOps</a> 
- <a href="#tg"> Notifications in Telegram using a bot</a>
---

<a id="tools"></a>
## 🔨 Technologies and tools:


| Java                                                                                                    | IntelliJ  <br>  Idea                                                                                              | GitHub                                                                                                    | JUnit 5                                                                                                          | Gradle                                                                                                    | Rest Assured                                                                                                        | Allure <br> Report                                                                                                        | Jenkins                                                                                                         | Jira                                                                                                                                   | Telegram                                                                                                           |                                                                                               Allure <br> TestOps |
|:--------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------:|
| <a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50"  alt="Java"/></a> | <a href="https://www.jetbrains.com/idea/"><img src="media/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="media/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="media/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="media/logo/rest-assured.jpg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://github.com/allure-framework"><img src="media/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> | <a href="https://www.jenkins.io/"><img src="media/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/software/jira/"><img src="media/logo/Jira.svg" width="50" height="50" alt="Java" title="Java"/></a> | <a href="https://web.telegram.org/"><img src="media/logo/Telegram.svg" width="50" height="50" alt="Telegram"/></a> | <a href="https://qameta.io/"><img src="media/logo/AllureTestOps.svg" width="50" height="50" alt="Allure_TO"/></a> |

Автотесты написаны на языке `Java` с использованием `JUnit 5`, `Selenide`, `Rest-Assured`. Сборщик проекта - `Gradle`. Для удаленного запуска реализована задача в `Jenkins` с формированием `Allure-отчета` и
отправкой результатов в `Telegram канал` при помощи бота. Так же осуществлена интеграция с `Allure TestOps` и `Jira`.

---

<a id="checking"></a>
## :male_detective:: Реализованные проверки

- ✓ The Get method is the presence of an email and the success of receiving the correct response code (code 200).
- ✓ The Put - update method of an existing entity (name,job) and the success of obtaining the correct response code(code 200)
- ✓ The Post method is the creation of a new user (name,job) and the success of receiving the correct response code (code 200)
- ✓ Delete method - deleting the user and the success of getting the correct response code (code 204)
- ✓ The Post method is not valid user authorization and the success of receiving the correct response code (code 400)


<a id="console"></a>
### Running tests locally from the terminal
`gradle clean test`

---

### Test launch options

You can choose one of three test suites to run:

```mermaid
flowchart LR
    A[Test Suite] --> B[All tests] --> C[test]
    A[Test Suite] --> D[Validation tests] -->E[account_test]
    A[Test Suite] -->H[Data tests] -->I[userData_test]
```
---

<a id="jenkins"></a>
## <img src="media/logo/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a> Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/job/zhizhkunav_restapi/"> Jenkins </a>
To start the build, go to the <code> sectionAssemble with the </code> parameters, select the necessary parameters and click the <code> buttonBuild </code>.

<p align="center">
<a href="https://jenkins.autotests.cloud/job/zhizhkunav_restapi//"><img src="media/screens/jenkins_main.PNG" alt="Jenkins1"/></a>
</p>
After the assembly is completed, the Allure Report and Allure TestOps icons will appear in the Assembly History block next to the assembly number, clicking on which will open a page with the generated html report and test documentation, respectively.

---
<a id="allureReport"></a>
## <img src="media/logo/Allure.svg" width="25" height="25"/> [Allure](https://jenkins.autotests.cloud/job/Melnikov_A-Beeline_autotests/61/allure/) report

### 🖨️ The main page of the report

<p align="center">
<img src="media/screens/allure_auto_main.PNG" alt="Allure report" width="1000" height="400">
</p>

### 📄 Test cases

<p align="center">
<img src="media/screens/allure_auto_2.PNG" alt="Test Case" width="1000" height="400">
</p>

---
<a id="allure"></a>
## <img alt="Allure_TO" height="25" src="media/logo/AllureTestOps.svg" width="25"/> </a>Integration with<a target="_blank" href="https://allure.autotests.cloud/project/4294/launches">Allure TestOps</a>
The statistics of the number of tests are visible on the *Dashboard* in <code>Allure TestOps</code>. New tests, as well as the results of the run, are received by integration every time the build is started.
## 🖨️ The main page of the report

---
#### The content of the task

- :heavy_check_mark: Target
- :heavy_check_mark: Tasks to complete
- :heavy_check_mark: Test cases from Allure TestOps
- :heavy_check_mark: The result of the test run in Allure TestOps

---

<a id="tg"></a>
## <img src="media/logo/Telegram.svg" width="25" height="25"/> Notifications in Telegram chat with a bot

After the build is completed, a special bot created in <code>Telegram</code> automatically processes and sends a message with a test run report.

### Notification from correspondence with a chatbot

<p align="center">
<img src="media/screens/telegram_main.png" alt="TestOps launch" width="500" height="400">
</p>


#### The content of the Telegram notification

- :heavy_check_mark: Environment
- :heavy_check_mark: Comment
- :heavy_check_mark: Duration of the tests
- :heavy_check_mark: Total number of scenarios
- :heavy_check_mark: Percentage of passing tests
- :heavy_check_mark: Link to the Allure report

---
