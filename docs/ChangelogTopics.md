# ChangelogTopics

## Creating Instance

`ChangelogTopics` takes the following to be created:

* <span id="internalTopicManager"> [InternalTopicManager](InternalTopicManager.md)
* <span id="topicGroups"> Topic Groups (`Map<Subtopology, TopicsInfo>`)
* <span id="tasksForTopicGroup"> Tasks for Topic Groups (`Map<Subtopology, Set<TaskId>>`)
* <span id="logPrefix"> Log Prefix

`ChangelogTopics` is created when:

* `StreamsPartitionAssignor` is requested to [assignTasksToClients](StreamsPartitionAssignor.md#assignTasksToClients)

## <span id="setup"> setup

```java
void setup()
```

`setup`...FIXME

`setup` is used when:

* `StreamsPartitionAssignor` is requested for [consumer group assignment](StreamsPartitionAssignor.md#assign) (and [assignTasksToClients](StreamsPartitionAssignor.md#assignTasksToClients))
