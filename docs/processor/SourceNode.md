# SourceNode

`SourceNode` is a [ProcessorNode](ProcessorNode.md).

## Creating Instance

`SourceNode` takes the following to be created:

* <span id="name"> Node Name
* <span id="timestampExtractor"><span id="getTimestampExtractor"> [TimestampExtractor](TimestampExtractor.md)
* <span id="keyDeserializer"> Key `Deserializer`
* <span id="valDeserializer"> Value `Deserializer`

`SourceNode` is created when:

* `SourceNodeFactory` is requested to [build a ProcessorNode](SourceNodeFactory.md#build)
