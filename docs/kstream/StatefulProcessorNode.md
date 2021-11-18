# StatefulProcessorNode

`StatefulProcessorNode` is a [ProcessorGraphNode](ProcessorGraphNode.md).

## Creating Instance

`StatefulProcessorNode` takes the following to be created:

* <span id="nodeName"> Name
* <span id="processorParameters"> `ProcessorParameters`
* [State Stores](#preRegisteredStores)
* <span id="valueGetterSuppliers"> `KTableValueGetterSupplier`s

`StatefulProcessorNode` is created when:

* `CogroupedStreamAggregateBuilder` is requested to `getStatefulProcessorNode`
* `GroupedStreamAggregateBuilder` is requested to [build](GroupedStreamAggregateBuilder.md#build)
* `KGroupedTableImpl` is requested to `doAggregate`
* `KStreamImpl` is requested to [flatTransform](KStreamImpl.md#flatTransform), [doTransformValues](KStreamImpl.md#doTransformValues), [doFlatTransformValues](KStreamImpl.md#doFlatTransformValues) and [process](KStreamImpl.md#process)
* `KTableImpl` is requested to [doJoinOnForeignKey](KTableImpl.md#doJoinOnForeignKey) and [suppress](KTableImpl.md#suppress)

## <span id="storeNames"><span id="preRegisteredStores"> State Stores

`StatefulProcessorNode` can be given the names of the associated state stores directly or as [StoreBuilder](../state/StoreBuilder.md)s (together with the stores of the [KTableValueGetterSupplier](#valueGetterSuppliers)s) when [created](#creating-instance).

## <span id="storeBuilder"> State Stores

`StatefulProcessorNode` can be given a [StoreBuilder](../state/StoreBuilder.md) when [created](#creating-instance).
