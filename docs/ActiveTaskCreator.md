# ActiveTaskCreator

`ActiveTaskCreator` is used by [TaskManager](TaskManager.md#activeTaskCreator).

## Creating Instance

`ActiveTaskCreator` takes the following to be created:

* <span id="builder"> [InternalTopologyBuilder](InternalTopologyBuilder.md)
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="streamsMetrics"> [StreamsMetricsImpl](metrics/StreamsMetricsImpl.md)
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="storeChangelogReader"> [ChangelogReader](ChangelogReader.md)
* <span id="cache"> [ThreadCache](state/ThreadCache.md)
* <span id="time"> `Time`
* <span id="clientSupplier"> [KafkaClientSupplier](KafkaClientSupplier.md)
* <span id="threadId"> Thread ID
* <span id="processId"> Process ID
* <span id="log"> `Logger`

`ActiveTaskCreator` is created when:

* `StreamThread` utility is used to [create a StreamThread](StreamThread.md#create) (for a [TaskManager](TaskManager.md#activeTaskCreator))

## <span id="threadProducer"> StreamsProducer

`ActiveTaskCreator` creates a [StreamsProducer](StreamsProducer.md) when [created](#creating-instance).

## <span id="createTasks"> createTasks

```java
Collection<Task> createTasks(
  Consumer<byte[], byte[]> consumer,
  Map<TaskId, Set<TopicPartition>> tasksToBeCreated)
```

For every [TaskId](TaskId.md) and `TopicPartition`s pair (in the given `tasksToBeCreated` collection), `createTasks` requests the [InternalTopologyBuilder](#builder) to [buildSubtopology](InternalTopologyBuilder.md#buildSubtopology). `createTasks` [createActiveTask](#createActiveTask) (with the [ProcessorTopology](processor/ProcessorTopology.md), a new [ProcessorStateManager](ProcessorStateManager.md) and [ProcessorContextImpl](processor/ProcessorContextImpl.md)).

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

## <span id="createActiveTask"> Creating Active StreamTask

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

`createActiveTask` determines whether to create a new [StreamsProducer](StreamsProducer.md) or use the [existing one](#threadProducer) based on [ProcessingMode](#processingMode) (`ProcessingMode.EXACTLY_ONCE_ALPHA` or not, respectively).

`createActiveTask` creates a [RecordCollectorImpl](RecordCollectorImpl.md).

`createActiveTask` creates a [StreamTask](StreamTask.md).

`createActiveTask` prints out the following TRACE message to the logs:

```text
Created task [taskId] with assigned partitions [inputPartitions]
```

In the end, `createActiveTask` requests the [createTaskSensor](#createTaskSensor) to record this occurrence.

`createActiveTask` is used when:

* `ActiveTaskCreator` is requested to [createTasks](#createTasks) and [createActiveTaskFromStandby](#createActiveTaskFromStandby)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.ActiveTaskCreator` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.ActiveTaskCreator=ALL
```

Refer to [Logging](logging.md).
