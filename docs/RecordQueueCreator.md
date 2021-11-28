# RecordQueueCreator

## Creating Instance

`RecordQueueCreator` takes the following to be created:

* <span id="logContext"> `LogContext`
* <span id="defaultTimestampExtractor"> [default.timestamp.extractor](StreamsConfig.md#defaultTimestampExtractor) configuration property
* <span id="defaultDeserializationExceptionHandler"> [default.deserialization.exception.handler](StreamsConfig.md#defaultDeserializationExceptionHandler) configuration property

`RecordQueueCreator` is created when:

* `StreamTask` is [created](StreamTask.md#recordQueueCreator)

## <span id="createQueue"> Creating RecordQueue

```java
RecordQueue createQueue(
  TopicPartition partition)
```

`createQueue` requests the [ProcessorTopology](AbstractTask.md#topology) for the [SourceNode](processor/ProcessorTopology.md#source) for the topic (of the given `TopicPartition`).

`createQueue` determines the [TimestampExtractor](processor/TimestampExtractor.md) that is one of the following:

1. [TimestampExtractor](processor/SourceNode.md#timestampExtractor) of the `SourceNode` if defined
1. [Default TimestampExtractor](#defaultTimestampExtractor)

`createQueue` is used when:

* `StreamTask` is requested to [createPartitionQueues](StreamTask.md#createPartitionQueues) and [updateInputPartitions](StreamTask.md#updateInputPartitions)
