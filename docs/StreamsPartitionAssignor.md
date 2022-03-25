# StreamsPartitionAssignor

`StreamsPartitionAssignor` is a `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor)) and a `Configurable` ([Apache Kafka]({{ book.kafka }}/Configurable)).

## <span id="supportedProtocols"> Supported Rebalance Protocols

```java
List<RebalanceProtocol> supportedProtocols()
```

`supportedProtocols` returns the following `RebalanceProtocol`s:

1. `RebalanceProtocol.EAGER`
1. `RebalanceProtocol.COOPERATIVE` (based on [upgrade.from](AssignorConfiguration.md#rebalanceProtocol))

`supportedProtocols` is part of the `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor#supportedProtocols)) abstraction.

## <span id="name"> Name

```java
String name()
```

`name` is **stream**.

`name` is part of the `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor#name)) abstraction.

## <span id="configure"> configure

```java
void configure(
  Map<String, ?> configs)
```

`configure` creates a new [AssignorConfiguration](AssignorConfiguration.md) (with the given `configs`).

`configure`...FIXME

`configure` is part of the `Configurable` ([Apache Kafka]({{ book.kafka }}/Configurable#configure)) abstraction.

## <span id="streamsMetadataState"> StreamsMetadataState

`StreamsPartitionAssignor` is given a [StreamsMetadataState](StreamsMetadataState.md) (from a [ReferenceContainer](ReferenceContainer.md#streamsMetadataState)) when requested to [configure](#configure).

The `StreamsMetadataState` is used (to [handle partition assignment change](StreamsMetadataState.md#onChange)) when:

* [assign](#assign)
* [onAssignment](#onAssignment)

## <span id="assign"> Consumer Group Assignment

```java
GroupAssignment assign(
  Cluster metadata,
  GroupSubscription groupSubscription)
```

`assign`...FIXME

`assign` prints out the following DEBUG message to the logs:

```text
Constructed client metadata [clientMetadata] from the member subscriptions.
```

`assign` [prepareRepartitionTopics](#prepareRepartitionTopics) with the given cluster metadata (that gives a `Map<TopicPartition, PartitionInfo>` as `allRepartitionTopicPartitions`).

`assign` prints out the following DEBUG message to the logs:

```text
Created repartition topics [allRepartitionTopicPartitions] from the parsed topology.
```

`assign`...FIXME

`assign` is part of the `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor#assign)) abstraction.

### <span id="prepareRepartitionTopics"> prepareRepartitionTopics

```java
Map<TopicPartition, PartitionInfo> prepareRepartitionTopics(
  Cluster metadata)
```

