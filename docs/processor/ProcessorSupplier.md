# ProcessorSupplier

`ProcessorSupplier` is an [extension](#contract) of the `ConnectedStoreProvider` and `Supplier` ([Java]({{ java.api }}/java/util/function/Supplier.html)) abstractions for processor suppliers.

`ProcessorSupplier` is marked with `FunctionalInterface` ([Java]({{ java.api }}/java/lang/FunctionalInterface.html)) annotation.

## Contract

### <span id="get"> Creating Processor

```java
Processor<KIn, VIn, KOut, VOut> get()
```

Creates a [Processor](Processor.md)
