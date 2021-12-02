package pl.japila.kafka.streams

import org.apache.kafka.streams.{KafkaStreams, KeyValue, StreamsConfig}
import org.apache.kafka.streams.kstream.{Printed, Transformer, TransformerSupplier}
import org.apache.kafka.streams.processor.ProcessorContext

import java.util.Properties

object MergeDemo {

  def main(args: Array[String]): Unit = {

    import org.apache.kafka.streams.scala._
    import ImplicitConversions._
    import serialization.Serdes._

    val sb = new StreamsBuilder()

    /**
     * export KAFKA_HOME=~/dev/oss/kafka/
     * $KAFKA_HOME/bin/kafka-topics.sh \
     * --bootstrap-server :9092 \
     * --create \
     * --topic demo-merge-one \
     * --partitions 1 \
     * --replication-factor 1
     */
    val ones = sb.stream[String, String](topic = "demo-merge-one")

    /**
     * export KAFKA_HOME=~/dev/oss/kafka/
     * $KAFKA_HOME/bin/kafka-topics.sh \
     * --bootstrap-server :9092 \
     * --create \
     * --topic demo-merge-two \
     * --partitions 2 \
     * --replication-factor 1
     */
    val twos = sb.stream[String, String](topic = "demo-merge-two")
    ones.merge(twos)
      .transform(new TransformerSupplier[String, String, KeyValue[String, String]]() {
        override def get(): Transformer[String, String, KeyValue[String, String]] =
          new Transformer[String, String, KeyValue[String, String]] {
            var context: ProcessorContext = _
            override def init(context: ProcessorContext): Unit = {
              this.context = context
            }

            override def transform(key: String, value: String): KeyValue[String, String] = {
              val taskId = context.taskId()
              println(s">>> Task ID: $taskId, key=$key, value=$value")
              KeyValue.pair(key, value)
            }

            override def close(): Unit = {}
          }
      })
      .print(Printed.toSysOut)

    val topology = sb.build()
    println(topology.describe())

    val props = new Properties()
    val appId = this.getClass.getSimpleName.replace("$", "")
    props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, appId)
    props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    import org.apache.kafka.common.serialization.Serdes.StringSerde
    props.putIfAbsent(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    props.putIfAbsent(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    import org.apache.kafka.common.metrics.Sensor.RecordingLevel
    props.putIfAbsent(StreamsConfig.METRICS_RECORDING_LEVEL_CONFIG, RecordingLevel.DEBUG.name)
    val config = new StreamsConfig(props)
    val ks = new KafkaStreams(topology, config)
    ks.start()

    /**
     * echo 1,0 | kcat -P -b :9092 -K , -t demo-merge-one -p 0
     *
     * echo 2,0 | kcat -P -b :9092 -K , -t demo-merge-two -p 0
     * echo 2,1 | kcat -P -b :9092 -K , -t demo-merge-two -p 1
     */

  }
}
