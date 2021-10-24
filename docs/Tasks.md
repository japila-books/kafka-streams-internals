# Tasks

## <span id="createTasks"> createTasks

```java
void createTasks(
  Map<TaskId, Set<TopicPartition>> activeTasksToCreate,
  Map<TaskId, Set<TopicPartition>> standbyTasksToCreate)
```

`createTasks` requests the [ActiveTaskCreator](#activeTaskCreator) to [create active tasks](ActiveTaskCreator.md#createTasks) (in the given `activeTasksToCreate` collection). `createTasks` registers the tasks in the [activeTasksPerId](#activeTasksPerId) and [allTasksPerId](#allTasksPerId) registries. `createTasks` registers the [inputPartitions](Task.md#inputPartitions) of the tasks in the [activeTasksPerPartition](#activeTasksPerPartition).

`createTasks` requests the [StandbyTaskCreator](#standbyTaskCreator) to [create standby tasks](StandbyTaskCreator.md#createTasks) (in the given `standbyTasksToCreate` collection). `createTasks` registers the tasks in the [standbyTasksPerId](#standbyTasksPerId) and [allTasksPerId](#allTasksPerId) registries.

`createTasks` is used when:

* `TaskManager` is requested to [handleAssignment](TaskManager.md#handleAssignment)

## <span id="convertStandbyToActive"> convertStandbyToActive

```java
void convertStandbyToActive(
  StandbyTask standbyTask,
  Set<TopicPartition> partitions)
```

`convertStandbyToActive`...FIXME

`convertStandbyToActive` is used when:

* `TaskManager` is requested to [handleCloseAndRecycle](TaskManager.md#handleCloseAndRecycle)
