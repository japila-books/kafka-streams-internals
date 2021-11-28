# TimestampExtractor

`TimestampExtractor` is an [abstraction](#contract) of [time extractors](#implementations) that Kafka Streams uses to [extract a timestamp from a record](#extract).

`TimestampExtractor` can be explicitly assigned with a [SourceNode](SourceNode.md#timestampExtractor) or configured application-wide using [default.timestamp.extractor](../StreamsConfig.md#defaultTimestampExtractor) configuration property.

## Contract

### <span id="extract"> Extracting Timestamp

```java
long extract(
  ConsumerRecord<Object, Object> record,
  long partitionTime)
```

Extracts a timestamp from the given `ConsumerRecord` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerRecord))

Used when:

* `RecordQueue` is requested to [update the head record](../RecordQueue.md#updateHead)

## Implementations

* [ExtractRecordMetadataTimestamp](ExtractRecordMetadataTimestamp.md)
* `WallclockTimestampExtractor`
