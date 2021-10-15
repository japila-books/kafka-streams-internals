# ProcessorStateManager

`ProcessorStateManager` is a [StateManager](StateManager.md).

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

`checkpoint` finds all the [persistent state stores](StateStore.md#persistent) (in the [stores](#stores) registry) that are logged (with a `changelogPartition`) and are not corrupted. For every state store, `checkpoint` records the `changelogPartition` and the offset (in a local `checkpointingOffsets` collection).

`checkpoint` prints out the following DEBUG message to the logs:

```text
Writing checkpoint: [checkpointingOffsets]
```

`checkpoint` requests the [OffsetCheckpoint](#checkpointFile) file to [write out the offsets](../state/OffsetCheckpoint.md#write).

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

## Logging

Enable `ALL` logging level for `org.apache.kafka.streams.processor.internals.ProcessorStateManager` logger to see what happens inside.

Add the following line to `log4j.properties`:

```text
log4j.logger.org.apache.kafka.streams.processor.internals.ProcessorStateManager=ALL
```

Refer to [Logging](../logging.md).
