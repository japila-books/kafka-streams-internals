# TimestampExtractor

`TimestampExtractor` is an [abstraction](#contract) of [time extractors](#implementations) that Kafka Streams uses to [extract a timestamp from a record](#extract).

`TimestampExtractor` can be configured as follows:

1. For [SourceNode](SourceNode.md#timestampExtractor)s using [Topology.addSource](../Topology.md#addSource) and [Topology.addGlobalStore](../Topology.md#addGlobalStore)
1. Application-wide using [default.timestamp.extractor](../StreamsConfig.md#defaultTimestampExtractor) configuration property

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
