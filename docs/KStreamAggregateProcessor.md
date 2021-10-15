# KStreamAggregateProcessor

`KStreamAggregateProcessor` is an [AbstractProcessor](AbstractProcessor.md).

## Creating Instance

`KStreamAggregateProcessor` takes no arguments to be created.

`KStreamAggregateProcessor` is created when:

* `KStreamAggregate` is requested for a [Processor](KStreamAggregate.md#get)

## <span id="KStreamAggregate"> KStreamAggregate

`KStreamAggregateProcessor` is a `private class` of [KStreamAggregate](KStreamAggregate.md) and so have access to the internal properties (e.g. [state name](KStreamAggregate.md#storeName)) thereof.

## <span id="store"> TimestampedKeyValueStore

`KStreamAggregateProcessor` [looks up](processor/ProcessorContext.md#getStateStore) a [TimestampedKeyValueStore](state/TimestampedKeyValueStore.md) by the name given when the owning [KStreamAggregate](KStreamAggregate.md#storeName) was created.

The `TimestampedKeyValueStore` is used for the following:

* Create a [TimestampedTupleForwarder](#tupleForwarder) (in [init](#init))
* [Process](#process) a key-value record (using a [ValueAndTimestamp](state/ValueAndTimestamp.md))

## <span id="init"> Initializing

```scala
void init(
  ProcessorContext context)
```

`init`...FIXME

`init` is part of the [AbstractProcessor](AbstractProcessor.md#init) abstraction.

## <span id="process"> Processing Record

```scala
void process(
  K key, 
  V value)
```

`process`...FIXME

`process` is part of the [AbstractProcessor](AbstractProcessor.md#process) abstraction.
