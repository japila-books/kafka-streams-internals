# Tasks

## <span id="createTasks"> createTasks

```java
void createTasks(
  Map<TaskId, Set<TopicPartition>> activeTasksToCreate,
  Map<TaskId, Set<TopicPartition>> standbyTasksToCreate)
```

`createTasks`...FIXME

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
