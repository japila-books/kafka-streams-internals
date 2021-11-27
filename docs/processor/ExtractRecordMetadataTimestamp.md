# ExtractRecordMetadataTimestamp

`ExtractRecordMetadataTimestamp` is an [extension](#contract) of the [TimestampExtractor](TimestampExtractor.md) abstraction for [timestamp extractors](#implementations) that can handle [invalid timestamps](#onInvalidTimestamp).

## Contract

### <span id="onInvalidTimestamp"> Handling Invalid Timestamp

```java
long onInvalidTimestamp(
  ConsumerRecord<Object, Object> record,
  long recordTimestamp,
  long partitionTime)
```

Used when:

* `ExtractRecordMetadataTimestamp` is requested to [extract a timestamp](#extract)

## Implementations

* `FailOnInvalidTimestamp`
* `LogAndSkipOnInvalidTimestamp`
* `UsePartitionTimeOnInvalidTimestamp`

## <span id="extract"> Extracting Timestamp

```java
long extract(
  ConsumerRecord<Object, Object> record,
  long partitionTime)
```

`extract` requests the given `ConsumerRecord` for the `timestamp`.

In case the (extracted) timestamp is negative, `extract` [onInvalidTimestamp](#onInvalidTimestamp).

`extract` is part of the [TimestampExtractor](TimestampExtractor.md#extract) abstraction.
