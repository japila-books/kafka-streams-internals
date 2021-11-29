# KStreamFilter

`KStreamFilter` is a [ProcessorSupplier](../processor/ProcessorSupplier.md) of [KStreamFilterProcessor](#KStreamFilterProcessor)s.

## Creating Instance

`KStreamFilter` takes the following to be created:

* <span id="predicate"> `Predicate`
* <span id="filterNot"> `filterNot` flag

`KStreamFilter` is created when:

* `KStreamImpl` is requested to [filter](KStreamImpl.md#filter), [filterNot](KStreamImpl.md#filterNot) and [create a repartitioned source](KStreamImpl.md#createRepartitionedSource)

## <span id="get"> get

```java
Processor<K, V, K, V> get()
```

`get` creates a new [KStreamFilterProcessor](#KStreamFilterProcessor).

`get` is part of the [ProcessorSupplier](../processor/ProcessorSupplier.md#get) abstraction.

## <span id="KStreamFilterProcessor"> KStreamFilterProcessor

`KStreamFilterProcessor` is a [ContextualProcessor](../processor/ContextualProcessor.md)

### <span id="process"> Processing Record

```java
void process(
  Record<K, V> record)
```

`process` requests the [ProcessorContext](../processor/ContextualProcessor.md#context) to [forward the given record](../processor/ProcessorContext.md#forward) when either the [filterNot](#filterNot) flag or the [Predicate](#predicate) is `true` but not both (_XOR_).

`process` is part of the [Processor](../processor/Processor.md#process) abstraction.
