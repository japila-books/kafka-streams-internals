# StreamsConfig

## <span id="APPLICATION_ID_CONFIG"><span id="application.id"> application.id

## <span id="CACHE_MAX_BYTES_BUFFERING_CONFIG"><span id="cache.max.bytes.buffering"> cache.max.bytes.buffering

## <span id="COMMIT_INTERVAL_MS_CONFIG"><span id="commit.interval.ms"> commit.interval.ms

How often to save (_commit_ and _flush_) the position of a processor

Default: `30000L` (or `100L` for [processing.guarantee](#processing.guarantee) being `exactly_once_v2` or deprecated `exactly_once`)

Must be at least `0`

`commit.interval.ms` has to be lower than `transaction.timeout.ms` (or [ongoing transaction always time out due to inactivity caused by long commit interval](#verifyEOSTransactionTimeoutCompatibility))

Used when:

* `GlobalStreamThread` is requested to [initialize](processor/GlobalStreamThread.md#initialize) (and create a `StateConsumer`)
* `StoreChangelogReader` is [created](processor/StoreChangelogReader.md#updateOffsetIntervalMs)
* `StreamThread` is [created](processor/StreamThread.md#commitTimeMs)

## <span id="POLL_MS_CONFIG"><span id="poll.ms"> poll.ms

Time (in millis) to block waiting for input

Default: `100L`

Used when:

* `GlobalStateManagerImpl` is [created](processor/GlobalStateManagerImpl.md#pollMsPlusRequestTimeout)
* `GlobalStreamThread` is requested to [initialize](processor/GlobalStreamThread.md#initialize)
* `StoreChangelogReader` is [created](processor/StoreChangelogReader.md#pollTime)
* `StreamThread` is [created](processor/StreamThread.md#pollTime)

## <span id="TASK_TIMEOUT_MS_CONFIG"><span id="task.timeout.ms"> task.timeout.ms

## <span id="UPGRADE_FROM_CONFIG"><span id="upgrade.from"> upgrade.from

Default: (undefined)
