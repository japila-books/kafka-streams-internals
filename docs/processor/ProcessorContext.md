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

### <span id="schedule"> Scheduling Recurring Action

```java
Cancellable schedule(
  Duration interval,
  PunctuationType type,
  Punctuator callback)
```

Schedules an recurring action ([Punctuator](Punctuator.md)) to be executed every `interval` ms

See [ProcessorContextImpl](ProcessorContextImpl.md#schedule)

## Implementations

* [AbstractProcessorContext](AbstractProcessorContext.md)
* [InternalProcessorContext](InternalProcessorContext.md)
