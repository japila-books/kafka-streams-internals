# StreamThread

`StreamThread` is a `Thread` ([Java]({{ java.api }}/java/lang/Thread.html)).

## Creating Instance

`StreamThread` takes the following to be created:

* <span id="time"> `Time`
* <span id="config"> [StreamsConfig](StreamsConfig.md)
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

## <span id="commitTimeMs"><span id="commit.interval.ms"> commit.interval.ms

`StreamThread` uses [commit.interval.ms](StreamsConfig.md#COMMIT_INTERVAL_MS_CONFIG) configuration property to [control whether to commit tasks or not](#maybeCommit).

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

`create` creates a new [ReferenceContainer](ReferenceContainer.md) with the given arguments:

* `Admin` client ([Apache Kafka]({{ book.kafka }}/clients/admin/Admin))
* [StreamsMetadataState](StreamsMetadataState.md)
* `Time`

`create` prints out the following INFO message to the logs:

```text
Creating restore consumer client
```

`create` requests the given `StreamsConfig` for the [restore consumer configs](StreamsConfig.md#getRestoreConsumerConfigs) (with [getRestoreConsumerClientId](#getRestoreConsumerClientId)) and requests the given [KafkaClientSupplier](KafkaClientSupplier.md) for a [restore consumer](KafkaClientSupplier.md#getRestoreConsumer).

`create` creates a [StoreChangelogReader](processor/StoreChangelogReader.md).

`create` creates a [ThreadCache](state/ThreadCache.md).

`create` creates a [ActiveTaskCreator](ActiveTaskCreator.md), a [StandbyTaskCreator](StandbyTaskCreator.md) and a [TaskManager](TaskManager.md).

`create` prints out the following INFO message to the logs:

```text
Creating consumer client
```

`create`...FIXME

`create` is used when:

* `KafkaStreams` is requested to [createAndAddStreamThread](KafkaStreams.md#createAndAddStreamThread)

## <span id="run"> Starting Execution

```java
void run()
```

`run`...FIXME

`run` is part of the `Thread` ([Java]({{ java.api }}/java/lang/Thread.html#run())) abstraction.

### <span id="runLoop"> runLoop

```java
void runLoop()
```

`runLoop`...FIXME

### <span id="runOnce"> runOnce

```java
void runOnce()
```

`runOnce`...FIXME

### <span id="maybeCommit"> maybeCommit

```java
int maybeCommit()
```

`maybeCommit` checks out whether to commit active and standby tasks (based on the last commit time and [commit.interval.ms](#commitTimeMs)).

If the last commit happened enough long ago, `maybeCommit` prints out the following DEBUG message to the logs:

```text
Committing all active tasks [ids] and standby tasks [ids] since [time]ms has elapsed (commit interval is [time]ms)
```

`maybeCommit` requests the [TaskManager](#taskManager) to [commit](TaskManager.md#commit) the tasks that are `RUNNING` or `RESTORING`.

If there were offsets committed, `maybeCommit` requests the [TaskManager](#taskManager) to [maybePurgeCommittedRecords](TaskManager.md#maybePurgeCommittedRecords). Otherwise, `maybeCommit` prints out the following DEBUG message to the logs:

```text
Unable to commit as we are in the middle of a rebalance, will try again when it completes.
```

If the last commit happened fairly recently, `maybeCommit` merely requests the [TaskManager](#taskManager) to [maybeCommitActiveTasksPerUserRequested](TaskManager.md#maybeCommitActiveTasksPerUserRequested)

Either way, in the end, `maybeCommit` returns the number of committed offsets.

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.StreamThread` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.StreamThread=ALL
```

Refer to [Logging](logging.md).
