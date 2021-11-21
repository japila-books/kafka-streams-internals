# AbstractProcessorContext

`AbstractProcessorContext<KOut, VOut>` is an [extension](#contract) of the [InternalProcessorContext](InternalProcessorContext.md) abstraction for [ProcessorContexts](#implementations) with an associated [StateManager](#stateManager).

## Contract

### <span id="stateManager"> StateManager

```java
StateManager stateManager()
```

[StateManager](../StateManager.md)

Used when:

* `AbstractProcessorContext` is requested to [stateDir](#stateDir), [register a StateStore](#register), [taskType](#taskType), [changelogFor](#changelogFor)
* `ProcessorContextImpl` is requested to [logChange](ProcessorContextImpl.md#logChange)

## Implementations

* [GlobalProcessorContextImpl](GlobalProcessorContextImpl.md)
* [ProcessorContextImpl](ProcessorContextImpl.md)

## Creating Instance

`AbstractProcessorContext` takes the following to be created:

* <span id="taskId"> [TaskId](../TaskId.md)
* <span id="config"> [StreamsConfig](../StreamsConfig.md)
* <span id="metrics"> [StreamsMetricsImpl](../metrics/StreamsMetricsImpl.md)
* <span id="cache"> [ThreadCache](../state/ThreadCache.md)

!!! note "Abstract Class"
    `AbstractProcessorContext` is an abstract class and cannot be created directly. It is created indirectly for the [concrete AbstractProcessorContexts](#implementations).

## <span id="currentNode"><span id="setCurrentNode"> Current ProcessorNode

`AbstractProcessorContext` defines `currentNode` internal registry for a [ProcessorNode](ProcessorNode.md) (that is required by [InternalProcessorContext](InternalProcessorContext.md) abstraction for [currentNode](InternalProcessorContext.md#currentNode)).
