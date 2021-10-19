# RepartitionTopics

`RepartitionTopics` is a helper class of [StreamsPartitionAssignor](StreamsPartitionAssignor.md) (to [prepare repartition topics](StreamsPartitionAssignor.md#prepareRepartitionTopics)).

## Creating Instance

`RepartitionTopics` takes the following to be created:

* <span id="internalTopologyBuilder"> [InternalTopologyBuilder](InternalTopologyBuilder.md)
* <span id="internalTopicManager"> [InternalTopicManager](InternalTopicManager.md)
* <span id="copartitionedTopicsEnforcer"> [CopartitionedTopicsEnforcer](CopartitionedTopicsEnforcer.md)
* <span id="clusterMetadata"> `Cluster` metadata
* <span id="logPrefix"> Log prefix

## <span id="topicPartitionInfos"><span id="topicPartitionsInfo"> topicPartitionInfos

```java
Map<TopicPartition, PartitionInfo> topicPartitionInfos
```

`RepartitionTopics` defines a `topicPartitionInfos` internal registry of `TopicPartition`s and the associated `PartitionInfo`.

`topicPartitionInfos` is initially empty and filled up when requested to [setup](#setup).

## <span id="setup"> setup

```java
void setup()
```

`setup` requests the [InternalTopologyBuilder](#internalTopologyBuilder) for the [topic groups](InternalTopologyBuilder.md#topicGroups) (that gives a `Map<Subtopology, TopicsInfo>`).

`setup` [computeRepartitionTopicConfig](#computeRepartitionTopicConfig) for the topic groups and the [cluster metadata](#clusterMetadata) (that gives a `Map<String, InternalTopicConfig>`).

`setup` [ensureCopartitioning](#ensureCopartitioning) of the [copartitionGroups](InternalTopologyBuilder.md#copartitionGroups) (from [InternalTopologyBuilder](#internalTopologyBuilder)).

`setup` requests the [InternalTopicManager](#internalTopicManager) to [make the repartition source topics ready](InternalTopicManager.md#makeReady) (exist and have proper number of partitions, creating if necessary).

### <span id="computeRepartitionTopicConfig"> computeRepartitionTopicConfig

```java
Map<String, InternalTopicConfig> computeRepartitionTopicConfig(
  Map<Subtopology, TopicsInfo> topicGroups,
  Cluster clusterMetadata)
```

`computeRepartitionTopicConfig`...FIXME

### <span id="ensureCopartitioning"> ensureCopartitioning

```java
void ensureCopartitioning(
  Collection<Set<String>> copartitionGroups,
  Map<String, InternalTopicConfig> repartitionTopicMetadata,
  Cluster clusterMetadata)
```

`ensureCopartitioning`...FIXME
