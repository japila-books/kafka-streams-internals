# Logging

Kafka Streams uses [Simple Logging Facade for Java (SLF4J)](http://www.slf4j.org/index.html) for logging.

Among the logging frameworks supported by slf4j is [Apache Log4j](https://logging.apache.org/log4j) that is used by Apache Kafka by default.

## Library Dependencies

Use `slf4j-api` and `slf4j-log4j12` library dependencies in a Kafka Streams application (in `build.sbt`) for logging.

```text
val slf4jVersion = "2.0.0-alpha5"
libraryDependencies += "org.slf4j" % "slf4j-api" % slf4jVersion
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % slf4jVersion
```

## <span id="log4j.properties"> log4j.properties

Use the following `log4j.properties` in `src/main/resources` in your Kafka Streams application's project.

```text
log4j.rootLogger=INFO, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=[%d] %p %m (%c)%n

log4j.logger.org.apache.kafka.streams.processor.internals.StreamThread=ALL
```
