# StateSerdes

`StateSerdes<K, V>` is a factory for creating serializers and deserializers for state stores in Kafka Streams.

## Demo

```scala
import org.apache.kafka.streams.state.StateSerdes
import java.lang.{Long => JLong}
val stateSerdes = StateSerdes.withBuiltinTypes[JLong, String]("topicName", classOf[JLong], classOf[String])
```

```text
scala> :type stateSerdes
org.apache.kafka.streams.state.StateSerdes[Long,String]
```

## Creating Instance

`StateSerdes` takes the following to be created:

* <span id="topic"> Topic Name
* <span id="keySerde"> Key `Serde`
* <span id="valueSerde"> Value `Serde`

`StateSerdes` is created when:

* `CachingWindowStore` is requested to `initInternal`
* `MeteredKeyValueStore` is requested to `initStoreSerde`
* `MeteredSessionStore` is requested to `initStoreSerde`
* `MeteredWindowStore` is requested to `initStoreSerde`
* [withBuiltinTypes](#withBuiltinTypes)

## <span id="withBuiltinTypes"> withBuiltinTypes

```java
StateSerdes<K, V> withBuiltinTypes(
  String topic,
  Class<K> keyClass,
  Class<V> valueClass)
```

`withBuiltinTypes` creates a [StateSerdes](#creating-instance) using `Serdes.serdeFrom` utility with the given key and value classes.
