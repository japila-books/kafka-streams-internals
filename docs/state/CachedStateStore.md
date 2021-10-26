# CachedStateStore

`CachedStateStore` is an [abstraction](#contract) of [cached state stores](#implementations).

## Contract

### <span id="flushCache"> flushCache

```java
void flushCache()
```

Used when:

* `ProcessorStateManager` is requested to [flush store caches](../ProcessorStateManager.md#flushCache)

### <span id="setFlushListener"> setFlushListener

```java
boolean setFlushListener(
  CacheFlushListener<K, V> listener,
  boolean sendOldValues)
```

## Implementations

* `CachingKeyValueStore`
* `CachingSessionStore`
* `CachingWindowStore`
* [WrappedStateStore](WrappedStateStore.md)
