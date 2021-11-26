# StreamsMetricsImpl

`StreamsMetricsImpl` is a concrete [StreamsMetrics](StreamsMetrics.md).

## Creating Instance

`StreamsMetricsImpl` takes the following to be created:

* <span id="metrics"> `Metrics` ([Apache Kafka]({{ book.kafka }}/Metrics))
* <span id="clientId"> Client ID
* <span id="builtInMetricsVersion"> Built-in metrics version
* <span id="time"> `Time`

`StreamsMetricsImpl` is created when:

* `KafkaStreams` is [created](../KafkaStreams.md#streamsMetrics)
* `TopologyTestDriver` is requested to [setupMetrics](../TopologyTestDriver.md#setupMetrics)

## <span id="rocksDBMetricsRecordingTrigger"> RocksDBMetricsRecordingTrigger

`StreamsMetricsImpl` creates a `RocksDBMetricsRecordingTrigger` when [created](#creating-instance).

The `RocksDBMetricsRecordingTrigger` is used when:

* `KafkaStreams` is requested to [start](../KafkaStreams.md#start)
* `RocksDBMetricsRecorder` is requested to `addValueProviders` and `removeValueProviders`

## <span id="taskLevelSensor"> taskLevelSensor

```java
Sensor taskLevelSensor(
  String threadId,
  String taskId,
  String sensorName,
  RecordingLevel recordingLevel,
  Sensor... parents)
```

`taskLevelSensor` [creates a taskSensorPrefix](#taskSensorPrefix) (with the given `threadId` and `taskId` identifiers) and [getSensors](#getSensors).

`taskLevelSensor` is used when:

* FIXME

## <span id="getSensors"> getSensors

```java
Sensor getSensors(
  Map<String, Deque<String>> sensors,
  String sensorName,
  String key,
  RecordingLevel recordingLevel,
  Sensor... parents)
```

`getSensors`...FIXME

`getSensors` is used when:

* `StreamsMetricsImpl` is requested to [threadLevelSensor](#threadLevelSensor), [taskLevelSensor](#taskLevelSensor), [nodeLevelSensor](#nodeLevelSensor), [cacheLevelSensor](#cacheLevelSensor), [storeLevelSensor](#storeLevelSensor)

## <span id="threadLevelSensors"> threadLevelSensors

`StreamsMetricsImpl` defines `threadLevelSensors` registry of sensors (names) per thread (id) for [threadLevelSensor](#threadLevelSensor).

Sensors are removed in [removeAllThreadLevelSensors](#removeAllThreadLevelSensors).
