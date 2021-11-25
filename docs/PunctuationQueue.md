# PunctuationQueue

## Creating Instance

`PunctuationQueue` takes no arguments to be created.

`PunctuationQueue` is created along with a [StreamTask](StreamTask.md#PunctuationQueue).

## <span id="pq"> PunctuationSchedules

`PunctuationQueue` creates a `PriorityQueue` ([Java]({{ java.api }}/java/util/PriorityQueue.html)) of [PunctuationSchedule](PunctuationSchedule.md)s.

A new `PunctuationSchedule` is added in [schedule](#schedule).

## <span id="schedule"> Scheduling PunctuationSchedule

```java
Cancellable schedule(
  PunctuationSchedule sched)
```

`schedule` adds the given [PunctuationSchedule](PunctuationSchedule.md) to the [PunctuationSchedules](#pq).

`schedule` is used when:

* `StreamTask` is requested to [schedule a Punctuator](StreamTask.md#schedule)

## <span id="mayPunctuate"> mayPunctuate

```java
boolean mayPunctuate(
  long timestamp,
  PunctuationType type,
  ProcessorNodePunctuator processorNodePunctuator)
```

`mayPunctuate` returns `true` when one or more punctuators were executed.

---

`mayPunctuate` takes a peek at the first [PunctuationSchedule](PunctuationSchedule.md) in the [PriorityQueue](#pq) (with order by `timestamp`).

`mayPunctuate` goes over the `PunctuationSchedule`s in the [PriorityQueue](#pq) until there are no more `PunctuationSchedule`s or their timestamp is after the given `timestamp` (in a `while` loop).

If the [timestamp](PunctuationSchedule.md#timestamp) of the `PunctuationSchedule` is below or equal to the given `timestamp`, `mayPunctuate` takes it out (and removes) from the [PriorityQueue](#pq).

If the `PunctuationSchedule` is not [cancelled](PunctuationSchedule.md#isCancelled), `mayPunctuate` requests the given [ProcessorNodePunctuator](processor/ProcessorNodePunctuator.md) to [punctuate](processor/ProcessorNodePunctuator.md#punctuate).

Only when the `PunctuationSchedule` was not [cancelled](PunctuationSchedule.md#isCancelled) when the punctuator was executed, `mayPunctuate` requests the `PunctuationSchedule` for [next PunctuationSchedule](PunctuationSchedule.md#next) (for the given current `timestamp`) and adds it to the [PriorityQueue](#pq).

`mayPunctuate` takes a peek again at the (next) first `PunctuationSchedule` in the [PriorityQueue](#pq) and starts (the loop) again.

`mayPunctuate` is used when:

* `StreamTask` is requested to [maybePunctuateStreamTime](StreamTask.md#maybePunctuateStreamTime) and [maybePunctuateSystemTime](StreamTask.md#maybePunctuateSystemTime)
