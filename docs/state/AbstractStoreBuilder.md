# AbstractStoreBuilder

`AbstractStoreBuilder` is a base abstraction of the [StoreBuilder](StoreBuilder.md) abstraction.

```java
AbstractStoreBuilder<K, V, T extends StateStore>
```

## Implementations

* [KeyValueStoreBuilder](KeyValueStoreBuilder.md)
* SessionStoreBuilder
* TimeOrderedWindowStoreBuilder
* TimestampedKeyValueStoreBuilder
* TimestampedWindowStoreBuilder
* [WindowStoreBuilder](WindowStoreBuilder.md)

## Creating Instance

`AbstractStoreBuilder` takes the following to be created:

* <span id="name"> Name
* <span id="keySerde"> Key `Serde<K>`
* <span id="valueSerde"> Value `Serde<V>`
* <span id="time"> `Time`

!!! note "Abstract Class"
    `AbstractStoreBuilder` is an abstract class and cannot be created directly. It is created indirectly for the [concrete AbstractStoreBuilders](#implementations).
