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

## <span id="isTerminalNode"> Terminal Node

`ProcessorNode` is considered **terminal** when there are no [children](#children).
