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

* BaseJoinProcessorNode
* [BaseRepartitionNode](BaseRepartitionNode.md)
* ProcessorGraphNode
* [SourceGraphNode](SourceGraphNode.md)
* [StateStoreNode](StateStoreNode.md)
* StreamSinkNode
* StreamTableJoinNode
* StreamToTableNode
* TableProcessorNode

## Creating Instance

`GraphNode` takes the following to be created:

* <span id="nodeName"> Node Name

!!! note "Abstract Class"
    `GraphNode` is an abstract class and cannot be created directly. It is created indirectly for the [concrete GraphNodes](#implementations).

## Demo

```scala
import org.apache.kafka.streams.StreamsBuilder

// Without this class the following would not be available
// due to `protected` access level
class MyStreamsBuilder extends StreamsBuilder {
  val root = internalStreamsBuilder.root
}

val builder = new MyStreamsBuilder
builder.root
```

```text
scala> println(builder.root)
StreamsGraphNode{nodeName='root', buildPriority=null, hasWrittenToTopology=false, keyChangingOperation=false, valueChangingOperation=false, mergeNode=false, parentNodes=[]}
```
