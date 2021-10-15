# Scala API for Kafka Streams

**Scala API for Kafka Streams** is a separate Kafka Streams module (a Scala library) that acts as a wrapper over the existing Java API for Kafka Streams.

The Scala API for Kafka Streams defines Scala-friendly types that wrap the corresponding Kafka Streams types and simply delegate all method calls to the underlying Java object with the purpose of making it much more expressive, with less boilerplate and more succinct.

## <span id="scala-package"> scala Package

The Scala API is available in the `org.apache.kafka.streams.scala` package.

```scala
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
```

## <span id="build"><span id="library-dependency"> Library Dependency

As a separate Scala library Scala API for Kafka Streams has to be defined as a dependency in a build configuration (e.g. `build.sbt`).

```text
val kafkaVersion = "{{ kafka.version }}"
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % kafkaVersion
```

## <span id="Serdes"><span id="ImplicitConversions"> Implicit Conversions

The Scala API for Kafka Streams defines implicit conversions, i.e. `Serdes`, and `ImplicitConversions`.

```scala
import org.apache.kafka.streams.scala._
import ImplicitConversions._
import serialization.Serdes._
```

## Consumed

The Scala API for Kafka Streams comes with Scala objects for creating [Consumed](kstream/Consumed.md), [Produced](kstream/Produced.md), `Materialized` and other metadata-related instances with [Serdes](#Serdes) objects for the key and value types available in implicit scope.

```scala
import org.apache.kafka.streams.scala.kstream._
```
