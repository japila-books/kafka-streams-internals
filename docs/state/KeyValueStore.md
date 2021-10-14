# KeyValueStore

`KeyValueStore<K, V>` is an [extension](#contract) of the [StateStore](../processor/StateStore.md) and [ReadOnlyKeyValueStore](ReadOnlyKeyValueStore.md) abstractions for [read-only key-value stores](#implementations).

## Contract

### <span id="delete"> delete

```java
V delete(
  K key)
```

### <span id="put"> put

```java
void put(
  K key,
  V value)
```

### <span id="putAll"> putAll

```java
void putAll(
  List<KeyValue<K, V>> entries)
```

### <span id="putIfAbsent"> putIfAbsent

```java
V putIfAbsent(
  K key,
  V value)
```

## Implementations

* `CachingKeyValueStore`
* `ChangeLoggingKeyValueBytesStore`
* `InMemoryKeyValueStore`
* `InMemoryTimestampedKeyValueStoreMarker`
* `KeyValueStoreFacade`
* `KeyValueStoreReadOnlyDecorator`
* `KeyValueStoreReadWriteDecorator`
* `KeyValueToTimestampedKeyValueByteStoreAdapter`
* `MemoryLRUCache`
* `MeteredKeyValueStore`
* `RocksDBStore`
* `Segment`
* `TimestampedKeyValueStore`
