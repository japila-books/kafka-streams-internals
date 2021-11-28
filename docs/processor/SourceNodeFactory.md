# SourceNodeFactory

`SourceNodeFactory` is a [NodeFactory](NodeFactory.md) with no [predecessors](NodeFactory.md#predecessors).

## Creating Instance

`SourceNodeFactory` takes the following to be created:

* <span id="name"> Node Name
* <span id="topics"> Topics
* <span id="pattern"> Topic Pattern
* <span id="timestampExtractor"> [TimestampExtractor](TimestampExtractor.md)
* <span id="keyDeserializer"> Key `Deserializer`
* <span id="valDeserializer"> Value `Deserializer`

`SourceNodeFactory` is created when:

* `InternalTopologyBuilder` is requested to [addSource](../InternalTopologyBuilder.md#addSource) and [addGlobalStore](../InternalTopologyBuilder.md#addGlobalStore)
