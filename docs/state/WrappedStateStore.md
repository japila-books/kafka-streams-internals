# WrappedStateStore

`WrappedStateStore` is an extension of the [StateStore](../processor/StateStore.md) and [CachedStateStore](CachedStateStore.md) abstractions for [state stores](#implementations) that hold (_wrap_) another [state store](#wrapped).

## Implementations

* `AbstractReadOnlyDecorator`
* `AbstractReadWriteDecorator`
* `CachingKeyValueStore`
* `CachingSessionStore`
* `CachingWindowStore`
* `ChangeLoggingKeyValueBytesStore`
* `ChangeLoggingSessionBytesStore`
* `ChangeLoggingWindowBytesStore`
* `MeteredKeyValueStore`
* `MeteredSessionStore`
* `MeteredWindowStore`
* `RocksDBSessionStore`
* `RocksDBTimeOrderedWindowStore`
* `RocksDBWindowStore`

## Creating Instance

`WrappedStateStore` takes the following to be created:

* <span id="wrapped"> [StateStore](../processor/StateStore.md)

??? note "Abstract Class"
    `WrappedStateStore` is an abstract class and cannot be created directly. It is created indirectly for the [concrete WrappedStateStores](#implementations).

## <span id="setFlushListener"> setFlushListener

```java
boolean setFlushListener(
  CacheFlushListener<K, V> listener,
  boolean sendOldValues)
```

`setFlushListener` returns `false` for the [wrapped state store](#wrapped) being of any type but a [CachedStateStore](CachedStateStore.md).

Otherwise, `setFlushListener` returns the value of requesting the [CachedStateStore](#wrapped) to [setFlushListener](CachedStateStore.md#setFlushListener).

`setFlushListener` is part of the [CachedStateStore](CachedStateStore.md#setFlushListener) abstraction.
