# ProcessorContextImpl

`ProcessorContextImpl` is a [AbstractProcessorContext](AbstractProcessorContext.md)

## Creating Instance

`ProcessorContextImpl` takes the following to be created:

* <span id="id"> [TaskId](../TaskId.md)
* <span id="config"> [StreamsConfig](../StreamsConfig.md)
* [ProcessorStateManager](#stateMgr)
* <span id="metrics"> `StreamsMetricsImpl`
* <span id="cache"> [ThreadCache](../state/ThreadCache.md)

`ProcessorContextImpl` is created when:

* `ActiveTaskCreator` is requested to [createTasks](../ActiveTaskCreator.md#createTasks)
* `StandbyTaskCreator` is requested to [createTasks](../StandbyTaskCreator.md#createTasks)

## <span id="stateManager"><span id="stateMgr"> ProcessorStateManager

`ProcessorContextImpl` is given a [ProcessorStateManager](../ProcessorStateManager.md) when [created](#creating-instance).

## <span id="schedule"> Scheduling Recurring Action

```java
Cancellable schedule(
  Duration interval,
  PunctuationType type,
  Punctuator callback)
```

`schedule` converts the `interval` to milliseconds and requests the [StreamTask](#streamTask) to [schedule the given Punctuator](../StreamTask.md#schedule).

`schedule` makes sure that the `interval` is at least 1 ms or throws an `IllegalArgumentException`:

```text
The minimum supported scheduling interval is 1 millisecond.
```

`schedule` is part of the [ProcessorContext](ProcessorContext.md#schedule) abstraction.

## Forwarding Record Downstream

`ProcessorContextImpl` is associated with a [ProcessorNode](ProcessorNode.md) known as the [current node](#currentNode).

[forward](#forward) (and [forwardInternal](#forwardInternal) in particular) uses the [current ProcessorNode](#currentNode) to [process a given record](ProcessorNode.md#process).

[ProcessorNode](ProcessorNode.md)s are associated with child `ProcessorNode`s known as [children](ProcessorNode.md#children).

### <span id="forward"> forward

```java
void forward(
  K key,
  V value)
void forward(
  K key,
  V value,
  To to)
void forward(
  Record<K, V> record)
void forward(
  Record<K, V> record, 
  String childName)
```

`forward` [forwardInternal](#forwardInternal) to the [child](ProcessorNode.md#getChild) or all the [children](ProcessorNode.md#children) nodes of the [current ProcessorNode](#currentNode).

---

`forward` throws an `UnsupportedOperationException` for a `TaskType.STANDBY` task:

```text
this should not happen: forward() is not supported in standby tasks.
```

---

`forward` is part of the [ProcessorContext](ProcessorContext.md#forward) abstraction.

### <span id="forwardInternal"> forwardInternal

```java
void forwardInternal(
  ProcessorNode<K, V, ?, ?> child,
  Record<K, V> record)
```

`forwardInternal` [sets the current node](AbstractProcessorContext.md#setCurrentNode) to be the given child [ProcessorNode](ProcessorNode.md) that is in turn requested to [process the record](ProcessorNode.md#process).

If the child node is [terminal](ProcessorNode.md#isTerminalNode) (no children), `forwardInternal` requests the [StreamTask](#streamTask) to [maybeRecordE2ELatency](../StreamTask.md#maybeRecordE2ELatency).
