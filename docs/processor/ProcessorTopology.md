# ProcessorTopology

## Creating Instance

`ProcessorTopology` takes the following to be created:

* <span id="processorNodes"> [ProcessorNode](ProcessorNode.md)s
* <span id="sourceNodesByTopic"> [SourceNode](SourceNode.md)s by topic
* <span id="sinksByTopic"> [SinkNode](SinkNode.md)s by topic
* <span id="stateStores"> [StateStore](StateStore.md)s
* <span id="globalStateStores"> Global [StateStore](StateStore.md)s
* <span id="storeToChangelogTopic"> Store names by topic
* <span id="repartitionTopics"> Repartition topics

`ProcessorTopology` is created when:

* `InternalTopologyBuilder` is requested to [build a ProcessorTopology](../InternalTopologyBuilder.md#build)

## <span id="toString"> Text Representation

```java
String toString() // (1)
String toString(
  String indent)
```

1. Uses an empty indent (to start recursion)

`toString`...FIXME

`toString` is part of the `Object` ([Java]({{ java.api }}/java/lang/Object.html#toString())) abstraction.

### <span id="childrenToString"> childrenToString

```java
String childrenToString(
  String indent, 
  List<? extends ProcessorNode<?, ?, ?, ?>> children)
```

`childrenToString`...FIXME
