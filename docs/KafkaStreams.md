# KafkaStreams

`KafkaStreams` is the execution environment of a single instance of a Kafka Streams application (_KafkaStreams instance_).

`KafkaStreams` is a Kafka client for continuous stream processing (on input coming from one or more input topics and sending output to zero, one, or more output topics).

## Creating Instance

`KafkaStreams` takes the following to be created:

* <span id="internalTopologyBuilder"><span id="topology"> [InternalTopologyBuilder](InternalTopologyBuilder.md) (or [Topology](Topology.md))
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="clientSupplier"> [KafkaClientSupplier](KafkaClientSupplier.md) (default: `DefaultKafkaClientSupplier`)
* <span id="time"> `Time`

When created, `KafkaStreams` requests the given [InternalTopologyBuilder](InternalTopologyBuilder.md) to [rewriteTopology](InternalTopologyBuilder.md#rewriteTopology) followed by building a [task](#taskTopology) and [global task](#globalTaskTopology) topologies.

`KafkaStreams` then...FIXME

### <span id="defaultStreamsUncaughtExceptionHandler"> defaultStreamsUncaughtExceptionHandler

```java
void defaultStreamsUncaughtExceptionHandler(
  Throwable throwable)
```

`defaultStreamsUncaughtExceptionHandler`...FIXME

## <span id="taskTopology"> Task Topology

`KafkaStreams` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [build a task topology](InternalTopologyBuilder.md#buildTopology) when [created](#creating-instance).

The [ProcessorTopology](processor/ProcessorTopology.md) can have [persistent local stores](processor/ProcessorTopology.md#hasPersistentLocalStore).

## <span id="globalTaskTopology"> Global Task Topology

When [created](#creating-instance) `KafkaStreams` requests the [InternalTopologyBuilder](#internalTopologyBuilder) to [build a global task topology](InternalTopologyBuilder.md#buildGlobalStateTopology).

## <span id="threads"> StreamThreads

`KafkaStreams` manages [StreamThread](StreamThread.md)s in a `threads` internal registry.

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

`start` requests all the [StreamThreads](#threads) to [start](StreamThread.md#run).

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

`createAndAddStreamThread` [creates a StreamThread](StreamThread.md#create) and requests it to [setStateListener](StreamThread.md#setStateListener) with the [StreamStateListener](#streamStateListener).

`createAndAddStreamThread` registers the `StreamThread` (in the [threads](#threads) and [threadState](#threadState) internal registries).

`createAndAddStreamThread` requests the [QueryableStoreProvider](#queryableStoreProvider) to [addStoreProviderForThread](state/QueryableStoreProvider.md#addStoreProviderForThread) (with the name of the `StreamThread` and a new `StreamThreadStateStoreProvider`).

`createAndAddStreamThread` is used when:

* `KafkaStreams` is [created](#creating-instance) and requested to [addStreamThread](#addStreamThread)

## <span id="streamsMetadataState"> StreamsMetadataState

`KafkaStreams` creates a new [StreamsMetadataState](StreamsMetadataState.md) when [created](#creating-instance) (with the endpoint based on [application.server](StreamsConfig.md#APPLICATION_SERVER_CONFIG) configuration property).

The `StreamsMetadataState` is used to [create a StreamThread](#createAndAddStreamThread) and for the following state-related metadata operators:

* [metadataForAllStreamsClients](#metadataForAllStreamsClients)
* [streamsMetadataForStore](#streamsMetadataForStore)
* [queryMetadataForKey](#queryMetadataForKey)
* [allLocalStorePartitionLags](#allLocalStorePartitionLags)

### <span id="queryMetadataForKey"> queryMetadataForKey

```java
KeyQueryMetadata queryMetadataForKey(
  String storeName,
  K key,
  Serializer<K> keySerializer)
KeyQueryMetadata queryMetadataForKey(
  String storeName,
  K key,
  StreamPartitioner<? super K, ?> partitioner)
```

`queryMetadataForKey` requests the [StreamsMetadataState](#streamsMetadataState) to [getKeyQueryMetadataForKey](StreamsMetadataState.md#getKeyQueryMetadataForKey).

## <span id="metrics"> Performance Metrics

`KafkaStreams` [gets the configured metrics](#getMetrics) when [created](#creating-instance).

`KafkaStreams` uses the metrics to create a [StreamsMetricsImpl](#streamsMetrics) right after.

### <span id="rocksDBMetricsRecordingService"> Metrics Recording Service

`KafkaStreams` [may create](#maybeCreateRocksDBMetricsRecordingService) a `rocksDBMetricsRecordingService` executor service ([ScheduledExecutorService]({{ java.api }}/java/util/concurrent/ScheduledExecutorService.html)) when [created](#creating-instance) (and the value of [metrics.recording.level](StreamsConfig.md#metrics.recording.level) configuration property is `DEBUG`).

`KafkaStreams` uses the `ScheduledExecutorService` to submit a [RocksDBMetricsRecordingTrigger](metrics/StreamsMetricsImpl.md#rocksDBMetricsRecordingTrigger) (of the [StreamsMetricsImpl](streamsMetrics)) to be executed every 1 minute (non-configurable).

The `ScheduledExecutorService` is shut down when [shutdownHelper](#shutdownHelper).

#### <span id="maybeCreateRocksDBMetricsRecordingService"> maybeCreateRocksDBMetricsRecordingService

```java
ScheduledExecutorService maybeCreateRocksDBMetricsRecordingService(
  String clientId,
  StreamsConfig config)
```

Only with [metrics.recording.level](StreamsConfig.md#metrics.recording.level) configuration property as `DEBUG`, `maybeCreateRocksDBMetricsRecordingService` creates a single-threaded executor.
The name of this one daemon thread is as follows:

```text
[clientId]-RocksDBMetricsRecordingTrigger
```

### <span id="streamsMetrics"><span id="ClientMetrics"> StreamsMetricsImpl

When [created](#creating-instance), `KafkaStreams` creates a [StreamsMetricsImpl](metrics/StreamsMetricsImpl.md) (based on the [configured metrics](#getMetrics) and [built.in.metrics.version](StreamsConfig.md#built.in.metrics.version) configuration property).

`KafkaStreams` registers [ClientMetrics](metrics/ClientMetrics.md).

`KafkaStreams` passes the `StreamsMetricsImpl` in while creating a [GlobalStreamThread](#globalStreamThread) and [StreamThread](#createAndAddStreamThread)s.

When [started](#start), `KafkaStreams` requests the `StreamsMetricsImpl` for [rocksDBMetricsRecordingTrigger](metrics/StreamsMetricsImpl.md#rocksDBMetricsRecordingTrigger) (to schedule it at fixed rate using the [Metrics Recording Service](#rocksDBMetricsRecordingService)).

#### <span id="getMetrics"> getMetrics

```java
Metrics getMetrics(
  StreamsConfig config,
  Time time,
  String clientId)
```

`getMetrics` creates a `MetricConfig` ([Apache Kafka]({{ book.kafka }}/metrics/MetricConfig)) based on the following configuration properties:

* [metrics.num.samples](StreamsConfig.md#METRICS_NUM_SAMPLES_CONFIG)
* [metrics.recording.level](StreamsConfig.md#METRICS_RECORDING_LEVEL_CONFIG)
* [metrics.sample.window.ms](StreamsConfig.md#METRICS_SAMPLE_WINDOW_MS_CONFIG)

`getMetrics` requests the given [StreamsConfig](StreamsConfig.md) for configured `MetricsReporter`s ([Apache Kafka]({{ book.kafka }}/metrics/MetricsReporter)) per [metric.reporters](StreamsConfig.md#METRIC_REPORTER_CLASSES_CONFIG) configuration property.

`getMetrics` always adds `JmxReporter` to the list of configured `MetricsReporter`s. `JmxReporter` is configured to use `kafka.streams` JMX prefix.

In the end, `getMetrics` creates a `Metrics` ([Apache Kafka]({{ book.kafka }}/metrics/Metrics)) (with the `MetricConfig`, the `MetricsReporter`s, et al.)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.KafkaStreams` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.KafkaStreams=ALL
```

Refer to [Logging](logging.md).
