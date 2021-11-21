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
