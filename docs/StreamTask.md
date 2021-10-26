# StreamTask

`StreamTask` is a concrete [AbstractTask](AbstractTask.md).

## Creating Instance

`StreamTask` takes the following to be created:

* <span id="id"> `TaskId`
* <span id="inputPartitions"> Input `TopicPartition`s
* <span id="topology"> [ProcessorTopology](processor/ProcessorTopology.md)
* <span id="mainConsumer"> Main `Consumer<byte[], byte[]>` ([Apache Kafka]({{ book.kafka }}/clients/consumer/Consumer))
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="cache"> `ThreadCache`
* <span id="time"> `Time`
* <span id="stateMgr"> [ProcessorStateManager](ProcessorStateManager.md)
* [RecordCollector](#recordCollector)
* <span id="processorContext"> `InternalProcessorContext`
* <span id="logContext"> `LogContext`

`StreamTask` is created when:

* `ActiveTaskCreator` is requested to [createActiveTask](ActiveTaskCreator.md#createActiveTask)
* `TopologyTestDriver` is requested to `setupTask`

## <span id="recordCollector"> RecordCollector

`StreamTask` is given a [RecordCollector](RecordCollector.md) when [created](#creating-instance).

## <span id="prepareCommit"> prepareCommit

```java
Map<TopicPartition, OffsetAndMetadata> prepareCommit()
```

`prepareCommit`...FIXME

`prepareCommit` is part of the [Task](Task.md#prepareCommit) abstraction.
