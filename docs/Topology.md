# Topology

`Topology` is a logical representation of a [ProcessorTopology](processor/ProcessorTopology.md).

`Topology` is a facade to [InternalTopologyBuilder](InternalTopologyBuilder.md) (with all methods delegating to it).

## Creating Instance

`Topology` takes no arguments to be created.

`Topology` is a part of the public API of Kafka Streams and can be created directly or indirectly for [StreamsBuilder](kstream/StreamsBuilder.md#topology).

## <span id="internalTopologyBuilder"> InternalTopologyBuilder

`Topology` creates an [InternalTopologyBuilder](InternalTopologyBuilder.md) when [created](#creating-instance).

## <span id="addGlobalStore"> addGlobalStore

```java
<KIn, VIn> Topology addGlobalStore(
  StoreBuilder<?> storeBuilder,
  String sourceName,
  TimestampExtractor timestampExtractor,
  Deserializer<KIn> keyDeserializer,
  Deserializer<VIn> valueDeserializer,
  String topic,
  String processorName,
  ProcessorSupplier<KIn, VIn, Void, Void> stateUpdateSupplier)
```

`addGlobalStore` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [add a global store](InternalTopologyBuilder.md#addGlobalStore).

## <span id="addProcessor"> addProcessor

```java
Topology addProcessor(
  String name,
  ProcessorSupplier<KIn, VIn, KOut, VOut> supplier,
  String... parentNames)
```

`addProcessor` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [add a processor](InternalTopologyBuilder.md#addProcessor).

If there are any state stores associated with the processor, `addProcessor` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [add them](InternalTopologyBuilder.md#addStateStore).

## <span id="addSource"> addSource

```java
Topology addSource(...) // (1)
```

1. There are over 10 different `addSource`s

`addSource` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [add a new source (node)](InternalTopologyBuilder.md#addSource) (with the given arguments).

## Demo

```scala
import org.apache.kafka.streams.Topology
val topology = new Topology
```
