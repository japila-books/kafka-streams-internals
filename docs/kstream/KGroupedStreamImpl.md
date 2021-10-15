# KGroupedStreamImpl

`KGroupedStreamImpl` is a [KGroupedStream](KGroupedStream.md) (and an [AbstractStream](AbstractStream.md)).

## Creating Instance

`KGroupedStreamImpl` takes the following to be created:

* <span id="name"> Name
* <span id="subTopologySourceNodes"> Sub-Topology Source Nodes (Names)
* <span id="groupedInternal"> `GroupedInternal<K, V>`
* [repartitionRequired](#repartitionRequired) flag
* <span id="graphNode"> [GraphNode](GraphNode.md)
* <span id="builder"> [InternalStreamsBuilder](InternalStreamsBuilder.md)

`KGroupedStreamImpl` is created when:

* `KStreamImpl` is requested to [groupBy](KStreamImpl.md#groupBy) and [groupByKey](KStreamImpl.md#groupByKey)

## <span id="aggregateBuilder"> GroupedStreamAggregateBuilder

`KGroupedStreamImpl` creates a [GroupedStreamAggregateBuilder](GroupedStreamAggregateBuilder.md) when [created](#creating-instance).

## <span id="repartitionRequired"> repartitionRequired Flag

`KGroupedStreamImpl` is given a `repartitionRequired` flag when [created](#creating-instance).

The `repartitionRequired` flag is always `true` for [groupBy](KStreamImpl.md#groupBy).
