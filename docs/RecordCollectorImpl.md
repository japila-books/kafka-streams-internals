# RecordCollectorImpl

`RecordCollectorImpl` is a [RecordCollector](RecordCollector.md).

## Creating Instance

`RecordCollectorImpl` takes the following to be created:

* <span id="logContext"> `LogContext`
* <span id="taskId"> [TaskId](TaskId.md)
* <span id="streamsProducer"> [StreamsProducer](StreamsProducer.md)
* <span id="productionExceptionHandler"> `ProductionExceptionHandler`
* <span id="streamsMetrics"> [StreamsMetricsImpl](metrics/StreamsMetricsImpl.md)

`RecordCollectorImpl` is created when:

* `ActiveTaskCreator` is requested to [createActiveTask](ActiveTaskCreator.md#createActiveTask)
* `TopologyTestDriver` is requested to [setupTask](TopologyTestDriver.md#setupTask)

## <span id="send"> Sending Record

```java
void send(
  String topic,
  K key,
  V value,
  Headers headers,
  Integer partition,
  Long timestamp,
  Serializer<K> keySerializer,
  Serializer<V> valueSerializer)
void send(
  String topic,
  K key,
  V value,
  Headers headers,
  Long timestamp,
  Serializer<K> keySerializer,
  Serializer<V> valueSerializer,
  StreamPartitioner<? super K, ? super V> partitioner)
```

`send` uses the given [StreamPartitioner](processor/StreamPartitioner.md) to [determine the partition (of the record to be sent out)](processor/StreamPartitioner.md#partition) (if defined) and creates a `ProducerRecord`.

`send` requests the [StreamsProducer](#streamsProducer) to [send the record](StreamsProducer.md#send) and records the offset in the [offsets](#offsets) registry.

`send` is part of the [RecordCollector](RecordCollector.md#send) abstraction.
