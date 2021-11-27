# PartitionGroup

## Creating Instance

`PartitionGroup` takes the following to be created:

* <span id="logContext"> `LogContext`
* <span id="partitionQueues"> Partition Queues (`Map<TopicPartition, RecordQueue>`)
* <span id="lagProvider"> Lag Provider Function (`Function<TopicPartition, OptionalLong>`)
* [recordLateness Sensor](#recordLatenessSensor)
* [enforcedProcessing Sensor](#enforcedProcessingSensor)
* <span id="maxTaskIdleMs"> [max.task.idle.ms](StreamsConfig.md#MAX_TASK_IDLE_MS_CONFIG) configuration property

`PartitionGroup` is created when:

* `StreamTask` is [created](StreamTask.md#partitionGroup)

## Sensors

### <span id="recordLatenessSensor"> recordLateness

`PartitionGroup` is given the [recordLateness metric sensor](metrics/TaskMetrics.md#recordLatenessSensor) when [created](#creating-instance).

The `recordLateness` sensor is requested to record the following values:

* `0` when the [event time advances](#nextRecord) (the timestamp of the current record is smaller than the current [stream time](#streamTime))

* the difference (_lateness_) between the current [stream time](#streamTime) and the timestamp of the current otherwise

### <span id="enforcedProcessingSensor"> enforcedProcessing

`PartitionGroup` is given an `enforcedProcessingSensor` metric sensor when [created](#creating-instance).

## <span id="streamTime"> Stream Time

`PartitionGroup` defines `streamTime` internal registry for the highest time (_event-time watermark_) across already-processed events (across all the partitions assigned to this `StreamTask`).

The `streamTime` is initially `RecordQueue.UNKNOWN` when `PartitionGroup` is [created](#creating-instance) and later when [cleared](#clear).

The `streamTime` changes to a given event timestamp in [setPartitionTime](#setPartitionTime) and [nextRecord](#nextRecord).

The `streamTime` is used when:

* `StreamTask` is requested to [maybePunctuateStreamTime](StreamTask.md#maybePunctuateStreamTime) and [streamTime](StreamTask.md#streamTime)

## <span id="setPartitionTime"> setPartitionTime

```java
void setPartitionTime(
  TopicPartition partition, 
  long partitionTime)
```

`setPartitionTime`...FIXME

`setPartitionTime` is used when:

* `StreamTask` is requested to [initializeTaskTime](StreamTask.md#initializeTaskTime)

## <span id="nextRecord"> Next (Stamped)Record

```java
StampedRecord nextRecord(
  RecordInfo info, 
  long wallClockTime)
```

`nextRecord`...FIXME

`nextRecord` is used when:

* `StreamTask` is requested to [process one record](StreamTask.md#process)

## <span id="addRawRecords"> Adding ConsumerRecords

```java
int addRawRecords(
  TopicPartition partition,
  Iterable<ConsumerRecord<byte[], byte[]>> rawRecords)
```

`addRawRecords`...FIXME

`addRawRecords` is used when:

* `StreamTask` is requested to [add records](StreamTask.md#addRecords)
