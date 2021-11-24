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

## <span id="activeTasksPerPartition"> activeTasksPerPartition

```java
Map<TopicPartition, Task> activeTasksPerPartition
```

`Tasks` defines an `activeTasksPerPartition` registry of [Task](Task.md)s that handle (records of) a `TopicPartition`.

A new `Task` can be added in [createTasks](#createTasks), [convertStandbyToActive](#convertStandbyToActive), [updateInputPartitionsAndResume](#updateInputPartitionsAndResume)

One or more `Task`s can be removed in [convertActiveToStandby](#convertActiveToStandby), [updateInputPartitionsAndResume](#updateInputPartitionsAndResume), [removeTaskBeforeClosing](#removeTaskBeforeClosing) and [clear](#clear).

A `Task` can be looked up using [activeTasksForInputPartition](#activeTasksForInputPartition).

### <span id="activeTasksForInputPartition"> activeTasksForInputPartition

```java
Task activeTasksForInputPartition(
  TopicPartition partition)
```

`activeTasksForInputPartition` looks up the [Task](Task.md) for the given `TopicPartition` in the [activeTasksPerPartition](#activeTasksPerPartition) registry.

`activeTasksForInputPartition` is used when:

* `TaskManager` is requested to [add records to active tasks](TaskManager.md#addRecordsToTasks)
