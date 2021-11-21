# PunctuationSchedule

`PunctuationSchedule` is a `Stamped` of [ProcessorNode](ProcessorNode.md)s.

As a `Stamped`, `PunctuationSchedule` is also a `Comparable` ([Java]({{ java.api }}/java/lang/Comparable.html)) and used in [PunctuationQueue](../PunctuationQueue.md#pq)s (which are `PriorityQueue`s under the covers).