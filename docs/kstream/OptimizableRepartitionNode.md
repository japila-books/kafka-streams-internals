# OptimizableRepartitionNode

`OptimizableRepartitionNode<K, V>` is a [BaseRepartitionNode](BaseRepartitionNode.md).

## Creating Instance

`OptimizableRepartitionNode` takes the following to be created:

* <span id="nodeName"> Node Name
* <span id="sourceName"> Source Name
* <span id="processorParameters"> `ProcessorParameters`
* <span id="keySerde"> Key `Serde` ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="valueSerde"> Value `Serde` ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="sinkName"> Sink Name
* <span id="repartitionTopic"> Repartition Topic
* <span id="partitioner"> [StreamPartitioner](../processor/StreamPartitioner.md)

`OptimizableRepartitionNode` is created when:

* `OptimizableRepartitionNodeBuilder` is requested to [build](#OptimizableRepartitionNodeBuilder)

## <span id="writeToTopology"> writeToTopology

```java
void writeToTopology(
  InternalTopologyBuilder topologyBuilder,
  Properties props)
```

`writeToTopology` requests the given [InternalTopologyBuilder](../InternalTopologyBuilder.md) for the following:

1. [addInternalTopic](../InternalTopologyBuilder.md#addInternalTopic) (with the [repartitionTopic](#repartitionTopic))
1. [addSink](../InternalTopologyBuilder.md#addSink) (with the [sinkName](#sinkName), the [repartitionTopic](#repartitionTopic))
1. [addSource](../InternalTopologyBuilder.md#addSource) (with the [sourceName](#sourceName), the [repartitionTopic](#repartitionTopic))

`writeToTopology` is part of the [GraphNode](GraphNode.md#writeToTopology) abstraction.

## <span id="optimizableRepartitionNodeBuilder"><span id="OptimizableRepartitionNodeBuilder"> Creating OptimizableRepartitionNodeBuilder

```java
OptimizableRepartitionNodeBuilder<K, V> optimizableRepartitionNodeBuilder()
```

`optimizableRepartitionNodeBuilder` creates a new `OptimizableRepartitionNodeBuilder`.

`optimizableRepartitionNodeBuilder` is used when:

* `CogroupedStreamAggregateBuilder` is requested to `processRepartitions` (with `repartitionRequired` flag on among `KGroupedStreamImpl`s)
* `GroupedStreamAggregateBuilder` is requested to [build a KTable](GroupedStreamAggregateBuilder.md#build) (with `repartitionRequired` flag on)
* `InternalStreamsBuilder` is requested to [createRepartitionNode](InternalStreamsBuilder.md#createRepartitionNode)
* `KStreamImpl` is requested to [toTable](KStreamImpl.md#toTable) (with `repartitionRequired` flag on), [repartitionForJoin](KStreamImpl.md#repartitionForJoin)
