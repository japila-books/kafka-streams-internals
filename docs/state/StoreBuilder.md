# StoreBuilder

`StoreBuilder` is an [abstraction](#contract) of [builders](#implementations) of [StateStore](../processor/StateStore.md)s (with optional caching and logging).

```java
StoreBuilder<T extends StateStore>
```

## Contract

### <span id="build"> Building StateStore

```java
T build()
```

Used when:

* `InternalTopologyBuilder` is requested to [rewriteTopology](../processor/InternalTopologyBuilder.md#rewriteTopology) (and build global state stores)
* `StateStoreFactory` is requested to [build a StateStore](../processor/StateStoreFactory.md#build)

### <span id="logConfig"> logConfig

```java
Map<String, String> logConfig()
```

### <span id="loggingEnabled"> loggingEnabled

```java
boolean loggingEnabled()
```

### <span id="name"> name

```java
String name()
```

### <span id="withCachingDisabled"> withCachingDisabled

```java
StoreBuilder<T> withCachingDisabled()
```

### <span id="withCachingEnabled"> withCachingEnabled

```java
StoreBuilder<T> withCachingEnabled()
```

### <span id="withLoggingDisabled"> withLoggingDisabled

```java
StoreBuilder<T> withLoggingDisabled()
```

### <span id="withLoggingEnabled"> withLoggingEnabled

```java
StoreBuilder<T> withLoggingEnabled(
  Map<String, String> config)
```

## Implementations

* [AbstractStoreBuilder](AbstractStoreBuilder.md)
