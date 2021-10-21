# KStream API &mdash; Stream of Records

`KStream<K, V>` is an [abstraction](#contract) of a stream of records (of key-value pairs).

`KStream` can be created directly from one or many Kafka topics (using [StreamsBuilder.stream](StreamsBuilder.md#stream) operators) or as a result of transformations on an existing `KStream` instance.

`KStream` offers a rich set of operators (_KStream API_) for building topologies to consume, process and produce (key-value) records.

## Contract (Subset)

### <span id="flatMap"> flatMap

```java
KStream<KR, VR> flatMap(
  KeyValueMapper<
    ? super K,
    ? super V,
    ? extends Iterable<? extends KeyValue<? extends KR, ? extends VR>>> mapper)
KStream<KR, VR> flatMap(
  KeyValueMapper<
    ? super K,
    ? super V,
    ? extends Iterable<? extends KeyValue<? extends KR, ? extends VR>>> mapper,
  Named named)
```

### <span id="foreach"> foreach

```java
void foreach(
  ForeachAction<? super K, ? super V> action)
void foreach(
  ForeachAction<? super K, ? super V> action,
  Named named)
```

### <span id="groupBy"> groupBy

```java
KGroupedStream<KR, V> groupBy(
  KeyValueMapper<? super K, ? super V, KR> keySelector)
KGroupedStream<KR, V> groupBy(
  KeyValueMapper<? super K, ? super V, KR> keySelector,
  Grouped<KR, V> grouped)
```

### <span id="groupByKey"> groupByKey

```java
KGroupedStream<K, V> groupByKey()
KGroupedStream<K, V> groupByKey(
  Grouped<K, V> grouped)
```

### <span id="join"> join

```java
KStream<K, RV> join(
  GlobalKTable<GK, GV> globalTable,
  KeyValueMapper<? super K, ? super V, ? extends GK> keySelector,
  ValueJoiner<? super V, ? super GV, ? extends RV> joiner)
KStream<K, RV> join(
  GlobalKTable<GK, GV> globalTable,
  KeyValueMapper<? super K, ? super V, ? extends GK> keySelector,
  ValueJoiner<? super V, ? super GV, ? extends RV> joiner,
  Named named)
KStream<K, RV> join(
  GlobalKTable<GK, GV> globalTable,
  KeyValueMapper<? super K, ? super V, ? extends GK> keySelector,
  ValueJoinerWithKey<? super K, ? super V, ? super GV, ? extends RV> joiner)
KStream<K, RV> join(
  GlobalKTable<GK, GV> globalTable,
  KeyValueMapper<? super K, ? super V, ? extends GK> keySelector,
  ValueJoinerWithKey<? super K, ? super V, ? super GV, ? extends RV> joiner,
  Named named)
KStream<K, VR> join(
  KStream<K, VO> otherStream,
  ValueJoiner<? super V, ? super VO, ? extends VR> joiner,
  JoinWindows windows)
KStream<K, VR> join(
  KStream<K, VO> otherStream,
  ValueJoiner<? super V, ? super VO, ? extends VR> joiner,
  JoinWindows windows,
  StreamJoined<K, V, VO> streamJoined)
KStream<K, VR> join(
  KStream<K, VO> otherStream,
  ValueJoinerWithKey<? super K, ? super V, ? super VO, ? extends VR> joiner,
  JoinWindows windows)
KStream<K, VR> join(
  KStream<K, VO> otherStream,
  ValueJoinerWithKey<? super K, ? super V, ? super VO, ? extends VR> joiner,
  JoinWindows windows,
  StreamJoined<K, V, VO> streamJoined)
KStream<K, VR> join(
  KTable<K, VT> table,
  ValueJoiner<? super V, ? super VT, ? extends VR> joiner)
KStream<K, VR> join(
  KTable<K, VT> table,
  ValueJoiner<? super V, ? super VT, ? extends VR> joiner,
  Joined<K, V, VT> joined)
KStream<K, VR> join(
  KTable<K, VT> table,
  ValueJoinerWithKey<? super K, ? super V, ? super VT, ? extends VR> joiner)
KStream<K, VR> join(
  KTable<K, VT> table,
  ValueJoinerWithKey<? super K, ? super V, ? super VT, ? extends VR> joiner,
  Joined<K, V, VT> joined)
```

### <span id="merge"> merge

```java
KStream<K, V> merge(
  KStream<K, V> stream)
KStream<K, V> merge(
  KStream<K, V> stream,
  Named named)
```

### <span id="peek"> peek

```java
KStream<K, V> peek(
  ForeachAction<? super K, ? super V> action)
KStream<K, V> peek(
  ForeachAction<? super K, ? super V> action,
  Named named)
```

### <span id="print"> print

```java
void print(
  Printed<K, V> printed)
```

### <span id="process"> process

```java
void process(
  ProcessorSupplier<? super K, ? super V, Void, Void> processorSupplier,
  Named named,
  String... stateStoreNames)
void process(
  ProcessorSupplier<? super K, ? super V, Void, Void> processorSupplier,
  String... stateStoreNames)
```

### <span id="repartition"> repartition

```java
KStream<K, V> repartition()
KStream<K, V> repartition(
  Repartitioned<K, V> repartitioned)
```

[KStreamImpl.repartition](KStreamImpl.md#repartition)

### <span id="split"> split

```java
BranchedKStream<K, V> split()
BranchedKStream<K, V> split(
  Named named)
```

### <span id="to"> to

```java
void to(
  String topic)
void to(
  String topic,
  Produced<K, V> produced)
void to(
  TopicNameExtractor<K, V> topicExtractor)
void to(
  TopicNameExtractor<K, V> topicExtractor,
  Produced<K, V> produced)
```

### <span id="toTable"> toTable

```java
KTable<K, V> toTable()
KTable<K, V> toTable(
  Materialized<K, V, KeyValueStore<Bytes, byte[]>> materialized)
KTable<K, V> toTable(
  Named named)
KTable<K, V> toTable(
  Named named,
  Materialized<K, V, KeyValueStore<Bytes, byte[]>> materialized)
```

### <span id="transform"> transform

```java
KStream<K1, V1> transform(
  TransformerSupplier<? super K, ? super V, KeyValue<K1, V1>> transformerSupplier,
  Named named,
  String... stateStoreNames)
KStream<K1, V1> transform(
  TransformerSupplier<? super K, ? super V, KeyValue<K1, V1>> transformerSupplier,
  String... stateStoreNames)
```

## Implementations

* [KStreamImpl](KStreamImpl.md)

## Demo

```scala
import org.apache.kafka.streams.scala._
import ImplicitConversions._
import serialization.Serdes._

val builder = new StreamsBuilder

// Use type annotation to describe the stream, i.e. stream[String, String]
// Else...Scala type inferencer gives us a stream of "nothing", i.e. KStream[Nothing, Nothing]
val input = builder.stream[String, String]("input")
```

```text
scala> :type input
org.apache.kafka.streams.scala.kstream.KStream[String,String]
```
