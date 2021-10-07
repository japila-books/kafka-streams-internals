# StreamsBuilder

`StreamsBuilder` provides the entry point to the [High-Level Kafka Streams DSL](kstream/index.md) to define and build a [stream processing topology](#topology).

## Creating Instance

`StreamsBuilder` takes no arguments to be created.

While being created, `StreamsBuilder` creates a [Topology](#topology) that in turn is requested for an [InternalTopologyBuilder](#internalTopologyBuilder). In the end, `StreamsBuilder` creates an [InternalStreamsBuilder](#internalStreamsBuilder).

## <span id="topology"> Topology

`StreamsBuilder` creates a [Topology](Topology.md) when [created](#creating-instance).

`StreamsBuilder` uses the `Topology` to create an [InternalTopologyBuilder](#internalTopologyBuilder).

The `Topology` is then optimized and returned when `StreamsBuilder` is requested to [build a topology](#build).

## <span id="build"> Building and Optimizing Topology

```java
Topology build() // (1)
Topology build(
  Properties props)
```

1. Uses undefined properties (`null`)

`build` requests the [InternalStreamsBuilder](#internalStreamsBuilder) to [build and optimize a topology](kstream/InternalStreamsBuilder.md#buildAndOptimizeTopology). In the end, `build` returns the [Topology](#topology).

`build` is part of the public API.
