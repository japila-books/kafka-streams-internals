# StreamsProducer

`StreamsProducer` uses a [Kafka Producer](#producer) to [send records](#send) for [RecordCollectorImpl](RecordCollectorImpl.md).

## Creating Instance

`StreamsProducer` takes the following to be created:

* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="threadId"> Thread ID
* <span id="clientSupplier"> [KafkaClientSupplier](KafkaClientSupplier.md)
* <span id="taskId"> [TaskId](TaskId.md)
* <span id="processId"> Process ID
* <span id="logContext"> `LogContext`

`StreamsProducer` is created when:

* `ActiveTaskCreator` is [created](ActiveTaskCreator.md#threadProducer) and requested to [createActiveTask](ActiveTaskCreator.md#createActiveTask)

## <span id="producer"> Kafka Producer

`StreamsProducer` requests the [KafkaClientSupplier](#clientSupplier) to [get a Kafka Producer](KafkaClientSupplier.md#getProducer) (`Producer<byte[], byte[]>`) when [created](#creating-instance) and requested to [resetProducer](#resetProducer).

## <span id="send"> Sending Record

```java
Future<RecordMetadata> send(
  ProducerRecord<byte[], byte[]> record,
  Callback callback)
```

`send` [maybeBeginTransaction](#maybeBeginTransaction) and requests the [Producer](#producer) to send the record (with the given `Callback`).

`send` is used when:

* `RecordCollectorImpl` is requested to [send a record](RecordCollectorImpl.md#send)

## <span id="maybeBeginTransaction"> maybeBeginTransaction

```java
void maybeBeginTransaction()
```

`maybeBeginTransaction`...FIXME

`maybeBeginTransaction` is used when:

* `StreamsProducer` is requested to [send a record](#send) and [commitTransaction](#commitTransaction)

## <span id="commitTransaction"> commitTransaction

```java
void commitTransaction(
  Map<TopicPartition, OffsetAndMetadata> offsets,
  ConsumerGroupMetadata consumerGroupMetadata)
```

`commitTransaction`...FIXME

`commitTransaction` is used when:

* `TaskManager` is requested to [commitOffsetsOrTransaction](TaskManager.md#commitOffsetsOrTransaction)
* `TopologyTestDriver` is requested to [commit](TopologyTestDriver.md#commit)
