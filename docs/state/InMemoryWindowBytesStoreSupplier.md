# InMemoryWindowBytesStoreSupplier

`InMemoryWindowBytesStoreSupplier` is a [WindowBytesStoreSupplier](WindowBytesStoreSupplier.md).

## Creating Instance

`InMemoryWindowBytesStoreSupplier` takes the following to be created:

* <span id="name"> Name
* <span id="retentionPeriod"> `retentionPeriod`
* <span id="windowSize"> `windowSize`
* <span id="retainDuplicates"> `retainDuplicates`

`InMemoryWindowBytesStoreSupplier` is created when:

* `Stores` is requested for [in-memory window store](Stores.md#inMemoryWindowStore)
