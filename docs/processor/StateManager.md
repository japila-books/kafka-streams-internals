# StateManager

`StateManager` is an [abstraction](#contract) of [state managers](#implementations).

## Contract

### <span id="baseDir"> baseDir

```java
File baseDir()
```

Used when:

* `AbstractProcessorContext` is requested for the [stateDir](AbstractProcessorContext.md#stateDir)
* `GlobalStateUpdateTask` is requested to [close](GlobalStateUpdateTask.md#close)
* `StateManagerUtil` is requested to [closeStateManager](StateManagerUtil.md#closeStateManager)

### <span id="changelogFor"> changelogFor

```java
String changelogFor(
  String storeName)
```

Used when:

* `AbstractProcessorContext` is requested for the [changelogFor](AbstractProcessorContext.md#changelogFor)
* `GlobalStateManagerImpl` is [created](GlobalStateManagerImpl.md)
* `ProcessorStateManager` is requested to [getStorePartition](ProcessorStateManager.md#getStorePartition) and [isLoggingEnabled](ProcessorStateManager.md#isLoggingEnabled)

### <span id="changelogOffsets"> changelogOffsets

```java
Map<TopicPartition, Long> changelogOffsets()
```

Used when:

* `AbstractTask` is requested to [maybeWriteCheckpoint](AbstractTask.md#maybeWriteCheckpoint)
* `GlobalStateUpdateTask` is requested to [initialize](GlobalStateUpdateTask.md#initialize)
* `ProcessorStateManager` is requested to [changelogPartitions](ProcessorStateManager.md#changelogPartitions)
* `StandbyTask` is requested to [commitNeeded](StandbyTask.md#commitNeeded) and [changelogOffsets](StandbyTask.md#changelogOffsets)
* `StoreChangelogReader` is requested to [getPositionString](StoreChangelogReader.md#getPositionString)
* `StreamTask` is requested to [changelogOffsets](StreamTask.md#changelogOffsets)

### <span id="checkpoint"> checkpoint

```java
void checkpoint()
```

Used when:

* `AbstractTask` is requested to [maybeWriteCheckpoint](AbstractTask.md#maybeWriteCheckpoint)
* `GlobalStateUpdateTask` is requested to [flushState](GlobalStateUpdateTask.md#flushState)

### <span id="close"> close

```java
void close()
```

Used when:

* `GlobalStateUpdateTask` is requested to [close](GlobalStateUpdateTask.md#close)
* `StateManagerUtil` is requested to [closeStateManager](StateManagerUtil.md#closeStateManager)

### <span id="flush"> flush

```java
void flush()
```

Used when:

* `AbstractTask` is requested to [maybeWriteCheckpoint](AbstractTask.md#maybeWriteCheckpoint)
* `GlobalStateUpdateTask` is requested to [flushState](GlobalStateUpdateTask.md#flushState)

### <span id="getGlobalStore"> getGlobalStore

```java
StateStore getGlobalStore(
  String name)
```

Used when:

* `GlobalProcessorContextImpl` is requested to [getStateStore](GlobalProcessorContextImpl.md#getStateStore)
* `GlobalStateManagerImpl` is requested to [getStore](GlobalStateManagerImpl.md#getStore)
* `ProcessorContextImpl` is requested to [getStateStore](ProcessorContextImpl.md#getStateStore)

### <span id="getStore"> getStore

```java
StateStore getStore(
  String name)
```

Used when:

* `AbstractTask` is requested to [getStore](AbstractTask.md#getStore)
* `ProcessorContextImpl` is requested to [getStateStore](ProcessorContextImpl.md#getStateStore)
* `TopologyTestDriver` is requested to [getStateStore](../TopologyTestDriver.md#getStateStore)

### <span id="registerStore"> registerStore

```java
void registerStore(
  StateStore store, 
  StateRestoreCallback stateRestoreCallback)
```

Used when:

* `AbstractProcessorContext` is requested to [register](AbstractProcessorContext.md#register)

### <span id="taskType"> taskType

```java
TaskType taskType()
```

### <span id="updateChangelogOffsets"> updateChangelogOffsets

```java
void updateChangelogOffsets(
  Map<TopicPartition, Long> writtenOffsets)
```

Used when:

* `GlobalStateUpdateTask` is requested to [flushState](GlobalStateUpdateTask.md#flushState)
* `StreamTask` is requested to [maybeWriteCheckpoint](StreamTask.md#maybeWriteCheckpoint)

## Implementations

* [GlobalStateManager](GlobalStateManager.md)
* [ProcessorStateManager](ProcessorStateManager.md)
