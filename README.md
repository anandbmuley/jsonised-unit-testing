# jsonised-unit-testing
A simple library for effective unit testing containing JSON data

## Pre-Requisites

* Tools needed

Name     | version
---------|--------
Gradle   |6.6
Java     | 1.8
Groovy   | 2.5.12


## Setup

* Clone the Repo
```
git clone https://github.com/anandbmuley/jsonised-unit-testing.git
```
* Build the Repo and Publish to Maven local Repo
```
gradle clean build pTML
```
* Add as Gradle test scope dependency into your Project

```
dependencies{
  testImplementation("abm:jsonised-unit-testing:1.0.0")
}
```

* Create below mentioned folders in the src/test/resources folder

FolderName | Usage
------------ | -------------
requests | The folder should contain the request JSON files which you want to use as API request body
responses | The folder should contain the response JSON files which you want to use for API response assertions
error-responses | The folder should contain the error response JSON files for error response assertions


## Classes to use

Class Name | Usage
------------ | -------------
TestDataLoader | The class is used for all the testing assertions
