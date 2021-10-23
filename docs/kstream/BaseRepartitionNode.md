# BaseRepartitionNode

`BaseRepartitionNode<K, V>` is an [extension](#contract) of the [GraphNode](GraphNode.md) abstraction for [repartition nodes](#implementations).

## Implementations

* [GroupedTableOperationRepartitionNode](GroupedTableOperationRepartitionNode.md)
* [OptimizableRepartitionNode](OptimizableRepartitionNode.md)
* [UnoptimizableRepartitionNode](UnoptimizableRepartitionNode.md)

## Creating Instance

`BaseRepartitionNode` takes the following to be created:

* <span id="nodeName"> Node Name
* <span id="sourceName"> Source Name
* <span id="processorParameters"> `ProcessorParameters`
* <span id="keySerde"> Key `Serde` ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="valueSerde"> Value `Serde` ([Apache Kafka]({{ book.kafka }}/Serde))
* <span id="sinkName"> Sink Name
* <span id="repartitionTopic"> Repartition Topic
* <span id="partitioner"> `StreamPartitioner<K, V>`
* <span id="internalTopicProperties"> `InternalTopicProperties`

!!! note "Abstract Class"
    `BaseRepartitionNode` is an abstract class and cannot be created directly. It is created indirectly for the [concrete BaseRepartitionNodes](#implementations).
