# TaskManager

## Creating Instance

`TaskManager` takes the following to be created:

* <span id="time"> `Time`
* <span id="changelogReader"> [ChangelogReader](ChangelogReader.md)
* <span id="processId"> Process UUID
* <span id="logPrefix"> Log Prefix
* <span id="streamsMetrics"> [StreamsMetricsImpl](metrics/StreamsMetricsImpl.md)
* [ActiveTaskCreator](#activeTaskCreator)
* <span id="standbyTaskCreator"> [StandbyTaskCreator](StandbyTaskCreator.md)
* <span id="builder"> [InternalTopologyBuilder](InternalTopologyBuilder.md)
* <span id="adminClient"> `Admin` Client ([Apache Kafka]({{ book.kafka }}/clients/admin/Admin))
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="processingMode"> `StreamThread.ProcessingMode`

`TaskManager` is created when:

* `StreamThread` utility is used to [create a StreamThread](StreamThread.md#create)

## <span id="tasks"> Tasks

`TaskManager` creates a [Tasks](Tasks.md) when [created](#creating-instance).

## <span id="activeTaskCreator"> ActiveTaskCreator

`TaskManager` is given an [ActiveTaskCreator](ActiveTaskCreator.md) when [created](#creating-instance).

`TaskManager` uses this `ActiveTaskCreator` (along with [StandbyTaskCreator](#standbyTaskCreator)) merely to create a [Tasks](#tasks).

## <span id="punctuate"> Punctuating Recurring Actions

```java
int punctuate()
```

`punctuate` requests every [active task](#activeTaskIterable) to [maybePunctuateStreamTime](Task.md#maybePunctuateStreamTime) and [maybePunctuateSystemTime](Task.md#maybePunctuateSystemTime) (counting punctuators that were executed).

`punctuate` is used when:

* `StreamThread` is requested to [runOnce](StreamThread.md#runOnce)

## <span id="commit"> Committing (Active) Tasks

```java
int commit(
  Collection<Task> tasksToCommit)
```

`commit` [commitAndFillInConsumedOffsetsAndMetadataPerTaskMap](#commitAndFillInConsumedOffsetsAndMetadataPerTaskMap) the given tasks.

In the end, `commit` returns consumed offsets and metadata per every committed task (`Map<Task, Map<TopicPartition, OffsetAndMetadata>>`).

`commit` is used when:

* `StreamThread` is requested to [maybeCommit](StreamThread.md#maybeCommit)
* `TaskManager` is requested to [maybeCommitActiveTasksPerUserRequested](#maybeCommitActiveTasksPerUserRequested)

## <span id="handleAssignment"> handleAssignment

```java
void handleAssignment(
  Map<TaskId, Set<TopicPartition>> activeTasks,
  Map<TaskId, Set<TopicPartition>> standbyTasks)
```

`handleAssignment` prints out the following INFO message to the logs:

```text
Handle new assignment with:
  New active tasks: [activeTasks]
  New standby tasks: [standbyTasks]
  Existing active tasks: [activeTaskIds]
  Existing standby tasks: [standbyTaskIds]
```

`handleAssignment` requests the [InternalTopologyBuilder](#builder) to [addSubscribedTopicsFromAssignment](InternalTopologyBuilder.md#addSubscribedTopicsFromAssignment) (with the `TopicPartition`s from the given `activeTasks`).

`handleAssignment` determines which [existing tasks](#tasks) to close (and remove) or recycle and [handleCloseAndRecycle](#handleCloseAndRecycle) them.

In the end, `handleAssignment` requests the [Tasks](#tasks) to [create active and standby tasks](Tasks.md#createTasks).

`handleAssignment` is used when:

* `StreamsPartitionAssignor` is requested to [onAssignment](StreamsPartitionAssignor.md#onAssignment)

### <span id="handleCloseAndRecycle"> handleCloseAndRecycle

```java
void handleCloseAndRecycle(
  Set<Task> tasksToRecycle,
  Set<Task> tasksToCloseClean,
  Set<Task> tasksToCloseDirty,
  Map<TaskId, Set<TopicPartition>> activeTasksToCreate,
  Map<TaskId, Set<TopicPartition>> standbyTasksToCreate,
  LinkedHashMap<TaskId, RuntimeException> taskCloseExceptions)
```

`handleCloseAndRecycle`...FIXME

## <span id="handleCorruption"> Handling TaskCorruptedException

```java
void handleCorruption(
  Set<TaskId> corruptedTasks)
```

`handleCorruption`...FIXME

`handleCorruption` is used when:

* `StreamThread` is requested to [runLoop](StreamThread.md#runLoop) (and caught a `TaskCorruptedException`)

## <span id="maybeCommitActiveTasksPerUserRequested"> maybeCommitActiveTasksPerUserRequested

```java
int maybeCommitActiveTasksPerUserRequested()
```

With [rebalance in progress](#rebalanceInProgress), `maybeCommitActiveTasksPerUserRequested` returns `-1` immediately.

Otherwise, `maybeCommitActiveTasksPerUserRequested` finds tasks (among [active tasks](#activeTaskIterable)) with [commitRequested](Task.md#commitRequested) or [commitNeeded](Task.md#commitNeeded) and, if there is at least one, [commits them](#commit).

`maybeCommitActiveTasksPerUserRequested` is used when:

* `StreamThread` is requested to [maybeCommit](StreamThread.md#maybeCommit)

## <span id="commitAndFillInConsumedOffsetsAndMetadataPerTaskMap"> commitAndFillInConsumedOffsetsAndMetadataPerTaskMap

```java
int commitAndFillInConsumedOffsetsAndMetadataPerTaskMap(
  Collection<Task> tasksToCommit,
  Map<Task, Map<TopicPartition, OffsetAndMetadata>> consumedOffsetsAndMetadataPerTask)
```

With [rebalance in progress](#rebalanceInProgress), `commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` returns `-1` immediately.

`commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` requests every [Task](Task.md) with [commitNeeded](Task.md#commitNeeded) (in the given `tasksToCommit` tasks) to [prepareCommit](Task.md#prepareCommit) (that gives offsets and metadata per partition). `commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` saves the offsets and metadata per partition for a task (that is [active](Task.md#isActive)) in the given `consumedOffsetsAndMetadataPerTask`.

`commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` [commitOffsetsOrTransaction](#commitOffsetsOrTransaction) (with the given `consumedOffsetsAndMetadataPerTask` that may have been updated with some active tasks as described above).

Once again, `commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` requests every [Task](Task.md) with [commitNeeded](Task.md#commitNeeded) (in the given `tasksToCommit` tasks) to [clearTaskTimeout](Task.md#clearTaskTimeout) and [postCommit](Task.md#postCommit) (with `enforceCheckpoint` flag disabled).

In the end, `commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` returns the number of tasks committed.

`commitAndFillInConsumedOffsetsAndMetadataPerTaskMap` is used when:

* `TaskManager` is requested to [commit](#commit) and [handle a TaskCorruptedException](#handleCorruption)

## Partition Rebalancing

### <span id="rebalanceInProgress"> rebalanceInProgress Flag

`TaskManager` uses `rebalanceInProgress` internal flag to indicate that it is in the middle of partition rebalancing (which is considered not safe to commit and used to skip [commitAndFillInConsumedOffsetsAndMetadataPerTaskMap](#commitAndFillInConsumedOffsetsAndMetadataPerTaskMap) and [maybeCommitActiveTasksPerUserRequested](#maybeCommitActiveTasksPerUserRequested)).

The `rebalanceInProgress` flag is disabled (`false`) initially. It is turned on (`true`) in [handleRebalanceStart](#handleRebalanceStart) and off in [handleRebalanceComplete](#handleRebalanceComplete).

### <span id="isRebalanceInProgress"> isRebalanceInProgress

```java
boolean isRebalanceInProgress()
```

`isRebalanceInProgress` returns the value of the internal [rebalanceInProgress](#rebalanceInProgress) flag.

`isRebalanceInProgress` is used when:

* `StreamThread` is requested to [run](StreamThread.md#runLoop)

### <span id="handleRebalanceStart"> handleRebalanceStart

```java
void handleRebalanceStart(
  Set<String> subscribedTopics)
```

`handleRebalanceStart` requests the [InternalTopologyBuilder](#builder) to [addSubscribedTopicsFromMetadata](InternalTopologyBuilder.md#addSubscribedTopicsFromMetadata) with the given `subscribedTopics`.

`handleRebalanceStart` [tryToLockAllNonEmptyTaskDirectories](#tryToLockAllNonEmptyTaskDirectories) and turns the [rebalanceInProgress](#rebalanceInProgress) internal flag on (`true`).

`handleRebalanceStart` is used when:

* `StreamsPartitionAssignor` is requested to [handleRebalanceStart](StreamsPartitionAssignor.md#handleRebalanceStart)

### <span id="handleRebalanceComplete"> handleRebalanceComplete

```java
void handleRebalanceComplete()
```

`handleRebalanceComplete` requests the [Consumer](#mainConsumer) to pause (_suspend_) fetching from the partitions that are assigned to this consumer.

`handleRebalanceComplete` [releaseLockedUnassignedTaskDirectories](#releaseLockedUnassignedTaskDirectories) and turns the [rebalanceInProgress](#rebalanceInProgress) internal flag off (`false`).

`handleRebalanceComplete` is used when:

* `StreamsPartitionAssignor` is requested to [onPartitionsAssigned](StreamsPartitionAssignor.md#onPartitionsAssigned)

## <span id="process"> Processing Records (with Active Stream Tasks)

```java
int process(
  int maxNumRecords,
  Time time)
```

`process` requests every [active StreamTask](#activeTaskIterable) to [process a record](Task.md#process) until the number of records processed (across all the active tasks) reaches the given `maxNumRecords` threshold or there are no more records to process.

`process` is used when:

* `StreamThread` is requested to [runOnce](StreamThread.md#runOnce) ([every iteration](StreamThread.md#runOnce-processing-tasks))

## <span id="tryToCompleteRestoration"> tryToCompleteRestoration

```java
boolean tryToCompleteRestoration(
  long now,
  java.util.function.Consumer<Set<TopicPartition>> offsetResetter)
```

`tryToCompleteRestoration`...FIXME

`tryToCompleteRestoration` is used when:

* `StreamThread` is requested to [initializeAndRestorePhase](StreamThread.md#initializeAndRestorePhase)

## <span id="addRecordsToTasks"> Adding Records to Active StreamTasks

```java
void addRecordsToTasks(
  ConsumerRecords<byte[], byte[]> records)
```

For every partition (in the given `records` registry of `ConsumerRecord`s), `addRecordsToTasks` [finds the active StreamTask that handles records of this partition](Tasks.md#activeTasksForInputPartition) and [passes the records](Task.md#addRecords) (for the partition).

!!! note
    A single active [Task](Task.md) is responsible for a single `TopicPartition`.

`addRecordsToTasks` is used when:

* `StreamThread` is requested to [poll for records](StreamThread.md#pollPhase)

## <span id="needsInitializationOrRestoration"> needsInitializationOrRestoration

```java
boolean needsInitializationOrRestoration()
```

`needsInitializationOrRestoration` is `true` if any of the [Tasks](#tasks) require [initialization or restoration](Task.md#needsInitializationOrRestoration).

`needsInitializationOrRestoration` is used when:

* `StreamThread` is requested to [initializeAndRestorePhase](StreamThread.md#initializeAndRestorePhase)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.TaskManager` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.TaskManager=ALL
```

Refer to [Logging](logging.md).
