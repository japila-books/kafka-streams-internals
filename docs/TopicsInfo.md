# TopicsInfo

## Creating Instance

`TopicsInfo` takes the following to be created:

* <span id="sinkTopics"> Names of the Sink Topics
* <span id="sourceTopics"> Names of the Source Topics
* <span id="repartitionSourceTopics"> Repartition Source Topics (`Map<String, InternalTopicConfig>`)
* <span id="stateChangelogTopics"> State Changelog Topics (`Map<String, InternalTopicConfig>`)

`TopicsInfo` is created when:

* `InternalTopologyBuilder` is requested for [topic groups](InternalTopologyBuilder.md#topicGroups)
