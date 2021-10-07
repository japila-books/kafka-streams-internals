# StateStoreNode

`StateStoreNode` is a [GraphNode](GraphNode.md).

## Creating Instance

`StateStoreNode` takes the following to be created:

* <span id="storeBuilder"> [StoreBuilder](../state/StoreBuilder.md)

`StateStoreNode` is created when:

* `InternalStreamsBuilder` is requested to [add a StoreBuilder](InternalStreamsBuilder.md#addStateStore)

## <span id="writeToTopology"> writeToTopology

```java
void writeToTopology(
  InternalTopologyBuilder topologyBuilder,
  Properties props)
```

`writeToTopology` merely requests the given [InternalTopologyBuilder](../processor/InternalTopologyBuilder.md) to [add](../processor/InternalTopologyBuilder.md#addStateStore) the [storeBuilder](#storeBuilder).

`writeToTopology` is part of the [GraphNode](GraphNode.md#writeToTopology) abstraction.
