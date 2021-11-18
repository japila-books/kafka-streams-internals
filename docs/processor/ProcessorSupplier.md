# ProcessorSupplier

`ProcessorSupplier` is an [extension](#contract) of the [ConnectedStoreProvider](ConnectedStoreProvider.md) and `Supplier` ([Java]({{ java.api }}/java/util/function/Supplier.html)) abstractions for processor suppliers (_factories_).

`ProcessorSupplier` is marked with `FunctionalInterface` ([Java]({{ java.api }}/java/lang/FunctionalInterface.html)) annotation.

The design idea of `ProcessorSupplier` is to be a Single Abstract Method (SAM) interface and let lambda expressions make code easier to type (and hopefully to read and maintain, too).

## Contract

### <span id="get"> Creating Processor

```java
Processor<KIn, VIn, KOut, VOut> get()
```

Creates a [Processor](Processor.md)
