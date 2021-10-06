# GraphNode

`GraphNode` is an [abstraction](#contract) of [graph nodes](#implementations) (for [InternalStreamsBuilder](InternalStreamsBuilder.md#root) to [build a topology](InternalStreamsBuilder.md#buildAndOptimizeTopology) for [StreamsBuilder](StreamsBuilder.md#build)).

## Contract

### <span id="writeToTopology"> writeToTopology

```java
void writeToTopology(
  InternalTopologyBuilder topologyBuilder,
  Properties props)
```

Used when:

* `InternalStreamsBuilder` is requested to [build and optimize a topology](InternalStreamsBuilder.md#buildAndOptimizeTopology)

## Implementations

* ProcessorGraphNode
* StreamToTableNode
* BaseJoinProcessorNode
* SourceGraphNode
* StreamSinkNode
* [StateStoreNode](StateStoreNode.md)
* TableProcessorNode
* BaseRepartitionNode
* StreamTableJoinNode
