# TableSourceNode

`TableSourceNode` is a [SourceGraphNode](SourceGraphNode.md).

## Creating Instance

`TableSourceNode` takes the following to be created:

* <span id="nodeName"> Node name
* <span id="sourceName"> Source name
* <span id="topic"> Topic
* <span id="consumedInternal"> `ConsumedInternal<K, V>`
* <span id="materializedInternal"> `MaterializedInternal<K, V, ?>`
* <span id="processorParameters"> `ProcessorParameters<K, V, ?, ?>`
* [isGlobalKTable](#isGlobalKTable) flag

`TableSourceNode` is created when:

* `TableSourceNodeBuilder` is requested to build a `TableSourceNode`

### <span id="isGlobalKTable"> isGlobalKTable Flag

`TableSourceNode` is given a `isGlobalKTable` flag when [created](#creating-instance).

The flag is used when [writeToTopology](#writeToTopology) (to [add a global state store](../InternalTopologyBuilder.md#addGlobalStore)).

## <span id="writeToTopology"> writeToTopology

```java
void writeToTopology(
  InternalTopologyBuilder topologyBuilder)
```

`writeToTopology`...FIXME

`writeToTopology` is part of the [GraphNode](GraphNode.md#writeToTopology) abstraction.
