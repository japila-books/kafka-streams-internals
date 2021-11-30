# ProcessorGraphNode

`ProcessorGraphNode` is a [GraphNode](GraphNode.md).

## Creating Instance

`ProcessorGraphNode` takes the following to be created:

* <span id="nodeName"> Node Name
* <span id="processorParameters"> `ProcessorParameters`

`ProcessorGraphNode` is created when:

* `BranchedKStreamImpl` is created and requested to `createBranch`
* `CogroupedStreamAggregateBuilder` is requested to `createTable`
* `KStreamImpl` is requested to [filter](KStreamImpl.md#filter), [filterNot](KStreamImpl.md#filterNot), [internalSelectKey](KStreamImpl.md#internalSelectKey), [map](KStreamImpl.md#map), [mapValues](KStreamImpl.md#mapValues), [flatMap](KStreamImpl.md#flatMap), [flatMapValues](KStreamImpl.md#flatMapValues), [print](KStreamImpl.md#print), [foreach](KStreamImpl.md#foreach), [peek](KStreamImpl.md#peek), [doBranch](KStreamImpl.md#doBranch), [merge](KStreamImpl.md#merge)
* `KStreamImplJoin` is requested to [join](KStreamImplJoin.md#join)
* `KTableImpl` is requested to [doJoinOnForeignKey](KTableImpl.md#doJoinOnForeignKey), [toStream](KTableImpl.md#toStream), [groupBy](KTableImpl.md#groupBy)

## <span id="writeToTopology"> writeToTopology

```java
void writeToTopology(
  InternalTopologyBuilder topologyBuilder,
  Properties props)
```

`writeToTopology` requests the given [InternalTopologyBuilder](../InternalTopologyBuilder.md) to [addProcessor](../InternalTopologyBuilder.md#addProcessor) (with the `processorName` and `processorSupplier` of the [ProcessorParameters](#processorParameters) and [parentNodeNames](GraphNode.md#parentNodeNames))

`writeToTopology` is part of the [GraphNode](GraphNode.md#writeToTopology) abstraction.
