# PartitionGrouper

## Creating Instance

`PartitionGrouper` takes no arguments to be created.

`PartitionGrouper` is created when:

* `StreamsPartitionAssignor` is requested to [configure](StreamsPartitionAssignor.md#configure)

## <span id="partitionGroups"> partitionGroups

```java
Map<TaskId, Set<TopicPartition>> partitionGroups(
  Map<Subtopology, Set<String>> topicGroups, 
  Cluster metadata)
```

`partitionGroups`...FIXME

`partitionGroups` is used when:

* `StreamsPartitionAssignor` is requested to [assign](StreamsPartitionAssignor.md#assign)

### <span id="maxNumPartitions"> Maximum Number of Partitions

```java
int maxNumPartitions(
  Cluster metadata, 
  Set<String> topics)
```

`maxNumPartitions` finds the maximum number of partitions across all the given `topics` (using `Cluster` metadata).
