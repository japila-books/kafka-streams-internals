# WindowBytesStoreSupplier

`WindowBytesStoreSupplier` is an [extension](#contract) of the [StoreSupplier](StoreSupplier.md) abstraction for [state store suppliers](#implementations) of [WindowStore](WindowStore.md)s (`WindowStore<Bytes, byte[]>`s).

## Contract

### <span id="retainDuplicates"> retainDuplicates

```java
boolean retainDuplicates()
```

### <span id="retentionPeriod"> retentionPeriod

```java
long retentionPeriod()
```

### <span id="segmentIntervalMs"> segmentIntervalMs

```java
long segmentIntervalMs()
```

### <span id="windowSize"> windowSize

```java
long windowSize()
```

## Implementations

* RocksDbWindowBytesStoreSupplier
* [InMemoryWindowBytesStoreSupplier](InMemoryWindowBytesStoreSupplier.md)
