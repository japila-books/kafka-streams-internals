# ReferenceContainer

`ReferenceContainer` is used to hold all of the following instances:

* <span id="mainConsumer"> Main Kafka `Consumer<byte[], byte[]>` ([Apache Kafka]({{ book.kafka }}/clients/consumer/Consumer))
* <span id="adminClient"> `Admin` Client ([Apache Kafka]({{ book.kafka }}/clients/admin/Admin))
* <span id="taskManager"> [TaskManager](TaskManager.md)
* <span id="streamsMetadataState"> [StreamsMetadataState](processor/StreamsMetadataState.md)
* <span id="assignmentErrorCode"> Assignment Error Code
* <span id="nextScheduledRebalanceMs"> Next Scheduled Rebalance Time
* <span id="time"> `Time`

## Creating Instance

`ReferenceContainer` takes no arguments to be created.

`ReferenceContainer` is created when:

* `StreamThread` utility is used to [create a StreamThread](StreamThread.md#create)
