# Consumed &mdash; Metadata for Consuming Records

`Consumed<K, V>` describes how to consume records in a topology in the [High-Level KStream DSL](index.md) for the following [StreamsBuilder](StreamsBuilder.md) operators:

* [StreamsBuilder.stream](StreamsBuilder.md#stream)
* [StreamsBuilder.table](StreamsBuilder.md#table)
* [StreamsBuilder.globalTable](StreamsBuilder.md#globalTable)
* [StreamsBuilder.addGlobalStore](StreamsBuilder.md#addGlobalStore)

`Consumed<K, V>` is a [NamedOperation](NamedOperation.md).

## Demo

```scala
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.Consumed
val consumed = Consumed.`with`(Serdes.Long, Serdes.String)
```

```text
scala> :type consumed
org.apache.kafka.streams.kstream.Consumed[Long,String]
```

## Creating Instance

`Consumed` takes the following to be created:

* <span id="keySerde"> `Serde<K>` of keys ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="valueSerde"> `Serde<V>` of values ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="timestampExtractor"> `TimestampExtractor`
* <span id="resetPolicy"> Reset Policy (`Topology.AutoOffsetReset`)
* <span id="processorName"> Processor Name

`Consumed` is created using the [factories](#factories).

## <span id="factories"> Creating Consumed

### <span id="as"> as

```java
Consumed<K, V> as(
  String processorName)
```

### <span id="with"> with

```java
Consumed<K, V> with(
  Serde<K> keySerde,
  Serde<V> valueSerde)
Consumed<K, V> with(
  Serde<K> keySerde,
  Serde<V> valueSerde,
  TimestampExtractor timestampExtractor,
  Topology.AutoOffsetReset resetPolicy)
Consumed<K, V> with(
  TimestampExtractor timestampExtractor)
Consumed<K, V> with(
  Topology.AutoOffsetReset resetPolicy)
```

## Scala API

[Scala API](../scala.md) for Kafka Streams makes the optional `Consumed` metadata an implicit parameter in the [StreamsBuilder](StreamsBuilder.md) API.

Moreover, `ImplicitConversions` object defines `consumedFromSerde` implicit method that creates a `Consumed` instance with the key and value `Serde` objects available in implicit scope.

And the last but not least, Scala API for Kafka Streams defines `Consumed` object with `with` factory methods that use implicit key and value `Serde` objects.
