# Processor

`Processor<KIn, VIn, KOut, VOut>` is an [abstraction](#contract) of [processing nodes](#implementations) (in a stream processing topology).

## Contract

### <span id="close"> close

```java
void close()
```

Used when:

* `ProcessorNode` is requested to [close](ProcessorNode.md#close)

### <span id="init"> init

```java
void init(
  ProcessorContext<KOut, VOut> context)
```

Used when:

* `ProcessorNode` is requested to [init](ProcessorNode.md#init)

### <span id="process"> process

```java
void process(
  Record<KIn, VIn> record)
```

Used when:

* `ProcessorNode` is requested to [process](ProcessorNode.md#process)

## Implementations

* `ContextualProcessor`
* `ForeachProcessor`
