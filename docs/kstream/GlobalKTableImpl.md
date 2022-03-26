# GlobalKTableImpl

`GlobalKTableImpl<K, V>` is a [GlobalKTable](GlobalKTable.md).

## Creating Instance

`GlobalKTableImpl` takes the following to be created:

* <span id="valueGetterSupplier"> `KTableValueGetterSupplier<K, V>`
* <span id="queryableStoreName"> Queryable Store Name

`GlobalKTableImpl` is created when:

* `InternalStreamsBuilder` is requested to [globalTable](InternalStreamsBuilder.md#globalTable)
