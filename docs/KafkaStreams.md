# KafkaStreams

`KafkaStreams` is the execution environment of a Kafka Streams application.

`KafkaStreams` is a Kafka client for continuous stream processing (on input coming from one or more input topics and sending output to zero, one, or more output topics).

## Creating Instance

`KafkaStreams` takes the following to be created:

* <span id="internalTopologyBuilder"><span id="topology"> [InternalTopologyBuilder](processor/InternalTopologyBuilder.md) (or [Topology](Topology.md))
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="clientSupplier"> [KafkaClientSupplier](KafkaClientSupplier.md) (default: `DefaultKafkaClientSupplier`)
* <span id="time"> `Time`

When created, `KafkaStreams` requests the given [InternalTopologyBuilder](processor/InternalTopologyBuilder.md) to [rewriteTopology](processor/InternalTopologyBuilder.md#rewriteTopology) followed by building a [task](#taskTopology) and [global task](#globalTaskTopology) topologies.

`KafkaStreams` then...FIXME

### <span id="defaultStreamsUncaughtExceptionHandler"> defaultStreamsUncaughtExceptionHandler

```java
void defaultStreamsUncaughtExceptionHandler(
  Throwable throwable)
```

`defaultStreamsUncaughtExceptionHandler`...FIXME

## <span id="taskTopology"> Task Topology

`KafkaStreams` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [build a task topology](processor/InternalTopologyBuilder.md#buildTopology) when [created](#creating-instance).

The [ProcessorTopology](processor/ProcessorTopology.md) can have [persistent local stores](processor/ProcessorTopology.md#hasPersistentLocalStore).

## <span id="globalTaskTopology"> Global Task Topology

When [created](#creating-instance) `KafkaStreams` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [build a global task topology](processor/InternalTopologyBuilder.md#buildGlobalStateTopology).

## <span id="threads"> StreamThreads

`KafkaStreams` manages [StreamThread](processor/StreamThread.md)s in a `threads` internal registry.

The `threads` collection starts empty when `KafkaStreams` is [created](#creating-instance).

`KafkaStreams` adds a new `StreamThread` when requested to [createAndAddStreamThread](#createAndAddStreamThread).

A `StreamThread` is removed when `KafkaStreams` is requested for the following:

* [defaultStreamsUncaughtExceptionHandler](#defaultStreamsUncaughtExceptionHandler)
* [addStreamThread](#addStreamThread)
* [removeStreamThread](#removeStreamThread)
* [getNumLiveStreamThreads](#getNumLiveStreamThreads)
* [getNextThreadIndex](#getNextThreadIndex)

`KafkaStreams` uses [processStreamThread](#processStreamThread) to work with the `StreamThread`s.

### <span id="processStreamThread"> processStreamThread

```java
void processStreamThread(
  java.util.function.Consumer<StreamThread> consumer)
```

`processStreamThread`...FIXME

### <span id="getNumLiveStreamThreads"> getNumLiveStreamThreads

```java
int getNumLiveStreamThreads()
```

`getNumLiveStreamThreads`...FIXME

## <span id="globalStreamThread"> GlobalStreamThread

`KafkaStreams` can use a [GlobalStreamThread](processor/GlobalStreamThread.md) if...FIXME

## <span id="start"> Starting Streams Client

```java
void start()
```

`start` attempts to enter `REBALANCING` state and, if successful, prints out the following INFO message to the logs:

```text
State transition from [oldState] to REBALANCING
```

`start` prints out the following DEBUG message to the logs:

```text
Starting Streams client
```

`start` requests the [GlobalStreamThread](#globalStreamThread) to [start](processor/GlobalStreamThread.md#start) (if defined).

`start` requests all the [StreamThreads](#threads) to [start](processor/StreamThread.md#run).

`start`...FIXME

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

## <span id="addStreamThread"> addStreamThread

```java
Optional<String> addStreamThread()
```

`addStreamThread`...FIXME

`addStreamThread` is part of the public API.

## <span id="createAndAddStreamThread"> createAndAddStreamThread

```java
StreamThread createAndAddStreamThread(
  long cacheSizePerThread,
  int threadIdx)
```

`createAndAddStreamThread` [creates a StreamThread](processor/StreamThread.md#create) and requests it to [setStateListener](processor/StreamThread.md#setStateListener) with the [StreamStateListener](#streamStateListener).

`createAndAddStreamThread` registers the `StreamThread` (in the [threads](#threads) and [threadState](#threadState) internal registries).

`createAndAddStreamThread` requests the [QueryableStoreProvider](#queryableStoreProvider) to [addStoreProviderForThread](state/QueryableStoreProvider.md#addStoreProviderForThread) (with the name of the `StreamThread` and a new `StreamThreadStateStoreProvider`).

`createAndAddStreamThread` is used when:

* `KafkaStreams` is [created](#creating-instance) and requested to [addStreamThread](#addStreamThread)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.KafkaStreams` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.KafkaStreams=ALL
```

Refer to [Logging](logging.md).
