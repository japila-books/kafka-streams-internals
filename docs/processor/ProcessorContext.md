# ProcessorContext

`ProcessorContext<KForward, VForward>` is an [abstraction](#contract) of [contexts](#implementations) for [Processor](Processor.md)s.

## Contract (Subset)

### <span id="forward"> Forwarding Record

```java
void forward(
  Record<K, V> record)
void forward(
  Record<K, V> record,
  String childName)
```

Forwards a `Record` (to all or the specified child processor)

See [ProcessorContextImpl](ProcessorContextImpl.md#forward)

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
