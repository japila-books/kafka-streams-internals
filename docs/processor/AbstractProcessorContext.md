# AbstractProcessorContext

`AbstractProcessorContext<KOut, VOut>` is an [extension](#contract) of the [InternalProcessorContext](InternalProcessorContext.md) abstraction for [ProcessorContexts](#implementations) with a [StateManager](#stateManager).

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
