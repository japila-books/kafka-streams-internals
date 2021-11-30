# KStreamImpl

`KStreamImpl` is a [KStream](KStream.md).

## Creating Instance

`KStreamImpl` takes the following to be created:

* <span id="name"> Name
* <span id="keySerde"> Key `Serde` ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="valueSerde"> Value `Serde` ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="subTopologySourceNodes"> Sub-Topology Source Nodes (Names)
* [repartitionRequired](#repartitionRequired) flag
* <span id="graphNode"> [GraphNode](GraphNode.md)
* <span id="builder"> [InternalStreamsBuilder](InternalStreamsBuilder.md)

`KStreamImpl` is created when:

* `InternalStreamsBuilder` is requested to [stream](InternalStreamsBuilder.md#stream)
* _others_

## <span id="repartitionRequired"> repartitionRequired Flag

`KStreamImpl` is given a `repartitionRequired` flag when [created](#creating-instance).

The flag is disabled (`false`) when:

* `InternalStreamsBuilder` is requested to [create a KStream](InternalStreamsBuilder.md#stream)
* `KStreamImpl` is requested to [doRepartition](#doRepartition), [repartitionForJoin](#repartitionForJoin), [doStreamTableJoin](#doStreamTableJoin)
* `KStreamImplJoin` is requested to [join](KStreamImplJoin.md#join)
* `KTableImpl` is requested to [toStream](KTableImpl.md#toStream)

The `repartitionRequired` flag is left unchanged (and handed over to the child nodes) in most operators (e.g. `filter`, `mapValues`, `split`).

The flag is enabled (`true`) when:

* `KStreamImpl` is requested to [selectKey](#selectKey), [map](#map), [flatMap](#flatMap), [merge](#merge) (when either `KStream` requires so), [flatTransform](#flatTransform)

The `repartitionRequired` flag is used in the following operators to add an extra (parent) `OptimizableRepartitionNode` to the [InternalStreamsBuilder](#builder):

* [toTable](#toTable)
* [doJoin](#doJoin), [join](#join) and [leftJoin](#leftJoin) (to [repartitionForJoin](#repartitionForJoin))

## <span id="merge"> merge

```java
KStream<K, V> merge(
  InternalStreamsBuilder builder,
  KStream<K, V> stream,
  NamedInternal named)
```

`merge` creates a [ProcessorGraphNode](ProcessorGraphNode.md) and turns [mergeNode](GraphNode.md#setMergeNode) flag on.

`merge` requests the [InternalStreamsBuilder](AbstractStream.md#builder) to [add the new ProcessorGraphNode](InternalStreamsBuilder.md#addGraphNode) (with this and the given `KStream`'s [GraphNode](AbstractStream.md#graphNode)s as the parents).

In the end, `merge` creates a new [KStreamImpl](#creating-instance) for the new `ProcessorGraphNode`.

`merge` is part of the [KStream](KStream.md#merge) abstraction.

## <span id="join"> join

```java
KStream<K, VR> join(
  GlobalKTable<KG, VG> globalTable,
  ...) // (1)
KStream<K, VR> join(
  KStream<K, VO> otherStream,
  ...)
KStream<K, VR> join(
  KTable<K, VO> table,
  ...)
```

1. There are quite a few `join`s

`join`...FIXME

`join` is part of the [KStream](KStream.md#join) abstraction.

## <span id="leftJoin"> leftJoin

```java
KStream<K, VR> leftJoin(
  GlobalKTable<KG, VG> globalTable,
  ...) // (1)
KStream<K, VR> leftJoin(
  KStream<K, VO> otherStream,
  ...)
KStream<K, VR> leftJoin(
  KTable<K, VO> table,
  ...)
```

1. There are quite a few `leftJoin`s

`leftJoin`...FIXME

`leftJoin` is part of the [KStream](KStream.md#leftJoin) abstraction.

## <span id="outerJoin"> outerJoin

```java
KStream<K, VR> outerJoin(
  KStream<K, VO> otherStream,
  ...) // (1)
```

1. There are quite a few `outerJoin`s

`outerJoin`...FIXME

`outerJoin` is part of the [KStream](KStream.md#outerJoin) abstraction.

## <span id="doJoin"> doJoin

```java
KStream<K, VR> doJoin(
  KStream<K, VO> otherStream,
  ValueJoinerWithKey<? super K, ? super V, ? super VO, ? extends VR> joiner,
  JoinWindows windows,
  StreamJoined<K, V, VO> streamJoined,
  KStreamImplJoin join)
```

In the end, `doJoin` requests the given [KStreamImplJoin](KStreamImplJoin.md) to [join](KStreamImplJoin.md#join).

`doJoin` is used when:

* `KStreamImpl` is requested to [join](#join), [leftJoin](#leftJoin) and [outerJoin](#outerJoin)

### <span id="repartitionForJoin"> repartitionForJoin

```java
KStreamImpl<K, V> repartitionForJoin(
  String repartitionName,
  Serde<K> keySerdeOverride,
  Serde<V> valueSerdeOverride)
```

`repartitionForJoin` [creates an OptimizableRepartitionNodeBuilder](OptimizableRepartitionNode.md#optimizableRepartitionNodeBuilder).

`repartitionForJoin` [creates a repartitioned source](#createRepartitionedSource).

Only when there is no [OptimizableRepartitionNode](#repartitionNode) defined already or the [name](AbstractStream.md#name) (of this `KStreamImpl`) is different from the given `repartitionName`, `repartitionForJoin` requests the `OptimizableRepartitionNodeBuilder` to build an `OptimizableRepartitionNode` and requests the [InternalStreamsBuilder](#builder) to [add the repartition node](InternalStreamsBuilder.md#addGraphNode) (to the [GraphNode](AbstractStream.md#graphNode)).

In the end, `repartitionForJoin` creates a new [KStreamImpl](#creating-instance) (with the [repartitionRequired](#repartitionRequired) flag off and the [OptimizableRepartitionNode](#repartitionNode) as the [GraphNode](#graphNode)).

## <span id="groupBy"> groupBy

```java
KGroupedStream<KR, V> groupBy(
  KeyValueMapper<? super K, ? super V, KR> keySelector)
KGroupedStream<KR, V> groupBy(
  KeyValueMapper<? super K, ? super V, KR> keySelector,
  Grouped<KR, V> grouped)
```

`groupBy`...FIXME

In the end, `groupBy` creates a [KGroupedStreamImpl](KGroupedStreamImpl.md) (with the [repartitionRequired](KGroupedStreamImpl.md#repartitionRequired) flag enabled).

`groupBy` is part of the [KStream](KStream.md#groupBy) abstraction.

## <span id="groupByKey"> groupByKey

```java
KGroupedStream<K, V> groupByKey()
KGroupedStream<K, V> groupByKey(
  Grouped<K, V> grouped)
```

`groupByKey` creates a [KGroupedStreamImpl](KGroupedStreamImpl.md).

`groupByKey` is part of the [KStream](KStream.md#groupByKey) abstraction.

## <span id="repartition"> repartition

```java
KStream<K, V> repartition()
KStream<K, V> repartition(
  Repartitioned<K, V> repartitioned)
```

`repartition` [doRepartition](#doRepartition)

`repartition` is part of the [KStream](KStream.md#repartition) abstraction.

### <span id="doRepartition"> doRepartition

```java
KStream<K, V> doRepartition(
  Repartitioned<K, V> repartitioned)
```

`doRepartition` [creates a new UnoptimizableRepartitionNodeBuilder](UnoptimizableRepartitionNode.md#unoptimizableRepartitionNodeBuilder) that is then used to [createRepartitionedSource](#createRepartitionedSource).

`doRepartition` requests the `UnoptimizableRepartitionNodeBuilder` to build a `UnoptimizableRepartitionNode`.

`doRepartition` requests the [InternalStreamsBuilder](AbstractStream.md#builder) to [add the UnoptimizableRepartitionNode](InternalStreamsBuilder.md#addGraphNode) to the [GraphNode](AbstractStream.md#graphNode) (as a child node).

In the end, `doRepartition` [creates a new KStreamImpl](#creating-instance) (with the [repartitionRequired](#repartitionRequired) turned off and the `UnoptimizableRepartitionNode` as the [GraphNode](#graphNode)).

## <span id="createRepartitionedSource"> Creating Repartitioned Source

```java
String createRepartitionedSource(
  InternalStreamsBuilder builder,
  Serde<K1> keySerde,
  Serde<V1> valueSerde,
  String repartitionTopicNamePrefix,
  StreamPartitioner<K1, V1> streamPartitioner,
  BaseRepartitionNodeBuilder<K1, V1, RN> baseRepartitionNodeBuilder)
```

`createRepartitionedSource`...FIXME

`createRepartitionedSource` is used when:

* `CogroupedStreamAggregateBuilder` is requested to `createRepartitionSource`
* `GroupedStreamAggregateBuilder` is requested to `createRepartitionSource`
* `InternalStreamsBuilder` is requested to [createRepartitionNode](InternalStreamsBuilder.md#createRepartitionNode)
* `KStreamImpl` is requested to [doRepartition](#doRepartition), [toTable](#toTable), [repartitionForJoin](#repartitionForJoin)

## <span id="toTable"> toTable

```java
KTable<K, V> toTable(
  Named named,
  Materialized<K, V, KeyValueStore<Bytes, byte[]>> materialized)
KTable<K, V> toTable(...) // (1)
```

1. There are other `toTable`s (of less interest)

Only when the [repartitionRequired](#repartitionRequired) flag is enabled, `toTable` creates an [OptimizableRepartitionNodeBuilder](#optimizableRepartitionNodeBuilder) and a [repartitioned source](#createRepartitionedSource). `toTable` requests the `OptimizableRepartitionNodeBuilder` to build a (parent) `OptimizableRepartitionNode` and requests the [InternalStreamsBuilder](#builder) to [add](InternalStreamsBuilder.md#addGraphNode) the repartition node (to the [GraphNode](#graphNode)).

`toTable`...FIXME

`toTable` is part of the [KStream](KStream.md#toTable) abstraction.
