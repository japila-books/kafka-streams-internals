# KStreamAggregate

`KStreamAggregate<K, V, T>` is a [KStreamAggProcessorSupplier](KStreamAggProcessorSupplier.md).

## Creating Instance

`KStreamAggregate` takes the following to be created:

* <span id="storeName"> Name of a State Store
* <span id="initializer"> [Initializer](Initializer.md) (of `T` values)
* <span id="aggregator"> `Aggregator<? super K, ? super V, T>`

`KStreamAggregate` is created when:

* `CogroupedStreamAggregateBuilder` is requested to `build` (a [KTable](KTable.md))
* `KGroupedStreamImpl` is requested to [aggregate](KGroupedStreamImpl.md#aggregate) and [doCount](KGroupedStreamImpl.md#doCount)
