# StreamTask

`StreamTask` is a concrete [AbstractTask](AbstractTask.md).

## Creating Instance

`StreamTask` takes the following to be created:

* <span id="id"> [TaskId](TaskId.md)
* <span id="inputPartitions"> Input `TopicPartition`s
* <span id="topology"> [ProcessorTopology](processor/ProcessorTopology.md)
* [Kafka Consumer](#mainConsumer)
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="cache"> [ThreadCache](state/ThreadCache.md)
* <span id="time"> `Time`
* <span id="stateMgr"> [ProcessorStateManager](ProcessorStateManager.md)
* [RecordCollector](#recordCollector)
* <span id="processorContext"> `InternalProcessorContext`
* <span id="logContext"> `LogContext`

`StreamTask` is created when:

* `ActiveTaskCreator` is requested to [create an active task](ActiveTaskCreator.md#createActiveTask)
* `TopologyTestDriver` is requested to [setup a task](TopologyTestDriver.md#setupTask)

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

`addRecords`...FIXME

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
