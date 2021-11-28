# AbstractTask

`AbstractTask` is a base abstraction of the [Task](Task.md) abstraction for [tasks](#implementations).

## Implementations

* [StandbyTask](StandbyTask.md)
* [StreamTask](StreamTask.md)

## Creating Instance

`AbstractTask` takes the following to be created:

* <span id="id"> [TaskId](TaskId.md)
* <span id="topology"> [ProcessorTopology](processor/ProcessorTopology.md)
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="stateMgr"> [ProcessorStateManager](ProcessorStateManager.md)
* [Input Partitions](#inputPartitions)
* <span id="taskTimeoutMs"> [task.timeout.ms](StreamsConfig.md#TASK_TIMEOUT_MS_CONFIG) configuration property
* <span id="taskType"> Task Type
* <span id="clazz"> `AbstractTask` [Implementation](#implementations)

??? note "Abstract Class"
    `AbstractTask` is an abstract class and cannot be created directly. It is created indirectly for the [concrete AbstractTasks](#implementations).

## <span id="inputPartitions"> Input Partitions

`AbstractTask` is given a set of input `TopicPartition`s to consume and process records from when [created](#creating-instance) and later when [updateInputPartitions](#updateInputPartitions).
