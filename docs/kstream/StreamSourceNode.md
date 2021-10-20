# StreamSourceNode

`StreamSourceNode<K, V>` is a [SourceGraphNode](SourceGraphNode.md).

## Creating Instance

`StreamSourceNode` takes the following to be created:

* <span id="nodeName"> Node Name
* <span id="topicNames"><span id="topicPattern"> Topic Names or Pattern
* <span id="consumedInternal"> `ConsumedInternal<K, V>`

`StreamSourceNode` is created when:

* `InternalStreamsBuilder` is requested to [stream](InternalStreamsBuilder.md#stream)
* `KTableImpl` is requested to [doJoinOnForeignKey](KTableImpl.md#doJoinOnForeignKey)

## <span id="writeToTopology"> writeToTopology

```java
void writeToTopology(
  InternalTopologyBuilder topologyBuilder, 
  Properties props)
```

`writeToTopology` requests the given [InternalTopologyBuilder](../InternalTopologyBuilder.md) to [addSource](../InternalTopologyBuilder.md#addSource).

`writeToTopology` is part of the [GraphNode](GraphNode.md#writeToTopology) abstraction.
