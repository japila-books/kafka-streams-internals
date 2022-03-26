# GlobalKTable

`GlobalKTable<K, V>` is an [abstraction](#contract) of [changelog streams](#implementations).

A `GlobalKTable` is created using [StreamsBuilder.globalTable](StreamsBuilder.md#globalTable).

A `GlobalKTable`is backed by a [ReadOnlyKeyValueStore](../state/ReadOnlyKeyValueStore.md) and is queryable via the [Interactive Queries API](../interactive-queries.md).

## Contract

### <span id="queryableStoreName"> queryableStoreName

```java
String queryableStoreName()
```

Gets the name of the local state store that can be used to query this `GlobalKTable`

## Implementations

* [GlobalKTableImpl](GlobalKTableImpl.md)
