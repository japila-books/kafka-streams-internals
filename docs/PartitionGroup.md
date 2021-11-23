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

`PartitionGroup` is given a `recordLateness` metric sensor when [created](#creating-instance).

### <span id="enforcedProcessingSensor"> enforcedProcessing

`PartitionGroup` is given a `enforcedProcessing` metric sensor when [created](#creating-instance).

## <span id="streamTime"> Stream Time

`PartitionGroup` defines `streamTime` internal registry for a timestamp.

The `streamTime` is `RecordQueue.UNKNOWN` when `PartitionGroup` is [created](#creating-instance) and [cleared](#clear).

The `streamTime` changes to a given timestamp in [setPartitionTime](#setPartitionTime) and [nextRecord](#nextRecord).

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

## <span id="nextRecord"> nextRecord

```java
StampedRecord nextRecord(
  RecordInfo info, 
  long wallClockTime)
```

`nextRecord`...FIXME

`nextRecord` is used when:

* `StreamTask` is requested to [process one record](StreamTask.md#process)
