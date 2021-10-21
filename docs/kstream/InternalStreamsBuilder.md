# InternalStreamsBuilder

## Creating Instance

`InternalStreamsBuilder` takes the following to be created:

* <span id="internalTopologyBuilder"> [InternalTopologyBuilder](../InternalTopologyBuilder.md)

`InternalStreamsBuilder` is created when:

* `StreamsBuilder` is [created](StreamsBuilder.md#creating-instance)

## <span id="root"> Root Node

`InternalStreamsBuilder` creates a root [GraphNode](GraphNode.md) when [created](#creating-instance).

This root node is used to [addGraphNode](#addGraphNode) in the following high-level operators:

* [stream](#stream)
* [table](#table)
* [globalTable](#globalTable)
* [addStateStore](#addStateStore)
* [addGlobalStore](#addGlobalStore)

This root node is then used to [build and optimize a topology](#buildAndOptimizeTopology) (for [StreamsBuilder](StreamsBuilder.md#build)).

## <span id="buildAndOptimizeTopology"> buildAndOptimizeTopology

```java
void buildAndOptimizeTopology() // (1)
void buildAndOptimizeTopology(
  Properties props)
```

1. Used in tests only

`buildAndOptimizeTopology`...FIXME

`buildAndOptimizeTopology` is used when:

* `StreamsBuilder` is requested to [build a topology](StreamsBuilder.md#build)

### <span id="mergeDuplicateSourceNodes"> mergeDuplicateSourceNodes

```java
void mergeDuplicateSourceNodes()
```

`mergeDuplicateSourceNodes`...FIXME

### <span id="maybePerformOptimizations"> maybePerformOptimizations

```java
void maybePerformOptimizations(
  Properties props)
```

`maybePerformOptimizations`...FIXME

### <span id="optimizeKTableSourceTopics"> optimizeKTableSourceTopics

```java
void optimizeKTableSourceTopics()
```

`optimizeKTableSourceTopics`...FIXME

### <span id="maybeOptimizeRepartitionOperations"> maybeOptimizeRepartitionOperations

```java
void maybeOptimizeRepartitionOperations()
```

`maybeOptimizeRepartitionOperations`...FIXME

### <span id="createRepartitionNode"> createRepartitionNode

```java
OptimizableRepartitionNode<K, V> createRepartitionNode(
  String repartitionTopicName,
  Serde<K> keySerde,
  Serde<V> valueSerde)
```

`createRepartitionNode`...FIXME

## <span id="addStateStore"> Adding StateStore to Topology

```java
void addStateStore(
  StoreBuilder<?> builder)
```

`addStateStore` [adds](#addGraphNode) a new [StateStoreNode](StateStoreNode.md) to the [root node](#root).

`addStateStore` is used when:

* `StreamsBuilder` is requested to [addStateStore](StreamsBuilder.md#addStateStore)
* `KTableImpl` is requested to `doJoinOnForeignKey`

## <span id="stream"> stream

```java
KStream<K, V> stream(
  Collection<String> topics,
  ConsumedInternal<K, V> consumed)
KStream<K, V> stream(
  Pattern topicPattern,
  ConsumedInternal<K, V> consumed)
```

`stream`...FIXME

`stream` is used when:

* `StreamsBuilder` is requested to [stream](StreamsBuilder.md#stream)
