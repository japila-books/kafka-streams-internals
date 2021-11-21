# Tasks

## Creating Instance

`Tasks` takes the following to be created:

* <span id="logPrefix"> Log Prefix
* <span id="builder"> [InternalTopologyBuilder](InternalTopologyBuilder.md)
* <span id="streamsMetrics"> [StreamsMetricsImpl](metrics/StreamsMetricsImpl.md)
* <span id="activeTaskCreator"> [ActiveTaskCreator](ActiveTaskCreator.md)
* <span id="standbyTaskCreator"> [StandbyTaskCreator](StandbyTaskCreator.md)

`Tasks` is [created](#creating-instance) along with [TaskManager](TaskManager.md#tasks).

## <span id="mainConsumer"> Main Kafka Consumer

`Tasks` is given a Kafka `Consumer<byte[], byte[]>` (using [setMainConsumer](#setMainConsumer) method) for the following:

* [createTasks](#createTasks)
* [convertStandbyToActive](#convertStandbyToActive)

The idea is to pass this `Consumer` on to [StreamTask](StreamTask.md#mainConsumer)s.

### <span id="setMainConsumer"> setMainConsumer

```java
void setMainConsumer(
  Consumer<byte[], byte[]> mainConsumer)
```

`setMainConsumer` sets the [Main Kafka Consumer](#mainConsumer).

`setMainConsumer` is used when:

* `TaskManager` is requested to [setMainConsumer](TaskManager.md#setMainConsumer) (when `StreamThread` utility is used to [create a StreamThread](StreamThread.md#))

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
