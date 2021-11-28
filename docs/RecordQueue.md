# RecordQueue

`RecordQueue` is a queue of `ConsumerRecord`s (with the head as a [deserialized record](#headRecord) and the tail of [raw records](#fifoQueue)).

`RecordQueue` is [created](#creating-instance) for an input [partition](#partition) (for the [PartitionGroup](StreamTask.md#partitionGroup) of a [StreamTask](StreamTask.md)).

## Creating Instance

`RecordQueue` takes the following to be created:

* <span id="partition"> `TopicPartition`
* <span id="source"> [SourceNode](processor/SourceNode.md)
* [TimestampExtractor](#timestampExtractor)
* <span id="deserializationExceptionHandler"> `DeserializationExceptionHandler`
* <span id="processorContext"> [InternalProcessorContext](processor/InternalProcessorContext.md)
* <span id="logContext"> `LogContext`

`RecordQueue` is created when:

* `RecordQueueCreator` is requested for a [new RecordQueue](RecordQueueCreator.md#createQueue)

## <span id="timestampExtractor"> TimestampExtractor

`RecordQueue` is given a [TimestampExtractor](processor/TimestampExtractor.md) when [created](#creating-instance).

## <span id="fifoQueue"> fifoQueue (of ConsumerRecords)

`RecordQueue` defines `fifoQueue` internal registry as a `ArrayDeque` ([Java]({{ java.api }}/java/util/ArrayDeque.html)) of serialized (_raw_) `ConsumerRecord<byte[], byte[]>`s.

`RecordQueue` creates an empty `ArrayDeque` when [created](#creating-instance).

`ConsumerRecord`s are added in [addRawRecords](#addRawRecords).

## <span id="headRecord"> StampedRecord

`RecordQueue` defines `headRecord` internal registry of `StampedRecord` (with a `ConsumerRecord<Object, Object>` and the extracted timestamp).

A `StampedRecord` is retrieved (and removed) from the [fifoQueue](#fifoQueue) when `RecordQueue` is requested to [updateHead](#updateHead).

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

## <span id="updateHead"> Updating Head Record

```java
void updateHead()
```

`updateHead` does its work until the [headRecord](#headRecord) is found in the [fifoQueue](#fifoQueue).

---

In other words, `updateHead` makes sure that the [headRecord](#headRecord) is available (for the follow-up operations) if there are timestamp-valid records in the [fifoQueue](#fifoQueue).

---

`updateHead` takes (and removes) the first `ConsumerRecord` from the [fifoQueue](#fifoQueue) and requests the [RecordDeserializer](#recordDeserializer) to [deserialize the record](RecordDeserializer.md#deserialize) (with the [ProcessorContext](#processorContext)).

`updateHead` skips this record if the [RecordDeserializer](#recordDeserializer) returns `null` (to announce to skip the record) and starts over.

`updateHead` requests the [TimestampExtractor](#timestampExtractor) to [extract the timestamp](processor/TimestampExtractor.md#extract) (with the [partitionTime](#partitionTime)).

`updateHead` prints out the following TRACE message to the logs:

```text
Source node [name] extracted timestamp [timestamp] for record [record]
```

`updateHead` creates a new `StampedRecord` (with the deserialized `ConsumerRecord` and the timestamp).

---

If the extracted timestamp from the deserialized `ConsumerRecord` is negative, `updateHead` prints out the following WARN message to the logs, requests the [droppedRecords sensor](#droppedRecordsSensor) to record the event and starts over.

```text
Skipping record due to negative extracted timestamp. topic=[[topic]] partition=[[partition]] offset=[[offset]] extractedTimestamp=[[timestamp]] extractor=[[timestampExtractor]]
```

---

`updateHead` is used when:

* `RecordQueue` is requested to [addRawRecords](#addRawRecords) and [poll](#poll)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.RecordQueue` logger to see what happens inside.

Add the following line to `conf/log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.RecordQueue=ALL
```

Refer to [Logging](logging.md).
