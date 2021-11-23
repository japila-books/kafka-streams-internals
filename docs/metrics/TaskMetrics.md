# TaskMetrics

## <span id="recordLatenessSensor"> recordLatenessSensor

```java
Sensor recordLatenessSensor(
  String threadId,
  String taskId,
  StreamsMetricsImpl streamsMetrics)
```

`recordLatenessSensor` creates a "avgAndMax" `Sensor` with the following:

* Metric Name: `record-lateness`
* Avg Description: The observed average lateness of records in milliseconds, measured by comparing the record timestamp with the current stream time
* Max Description: The observed maximum lateness of records in milliseconds, measured by comparing the record timestamp with the current stream time
* RecordingLevel: `DEBUG`

`recordLatenessSensor` is used when:

* `StreamTask` is [created](../StreamTask.md#partitionGroup)
