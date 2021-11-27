# RecordQueue

## Creating Instance

`RecordQueue` takes the following to be created:

* <span id="partition"> `TopicPartition`
* <span id="source"> [SourceNode](processor/SourceNode.md)
* <span id="timestampExtractor"> [TimestampExtractor](processor/TimestampExtractor.md)
* <span id="deserializationExceptionHandler"> `DeserializationExceptionHandler`
* <span id="processorContext"> [InternalProcessorContext](processor/InternalProcessorContext.md)
* <span id="logContext"> `LogContext`

`RecordQueue` is created when:

* `RecordQueueCreator` is requested to [createQueue](RecordQueueCreator.md#createQueue)

## <span id="addRawRecords"> addRawRecords

```java
int addRawRecords(
  Iterable<ConsumerRecord<byte[], byte[]>> rawRecords)
```

`addRawRecords`...FIXME

`addRawRecords` is used when:

* `PartitionGroup` is requested to [add ConsumerRecords](PartitionGroup.md#addRawRecords)

## <span id="poll"> poll

```java
StampedRecord poll()
```

`poll`...FIXME

`poll` is used when:

* `PartitionGroup` is requested for [next record](PartitionGroup.md#nextRecord)

## <span id="updateHead"> updateHead

```java
void updateHead()
```

`updateHead`...FIXME

`updateHead` is used when:

* `RecordQueue` is requested to [addRawRecords](#addRawRecords) and [poll](#poll)
