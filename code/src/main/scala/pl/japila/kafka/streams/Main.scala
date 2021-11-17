package pl.japila.kafka.streams

import org.apache.kafka.common.serialization.Serdes.StringSerde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig, Topology}

import java.util.Properties

object Main {

  def main(args: Array[String]): Unit = {
    println(">>> main started")

    val topology = new Topology
    // Registers a SourceNodeFactory to handle the given topic(s)
    topology.addSource("input", "input-topic")

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
