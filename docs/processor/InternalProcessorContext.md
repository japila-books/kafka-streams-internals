# InternalProcessorContext

`InternalProcessorContext` is an [extension](#contract) of [ProcessorContext](ProcessorContext.md) and `StateStoreContext` abstractions.

## Contract (Subset)

### <span id="currentNode"> currentNode

```java
ProcessorNode<?, ?, ?, ?> currentNode()
```

Current [ProcessorNode](ProcessorNode.md) (as set by [setCurrentNode](#setCurrentNode))

### <span id="initialize"> initialize

```java
void initialize()
```

Used when:

* `GlobalStateUpdateTask` is requested to [initialize](GlobalStateUpdateTask.md#initialize)
* `StandbyTask` is requested to [initializeIfNeeded](../StandbyTask.md#initializeIfNeeded)
* `StreamTask` is requested to [completeRestoration](../StreamTask.md#completeRestoration)

### <span id="setCurrentNode"> setCurrentNode

```java
void setCurrentNode(
  ProcessorNode<?, ?, ?, ?> currentNode)
```

Sets the given [ProcessorNode](ProcessorNode.md) as the [current node](#currentNode)

## Implementations

* [AbstractProcessorContext](AbstractProcessorContext.md)
