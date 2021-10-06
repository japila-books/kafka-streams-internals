# StoreSupplier

`StoreSupplier` is an [abstraction](#contract) of [suppliers](#implementations) of [StateStore](../processor/StateStore.md)s.

## Contract

### <span id="get"> Creating StateStore

```java
T get()
```

Used when:

* `KStreamImplJoin` is requested to `sharedOuterJoinWindowStoreBuilder`
* `KeyValueStoreBuilder` is requested to `build`
* `SessionStoreBuilder` is requested to `build`
* `TimeOrderedWindowStoreBuilder` is requested to `build`
* `TimestampedKeyValueStoreBuilder` is requested to `build`
* `TimestampedWindowStoreBuilder` is requested to `build`
* `WindowStoreBuilder` is requested to `build`

### <span id="metricsScope"> metricsScope

```java
String metricsScope()
```

### <span id="name"> name

```java
String name()
```

## Implementations

* KeyValueBytesStoreSupplier
* SessionBytesStoreSupplier
* [WindowBytesStoreSupplier](WindowBytesStoreSupplier.md)
