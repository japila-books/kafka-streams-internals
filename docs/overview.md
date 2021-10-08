# {{ book.title }} &mdash; Stream Processing Library on Apache Kafka

[Kafka Streams](https://kafka.apache.org/) is a library for developing applications for processing records from topics in Apache Kafka.

Kafka Streams comes with high-level [Streams DSL](kstream/index.md) and low-level [Processor API](processor/index.md) to describe a [Topology](Topology.md) that eventually is built as a [ProcessorTopology](processor/ProcessorTopology.md).

The execution environment of `ProcessorTopology` is [KafkaStreams](KafkaStreams.md). Once created, the `KafkaStreams` instance is supposed to be [started](KafkaStreams.md#start) to start stream processing.
