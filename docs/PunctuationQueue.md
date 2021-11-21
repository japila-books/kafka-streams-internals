# PunctuationQueue

## Creating Instance

`PunctuationQueue` takes no arguments to be created.

`PunctuationQueue` is created along with a [StreamTask](StreamTask.md#PunctuationQueue).

## <span id="mayPunctuate"> mayPunctuate

```java
boolean mayPunctuate(
  long timestamp,
  PunctuationType type,
  ProcessorNodePunctuator processorNodePunctuator)
```

`mayPunctuate`...FIXME

`mayPunctuate` is used when:

* `StreamTask` is requested to [maybePunctuateStreamTime](StreamTask.md#maybePunctuateStreamTime) and [maybePunctuateSystemTime](StreamTask.md#maybePunctuateSystemTime)

## <span id="pq"> PunctuationSchedules

`PunctuationQueue` creates a `PriorityQueue` ([Java]({{ java.api }}/java/util/PriorityQueue.html)) of [PunctuationSchedule](processor/PunctuationSchedule.md)s.

A new `PunctuationSchedule` is added in [schedule](#schedule).

## <span id="schedule"> Scheduling PunctuationSchedule

```java
Cancellable schedule(
  PunctuationSchedule sched)
```

`schedule` adds the given [PunctuationSchedule](processor/PunctuationSchedule.md) to the [PunctuationSchedules](#pq).

`schedule` is used when:

* `StreamTask` is requested to [schedule a Punctuator](StreamTask.md#schedule)
