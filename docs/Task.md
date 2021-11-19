# Task

`Task` is an [abstraction](#contract) of [tasks](#implementations).

## Contract

### <span id="addRecords"> addRecords

```java
void addRecords(
  TopicPartition partition,
  Iterable<ConsumerRecord<byte[], byte[]>> records)
```

Used when:

* `TaskManager` is requested to [addRecordsToTasks](TaskManager.md#addRecordsToTasks)
* `TopologyTestDriver` is requested to [enqueueTaskRecord](TopologyTestDriver.md#enqueueTaskRecord)

### <span id="changelogOffsets"> changelogOffsets

```java
Map<TopicPartition, Long> changelogOffsets()
```

### <span id="changelogPartitions"> changelogPartitions

```java
Collection<TopicPartition> changelogPartitions()
```

### <span id="clearTaskTimeout"> clearTaskTimeout

```java
void clearTaskTimeout()
```

### <span id="closeClean"> closeClean

```java
void closeClean()
```

### <span id="closeCleanAndRecycleState"> closeCleanAndRecycleState

```java
void closeCleanAndRecycleState()
```

### <span id="closeDirty"> closeDirty

```java
void closeDirty()
```

### <span id="commitNeeded"> commitNeeded

```java
boolean commitNeeded()
```

### <span id="committedOffsets"> committedOffsets

```java
Map<TopicPartition, Long> committedOffsets()
```

### <span id="completeRestoration"> completeRestoration

```java
void completeRestoration(
  java.util.function.Consumer<Set<TopicPartition>> offsetResetter)
```

### <span id="getStore"> getStore

```java
StateStore getStore(
  String name)
```

Used when:

* `StreamThreadStateStoreProvider` is requested for [stores](state/StreamThreadStateStoreProvider.md#stores)

### <span id="highWaterMark"> highWaterMark

```java
Map<TopicPartition, Long> highWaterMark()
```

### <span id="id"> TaskId

```java
TaskId id()
```

### <span id="initializeIfNeeded"> initializeIfNeeded

```java
void initializeIfNeeded()
```

### <span id="inputPartitions"> inputPartitions

```java
Set<TopicPartition> inputPartitions()
```

### <span id="isActive"> isActive

```java
boolean isActive()
```

### <span id="markChangelogAsCorrupted"> markChangelogAsCorrupted

```java
void markChangelogAsCorrupted(
  Collection<TopicPartition> partitions)
```

### <span id="maybeInitTaskTimeoutOrThrow"> maybeInitTaskTimeoutOrThrow

```java
void maybeInitTaskTimeoutOrThrow(
  long currentWallClockMs,
  Exception cause)
```

### <span id="maybePunctuateStreamTime"> maybePunctuateStreamTime

```java
boolean maybePunctuateStreamTime()
```

Default: `false`

Used when:

* `TaskManager` is requested to [punctuate](TaskManager.md#punctuate)
* `TopologyTestDriver` is requested to [completeAllProcessableWork](TopologyTestDriver.md#completeAllProcessableWork)

### <span id="maybePunctuateSystemTime"> maybePunctuateSystemTime

```java
boolean maybePunctuateSystemTime()
```

Default: `false`

Used when:

* `TaskManager` is requested to [punctuate](TaskManager.md#punctuate)
* `TopologyTestDriver` is requested to [advanceWallClockTime](TopologyTestDriver.md#advanceWallClockTime)

### <span id="postCommit"> postCommit

```java
void postCommit(
  boolean enforceCheckpoint)
```

### <span id="prepareCommit"> prepareCommit

```java
Map<TopicPartition, OffsetAndMetadata> prepareCommit()
```

Used when:

* `TaskManager` is requested to [closeDirtyAndRevive](TaskManager.md#closeDirtyAndRevive), [handleCloseAndRecycle](TaskManager.md#handleCloseAndRecycle), [prepareCommitAndAddOffsetsToMap](TaskManager.md#prepareCommitAndAddOffsetsToMap), [closeTaskDirty](TaskManager.md#closeTaskDirty), [tryCloseCleanAllActiveTasks](TaskManager.md#tryCloseCleanAllActiveTasks), [tryCloseCleanAllStandbyTasks](TaskManager.md#tryCloseCleanAllStandbyTasks) and [commitAndFillInConsumedOffsetsAndMetadataPerTaskMap](TaskManager.md#commitAndFillInConsumedOffsetsAndMetadataPerTaskMap)
* `TopologyTestDriver` is requested to [completeAllProcessableWork](TopologyTestDriver.md#completeAllProcessableWork), [advanceWallClockTime](TopologyTestDriver.md#advanceWallClockTime) and [close](TopologyTestDriver.md#close)

### <span id="process"> Processing Record

```java
boolean process(
  long wallClockTime)
```

Default: `false` (and overriden in [StreamTask](StreamTask.md#process))

Used when:

* `TaskManager` is requested to [process records](TaskManager.md#process)
* `TopologyTestDriver` is requested to [completeAllProcessableWork](TopologyTestDriver.md#completeAllProcessableWork)

### <span id="resume"> resume

```java
void resume()
```

### <span id="revive"> revive

```java
void revive()
```

### <span id="state"> state

```java
State state()
```

### <span id="suspend"> suspend

```java
void suspend()
```

### <span id="timeCurrentIdlingStarted"> timeCurrentIdlingStarted

```java
Optional<Long> timeCurrentIdlingStarted()
```

### <span id="updateInputPartitions"> updateInputPartitions

```java
void updateInputPartitions(
  Set<TopicPartition> topicPartitions,
  Map<String, List<String>> allTopologyNodesToSourceTopics)
```

## Implementations

* [AbstractTask](AbstractTask.md)
* [StandbyTask](StandbyTask.md)
* [StreamTask](StreamTask.md)
