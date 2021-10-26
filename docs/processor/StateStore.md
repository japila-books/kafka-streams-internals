# StateStore

`StateStore` is an [abstraction](#contract) of [storage engines](#implementations) (for the state of a stream processor).

## Contract

### <span id="close"> Closing

```java
void close()
```

### <span id="flush"> Flushing

```java
void flush()
```

### <span id="init"> Initializing

```java
void init(
  StateStoreContext context,
  StateStore root)
```

### <span id="isOpen"> isOpen

```java
boolean isOpen()
```

### <span id="name"> Name

```java
String name()
```

### <span id="persistent"> persistent

```java
boolean persistent()
```

Used when:

* `GlobalStateManagerImpl` is [created](GlobalStateManagerImpl.md) (and finds global non-persistent state stores)
* `ProcessorStateManager` is requested to [initializeStoreOffsetsFromCheckpoint](../ProcessorStateManager.md#initializeStoreOffsetsFromCheckpoint) and [checkpoint](../ProcessorStateManager.md#checkpoint)
* `ProcessorTopology` is requested to [hasPersistentLocalStore](ProcessorTopology.md#hasPersistentLocalStore) and [hasPersistentGlobalStore](ProcessorTopology.md#hasPersistentGlobalStore)
* `TimestampedKeyValueStoreBuilder` is requested to build a `TimestampedKeyValueStore`
* `TimestampedWindowStoreBuilder` is requested to build a `TimestampedWindowStore`
* _others_

## Implementations

* [KeyValueStore](../state/KeyValueStore.md)
* `SegmentedBytesStore`
* `SessionStore`
* `TimeOrderedKeyValueBuffer`
* [WindowStore](../state/WindowStore.md)
* [WrappedStateStore](../state/WrappedStateStore.md)
