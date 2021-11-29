# ProcessorStateManager

`ProcessorStateManager` is a [StateManager](StateManager.md).

## Creating Instance

`ProcessorStateManager` takes the following to be created:

* <span id="taskId"> [TaskId](TaskId.md)
* <span id="taskType"> `TaskType`
* [eosEnabled](#eosEnabled) flag
* <span id="logContext"> `LogContext`
* <span id="stateDirectory"> [StateDirectory](StateDirectory.md)
* <span id="changelogReader"> `ChangelogRegister`
* <span id="storeToChangelogTopic"> `storeToChangelogTopic` collection
* <span id="sourcePartitions"> Source `TopicPartition`s

`ProcessorStateManager` is created when:

* `ActiveTaskCreator` is requested to [createTasks](ActiveTaskCreator.md#createTasks)
* `StandbyTaskCreator` is requested to [createTasks](StandbyTaskCreator.md#createTasks)
* `TopologyTestDriver` is requested to [setupTask](TopologyTestDriver.md#setupTask)

## <span id="eosEnabled"> eosEnabled Flag

`ProcessorStateManager` is given `eosEnabled` flag when [created](#creating-instance).

## <span id="checkpointFile"> Offset Checkpoint File

When [created](#creating-instance), `ProcessorStateManager` requests the given [StateDirectory](#stateDirectory) for a [checkpoint file](StateDirectory.md#checkpointFileFor) for the given [TaskId](#taskId) and creates a new `OffsetCheckpoint`.

`ProcessorStateManager` uses the `OffsetCheckpoint` for the following:

* [initializeStoreOffsetsFromCheckpoint](#initializeStoreOffsetsFromCheckpoint) (to read offsets and then delete it with [eosEnabled](#eosEnabled))
* [Checkpoint](#checkpoint)
* [deleteCheckPointFileIfEOSEnabled](#deleteCheckPointFileIfEOSEnabled)

## <span id="flush"> Flushing State Stores

```java
void flush()
```

`flush` does nothing (_noop_) when there are no [state stores](#stores) registered.

---

`flush` prints out the following DEBUG message to the logs:

```text
Flushing all stores registered in the state manager: [stores]
```

For every [state store](#stores), `flush` prints out the following TRACE message to the logs and requests the `StateStore` to [flush cached data](processor/StateStore.md#flush):

```text
Flushing store [name]
```

---

`flush` is part of the [StateManager](StateManager.md#flush) abstraction.

## <span id="flushCache"> Flushing Store Caches

```java
void flushCache()
```

`flushCache`...FIXME

`flushCache` is used when:

* `StreamTask` is requested to [prepareCommit](StreamTask.md#prepareCommit)

## <span id="checkpoint"> Checkpointing

```java
void checkpoint()
```

`checkpoint` finds all the [persistent state stores](processor/StateStore.md#persistent) (in the [stores](#stores) registry) that are logged (with a `changelogPartition`) and are not corrupted. For every state store, `checkpoint` records the `changelogPartition` and the offset (in a local `checkpointingOffsets` collection).

`checkpoint` prints out the following DEBUG message to the logs:

```text
Writing checkpoint: [checkpointingOffsets]
```

`checkpoint` requests the [OffsetCheckpoint](#checkpointFile) file to [write out the offsets](state/OffsetCheckpoint.md#write).

---

In case of any IO exceptions, `checkpoint` prints out the following WARN message to the logs:

```text
Failed to write offset checkpoint file to [checkpointFile].
This may occur if OS cleaned the state.dir in case when it located in ${java.io.tmpdir} directory.
This may also occur due to running multiple instances on the same machine using the same state dir.
Changing the location of state.dir may resolve the problem.
```

---

`checkpoint` is part of the [StateManager](StateManager.md#checkpoint) abstraction.

## <span id="registerStore"> registerStore

```java
void registerStore(
  StateStore store,
  StateRestoreCallback stateRestoreCallback)
```

`registerStore`...FIXME

`registerStore` is part of the [StateManager](StateManager.md#registerStore) abstraction.

## <span id="registerStateStores"> registerStateStores

```java
void registerStateStores(
  List<StateStore> allStores, 
  InternalProcessorContext processorContext)
```

`registerStateStores`...FIXME

`registerStateStores` is used when:

* `StateManagerUtil` is requested to [registerStateStores](StateManagerUtil.md#registerStateStores)

## <span id="maybeRegisterStoreWithChangelogReader"> maybeRegisterStoreWithChangelogReader

```java
void maybeRegisterStoreWithChangelogReader(
  String storeName)
```

`maybeRegisterStoreWithChangelogReader`...FIXME

`maybeRegisterStoreWithChangelogReader` is used when:

* `ProcessorStateManager` is requested to [registerStateStores](#registerStateStores) and [registerStore](#registerStore)

## <span id="getStorePartition"> getStorePartition

```java
TopicPartition getStorePartition(
  String storeName)
```

`getStorePartition` creates a `TopicPartition` with the following:

* [changelogFor](#changelogFor) with the given `storeName` for the name of the (changelog) topic
* The [partition](TaskId.md#partition) of the [TaskId](#taskId) for the partition (of the changelog topic)

`getStorePartition` is used when:

* `ProcessorStateManager` is requested to [maybeRegisterStoreWithChangelogReader](#maybeRegisterStoreWithChangelogReader) and [registerStore](#registerStore)

## <span id="initializeStoreOffsetsFromCheckpoint"> initializeStoreOffsetsFromCheckpoint

```java
void initializeStoreOffsetsFromCheckpoint(
  boolean storeDirIsEmpty)
```

`initializeStoreOffsetsFromCheckpoint`...FIXME

`initializeStoreOffsetsFromCheckpoint` is used when:

* `StateManagerUtil` is requested to [registerStateStores](StateManagerUtil.md#registerStateStores)

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.ProcessorStateManager` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.ProcessorStateManager=ALL
```

Refer to [Logging](logging.md).
