# InternalStreamsBuilder

## Creating Instance

`InternalStreamsBuilder` takes the following to be created:

* <span id="internalTopologyBuilder"> [InternalTopologyBuilder](../InternalTopologyBuilder.md)

`InternalStreamsBuilder` is created when:

* `StreamsBuilder` is [created](StreamsBuilder.md#creating-instance)

## <span id="root"> Root Node

```java
GraphNode root
```

`InternalStreamsBuilder` creates a root [GraphNode](GraphNode.md) when [created](#creating-instance).

This root node is used to [addGraphNode](#addGraphNode) in the following high-level operators:

* [stream](#stream)
* [table](#table)
* [globalTable](#globalTable)
* [addStateStore](#addStateStore)
* [addGlobalStore](#addGlobalStore)

This root node is then used to [build and optimize a topology](#buildAndOptimizeTopology) (for [StreamsBuilder](StreamsBuilder.md#build)).

## <span id="globalTable"> globalTable

```java
GlobalKTable<K, V> globalTable(
  String topic,
  ConsumedInternal<K, V> consumed,
  MaterializedInternal<K, V, KeyValueStore<Bytes, byte[]>> materialized)
```

`globalTable` requests the given `MaterializedInternal` to disable logging (`withLoggingDisabled`).

`globalTable` requests the given `ConsumedInternal` for the `name` and creates a `NamedInternal`.

`globalTable` requests the `NamedInternal` for source and processor names.

`globalTable` requests the given `MaterializedInternal` for the queryable store name and creates a [KTableSource](KTableSource.md).

`globalTable` builds a [TableSourceNode](TableSourceNode.md) and [addGraphNode](#addGraphNode) it to the [root node](#root).

In the end, `globalTable` creates a [GlobalKTableImpl](GlobalKTableImpl.md) (with a `KTableSourceValueGetterSupplier` with the store name and the queryable store name from the given `MaterializedInternal`).

`globalTable` is used when:

* `StreamsBuilder` is requested to [globalTable](StreamsBuilder.md#globalTable)

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

### <span id="maybeUpdateKeyChangingRepartitionNodeMap"> maybeUpdateKeyChangingRepartitionNodeMap

```java
void maybeUpdateKeyChangingRepartitionNodeMap()
```

`maybeUpdateKeyChangingRepartitionNodeMap`...FIXME

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

## <span id="addGraphNode"> Adding GraphNode

```java
void addGraphNode(
  Collection<GraphNode> parents,
  GraphNode child)
```

`addGraphNode` adds the given child [GraphNode](GraphNode.md) to the [children](GraphNode.md#addChild) of the given parent `GraphNode`s.

In the end, `addGraphNode` [maybeAddNodeForOptimizationMetadata](#maybeAddNodeForOptimizationMetadata) (with the `child` node).

### <span id="maybeAddNodeForOptimizationMetadata"> maybeAddNodeForOptimizationMetadata

```java
void maybeAddNodeForOptimizationMetadata(
  GraphNode node)
```

`maybeAddNodeForOptimizationMetadata` [setBuildPriority](GraphNode.md#setBuildPriority) of the given [GraphNode](GraphNode.md) to the current [buildPriorityIndex](#buildPriorityIndex) (and increments it).

`maybeAddNodeForOptimizationMetadata` adds the given `GraphNode` to the following internal registries:

* [keyChangingOperationsToOptimizableRepartitionNodes](#keyChangingOperationsToOptimizableRepartitionNodes) when [isKeyChangingOperation](GraphNode.md#isKeyChangingOperation)
* `OptimizableRepartitionNode` (FIXME)
* [mergeNodes](#mergeNodes) when [isMergeNode](GraphNode.md#isMergeNode)
* [tableSourceNodes](#tableSourceNodes) when the [GraphNode](GraphNode.md) is [TableSourceNode](TableSourceNode.md)

## <span id="mergeNodes"> Merge GraphNodes

`InternalStreamsBuilder` defines `mergeNodes` internal registry of [GraphNode](GraphNode.md)s that are [merge nodes](GraphNode.md#isMergeNode) (that are found in [maybeAddNodeForOptimizationMetadata](#maybeAddNodeForOptimizationMetadata) while [adding a new GraphNode](#addGraphNode)).

`mergeNodes` is used for [maybeUpdateKeyChangingRepartitionNodeMap](#maybeUpdateKeyChangingRepartitionNodeMap).
