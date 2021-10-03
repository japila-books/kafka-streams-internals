# KafkaStreams

`KafkaStreams` is the execution environment of a Kafka Streams application.

`KafkaStreams` is a Kafka client for continuous stream processing (on input coming from one or more input topics and sending output to zero, one, or more output topics).

## Creating Instance

`KafkaStreams` takes the following to be created:

* <span id="internalTopologyBuilder"><span id="topology"> `InternalTopologyBuilder` (or [Topology](Topology.md))
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="clientSupplier"> [KafkaClientSupplier](KafkaClientSupplier.md) (default: `DefaultKafkaClientSupplier`)
* <span id="time"> `Time`

### <span id="defaultStreamsUncaughtExceptionHandler"> defaultStreamsUncaughtExceptionHandler

```java
void defaultStreamsUncaughtExceptionHandler(
  Throwable throwable)
```

`defaultStreamsUncaughtExceptionHandler`...FIXME

## <span id="setUncaughtExceptionHandler"> setUncaughtExceptionHandler

```java
void setUncaughtExceptionHandler(
  StreamsUncaughtExceptionHandler streamsUncaughtExceptionHandler)
```

`setUncaughtExceptionHandler`...FIXME

`setUncaughtExceptionHandler` is part of the public API.

## <span id="handleStreamsUncaughtException"> handleStreamsUncaughtException

```java
void handleStreamsUncaughtException(
  Throwable throwable,
  StreamsUncaughtExceptionHandler streamsUncaughtExceptionHandler)
```

`handleStreamsUncaughtException`...FIXME

`handleStreamsUncaughtException` is used when:

* `KafkaStreams` is requested to [setUncaughtExceptionHandler](#setUncaughtExceptionHandler) and [defaultStreamsUncaughtExceptionHandler](#defaultStreamsUncaughtExceptionHandler)

### <span id="replaceStreamThread"> replaceStreamThread

```java
void replaceStreamThread(
  Throwable throwable)
```

`replaceStreamThread`...FIXME

### <span id="addStreamThread"> addStreamThread

```java
Optional<String> addStreamThread()
```

`addStreamThread`...FIXME

## <span id="createAndAddStreamThread"> createAndAddStreamThread

```java
StreamThread createAndAddStreamThread(
  long cacheSizePerThread,
  int threadIdx)
```

`createAndAddStreamThread` [creates a StreamThread](processor/StreamThread.md#create) and requests it to [setStateListener](processor/StreamThread.md#setStateListener) with the [StreamStateListener](#streamStateListener).

`createAndAddStreamThread` registers the `StreamThread` in the [threads](#threads) and the [threadState](#threadState) internal registries.

`createAndAddStreamThread` requests the [QueryableStoreProvider](#queryableStoreProvider) to [addStoreProviderForThread](state/QueryableStoreProvider.md#addStoreProviderForThread) (with the name of the `StreamThread` and a new `StreamThreadStateStoreProvider`).

`createAndAddStreamThread` is used when:

* `KafkaStreams` is [created](#creating-instance) and requested to [addStreamThread](#addStreamThread)
