# StreamsConfig

## <span id="APPLICATION_ID_CONFIG"><span id="application.id"> application.id

## <span id="CACHE_MAX_BYTES_BUFFERING_CONFIG"><span id="cache.max.bytes.buffering"> cache.max.bytes.buffering

## <span id="POLL_MS_CONFIG"><span id="poll.ms"> poll.ms

Time (in millis) to block waiting for input

Default: `100L`

Used when:

* `GlobalStateManagerImpl` is [created](processor/GlobalStateManagerImpl.md#pollMsPlusRequestTimeout)
* `GlobalStreamThread` is requested to [initialize](processor/GlobalStreamThread.md#initialize)
* `StoreChangelogReader` is [created](processor/StoreChangelogReader.md#pollTime)
* `StreamThread` is [created](processor/StreamThread.md#pollTime)

## <span id="TASK_TIMEOUT_MS_CONFIG"><span id="task.timeout.ms"> task.timeout.ms
