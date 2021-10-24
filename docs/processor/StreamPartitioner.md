# StreamPartitioner

`StreamPartitioner<K, V>` is an [abstraction](#contract) of [partitioners](#implementations) that [determine how records are distributed among the partitions in a Kafka topic](#partition).

## Contract

### <span id="partition"> partition

```java
Integer partition(
  String topic,
  K key,
  V value,
  int numPartitions)
```

Used when:

* `RecordCollectorImpl` is requested to [send a record](RecordCollectorImpl.md#send)
* `StreamsMetadataState` is requested to [getKeyQueryMetadataForKey](../StreamsMetadataState.md#getKeyQueryMetadataForKey)

## Implementations

* `DefaultStreamPartitioner`
* `WindowedStreamPartitioner`
