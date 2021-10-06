# Stores

`Stores` is a factory for creating state stores in Kafka Streams.

## <span id="inMemoryWindowStore"> inMemoryWindowStore

```java
WindowBytesStoreSupplier inMemoryWindowStore(
  String name,
  Duration retentionPeriod,
  Duration windowSize,
  boolean retainDuplicates)
```

`inMemoryWindowStore`...FIXME

`inMemoryWindowStore` is used when:

* `KStreamImplJoin` is requested to `sharedOuterJoinWindowStoreBuilder` (for left outer join)
