# Stores

`Stores` utility is a factory for creating state stores in Kafka Streams.

## <span id="inMemoryWindowStore"> inMemoryWindowStore

```java
WindowBytesStoreSupplier inMemoryWindowStore(
  String name,
  Duration retentionPeriod,
  Duration windowSize,
  boolean retainDuplicates)
```

`inMemoryWindowStore`...FIXME

`inMemoryWindowStore` is used when:

* `KStreamImplJoin` is requested to `sharedOuterJoinWindowStoreBuilder` (for left outer join)

## <span id="keyValueStoreBuilder"> keyValueStoreBuilder

```java
StoreBuilder<KeyValueStore<K, V>> keyValueStoreBuilder(
  KeyValueBytesStoreSupplier supplier,
  Serde<K> keySerde,
  Serde<V> valueSerde)
```

`keyValueStoreBuilder` creates a [KeyValueStoreBuilder](KeyValueStoreBuilder.md) (with the given arguments and `Time.SYSTEM`).

## Demo

This demo uses the [Processor API](../processor/index.md) to add a [StoreBuilder](StoreBuilder.md) to a [Topology](../Topology.md). Once created with the [Stores](Stores.md) utility, the `StoreBuilder` is then attached to a [Processor](../processor/Processor.md) using [Topology.addStateStore](../Topology.md#addStateStore).

### Create Topology

```scala
import org.apache.kafka.streams.Topology
val builder = new Topology()
```

### Add Processor

```scala
val processorName = "my-custom-processor"
topology.addProcessor(processorName, ...);
```

### Create StoreBuilder

```scala
import org.apache.kafka.streams.state.Stores
val storeBuilder = Stores.keyValueStoreBuilder(...)
```

### Attach Processor to StateStore(Builder)

```scala
builder.addStateStore(storeBuilder, processorName)
```
