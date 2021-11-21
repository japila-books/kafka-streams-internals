# StreamsMetrics

`StreamsMetrics` is an [abstraction](#contract) of [performance metric registries](#implementations) (of a [KafkaStreams](../KafkaStreams.md#streamsMetrics) instance).

`StreamsMetrics` is available using [ProcessorContext](../processor/ProcessorContext.md#metrics).

## Contract

### <span id="addLatencyRateTotalSensor"> addLatencyRateTotalSensor

```java
Sensor addLatencyRateTotalSensor(
  String scopeName,
  String entityName,
  String operationName,
  Sensor.RecordingLevel recordingLevel,
  String... tags)
```

### <span id="addRateTotalSensor"> addRateTotalSensor

```java
Sensor addRateTotalSensor(
  String scopeName,
  String entityName,
  String operationName,
  Sensor.RecordingLevel recordingLevel,
  String... tags)
```

### <span id="addSensor"> addSensor

```java
Sensor addSensor(
  String name,
  Sensor.RecordingLevel recordingLevel)
Sensor addSensor(
  String name,
  Sensor.RecordingLevel recordingLevel,
  Sensor... parents)
```

### <span id="metrics"> metrics

```java
Map<MetricName, ? extends Metric> metrics()
```

### <span id="removeSensor"> removeSensor

```java
void removeSensor(
  Sensor sensor)
```

## Implementations

* [StreamsMetricsImpl](StreamsMetricsImpl.md)
