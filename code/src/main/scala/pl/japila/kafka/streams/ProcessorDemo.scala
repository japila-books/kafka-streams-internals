package pl.japila.kafka.streams

import org.apache.kafka.common.serialization.Serdes.StringSerde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig, Topology}

import java.util.Properties

object ProcessorDemo {

  def main(args: Array[String]): Unit = {
    println(">>> Creating a Topology")

    val topology = new Topology

    // Registers a SourceNodeFactory to handle the given topic(s)
    // No relation to other nodes thus far
    val sourceName = "my-source"
    topology.addSource(sourceName, "input-topic")

    // Registers a ProcessorNodeFactory
    // Supplier can come with StateStores (StoreBuilders)
    // Processors must have at least one parent (although they are optional)
    // parents are known as "predecessors" internally
    val processorName = "my-processor-forward"
    topology.addProcessor(
      processorName,
      () => new DemoProcessor[String, String, String, String](forward = true),
      sourceName)

    topology.addProcessor(
      "foreach",
      () => new DemoProcessor[String, String, String, String](forward = false),
      processorName)

    println(topology.describe())

    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "processor-demo")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    val config = new StreamsConfig(props)
    val ks = new KafkaStreams(topology, config)
    ks.start()
  }

}
