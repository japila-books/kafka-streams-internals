# StreamsMetadataState

## <span id="getKeyQueryMetadataForKey"> getKeyQueryMetadataForKey

```java
KeyQueryMetadata getKeyQueryMetadataForKey(
  String storeName,
  K key,
  Serializer<K> keySerializer) // (1)
KeyQueryMetadata getKeyQueryMetadataForKey(
  String storeName,
  K key,
  StreamPartitioner<? super K, ?> partitioner)
KeyQueryMetadata getKeyQueryMetadataForKey(
  String storeName,
  K key,
  StreamPartitioner<? super K, ?> partitioner,
  SourceTopicsInfo sourceTopicsInfo) // (2)
```

1. Uses `DefaultStreamPartitioner`
2. A private method

`getKeyQueryMetadataForKey`...FIXME

`getKeyQueryMetadataForKey` is used when:

* `KafkaStreams` is requested to [queryMetadataForKey](../KafkaStreams.md#queryMetadataForKey)
