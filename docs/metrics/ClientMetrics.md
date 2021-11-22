# ClientMetrics

`ClientMetrics` registers performance metrics of a [KafkaStreams](../KafkaStreams.md#ClientMetrics) instance (and associated [StreamThread](#failed-stream-threads)s).

Name       | Description | RecordingLevel | Value
-----------|----------|---------|---------
 `state`   | The state of the Kafka Streams client | `INFO` | [state](../KafkaStreams.md#state)
 `version` | The version of the Kafka Streams client | `INFO` | [version](#version)
 `topology-description` | The description of the topology executed in the Kafka Streams client | `INFO` |
 `alive-stream-threads` | The current number of alive stream threads that are running or participating in rebalance | `INFO` | [getNumLiveStreamThreads](../KafkaStreams.md#getNumLiveStreamThreads)
 <span id="failed-stream-threads"> `failed-stream-threads` | The number of failed stream threads since the start of the Kafka Streams client | `INFO` | [failedStreamThreadSensor](../StreamThread.md#failedStreamThreadSensor)

`ClientMetrics` was introduced in [KIP-444](https://cwiki.apache.org/confluence/display/KAFKA/KIP-444%3A+Augment+metrics+for+Kafka+Streams).

## Creating Instance

`ClientMetrics` takes no arguments to be created.

### <span id="version"><span id="VERSION_FROM_FILE"><span id="commitId"><span id="COMMIT_ID_FROM_FILE"> kafka-streams-version.properties

While being created, `ClientMetrics` loads `/kafka/kafka-streams-version.properties` file (from the CLASSPATH) for the `version` and `commitId` values.

The values are used when `KafkaStreams` is [created](../KafkaStreams.md#creating-instance) (and prints them out as INFO message to the logs).

```text
Kafka Streams version: [version]
Kafka Streams commit ID: [commitId]
```

## <span id="addVersionMetric"> addVersionMetric

```java
void addVersionMetric(
  StreamsMetricsImpl streamsMetrics)
```

`addVersionMetric` requests the given [StreamsMetricsImpl](StreamsMetricsImpl.md) to [addClientLevelImmutableMetric](StreamsMetricsImpl.md#addClientLevelImmutableMetric) with the following:

* Name: `version`
* Description: The version of the Kafka Streams client
* RecordingLevel: `INFO`
* Value: [version](#version)

`addVersionMetric` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)

## <span id="addTopologyDescriptionMetric"> addTopologyDescriptionMetric

```java
void addTopologyDescriptionMetric(
  StreamsMetricsImpl streamsMetrics,
  String topologyDescription)
```

`addTopologyDescriptionMetric` requests the given [StreamsMetricsImpl](StreamsMetricsImpl.md) to [addClientLevelImmutableMetric](StreamsMetricsImpl.md#addClientLevelImmutableMetric) with the following:

* Name: `topology-description`
* Description: The description of the topology executed in the Kafka Streams client
* RecordingLevel: `INFO`
* Value: The given `topologyDescription`

`addTopologyDescriptionMetric` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)

## <span id="addStateMetric"> addStateMetric

```java
void addStateMetric(
  StreamsMetricsImpl streamsMetrics,
  Gauge<State> stateProvider)
```

`addStateMetric` requests the given [StreamsMetricsImpl](StreamsMetricsImpl.md) to [addClientLevelMutableMetric](StreamsMetricsImpl.md#addClientLevelMutableMetric) with the following:

* Name: `state`
* Description: The state of the Kafka Streams client
* RecordingLevel: `INFO`
* Value: The given `Gauge<State>` (with the [state](../KafkaStreams.md#state) of the owning `KafkaStreams` instance)

`addStateMetric` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)

## <span id="addNumAliveStreamThreadMetric"> addNumAliveStreamThreadMetric

```java
void addNumAliveStreamThreadMetric(
  StreamsMetricsImpl streamsMetrics,
  Gauge<Integer> stateProvider)
```

`addNumAliveStreamThreadMetric` requests the given [StreamsMetricsImpl](StreamsMetricsImpl.md) to [addClientLevelMutableMetric](StreamsMetricsImpl.md#addClientLevelMutableMetric) with the following:

* Name: `alive-stream-threads`
* Description: The current number of alive stream threads that are running or participating in rebalance
* RecordingLevel: `INFO`
* Value: The given `Gauge<Integer>` (with [getNumLiveStreamThreads](../KafkaStreams.md#getNumLiveStreamThreads) of the owning `KafkaStreams` instance)

`addNumAliveStreamThreadMetric` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)
