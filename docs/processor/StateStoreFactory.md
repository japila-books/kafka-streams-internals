# StateStoreFactory

`StateStoreFactory` is a factory of [StateStore](StateStore.md)s.

```java
StateStoreFactory<S extends StateStore>
```

`StateStoreFactory` is a `public static class` of [InternalTopologyBuilder](InternalTopologyBuilder.md).

## Creating Instance

`StateStoreFactory` takes the following to be created:

* <span id="builder"> [StoreBuilder](../state/StoreBuilder.md) (of `S` [StateStore](StateStore.md)s)

`StateStoreFactory` is created when:

* `InternalTopologyBuilder` is requested to [addStateStore](InternalTopologyBuilder.md#addStateStore)
