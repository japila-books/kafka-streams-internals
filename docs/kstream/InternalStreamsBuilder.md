# InternalStreamsBuilder

## Creating Instance

`InternalStreamsBuilder` takes the following to be created:

* <span id="internalTopologyBuilder"> [InternalTopologyBuilder](../processor/InternalTopologyBuilder.md)

`InternalStreamsBuilder` is created when:

* `StreamsBuilder` is [created](../StreamsBuilder.md#creating-instance)

## <span id="root"> Root Node

`InternalStreamsBuilder` creates a root [GraphNode](GraphNode.md) when [created](#creating-instance).

This root node is used to [addGraphNode](#addGraphNode) in the following high-level operators:

* [stream](#stream)
* [table](#table)
* [globalTable](#globalTable)
* [addStateStore](#addStateStore)
* [addGlobalStore](#addGlobalStore)

This root node is then used to [build and optimize a topology](#buildAndOptimizeTopology) (for [StreamsBuilder](../StreamsBuilder.md#build)).

## <span id="buildAndOptimizeTopology"> buildAndOptimizeTopology

```java
void buildAndOptimizeTopology(
  Properties props)
```

`buildAndOptimizeTopology`...FIXME

`buildAndOptimizeTopology` is used when:

* `StreamsBuilder` is requested to [build a topology](../StreamsBuilder.md#build)

### <span id="mergeDuplicateSourceNodes"> mergeDuplicateSourceNodes

```java
void mergeDuplicateSourceNodes()
```

`mergeDuplicateSourceNodes`...FIXME

## <span id="addStateStore"> Adding StateStore to Topology

```java
void addStateStore(
  StoreBuilder<?> builder)
```

`addStateStore` [adds](#addGraphNode) a new [StateStoreNode](StateStoreNode.md) to the [root node](#root).

`addStateStore` is used when:

* `StreamsBuilder` is requested to [addStateStore](../StreamsBuilder.md#addStateStore)
* `KTableImpl` is requested to `doJoinOnForeignKey`
