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

## <span id="onAssignment"> onAssignment

```java
void onAssignment(
  Assignment assignment,
  ConsumerGroupMetadata metadata)
```

`onAssignment`...FIXME

`onAssignment` is part of the `ConsumerPartitionAssignor` ([Apache Kafka]({{ book.kafka }}/clients/consumer/ConsumerPartitionAssignor#onAssignment)) abstraction.

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
