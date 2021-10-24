# Produced &mdash; Metadata for Producing Records

`Produced<K, V>` describes how to produce records in a topology in the [High-Level KStream DSL](index.md) for the following high-level operators:

* [KStream.to](KStream.md#to)

`Produced<K, V>` is a [NamedOperation](NamedOperation.md).

## Demo

```scala
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.Produced
val produced = Produced.`with`(Serdes.Long, Serdes.String)
```

```text
scala> :type produced
org.apache.kafka.streams.kstream.Produced[Long,String]
```

## Creating Instance

`Produced` takes the following to be created:

* <span id="keySerde"> `Serde<K>` of keys ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="valueSerde"> `Serde<V>` of values ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="partitioner"> [StreamPartitioner](../processor/StreamPartitioner.md)
* <span id="processorName"> Processor Name

`Produced` is created using the [factories](#factories).

## <span id="factories"> Creating Consumed

### <span id="as"> as

```java
Produced<K, V> as(
  String processorName)
```

### <span id="with"> with

```java
Produced<K, V> with(
  Serde<K> keySerde,
  Serde<V> valueSerde)
Produced<K, V> with(
  Serde<K> keySerde,
  Serde<V> valueSerde,
  StreamPartitioner<? super K, ? super V> partitioner)
```

### <span id="keySerde"> keySerde

```java
Produced<K, V> keySerde(
  Serde<K> keySerde)
```

### <span id="valueSerde"> valueSerde

```java
Produced<K, V> valueSerde(
  Serde<V> valueSerde)
```

### <span id="streamPartitioner"> streamPartitioner

```java
Produced<K, V> streamPartitioner(
  StreamPartitioner<? super K, ? super V> partitioner)
```

## Scala API

[Scala API](../scala.md) for Kafka Streams makes the optional `Produced` metadata an implicit parameter in the [KStream](KStream.md) API.

Moreover, `ImplicitConversions` object defines `producedFromSerde` implicit method that creates a `Produced` instance with the key and value `Serde` objects available in implicit scope.

And the last but not least, Scala API for Kafka Streams defines `Produced` object with `with` factory methods that use implicit key and value `Serde` objects.
