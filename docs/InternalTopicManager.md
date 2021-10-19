# InternalTopicManager

## Creating Instance

`InternalTopicManager` takes the following to be created:

* <span id="time"> `Time`
* <span id="adminClient"> `Admin` ([Apache Kafka]({{ book.kafka }}/Admin))
* <span id="streamsConfig"> [StreamsConfig](StreamsConfig.md)

`InternalTopicManager` is created when:

* `AssignorConfiguration` is requested for an [InternalTopicManager](AssignorConfiguration.md#internalTopicManager)

## <span id="makeReady"> makeReady

```java
Set<String> makeReady(
  Map<String, InternalTopicConfig> topics)
```

`makeReady`...FIXME

`makeReady` is used when:

* `ChangelogTopics` is requested to [setup](ChangelogTopics.md#setup)
* `RepartitionTopics` is requested to [setup](RepartitionTopics.md#setup)
