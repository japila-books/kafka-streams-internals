# Demo: Developing Kafka Streams Application

## <span id="Topology"> Processing Topology

A Kafka Streams application requires a [processing topology](../Topology.md) that is described (_built_) using [StreamsBuilder](../StreamsBuilder.md).

```scala
import org.apache.kafka.streams.scala.StreamsBuilder
val streamBuilder = new StreamsBuilder
```

```scala
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes._
```

```scala
val records = streamBuilder.stream[String, String](topic = "streams-demo-input")
records.to(topic = "streams-demo-output")
```

```scala
import org.apache.kafka.streams.Topology
val topology = streamBuilder.build()
```

A topology can be described.

```scala
println(topology.describe)
```

```text
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [streams-demo-input])
      --> KSTREAM-SINK-0000000001
    Sink: KSTREAM-SINK-0000000001 (topic: streams-demo-output)
      <-- KSTREAM-SOURCE-0000000000
```

## Create Kafka Topics

Kafka Streams requires that all input topics are available before it can be started (or `MissingSourceTopicException` is thrown).

```text
./bin/kafka-topics.sh \
  --bootstrap-server :9092 \
  --create \
  --topic streams-demo-input \
  --partitions 1 \
  --replication-factor 1
```

```text
./bin/kafka-topics.sh \
  --bootstrap-server :9092 \
  --create \
  --topic streams-demo-output \
  --partitions 1 \
  --replication-factor 1
```

## <span id="StreamsConfig"> StreamsConfig

An execution environment of a Kafka Streams application is configured using [StreamsConfig](../StreamsConfig.md).

```scala
import org.apache.kafka.streams.StreamsConfig
import scala.jdk.CollectionConverters._
// Only required configuration properties
val props = Map(
  StreamsConfig.APPLICATION_ID_CONFIG -> "kafka-streams-demo",
  StreamsConfig.BOOTSTRAP_SERVERS_CONFIG -> ":9092").asJava
val config = new StreamsConfig(props)
```

## <span id="KafkaStreams"> KafkaStreams

The execution environment of a Kafka Stream application is [KafkaStreams](../KafkaStreams.md).

```scala
import org.apache.kafka.streams.KafkaStreams
val streams = new KafkaStreams(topology, config)
```

Eventually, `KafkaStreams` should be started for the stream processing to be executed.

```scala
streams.start()
```

## kcat

```text
kcat -P -b localhost -t streams-demo-input
```

```text
kcat -C -b localhost -t streams-demo-output
```
