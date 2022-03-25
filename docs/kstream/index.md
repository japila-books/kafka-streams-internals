# Streams DSL

**Streams DSL** (_KStream DSL_) is a high-level API for developers to define streaming topologies in Kafka Streams.

The entry point to the KStream DSL is [StreamsBuilder](StreamsBuilder.md).

Main abstractions (for Kafka Streams developers):

* [Consumed](Consumed.md)
* [GlobalKTable](GlobalKTable.md)
* [KStream](KStream.md)
* [KTable](KTable.md)
* [Materialized](Materialized.md)
* [Produced](Produced.md)
* _others_

A typical Kafka Streams application (that uses this Streams DSL and [Scala API for Kafka Streams](../scala.md)) looks as follows:

```scala
import org.apache.kafka.streams.scala._
import ImplicitConversions._
import serialization.Serdes._

val builder = new StreamsBuilder

// Add a KStream if needed
// K and V are the types of keys and values, accordingly
builder.stream[K, V](...)

// Add a KTable if needed
builder.table[K, V](...)

// Add a global store if needed
builder.addGlobalStore(...)

// Add a global store if needed
builder.globalTable[K, V](...)

// In the end, build a topology
val topology = builder.build
```
