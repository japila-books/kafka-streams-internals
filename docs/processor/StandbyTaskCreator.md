# StandbyTaskCreator

## Creating Instance

`StandbyTaskCreator` takes the following to be created:

* <span id="builder"> [InternalTopologyBuilder](../InternalTopologyBuilder.md)
* <span id="config"> [StreamsConfig](../StreamsConfig.md)
* <span id="streamsMetrics"> `StreamsMetricsImpl`
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="storeChangelogReader"> `ChangelogReader`
* <span id="threadId"> Thread ID
* <span id="log"> `Logger`

When created, `StandbyTaskCreator` initializes a [task sensor](#createTaskSensor) and a [ThreadCache](#dummyCache).

`StandbyTaskCreator` is created when:

* `StreamThread` utility is used to [create a StreamThread](StreamThread.md#create)
