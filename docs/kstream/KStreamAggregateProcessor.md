# KStreamAggregateProcessor

`KStreamAggregateProcessor` is an [AbstractProcessor](../processor/AbstractProcessor.md) of [KStreamAggregate](KStreamAggregate.md).

## Creating Instance

`KStreamAggregateProcessor` takes no arguments to be created.

`KStreamAggregateProcessor` is created when:

* `KStreamAggregate` is requested for a [Processor](KStreamAggregate.md#get)

## <span id="KStreamAggregate"> KStreamAggregate

`KStreamAggregateProcessor` is a `private class` of [KStreamAggregate](KStreamAggregate.md) and so have access to the internal properties (e.g. [state name](KStreamAggregate.md#storeName)) thereof.

## <span id="store"> TimestampedKeyValueStore

`KStreamAggregateProcessor` [looks up](../processor/ProcessorContext.md#getStateStore) a [TimestampedKeyValueStore](../state/TimestampedKeyValueStore.md) by the name given when the owning [KStreamAggregate](KStreamAggregate.md#storeName) was created.

The `TimestampedKeyValueStore` is used for the following:

* Create a [TimestampedTupleForwarder](#tupleForwarder) (in [init](#init))
* [Process](#process) a key-value record (using a [ValueAndTimestamp](../state/ValueAndTimestamp.md))

## <span id=tupleForwarder"> TimestampedTupleForwarder

`KStreamAggregateProcessor` creates a new [TimestampedTupleForwarder](TimestampedTupleForwarder.md) when [created](#creating-instance).

The `TimestampedTupleForwarder` is used when [processing a record](#process).

## <span id="init"> Initializing

```scala
void init(
  ProcessorContext context)
```

`init`...FIXME

`init` is part of the [AbstractProcessor](../processor/AbstractProcessor.md#init) abstraction.

## <span id="process"> Processing Record

```scala
void process(
  K key, 
  V value)
```

`process` requests the [TimestampedKeyValueStore](#store) for the [value](../state/ReadOnlyKeyValueStore.md#get) for the input key (that gives a [ValueAndTimestamp](../state/ValueAndTimestamp.md) if found).

With no previous value found, `process` requests the parent's [Initializer](KStreamAggregate.md#initializer) for the [initial value](Initializer.md#apply) and the [ProcessorContext](../processor/AbstractProcessor.md#context) for the [timestamp](../processor/ProcessorContext.md#timestamp).

`process` requests the parent's [Aggregator](KStreamAggregate.md#aggregator) for a [new aggregate](Aggregator.md#apply) for the input key and value (and the previous or newly-created aggregation).

`process` [creates a new ValueAndTimestamp](../state/ValueAndTimestamp.md#make) with the new aggregate and the timestamp and requests the [TimestampedKeyValueStore](#store) to [store it](../state/KeyValueStore.md#put) (for the key).

In the end, `process` requests the [TimestampedTupleForwarder](#tupleForwarder) to [maybeForward](TimestampedTupleForwarder.md#maybeForward).

---

`process` is part of the [AbstractProcessor](../processor/AbstractProcessor.md#process) abstraction.