`prepareRepartitionTopics` creates a new [RepartitionTopics](RepartitionTopics.md) that is requested to [setup](RepartitionTopics.md#setup) and then for the [topicPartitionsInfo](RepartitionTopics.md#topicPartitionsInfo).

### <span id="assignTasksToClients"> assignTasksToClients

```java
boolean assignTasksToClients(
  Cluster fullMetadata,
  Set<String> allSourceTopics,
  Map<Subtopology, TopicsInfo> topicGroups,
  Map<UUID, ClientMetadata> clientMetadataMap,
  Map<TaskId, Set<TopicPartition>> partitionsForTask,
  Set<TaskId> statefulTasks)
```

`assignTasksToClients` creates local `taskForPartition` (`Map<TopicPartition, TaskId>`) and `tasksForTopicGroup` (`Map<Subtopology, Set<TaskId>>`) collections that are used to [populate tasks](#populateTasksForMaps).

`assignTasksToClients` creates a [ChangelogTopics](ChangelogTopics.md) (with the `tasksForTopicGroup` local collection) that is in turn requested to [setup](ChangelogTopics.md#setup).

`assignTasksToClients` [populateClientStatesMap](#populateClientStatesMap).

`assignTasksToClients` prints out the following INFO message to the logs:

```text
All members participating in this rebalance:
 [UUID]: [consumers].
```

`assignTasksToClients` prints out the following DEBUG message to the logs:

```text
Assigning tasks [allTasks] including stateful [statefulTasks] to clients [clientStates] with number of replicas [numStandbyReplicas]
```

`assignTasksToClients` [creates a TaskAssignor](#createTaskAssignor) that is in turn requested to [assign](TaskAssignor.md#assign).

`assignTasksToClients` prints out the following INFO message to the logs:

```text
Assigned tasks [allTasks] including stateful [statefulTasks] to clients as:
 [UUID]=[currentAssignment].
```

In the end, `assignTasksToClients` returns whether the generated assignment requires a followup probing rebalance (from the [TaskAssignor](TaskAssignor.md#assign)).

### <span id="populateTasksForMaps"> populateTasksForMaps

```java
void populateTasksForMaps(
  Map<TopicPartition, TaskId> taskForPartition,
  Map<Subtopology, Set<TaskId>> tasksForTopicGroup,
  Set<String> allSourceTopics,
  Map<TaskId, Set<TopicPartition>> partitionsForTask,
  Cluster fullMetadata)
```

`populateTasksForMaps`...FIXME

### <span id="populateClientStatesMap"> populateClientStatesMap

```java
boolean populateClientStatesMap(
  Map<UUID, ClientState> clientStates,
  Map<UUID, ClientMetadata> clientMetadataMap,
  Map<TopicPartition, TaskId> taskForPartition,
  ChangelogTopics changelogTopics)
```

`populateClientStatesMap`...FIXME

### <span id="createTaskAssignor"> createTaskAssignor

```java
TaskAssignor createTaskAssignor(
  boolean lagComputationSuccessful)
```

`createTaskAssignor` creates a [TaskAssignor](TaskAssignor.md) (using the [taskAssignorSupplier](#taskAssignorSupplier) function).

## <span id="onAssignment"> Handling Task and Partition Assignment

```java
void onAssignment(
  Assignment assignment,
  ConsumerGroupMetadata metadata)
```

`onAssignment` is part of the `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor#onAssignment)) abstraction.

---

`onAssignment` [validateActiveTaskEncoding](#validateActiveTaskEncoding).

`onAssignment` [gets the active tasks](#getActiveTasks) (from the partitions and the `AssignmentInfo` of the given `Assignment`).

`onAssignment` [maybeScheduleFollowupRebalance](#maybeScheduleFollowupRebalance).

`onAssignment` creates an empty (fake) `Cluster` metadata (with the partitions by host, i.e. `Map<HostInfo, Set<TopicPartition>>`) and requests the [StreamsMetadataState](#streamsMetadataState) to [handle the assignment change](StreamsMetadataState.md#onChange).

In the end, `onAssignment` requests the [TaskManager](#taskManager) to [handle the task and partition assignment](TaskManager.md#handleAssignment) (with the active and standby tasks).

### <span id="validateActiveTaskEncoding"> validateActiveTaskEncoding

```java
void validateActiveTaskEncoding(
  List<TopicPartition> partitions,
  AssignmentInfo info,
  String logPrefix)
```

`validateActiveTaskEncoding` throws a `TaskAssignmentException` when the number of `partitions` is not the same as the number of active tasks (of the given `AssignmentInfo`):

```text
[logPrefix]Number of assigned partitions [partitions]
is not equal to the number of active taskIds [activeTasks], assignmentInfo=[info]
```

### <span id="getActiveTasks"> Active Tasks

```java
Map<TaskId, Set<TopicPartition>> getActiveTasks(
  List<TopicPartition> partitions,
  AssignmentInfo info)
```

`getActiveTasks` returns [TaskId](TaskId.md)s and the associated `TopicPartition`s (from the `partitions`).

`getActiveTasks` finds the [TaskId](TaskId.md)s among the `activeTasks` in the given `AssignmentInfo`.

`getActiveTasks` assumes that the position of the `TopicPartition` in the given `partitions` is the position of the corresponding `TaskId` in the `activeTasks` in the given `AssignmentInfo`.

### <span id="maybeScheduleFollowupRebalance"> maybeScheduleFollowupRebalance

```java
void maybeScheduleFollowupRebalance(
  long encodedNextScheduledRebalanceMs,
  int receivedAssignmentMetadataVersion,
  int latestCommonlySupportedVersion,
  Set<HostInfo> groupHostInfo)
```

`maybeScheduleFollowupRebalance`...FIXME

## <span id="subscriptionUserData"> subscriptionUserData

```java
ByteBuffer subscriptionUserData(
  Set<String> topics)
```

`subscriptionUserData`...FIXME

`subscriptionUserData` is part of the `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor#subscriptionUserData)) abstraction.

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.StreamsPartitionAssignor` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.StreamsPartitionAssignor=ALL
```

Refer to [Logging](logging.md).
