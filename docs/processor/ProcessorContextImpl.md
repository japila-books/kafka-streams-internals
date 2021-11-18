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

## <span id="schedule"> schedule

```java
Cancellable schedule(
  Duration interval,
  PunctuationType type,
  Punctuator callback)
```

`schedule` converts the `interval` to milliseconds and requests the [StreamTask](#streamTask) to [schedule](../StreamTask.md#schedule).

`schedule` makes sure that the `interval` is at least 1 ms or throws an `IllegalArgumentException`:

```text
The minimum supported scheduling interval is 1 millisecond.
```

`schedule` is part of the [ProcessorContext](ProcessorContext.md#schedule) abstraction.
