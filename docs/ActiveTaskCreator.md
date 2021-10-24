# ActiveTaskCreator

## <span id="createTasks"> createTasks

```java
Collection<Task> createTasks(
  Consumer<byte[], byte[]> consumer,
  Map<TaskId, Set<TopicPartition>> tasksToBeCreated)
```

For every [TaskId](TaskId.md) and `TopicPartition`s pair (in the given `tasksToBeCreated` collection), `createTasks` requests the [InternalTopologyBuilder](#builder) to [buildSubtopology](InternalTopologyBuilder.md#buildSubtopology). `createTasks` [createActiveTask](#createActiveTask) (with the [ProcessorTopology](processor/ProcessorTopology.md), a new [ProcessorStateManager](processor/ProcessorStateManager.md) and [ProcessorContextImpl](processor/ProcessorContextImpl.md)).

In the end, `createTasks` returns the newly-created [StreamTask](StreamTask.md)s.

`createTasks` is used when:

* `Tasks` is requested to [createTasks](Tasks.md#createTasks)

## <span id="createActiveTaskFromStandby"> createActiveTaskFromStandby

```java
StreamTask createActiveTaskFromStandby(
  StandbyTask standbyTask,
  Set<TopicPartition> inputPartitions,
  Consumer<byte[], byte[]> consumer)
```

`createActiveTaskFromStandby`...FIXME

`createActiveTaskFromStandby` is used when:

* `Tasks` is requested to [convertStandbyToActive](Tasks.md#convertStandbyToActive)

## <span id="createActiveTask"> createActiveTask

```java
StreamTask createActiveTask(
  TaskId taskId,
  Set<TopicPartition> inputPartitions,
  Consumer<byte[], byte[]> consumer,
  LogContext logContext,
  ProcessorTopology topology,
  ProcessorStateManager stateManager,
  InternalProcessorContext context)
```

`createActiveTask`...FIXME

`createActiveTask` is used when:

* `ActiveTaskCreator` is requested to [createTasks](#createTasks) and [createActiveTaskFromStandby](#createActiveTaskFromStandby)
