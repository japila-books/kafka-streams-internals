# TimestampExtractor

`TimestampExtractor` is an [abstraction](#contract) of [time extractors](#implementations) that Kafka Streams uses to [extract a timestamp from a record](#extract).

## Contract

### <span id="extract"> Extracting Timestamp

```java
long extract(
  ConsumerRecord<Object, Object> record,
  long partitionTime)
```

Extracts a timestamp from the given `ConsumerRecord` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerRecord))

Used when:

* `RecordQueue` is requested to [updateHead](../RecordQueue.md#updateHead)

## Implementations

* [ExtractRecordMetadataTimestamp](ExtractRecordMetadataTimestamp.md)
* `WallclockTimestampExtractor`
