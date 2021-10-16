# StateManagerUtil

## <span id="registerStateStores"> registerStateStores

```java
void registerStateStores(
  Logger log,
  String logPrefix,
  ProcessorTopology topology,
  ProcessorStateManager stateMgr,
  StateDirectory stateDirectory,
  InternalProcessorContext processorContext)
```

`registerStateStores`...FIXME

`registerStateStores` is used when:

* `StandbyTask` is requested to [initializeIfNeeded](StandbyTask.md#initializeIfNeeded)
* `StreamTask` is requested to [initializeIfNeeded](StreamTask.md#initializeIfNeeded)
