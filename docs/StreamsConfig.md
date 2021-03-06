# StreamsConfig

## <span id="APPLICATION_ID_CONFIG"><span id="application.id"> application.id

## <span id="APPLICATION_SERVER_CONFIG"><span id="application.server"> application.server

A `host:port` endpoint address of the KafkaStreams instance

## <span id="BUFFERED_RECORDS_PER_PARTITION_CONFIG"><span id="buffered.records.per.partition"> buffered.records.per.partition

Maximum number of records to buffer per partition (that `StreamTask` uses for [pausing and resuming record fetching](StreamTask.md#maxBufferedSize))

Default: `1000`

## <span id="BUILT_IN_METRICS_VERSION_CONFIG"><span id="built.in.metrics.version"> built.in.metrics.version

## <span id="CACHE_MAX_BYTES_BUFFERING_CONFIG"><span id="cache.max.bytes.buffering"> cache.max.bytes.buffering

Maximum number of memory bytes for buffering across all threads (that [KafkaStreams](KafkaStreams.md#totalCacheSize) uses to indirectly create [ThreadCache](state/ThreadCache.md)s).

Default: `10MB` (`10 * 1024 * 1024L`)

Must be at least `0`

Used when:

* `KafkaStreams` is [created](KafkaStreams.md#totalCacheSize)
* `InternalTopologyBuilder` is requested to [rewriteTopology](InternalTopologyBuilder.md#rewriteTopology) (and disable caching for local and global state stores)
* `TopologyTestDriver` is [created](TopologyTestDriver.md#creating-instance)

## <span id="CLIENT_ID_CONFIG"><span id="client.id"> client.id

See [Apache Kafka]({{ book.kafka }}/clients/CommonClientConfigs#client.id)

## <span id="COMMIT_INTERVAL_MS_CONFIG"><span id="commit.interval.ms"> commit.interval.ms

How often to save (_commit_ and _flush_) the position of a processor

Default: `30000L` (or `100L` for [processing.guarantee](#processing.guarantee) being `exactly_once_v2` or deprecated `exactly_once`)

Must be at least `0`

`commit.interval.ms` has to be lower than `transaction.timeout.ms` (or [ongoing transaction always time out due to inactivity caused by long commit interval](#verifyEOSTransactionTimeoutCompatibility))

Used when:

* `GlobalStreamThread` is requested to [initialize](processor/GlobalStreamThread.md#initialize) (and create a `StateConsumer`)
* `StoreChangelogReader` is [created](processor/StoreChangelogReader.md#updateOffsetIntervalMs)
* `StreamThread` is [created](StreamThread.md#commitTimeMs)

## <span id="DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG"><span id="defaultDeserializationExceptionHandler"><span id="default.deserialization.exception.handler"> default.deserialization.exception.handler

The default `DeserializationExceptionHandler`

Default: `LogAndFailExceptionHandler`

## <span id="DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG"><span id="defaultTimestampExtractor"><span id="default.timestamp.extractor"> default.timestamp.extractor

The default [TimestampExtractor](processor/TimestampExtractor.md)

Default: [FailOnInvalidTimestamp](processor/FailOnInvalidTimestamp.md)

## <span id="MAX_TASK_IDLE_MS_CONFIG"><span id="max.task.idle.ms"> max.task.idle.ms

## <span id="METRICS_NUM_SAMPLES_CONFIG"><span id="metrics.num.samples"> metrics.num.samples

(Re)Defined in `StreamsConfig` as `CommonClientConfigs.METRICS_NUM_SAMPLES_CONFIG` ([Apache Kafka]({{ book.kafka }}/clients/CommonClientConfigs#METRICS_NUM_SAMPLES_CONFIG))

## <span id="METRICS_RECORDING_LEVEL_CONFIG"><span id="metrics.recording.level"> metrics.recording.level

(Re)Defined in `StreamsConfig` as `CommonClientConfigs.METRICS_RECORDING_LEVEL_CONFIG` ([Apache Kafka]({{ book.kafka }}/clients/CommonClientConfigs#METRICS_RECORDING_LEVEL_CONFIG))

## <span id="METRIC_REPORTER_CLASSES_CONFIG"><span id="metric.reporters"> metric.reporters

(Re)Defined in `StreamsConfig` as `CommonClientConfigs.METRIC_REPORTER_CLASSES_CONFIG` ([Apache Kafka]({{ book.kafka }}/clients/CommonClientConfigs#METRIC_REPORTER_CLASSES_CONFIG))

## <span id="METRICS_SAMPLE_WINDOW_MS_CONFIG"><span id="metrics.sample.window.ms"> metrics.sample.window.ms

(Re)Defined in `StreamsConfig` as `CommonClientConfigs.METRICS_SAMPLE_WINDOW_MS_CONFIG` ([Apache Kafka]({{ book.kafka }}/clients/CommonClientConfigs#METRICS_SAMPLE_WINDOW_MS_CONFIG))

## <span id="POLL_MS_CONFIG"><span id="poll.ms"> poll.ms

Time (in millis) to block waiting for input

Default: `100L`

Used when:

* `GlobalStateManagerImpl` is [created](processor/GlobalStateManagerImpl.md#pollMsPlusRequestTimeout)
* `GlobalStreamThread` is requested to [initialize](processor/GlobalStreamThread.md#initialize)
* `StoreChangelogReader` is [created](processor/StoreChangelogReader.md#pollTime)
* `StreamThread` is [created](StreamThread.md#pollTime)

## <span id="TASK_TIMEOUT_MS_CONFIG"><span id="task.timeout.ms"> task.timeout.ms

## <span id="UPGRADE_FROM_CONFIG"><span id="upgrade.from"> upgrade.from

Default: (undefined)
