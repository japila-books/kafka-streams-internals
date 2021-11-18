# ConnectedStoreProvider

`ConnectedStoreProvider` is an [abstraction](#contract) of [providers](#implementations) of [StoreBuilders](#stores)

## Contract

### <span id="stores"> stores

```java
Set<StoreBuilder<?>> stores()
```

Default: `null` (uninitialized and so undefined)

Used when:

* `AbstractStream` is requested to [toValueTransformerWithKeySupplier](../kstream/AbstractStream.md#toValueTransformerWithKeySupplier)
* `Topology` is requested to [addProcessor](../Topology.md#addProcessor)
* `StatefulProcessorNode` is requested to [writeToTopology](../kstream/StatefulProcessorNode.md#writeToTopology)
* _others_

## Implementations

* [ProcessorSupplier](ProcessorSupplier.md)
* [TransformerSupplier](TransformerSupplier.md)
* `ValueTransformerSupplier`
* `ValueTransformerWithKeySupplier`
