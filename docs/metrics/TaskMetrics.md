# TaskMetrics

## <span id="recordLatenessSensor"> recordLatenessSensor

```java
Sensor recordLatenessSensor(
  String threadId,
  String taskId,
  StreamsMetricsImpl streamsMetrics)
```

`recordLatenessSensor` creates an [avg and max metric sensors](#avgAndMaxSensor) with the following:

Metric Name | Avg Description | Max Description | RecordingLevel
------------|-----------------|-----------------|---------------
 `record-lateness` | The observed average lateness of records in milliseconds, measured by comparing the record timestamp with the current stream time | The observed maximum lateness of records in milliseconds, measured by comparing the record timestamp with the current stream time | `DEBUG`

`recordLatenessSensor` is used when:

* `StreamTask` is [created](../StreamTask.md#recordLatenessSensor) (and creates a [PartitionGroup](../PartitionGroup.md#recordLatenessSensor))

## <span id="avgAndMaxSensor"> avgAndMaxSensor

```java
Sensor avgAndMaxSensor(
  String threadId,
  String taskId,
  String metricName,
  String descriptionOfAvg,
  String descriptionOfMax,
  RecordingLevel recordingLevel,
  StreamsMetricsImpl streamsMetrics,
  Sensor... parentSensors)
```

`avgAndMaxSensor` requests the given [StreamsMetricsImpl](StreamsMetricsImpl.md) to create a [taskLevelSensor](StreamsMetricsImpl.md#taskLevelSensor) and a [taskLevelTagMap](StreamsMetricsImpl.md#taskLevelTagMap).

`avgAndMaxSensor` [addAvgAndMaxToSensor](#addAvgAndMaxToSensor).

`avgAndMaxSensor` is used when:

* `TaskMetrics` is requested to [processLatencySensor](#processLatencySensor) and [recordLatenessSensor](#recordLatenessSensor)

## <span id="addAvgAndMaxToSensor"> addAvgAndMaxToSensor

```java
void addAvgAndMaxToSensor(
  Sensor sensor,
  String group,
  Map<String, String> tags,
  String operation,
  String descriptionOfAvg,
  String descriptionOfMax)
```

`addAvgAndMaxToSensor`...FIXME

`addAvgAndMaxToSensor` is used when:

* `StreamsMetricsImpl` is requested to [addLatencyRateTotalSensor](StreamsMetricsImpl.md#addLatencyRateTotalSensor) and [addAvgAndMinAndMaxToSensor](StreamsMetricsImpl.md#addAvgAndMinAndMaxToSensor)
* `TaskMetrics` is requested to [avgAndMaxSensor](#avgAndMaxSensor) and [invocationRateAndCountAndAvgAndMaxLatencySensor](#invocationRateAndCountAndAvgAndMaxLatencySensor)
* `ThreadMetrics` is requested to [processLatencySensor](ThreadMetrics.md#processLatencySensor), [pollRecordsSensor](ThreadMetrics.md#pollRecordsSensor), [processRecordsSensor](ThreadMetrics.md#processRecordsSensor), [invocationRateAndCountAndAvgAndMaxLatencySensor](ThreadMetrics.md#invocationRateAndCountAndAvgAndMaxLatencySensor)
* `StateStoreMetrics` is requested to [prefixScanSensor](StateStoreMetrics.md#prefixScanSensor), [sizeOrCountSensor](StateStoreMetrics.md#sizeOrCountSensor), [throughputAndLatencySensor](StateStoreMetrics.md#throughputAndLatencySensor)
