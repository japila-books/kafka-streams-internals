# TaskManager

## Creating Instance

`TaskManager` takes the following to be created:

* <span id="time"> `Time`
* <span id="changelogReader"> `ChangelogReader`
* <span id="processId"> Process UUID
* <span id="logPrefix"> Log Prefix
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="activeTaskCreator"> [ActiveTaskCreator](ActiveTaskCreator.md)
* <span id="standbyTaskCreator"> [StandbyTaskCreator](StandbyTaskCreator.md)
* <span id="builder"> `InternalTopologyBuilder`
* <span id="adminClient"> `Admin`
* <span id="stateDirectory"> `StateDirectory`
* <span id="processingMode"> `StreamThread.ProcessingMode`

`TaskManager` is created when:

* `StreamThread` utility is used to [create a StreamThread](StreamThread.md#create)

## <span id="rebalanceInProgress"> rebalanceInProgress Flag

`TaskManager` uses `rebalanceInProgress` internal flag to indicate that it is in the middle of a rebalance (which is not safe to commit, i.e. [commitAndFillInConsumedOffsetsAndMetadataPerTaskMap](#commitAndFillInConsumedOffsetsAndMetadataPerTaskMap) and [maybeCommitActiveTasksPerUserRequested](#maybeCommitActiveTasksPerUserRequested)).

The `rebalanceInProgress` flag is disabled initially. It is turned on (`true`) in [handleRebalanceStart](#handleRebalanceStart) and off in [handleRebalanceComplete](#handleRebalanceComplete).

### <span id="isRebalanceInProgress"> isRebalanceInProgress

```java
boolean isRebalanceInProgress()
```

`isRebalanceInProgress` returns the value of the internal [rebalanceInProgress](#rebalanceInProgress) flag.

`isRebalanceInProgress` is used when:

* `StreamThread` is requested to [run](StreamThread.md#runLoop)

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

`handleAssignment`...FIXME

`handleAssignment` is used when:

* `StreamsPartitionAssignor` is requested to [onAssignment](../StreamsPartitionAssignor.md#onAssignment)

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

## <span id="handleRebalanceStart"> handleRebalanceStart

```java
void handleRebalanceStart(
  Set<String> subscribedTopics)
```

`handleRebalanceStart` requests the [InternalTopologyBuilder](#builder) to [addSubscribedTopicsFromMetadata](InternalTopologyBuilder.md#addSubscribedTopicsFromMetadata) with the given `subscribedTopics`.

`handleRebalanceStart` [tryToLockAllNonEmptyTaskDirectories](#tryToLockAllNonEmptyTaskDirectories) and turns the [rebalanceInProgress](#rebalanceInProgress) internal flag on (`true`).

`handleRebalanceStart` is used when:

* `StreamsPartitionAssignor` is requested to [handleRebalanceStart](../StreamsPartitionAssignor.md#handleRebalanceStart)

## <span id="handleRebalanceComplete"> handleRebalanceComplete

```java
void handleRebalanceComplete()
```

`handleRebalanceComplete` requests the [Consumer](#mainConsumer) to pause (_suspend_) fetching from the partitions that are assigned to this consumer.

`handleRebalanceComplete` [releaseLockedUnassignedTaskDirectories](#releaseLockedUnassignedTaskDirectories) and turns the [rebalanceInProgress](#rebalanceInProgress) internal flag off (`false`).

`handleRebalanceComplete` is used when:

* `StreamsPartitionAssignor` is requested to [onPartitionsAssigned](../StreamsPartitionAssignor.md#onPartitionsAssigned)
