# StandbyTask

`StandbyTask` is a [Task](Task.md) (and [AbstractTask](#AbstractTask)).

## Creating Instance

`StandbyTask` takes the following to be created:

* <span id="id"> `TaskId`
* <span id="inputPartitions"> Input `TopicPartition`s
* <span id="topology"> [ProcessorTopology](ProcessorTopology.md)
* <span id="config"> [StreamsConfig](../StreamsConfig.md)
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="stateMgr"> [ProcessorStateManager](ProcessorStateManager.md)
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="cache"> `ThreadCache`
* <span id="processorContext"> `InternalProcessorContext`

When created, `StandbyTask` requests the [InternalProcessorContext](#processorContext) to `transitionToStandby` with the [ThreadCache](#cache).

`StandbyTask` is created when:

* `StandbyTaskCreator` is requested to [createStandbyTask](StandbyTaskCreator.md#createStandbyTask)

## <span id="AbstractTask"> AbstractTask

`StandbyTask` is an [AbstractTask](AbstractTask.md).

### <span id="taskType"> Task Type

`StandbyTask` uses **standby-task** for [task type](AbstractTask.md#taskType).

### <span id="clazz"> Class

`StandbyTask` uses `StandbyTask.class` for [clazz](AbstractTask.md#clazz).
