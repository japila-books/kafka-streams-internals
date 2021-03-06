package pl.japila.kafka.streams

import org.apache.kafka.streams.{KafkaStreams, StreamsConfig, Topology}

import java.util.Properties

object ProcessorApp {

  def main(args: Array[String]): Unit = {
    println(">>> Creating a Topology")

    val topology = new Topology

    // Registers a SourceNodeFactory to handle the given topic(s)
    // No relation to other nodes thus far
    val sourceName = "my-source"
    import org.apache.kafka.clients.consumer.ConsumerRecord
    import org.apache.kafka.streams.processor.FailOnInvalidTimestamp
    object timestampExtractor extends FailOnInvalidTimestamp {
      override def extract(record: ConsumerRecord[Object, Object], partitionTime: Long): Long = {
        val timestamp = super.extract(record, partitionTime)
        val key = record.key()
        val value = record.value()
        println(s">>> extract(key=$key, value=$value, partitionTime=$partitionTime): $timestamp")
        value match {
          case back: String =>
            val timeChange = back.toIntOption.getOrElse(0)
            println(s">>> [extract] timeChange = $timeChange")
            timestamp - timeChange
          case _ =>
            println(s">>> [extract] No timeChange")
            timestamp
        }
      }
    }
    topology.addSource(
      timestampExtractor,
      sourceName,
      "input-topic")

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
    import org.apache.kafka.common.serialization.Serdes.StringSerde
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    import org.apache.kafka.common.metrics.Sensor.RecordingLevel
    props.put(StreamsConfig.METRICS_RECORDING_LEVEL_CONFIG, RecordingLevel.DEBUG.name)
    val config = new StreamsConfig(props)
    val ks = new KafkaStreams(topology, config)
    ks.start()
  }

}
