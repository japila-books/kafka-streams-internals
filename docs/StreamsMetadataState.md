# StreamsMetadataState

`StreamsMetadataState` is used by a [KafkaStreams](KafkaStreams.md#streamsMetadataState) instance to manage [global metadata](#allMetadata) (of all the `KafkaStreams`s of a Kafka Streams application).

## Creating Instance

`StreamsMetadataState` takes the following to be created:

* <span id="builder"> [InternalTopologyBuilder](InternalTopologyBuilder.md)
* <span id="thisHost"> User-Defined Endpoint

`StreamsMetadataState` is created when:

* `KafkaStreams` is [created](KafkaStreams.md#streamsMetadataState)

## <span id="allMetadata"><span id="getAllMetadata"> Global Metadata

```java
List<StreamsMetadata> allMetadata
```

`StreamsMetadataState` uses a `allMetadata` internal registry for all [StreamsMetadata](StreamsMetadata.md)s.

The `allMetadata` registry is initially empty and [rebuilt](#rebuildMetadata) every [onChange](#onChange).

The `allMetadata` registry is available using [KafkaStreams.metadataForAllStreamsClients](KafkaStreams.md#metadataForAllStreamsClients).

Used when:

* [toString](#toString)
* [getAllMetadataForStore](#getAllMetadataForStore)
* [getKeyQueryMetadataForKey](#getKeyQueryMetadataForKey)

## <span id="onChange"> onChange

```java
void onChange(
  Map<HostInfo, Set<TopicPartition>> activePartitionHostMap,
  Map<HostInfo, Set<TopicPartition>> standbyPartitionHostMap,
  Cluster clusterMetadata)
```

`onChange` stores the given `Cluster` metadata (in the [clusterMetadata](#clusterMetadata) internal registry) and [rebuildMetadata](#rebuildMetadata).

`onChange` is used when:

* `StreamsPartitionAssignor` is requested to [assign](StreamsPartitionAssignor.md#assign) and [onAssignment](StreamsPartitionAssignor.md#onAssignment)

### <span id="rebuildMetadata"> rebuildMetadata

```java
void rebuildMetadata(
  Map<HostInfo, Set<TopicPartition>> activePartitionHostMap,
  Map<HostInfo, Set<TopicPartition>> standbyPartitionHostMap)
```

`rebuildMetadata`...FIXME

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

* `KafkaStreams` is requested to [queryMetadataForKey](KafkaStreams.md#queryMetadataForKey)
