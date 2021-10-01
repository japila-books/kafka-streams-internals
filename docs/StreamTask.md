# StreamTask

`StreamTask` is a concrete [AbstractTask](AbstractTask.md).

## Creating Instance

`StreamTask` takes the following to be created:

* <span id="id"> `TaskId`
* <span id="inputPartitions"> Input `TopicPartition`s
* <span id="topology"> `ProcessorTopology`
* <span id="mainConsumer"> Main `Consumer<byte[], byte[]>`
* <span id="config"> [StreamsConfig](StreamsConfig.md)
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="stateDirectory"> `StateDirectory`
* <span id="cache"> `ThreadCache`
* <span id="time"> `Time`
* <span id="stateMgr"> `ProcessorStateManager`
* <span id="recordCollector"> `RecordCollector`
* <span id="processorContext"> `InternalProcessorContext`
* <span id="logContext"> `LogContext`

`StreamTask` is created when:

* `ActiveTaskCreator` is requested to [createActiveTask](ActiveTaskCreator.md#createActiveTask)
* `TopologyTestDriver` is requested to `setupTask`
