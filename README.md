# Software Security Demo App (Spring Boot/Angular version)

## Requirements

* Java 8+
* Maven 3+
* NodeJS 14.18.0+

## Install

Install Java packages and database using:

```shell
mvn install
```

Install Angular packages:

<!-- Test this -->

```shell
npm install -g @angular/cli@11.0.0
cd spa
npm install
```

## Run

Launch the API:

```shell
mvn jetty:run
```

Then launch the SPA:

```shell
cd spa
ng serve
```

## Security analysis

### SAST

```shell
mvn compile spotbugs:spotbugs spotbugs:gui
```

### SCA

```shell
mvn dependency-check:check
```
