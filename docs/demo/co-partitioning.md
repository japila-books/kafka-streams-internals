# Demo: Co-partitioning

The demo shows what happens when the topics to be joined are not co-partitioned.

## Create Kafka Topics

Kafka Streams requires that all input topics are available before it can be started (or `MissingSourceTopicException` is thrown).

Please note that the topics have different number of partitions.

```text
./bin/kafka-topics.sh \
  --bootstrap-server :9092 \
  --create \
  --topic demo-left \
  --partitions 1 \
  --replication-factor 1
```

```text
./bin/kafka-topics.sh \
  --bootstrap-server :9092 \
  --create \
  --topic demo-right \
  --partitions 3 \
  --replication-factor 1
```

Make sure the topics are available.

```text
./bin/kafka-topics.sh \
  --bootstrap-server :9092 \
  --list
```

## Stream Join

```scala
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._

import org.apache.kafka.streams.scala._
import ImplicitConversions._
import serialization.Serdes._

import org.apache.kafka.streams.scala.StreamsBuilder
val builder = new StreamsBuilder

type VO = String
type VR = String

val left = builder.stream[String, String](topic = "demo-left")
val right = builder.stream[String, VO](topic = "demo-right")

val joiner: (String, VO) => VR = (leftValue, rightValue) => s"$leftValue -> $rightValue"

import org.apache.kafka.streams.kstream.{JoinWindows, Printed}
import java.time.Duration

val windows = JoinWindows.of(Duration.ofSeconds(10))
val leftRightJoined = left.join(otherStream = right)(joiner, windows)
leftRightJoined.print(Printed.toSysOut)

import org.apache.kafka.streams.Topology
val topology = builder.build()
```

## Review Topology

```scala
println(topology.describe)
```

```text
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [demo-left])
      --> KSTREAM-WINDOWED-0000000002
    Source: KSTREAM-SOURCE-0000000001 (topics: [demo-right])
      --> KSTREAM-WINDOWED-0000000003
    Processor: KSTREAM-WINDOWED-0000000002 (stores: [KSTREAM-JOINTHIS-0000000004-store])
      --> KSTREAM-JOINTHIS-0000000004
      <-- KSTREAM-SOURCE-0000000000
    Processor: KSTREAM-WINDOWED-0000000003 (stores: [KSTREAM-JOINOTHER-0000000005-store])
      --> KSTREAM-JOINOTHER-0000000005
      <-- KSTREAM-SOURCE-0000000001
    Processor: KSTREAM-JOINOTHER-0000000005 (stores: [KSTREAM-JOINTHIS-0000000004-store])
      --> KSTREAM-MERGE-0000000006
      <-- KSTREAM-WINDOWED-0000000003
    Processor: KSTREAM-JOINTHIS-0000000004 (stores: [KSTREAM-JOINOTHER-0000000005-store])
      --> KSTREAM-MERGE-0000000006
      <-- KSTREAM-WINDOWED-0000000002
    Processor: KSTREAM-MERGE-0000000006 (stores: [])
      --> KSTREAM-PRINTER-0000000007
      <-- KSTREAM-JOINTHIS-0000000004, KSTREAM-JOINOTHER-0000000005
    Processor: KSTREAM-PRINTER-0000000007 (stores: [])
      --> none
      <-- KSTREAM-MERGE-0000000006
```

## <span id="KafkaStreams"> KafkaStreams

```scala
import org.apache.kafka.streams.StreamsConfig
import scala.jdk.CollectionConverters._
// Only required configuration properties
// And one more for demo purposes to slow processing to 15 secs
import scala.concurrent.duration._
val props = Map(
  StreamsConfig.APPLICATION_ID_CONFIG -> "demo-join",
  StreamsConfig.BOOTSTRAP_SERVERS_CONFIG -> ":9092",
  StreamsConfig.POLL_MS_CONFIG -> 15.seconds.toMillis).asJava
val config = new StreamsConfig(props)

import org.apache.kafka.streams.KafkaStreams
val streams = new KafkaStreams(topology, config)

streams.start
```

## <span id="TopologyException"> TopologyException: Topics not co-partitioned

```text
org.apache.kafka.streams.errors.TopologyException: Invalid topology: stream-thread [demo-join-6e1e5363-2e22-4cd6-809b-c73d06d405ac-StreamThread-1-consumer] Topics not co-partitioned: [{demo-left=1, demo-right=3}]
	at org.apache.kafka.streams.processor.internals.assignment.CopartitionedTopicsEnforcer.getSamePartitions(CopartitionedTopicsEnforcer.java:161)
	at org.apache.kafka.streams.processor.internals.assignment.CopartitionedTopicsEnforcer.enforce(CopartitionedTopicsEnforcer.java:92)
	at org.apache.kafka.streams.processor.internals.RepartitionTopics.ensureCopartitioning(RepartitionTopics.java:112)
	at org.apache.kafka.streams.processor.internals.RepartitionTopics.setup(RepartitionTopics.java:69)
	at org.apache.kafka.streams.processor.internals.StreamsPartitionAssignor.prepareRepartitionTopics(StreamsPartitionAssignor.java:485)
	at org.apache.kafka.streams.processor.internals.StreamsPartitionAssignor.assign(StreamsPartitionAssignor.java:365)
	at org.apache.kafka.clients.consumer.internals.ConsumerCoordinator.performAssignment(ConsumerCoordinator.java:589)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator.onJoinLeader(AbstractCoordinator.java:690)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator.access$1000(AbstractCoordinator.java:112)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator$JoinGroupResponseHandler.handle(AbstractCoordinator.java:594)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator$JoinGroupResponseHandler.handle(AbstractCoordinator.java:557)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator$CoordinatorResponseHandler.onSuccess(AbstractCoordinator.java:1184)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator$CoordinatorResponseHandler.onSuccess(AbstractCoordinator.java:1159)
	at org.apache.kafka.clients.consumer.internals.RequestFuture$1.onSuccess(RequestFuture.java:206)
	at org.apache.kafka.clients.consumer.internals.RequestFuture.fireSuccess(RequestFuture.java:169)
	at org.apache.kafka.clients.consumer.internals.RequestFuture.complete(RequestFuture.java:129)
	at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient$RequestFutureCompletionHandler.fireCompletion(ConsumerNetworkClient.java:602)
	at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.firePendingCompletedRequests(ConsumerNetworkClient.java:412)
	at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.poll(ConsumerNetworkClient.java:297)
	at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.poll(ConsumerNetworkClient.java:236)
	at org.apache.kafka.clients.consumer.KafkaConsumer.pollForFetches(KafkaConsumer.java:1297)
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1238)
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1211)
	at org.apache.kafka.streams.processor.internals.StreamThread.pollRequests(StreamThread.java:932)
	at org.apache.kafka.streams.processor.internals.StreamThread.pollPhase(StreamThread.java:885)
	at org.apache.kafka.streams.processor.internals.StreamThread.runOnce(StreamThread.java:720)
	at org.apache.kafka.streams.processor.internals.StreamThread.runLoop(StreamThread.java:583)
	at org.apache.kafka.streams.processor.internals.StreamThread.run(StreamThread.java:555)
```
