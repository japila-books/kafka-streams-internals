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
