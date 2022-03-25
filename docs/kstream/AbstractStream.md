# AbstractStream

`AbstractStream<K, V>` is a base abstraction of [KTables and KStreams](#implementations).

## Implementations

* `CogroupedKStreamImpl`
* [KGroupedStreamImpl](KGroupedStreamImpl.md)
* `KGroupedTableImpl`
* [KStreamImpl](KStreamImpl.md)
* [KTableImpl](KTableImpl.md)
* `SessionWindowedCogroupedKStreamImpl`
* `SessionWindowedKStreamImpl`
* `SlidingWindowedCogroupedKStreamImpl`
* `SlidingWindowedKStreamImpl`
* `TimeWindowedCogroupedKStreamImpl`
* `TimeWindowedKStreamImpl`

## Creating Instance

`AbstractStream` takes the following to be created:

* <span id="name"> Name
* <span id="keySerde"> `Serde<K>`
* <span id="valueSerde"> `Serde<V>`
* <span id="subTopologySourceNodes"> Names of the Sub-Topology Source Nodes
* <span id="graphNode"> [GraphNode](GraphNode.md)
* <span id="builder"> [InternalStreamsBuilder](InternalStreamsBuilder.md)

!!! note "Abstract Class"
    `AbstractStream` is an abstract class and cannot be created directly. It is created indirectly for the [concrete AbstractStreams](#implementations).

## <span id="ensureCopartitionWith"> ensureCopartitionWith

```java
Set<String> ensureCopartitionWith(
  Collection<? extends AbstractStream<K, ?>> otherStreams)
```

`ensureCopartitionWith` requests the [InternalStreamsBuilder](#builder) for the [InternalTopologyBuilder](InternalStreamsBuilder.md#internalTopologyBuilder) that is in turn requested to [copartitionSources](InternalTopologyBuilder.md#copartitionSources) (with the [subTopologySourceNodes](#subTopologySourceNodes) and the `subTopologySourceNodes` of all the other `AbstractStream`s).

`ensureCopartitionWith` returns the [subTopologySourceNodes](#subTopologySourceNodes) and the `subTopologySourceNodes` of all the other `AbstractStream`s.

`ensureCopartitionWith` is used when:

* `CogroupedStreamAggregateBuilder` is requested to `processRepartitions`
* `KStreamImpl` is requested to [doJoin](KStreamImpl.md#doJoin) and [doStreamTableJoin](KStreamImpl.md#doStreamTableJoin)
* `KTableImpl` is requested to [doJoin](KTableImpl.md#doJoin)
