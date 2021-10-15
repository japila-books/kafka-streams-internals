# KStreamImpl

`KStreamImpl` is a [KStream](KStream.md).

## Creating Instance

`KStreamImpl` takes the following to be created:

* <span id="name"> Name
* <span id="keySerde"> `Serde<K>`
* <span id="valueSerde"> `Serde<V>`
* <span id="subTopologySourceNodes"> Sub-Topology Source Nodes (Names)
* [repartitionRequired](#repartitionRequired) flag
* <span id="graphNode"> [GraphNode](GraphNode.md)
* <span id="builder"> [InternalStreamsBuilder](InternalStreamsBuilder.md)

`KStreamImpl` is created when:

* `InternalStreamsBuilder` is requested to [stream](InternalStreamsBuilder.md#stream)
* _others_

## <span id="repartitionRequired"> repartitionRequired Flag

`KStreamImpl` is given a `repartitionRequired` flag when [created](#creating-instance).

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
