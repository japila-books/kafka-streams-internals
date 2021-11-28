# StreamTask

`StreamTask` is a concrete [AbstractTask](AbstractTask.md).

`StreamTask` creates exactly one [PartitionGroup](#partitionGroup) to handle all the [input partitions](#inputPartitions).

## Creating Instance

`StreamTask` takes the following to be created:

* <span id="id"> [TaskId](TaskId.md)
* <span id="inputPartitions"> Input `TopicPartition`s
* <span id="topology"> [ProcessorTopology](processor/ProcessorTopology.md)
* [Kafka Consumer](#mainConsumer)
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="streamsMetrics"> [StreamsMetricsImpl](metrics/StreamsMetricsImpl.md)
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="cache"> [ThreadCache](state/ThreadCache.md)
* <span id="time"> `Time`
* <span id="stateMgr"> [ProcessorStateManager](ProcessorStateManager.md)
* [RecordCollector](#recordCollector)
* <span id="processorContext"> [InternalProcessorContext](processor/InternalProcessorContext.md)
* <span id="logContext"> `LogContext`

`StreamTask` is created when:

* `ActiveTaskCreator` is requested to [create an active task](ActiveTaskCreator.md#createActiveTask)
* `TopologyTestDriver` is requested to [setup a task](TopologyTestDriver.md#setupTask)

## <span id="recordQueueCreator"> RecordQueueCreator

When [created](#creating-instance), `StreamTask` creates a [RecordQueueCreator](RecordQueueCreator.md) based on the following configuration properties:

* [default.timestamp.extractor](StreamsConfig.md#defaultTimestampExtractor)
* [default.deserialization.exception.handler](StreamsConfig.md#defaultDeserializationExceptionHandler)

`StreamTask` uses the `RecordQueueCreator` when:

* [createPartitionQueues](#createPartitionQueues)
* [updateInputPartitions](#updateInputPartitions)

## <span id="maxBufferedSize"><span id="buffered.records.per.partition"> buffered.records.per.partition

`StreamTask` uses [buffered.records.per.partition](StreamsConfig.md#BUFFERED_RECORDS_PER_PARTITION_CONFIG) configuration property to control when to pause and resume record consumption (on a partition) when requested to [add](#addRecords) and [process](#process) records, respectively.

## <span id="mainConsumer"> Main Kafka Consumer

`StreamTask` is given a `Consumer<byte[], byte[]>` ([Apache Kafka]({{ book.kafka }}/clients/consumer/Consumer)) when [created](#creating-instance).

`StreamTask` uses the `Consumer` for the following:

* [Adding Records](#addRecords)
* [addPartitionsForOffsetReset](#addPartitionsForOffsetReset)
* [prepareCommit](#prepareCommit) (to [committableOffsetsAndMetadata](#committableOffsetsAndMetadata))
* [Processing One Record](#process)
* [completeRestoration](#completeRestoration) (to [resetOffsetsIfNeededAndInitializeMetadata](#resetOffsetsIfNeededAndInitializeMetadata))

## <span id="recordCollector"> RecordCollector

`StreamTask` is given a [RecordCollector](RecordCollector.md) when [created](#creating-instance).

## <span id="partitionGroup"> PartitionGroup

When [created](#creating-instance), `StreamTask` creates a [PartitionGroup](PartitionGroup.md) with the following:

* [Partition Queues](#createPartitionQueues)
* `currentLag` function ([Apache Kafka]({{ book.kafka }}/clients/consumer/Consumer#currentLag)) of the [main Consumer](#mainConsumer)
* <span id="recordLatenessSensor"> [recordLatenessSensor](metrics/TaskMetrics.md#recordLatenessSensor)
* [enforcedProcessingSensor](metrics/TaskMetrics.md#enforcedProcessingSensor)
* [max.task.idle.ms](StreamsConfig.md#MAX_TASK_IDLE_MS_CONFIG) configuration property

### <span id="createPartitionQueues"> createPartitionQueues

```java
Map<TopicPartition, RecordQueue> createPartitionQueues()
```

`createPartitionQueues` requests the [RecordQueueCreator](#recordQueueCreator) to create one [RecordQueue](RecordQueue.md) per every partition in the [input partitions](AbstractTask.md#inputPartitions).

## <span id="updateInputPartitions"> updateInputPartitions

```java
void updateInputPartitions(
  Set<TopicPartition> topicPartitions,
  Map<String, List<String>> allTopologyNodesToSourceTopics)
```

`updateInputPartitions` [updateInputPartitions](AbstractTask.md#updateInputPartitions).

In the end, `updateInputPartitions` requests the [PartitionGroup](#partitionGroup) to [updatePartitions](PartitionGroup.md#updatePartitions) (with the given `TopicPartition`s and [createQueue](RecordQueueCreator.md#createQueue) factory).

`updateInputPartitions` is part of the [Task](Task.md#updateInputPartitions) abstraction.

## <span id="streamTime"> streamTime

```java
long streamTime()
```

`streamTime` requests the [PartitionGroup](#partitionGroup) for the [streamTime](PartitionGroup.md#streamTime).

`streamTime` is used when:

* `ProcessorContextImpl` is requested for the [current stream time](processor/ProcessorContextImpl.md#currentStreamTimeMs)

## <span id="schedule"> Scheduling Recurring Action

```java
Cancellable schedule(
  long startTime,
  long interval,
  PunctuationType type,
  Punctuator punctuator)
Cancellable schedule(
  long interval,
  PunctuationType type,
  Punctuator punctuator)
```

`schedule` creates a [PunctuationSchedule](PunctuationSchedule.md) (for the current [ProcessorNode](processor/ProcessorNode.md)) and requests the [stream-time](#streamTimePunctuationQueue) or [system-time](#systemTimePunctuationQueue)  `PunctuationQueue`s to [schedule the PunctuationSchedule](PunctuationQueue.md#schedule) based on the given `PunctuationType` (`STREAM_TIME` or `WALL_CLOCK_TIME`, respectively).

`schedule` is used when:

* `ProcessorContextImpl` is requested to [schedule a recurring action](processor/ProcessorContextImpl.md#schedule)

### <span id="PunctuationQueue"><span id="streamTimePunctuationQueue"><span id="systemTimePunctuationQueue"> Stream- and System-Time PunctuationQueues

`StreamTask` creates two [PunctuationQueue](PunctuationQueue.md)s when [created](#creating-instance) for the following [Punctuator](processor/Punctuator.md)s:

1. `streamTimePunctuationQueue` queue for `STREAM_TIME`-type punctuators
1. `systemTimePunctuationQueue` queue for `WALL_CLOCK_TIME`-type punctuators

`Punctuator`s are added to a corresponding `PunctuationQueue` when `StreamTask` is requested to [schedule a recurring action](#schedule).

The actions are executed every [maybePunctuateStreamTime](#maybePunctuateStreamTime) or [maybePunctuateSystemTime](#maybePunctuateSystemTime) (when `TaskManager` is requested to [punctuate](TaskManager.md#punctuate)).

### <span id="maybePunctuateStreamTime"> maybePunctuateStreamTime

```java
boolean maybePunctuateStreamTime()
```

`maybePunctuateStreamTime` returns `true` when at least one `STREAM_TIME` punctuator has been executed.

---

`maybePunctuateStreamTime` requests the [PartitionGroup](#partitionGroup) for the [stream time](PartitionGroup.md#streamTime).

!!! note
    Stream time for `STREAM_TIME` punctuators is determined using the [PartitionGroup](PartitionGroup.md#streamTime).

`maybePunctuateStreamTime` returns `false` for the stream time as `RecordQueue.UNKNOWN` (the stream time is yet to be determined and unknown).

`maybePunctuateStreamTime` requests the [stream-time PunctuationQueue](#streamTimePunctuationQueue) to [mayPunctuate](PunctuationQueue.md#mayPunctuate) (with the stream time, `STREAM_TIME` punctuation type and this `StreamTask`). If there was at least one recurring action triggered (punctuated), `maybePunctuateStreamTime` marks this `StreamTask` as [commitNeeded](#commitNeeded).

`maybePunctuateStreamTime` is part of the [Task](Task.md#maybePunctuateStreamTime) abstraction.

### <span id="maybePunctuateSystemTime"> maybePunctuateSystemTime

```java
boolean maybePunctuateSystemTime()
```

`maybePunctuateSystemTime` returns `true` when at least one `WALL_CLOCK_TIME` punctuator has been executed.

---

`maybePunctuateSystemTime` requests the [Time](#time) for the current time (in ms).

!!! note
    System time for `WALL_CLOCK_TIME` punctuators is determined using the [Time](#time).

`maybePunctuateSystemTime` requests the [system-time PunctuationQueue](#systemTimePunctuationQueue) to [mayPunctuate](PunctuationQueue.md#mayPunctuate) (with the current system time, `WALL_CLOCK_TIME` punctuation type and this `StreamTask`). If there was at least one recurring action triggered (punctuated), `maybePunctuateSystemTime` marks this `StreamTask` as [commitNeeded](#commitNeeded).

`maybePunctuateSystemTime` is part of the [Task](Task.md#maybePunctuateSystemTime) abstraction.

## <span id="prepareCommit"> prepareCommit

```java
Map<TopicPartition, OffsetAndMetadata> prepareCommit()
```

`prepareCommit`...FIXME

`prepareCommit` is part of the [Task](Task.md#prepareCommit) abstraction.

### <span id="committableOffsetsAndMetadata"> committableOffsetsAndMetadata

```java
Map<TopicPartition, OffsetAndMetadata> committableOffsetsAndMetadata()
```

`committableOffsetsAndMetadata`...FIXME

## <span id="addRecords"> Adding Records

```java
void addRecords(
  TopicPartition partition,
  Iterable<ConsumerRecord<byte[], byte[]>> records)
```

`addRecords` requests the [PartitionGroup](#partitionGroup) to [add the given ConsumerRecords](PartitionGroup.md#addRawRecords) (for the given `TopicPartition`).

`addRecords` prints out the following TRACE message to the logs:

```text
Added records into the buffered queue of partition [partition],
new queue size is [newQueueSize]
```

`addRecords` can request the [main Consumer](#mainConsumer) to pause (_suspend_) fetching and consuming records from the partition if the queue size for the partition (after adding the new raw records) crossed the [buffered.records.per.partition](#maxBufferedSize) threshold.

`addRecords` is part of the [Task](Task.md#addRecords) abstraction.

## <span id="addPartitionsForOffsetReset"> addPartitionsForOffsetReset

```java
void addPartitionsForOffsetReset(
  Set<TopicPartition> partitionsForOffsetReset)
```

`addPartitionsForOffsetReset`...FIXME

`addPartitionsForOffsetReset` is part of the [Task](Task.md#addPartitionsForOffsetReset) abstraction.

## <span id="process"> Processing One Record

```java
boolean process(
  long wallClockTime)
```

`process`...FIXME

`process` is part of the [Task](Task.md#process) abstraction.

## <span id="punctuate"> punctuate

```java
void punctuate(
  ProcessorNode<?, ?, ?, ?> node,
  long timestamp,
  PunctuationType type,
  Punctuator punctuator)
```

`punctuate`...FIXME

`punctuate` is part of the [ProcessorNodePunctuator](processor/ProcessorNodePunctuator.md#completeRestoration) abstraction.

## <span id="completeRestoration"> completeRestoration

```java
void completeRestoration(
  java.util.function.Consumer<Set<TopicPartition>> offsetResetter)
```

`completeRestoration`...FIXME

`completeRestoration` is part of the [Task](Task.md#completeRestoration) abstraction.

### <span id="resetOffsetsIfNeededAndInitializeMetadata"> resetOffsetsIfNeededAndInitializeMetadata

```java
void resetOffsetsIfNeededAndInitializeMetadata(
  java.util.function.Consumer<Set<TopicPartition>> offsetResetter)
```

`resetOffsetsIfNeededAndInitializeMetadata`...FIXME

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.StreamTask` logger to see what happens inside.

Add the following line to `conf/log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.StreamTask=ALL
```

Refer to [Logging](logging.md).
