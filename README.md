## Introduction

Test implementation of Selenium WebDriver in Java following the Page Object Design Pattern.  
The tests target [a personal website](https://openstock.deakan.com) of mine
with functionalities such as a search engine with lists of movies, games, TV shows, ..., and
accounts with register/login, ...

## Languages / Tools used

- Selenium WebDriver (Java)
- Gradle
- JUnit
- Apache Log4j

Page Object classes are [defined here](https://github.com/Olivier-Leb/selenium-java-testing/tree/master/src/main/java/example/selenium/pages),
and are [tested here](https://github.com/Olivier-Leb/selenium-java-testing/tree/master/src/test/java/example/selenium/pages).

GitHub actions are used to [build and test](https://github.com/Olivier-Leb/selenium-java-testing/blob/master/.github/workflows/gradle.yml) the project.  
The HTML report can be [seen here](https://htmlpreview.github.io/?https://raw.githubusercontent.com/Olivier-Leb/selenium-java-testing/master/test-reports/html/index.html).
