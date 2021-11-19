# ProcessorContext

`ProcessorContext<KForward, VForward>` is an [abstraction](#contract) of [contexts](#implementations) for [Processor](Processor.md)s.

## Contract (Subset)

### <span id="schedule"> schedule

```java
Cancellable schedule(
  Duration interval,
  PunctuationType type,
  Punctuator callback)
```

Schedules an recurring action (`Punctuator`) to be executed every `interval` ms

## Implementations

* `InternalProcessorContext`
* [AbstractProcessorContext](AbstractProcessorContext.md)
