# KeyValueStoreBuilder

`KeyValueStoreBuilder` is a concrete [AbstractStoreBuilder](AbstractStoreBuilder.md) of [KeyValueStore](KeyValueStore.md)s.

## Creating Instance

`KeyValueStoreBuilder` takes the following to be created:

* <span id="storeSupplier"> [KeyValueBytesStoreSupplier](KeyValueBytesStoreSupplier.md)
* <span id="keySerde"> Key `Serde<K>`
* <span id="valueSerde"> Value `Serde<V>`
* <span id="time"> `Time`

`KeyValueStoreBuilder` is created when:

* `Stores` utility is used to [create a StoreBuilder of KeyValueStores](Stores.md#keyValueStoreBuilder)

## <span id="build"> Building StateStore

```java
KeyValueStore<K, V> build()
```

`build` creates a [MeteredKeyValueStore](MeteredKeyValueStore.md).

`build` is part of the [StoreBuilder](StoreBuilder.md#build) abstraction.
