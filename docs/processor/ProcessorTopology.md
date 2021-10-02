# ProcessorTopology

## Creating Instance

`ProcessorTopology` takes the following to be created:

* <span id="processorNodes"> `ProcessorNode`s
* <span id="sourceNodesByTopic"> `SourceNode`s by topic
* <span id="sinksByTopic"> `SinkNode` by topic
* <span id="stateStores"> `StateStore`s
* <span id="globalStateStores"> Global `StateStore`s
* <span id="storeToChangelogTopic"> Store names by topic
* <span id="repartitionTopics"> Repartition topics

`ProcessorTopology` is created when:

* `InternalTopologyBuilder` is requested to [build a ProcessorTopology](InternalTopologyBuilder.md#build)
