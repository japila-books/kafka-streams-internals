# ProcessorNode

`ProcessorNode` is a "hosting environment" of a [Processor](Processor.md) in a processor topology.

## Creating Instance

`ProcessorNode` takes the following to be created:

* <span id="name"> Name
* <span id="processor"> [Processor](Processor.md)
* <span id="stateStores"> Names of the state stores

`ProcessorNode` is created when:

* `ProcessorNodeFactory` is requested to [build a processor](ProcessorNodeFactory.md#build)

## Specialized ProcessorNodes

[SourceNode](SourceNode.md) and [SinkNode](SinkNode.md) are specialized `ProcessorNode`s.

## <span id="children"> Child ProcessorNodes

`ProcessorNode` defines `children` internal registry of child `ProcessorNode`s.

The `children` is empty when `ProcessorNode` is [created](#creating-instance).

A new `ProcessorNode` is added in [addChild](#addChild).

### <span id="addChild"> addChild

```java
void addChild(
  ProcessorNode<KOut, VOut, ?, ?> child)
```

`addChild` adds a new `ProcessorNode` to the [children](#children) and the [childByName](#childByName) internal registries.

`addChild` is used when:

* `InternalTopologyBuilder` is requested to [buildSinkNode](../InternalTopologyBuilder.md#buildSinkNode) and [buildProcessorNode](../InternalTopologyBuilder.md#buildProcessorNode)

### <span id="children"> children

```java
List<ProcessorNode<KOut, VOut, ?, ?>> children()
```

`children` is used when:

* `GlobalProcessorContextImpl` is requested to [forward a record](GlobalProcessorContextImpl.md#forward)
* `ProcessorContextImpl` is requested to [forward a record](ProcessorContextImpl.md#forward)
* `ProcessorTopology` is requested for [text representation](ProcessorTopology.md#toString)

## <span id="isTerminalNode"> Terminal Node

`ProcessorNode` is **terminal** when has got no [children](#children).

## <span id="punctuate"> punctuate

```java
void punctuate(
  long timestamp, 
  Punctuator punctuator)
```

`punctuate` requests the given [Punctuator](Punctuator.md) to [punctuate](Punctuator.md#punctuate) (with the given `timestamp`).

`punctuate` is used when:

* `StreamTask` is requested to [punctuate](../StreamTask.md#punctuate)
