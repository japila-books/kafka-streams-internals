# TimestampedTupleForwarder

`TimestampedTupleForwarder` is used by processors to [determine whether or not to forward records to child nodes](#maybeForward) (_downstream processors_) (that happens only with no [caching](#cachingEnabled)).

## Creating Instance

`TimestampedTupleForwarder` takes the following to be created:

* <span id="store"> [StateStore](../processor/StateStore.md)
* <span id="context"> [ProcessorContext](../processor/ProcessorContext.md)
* <span id="flushListener"> `TimestampedCacheFlushListener`
* <span id="sendOldValues"> `sendOldValues` flag

`TimestampedTupleForwarder` is created when:

* `KStreamAggregateProcessor` is requested to [initialize](KStreamAggregateProcessor.md#init)
* `KStreamSlidingWindowAggregateProcessor` is requested to [initialize](KStreamSlidingWindowAggregateProcessor.md#init)
* `KStreamWindowAggregateProcessor` is requested to [initialize](KStreamWindowAggregateProcessor.md#init)
* `KTableSource` is requested to [initialize](KTableSource.md#init)
* _others_

## <span id="cachingEnabled"> cachingEnabled Flag

`TimestampedTupleForwarder` requests the [StateStore](#store) to [setFlushListener](../state/WrappedStateStore.md#setFlushListener) when [created](#creating-instance). The returned value is used to initialize `cachingEnabled` internal flag for [maybeForward](#maybeForward).

## <span id="maybeForward"> maybeForward

```java
void maybeForward(
  K key,
  V newValue,
  V oldValue) // (1)
void maybeForward(
  K key,
  V newValue,
  V oldValue,
  long timestamp)
void maybeForward(
  Record<K, Change<V>> record)
```

`maybeForward` requests the [InternalProcessorContext](#context) to [forward a record](../processor/ProcessorContext.md#forward) only with the [cachingEnabled](#cachingEnabled) flag disabled.
