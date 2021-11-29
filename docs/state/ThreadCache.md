# ThreadCache

## Creating Instance

`ThreadCache` takes the following to be created:

* <span id="logContext"> `LogContext`
* [Maximum Cache Size](#maxCacheSizeBytes)
* <span id="metrics"> [StreamsMetricsImpl](../metrics/StreamsMetricsImpl.md)

`ThreadCache` is created when:

* `GlobalStreamThread` is [created](../processor/GlobalStreamThread.md#cache)
* `StandbyTaskCreator` is [created](../StandbyTaskCreator.md#dummyCache)
* `StreamThread` utility is used to [create a StreamThread](../StreamThread.md#create) (and creates a [ActiveTaskCreator](../ActiveTaskCreator.md#cache))

## <span id="maxCacheSizeBytes"> Maximum Cache Size

`ThreadCache` is given the maximum cache size (in bytes) when [created](#creating-instance).

The cache size is determined using [cache.max.bytes.buffering](../KafkaStreams.md#getCacheSizePerThread) configuration property when `KafkaStreams` is [created](../KafkaStreams.md#creating-instance) and requested to [addStreamThread](../KafkaStreams.md#addStreamThread).

## <span id="getOrCreateCache"> getOrCreateCache

```java
NamedCache getOrCreateCache(
  String name)
```

`getOrCreateCache`...FIXME

`getOrCreateCache` is used when:

* `ThreadCache` is requested to [addDirtyEntryFlushListener](#addDirtyEntryFlushListener), [put](#put), [putIfAbsent](#putIfAbsent), [maybeEvict](#maybeEvict)

## <span id="put"> put

```java
void put(
  String namespace,
  Bytes key,
  LRUCacheEntry value)
```

`put`...FIXME

`put` is used when:

* `CachingKeyValueStore` is requested to `putInternal` and `getInternal`
* `CachingSessionStore` is requested to `put`
* `CachingWindowStore` is requested to `put`

## <span id="flush"> flush

```java
void flush(
  String namespace)
```

`flush`...FIXME

`flush` is used when:

* `CachingKeyValueStore` is requested to `flush`, `flushCache`, `close`
* `CachingSessionStore` is requested to `flush`, `flushCache`, `close`
* `CachingWindowStore` is requested to `flush`, `flushCache`, `close`

## <span id="sizeBytes"> sizeBytes

```java
long sizeBytes()
```

`sizeBytes` is the sum of all the sizes of the [NamedCaches](#caches).

`sizeBytes` is used when:

* `ThreadCache` is requested to [resize](#resize) and [maybeEvict](#maybeEvict)
