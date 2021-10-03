# StreamThread

`StreamThread` is a `Thread` ([Java]({{ java.api }}/java/lang/Thread.html)).

## Creating Instance

`StreamThread` takes the following to be created:

* <span id="time"> `Time`
* <span id="config"> [StreamsConfig](../StreamsConfig.md)
* <span id="adminClient"> `Admin`
* <span id="mainConsumer"> Main `Consumer<byte[], byte[]>`
* <span id="restoreConsumer"> Restore `Consumer<byte[], byte[]>`
* <span id="changelogReader"> `ChangelogReader`
* <span id="originalReset"> `originalReset`
* <span id="taskManager"> [TaskManager](TaskManager.md)
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="builder"> `InternalTopologyBuilder`
* <span id="threadId"> Thread ID
* <span id="logContext"> `LogContext`
* <span id="assignmentErrorCode"> `assignmentErrorCode`
* <span id="nextProbingRebalanceMs"> `nextProbingRebalanceMs`
* <span id="shutdownErrorHook"> Shutdown Error Hook
* <span id="streamsUncaughtExceptionHandler"> `java.util.function.Consumer<Throwable>`
* <span id="cacheResizer"> `java.util.function.Consumer<Long>`

`StreamThread` is created using [create](#create) utility.

## <span id="create"> Creating StreamThread

```java
StreamThread create(
  InternalTopologyBuilder builder,
  StreamsConfig config,
  KafkaClientSupplier clientSupplier,
  Admin adminClient,
  UUID processId,
  String clientId,
  StreamsMetricsImpl streamsMetrics,
  Time time,
  StreamsMetadataState streamsMetadataState,
  long cacheSizeBytes,
  StateDirectory stateDirectory,
  StateRestoreListener userStateRestoreListener,
  int threadIdx,
  Runnable shutdownErrorHook,
  java.util.function.Consumer<Throwable> streamsUncaughtExceptionHandler)
```

`create` prints out the following INFO message to the logs:

```text
Creating restore consumer client
```

`create` requests the given `StreamsConfig` for the [restore consumer configs](../StreamsConfig.md#getRestoreConsumerConfigs) (with [getRestoreConsumerClientId](#getRestoreConsumerClientId)) and requests the given [KafkaClientSupplier](../KafkaClientSupplier.md) for a [restore consumer](../KafkaClientSupplier.md#getRestoreConsumer).

`create` creates a [StoreChangelogReader](StoreChangelogReader.md).

`create` creates a [ThreadCache](../state/ThreadCache.md).

`create` creates a [ActiveTaskCreator](ActiveTaskCreator.md), a [StandbyTaskCreator](StandbyTaskCreator.md) and a [TaskManager](TaskManager.md).

`create` prints out the following INFO message to the logs:

```text
Creating consumer client
```

`create`...FIXME

`create` is used when:

* `KafkaStreams` is requested to [createAndAddStreamThread](../KafkaStreams.md#createAndAddStreamThread)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.StreamThread` logger to see what happens inside.

Add the following line to `conf/log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.StreamThread=ALL
```

Refer to [Logging](../logging.md).
