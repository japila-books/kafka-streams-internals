# PunctuationSchedule

`PunctuationSchedule` is a `Stamped` of [ProcessorNode](processor/ProcessorNode.md)s.

As a `Stamped`, `PunctuationSchedule` is also a `Comparable` ([Java]({{ java.api }}/java/lang/Comparable.html)) and used in [PunctuationQueue](PunctuationQueue.md#pq)s (which are `PriorityQueue`s under the covers).

## Creating Instance

`PunctuationSchedule` takes the following to be created:

* <span id="node"> [ProcessorNode](processor/ProcessorNode.md)
* <span id="time"> Timestamp
* <span id="interval"> Interval
* <span id="punctuator"> [Punctuator](processor/Punctuator.md)
* <span id="cancellable"> `RepointableCancellable`

`PunctuationSchedule` is created when:

* `PunctuationSchedule` is requested for the [next PunctuationSchedule](#next)
* `StreamTask` is requested to [schedule a punctuator](StreamTask.md#schedule)

## <span id="isCancelled"> isCancelled

`PunctuationSchedule` defines `isCancelled` internal flag when [created](#creating-instance).

`isCancelled` is off (`false`) by default and can be turned on (`true`) in [markCancelled](#markCancelled).

### <span id="markCancelled"> markCancelled

```java
void markCancelled()
```

`markCancelled` turns the [isCancelled](#isCancelled) flag on.
