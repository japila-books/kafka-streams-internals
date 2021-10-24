# AssignorConfiguration

## Creating Instance

`AssignorConfiguration` takes the following to be created:

* <span id="configs"> Configuration Properties

While being created, `AssignorConfiguration` creates a new `LogContext` with the following log prefix (based on [client.id](StreamsConfig.md#CLIENT_ID_CONFIG)):

```text
stream-thread [client.id]
```

`AssignorConfiguration` uses the given [configs](#configs) to look up [\_\_reference.container.instance__](InternalConfig.md#REFERENCE_CONTAINER_PARTITION_ASSIGNOR) internal property to be the [ReferenceContainer](#referenceContainer).

`AssignorConfiguration`...FIXME (`INTERNAL_TASK_ASSIGNOR_CLASS`)

---

`AssignorConfiguration` is created when:

* `StreamsPartitionAssignor` is requested to [configure](StreamsPartitionAssignor.md#configure)

## <span id="referenceContainer"> ReferenceContainer

`AssignorConfiguration` looks up a [ReferenceContainer](ReferenceContainer.md) when [created](#creating-instance).

The `ReferenceContainer` is used in the following:

* [Create an InternalTopicManager](#internalTopicManager)
* `StreamsPartitionAssignor` is requested to [configure](StreamsPartitionAssignor.md#configure)

## <span id="rebalanceProtocol"> rebalanceProtocol

```java
RebalanceProtocol rebalanceProtocol()
```

`rebalanceProtocol` takes the value of [upgrade.from](StreamsConfig.md#UPGRADE_FROM_CONFIG) configuration property (from the [StreamsConfig](#streamsConfig)).

Unless `upgrade.from` is defined, `rebalanceProtocol` prints out the following INFO message to the logs and returns `RebalanceProtocol.COOPERATIVE`.

```text
Cooperative rebalancing enabled now
```

With `upgrade.from` defined, `rebalanceProtocol`...FIXME

`rebalanceProtocol` is used when:

* `StreamsPartitionAssignor` is requested to [configure](StreamsPartitionAssignor.md#configure)

## <span id="copartitionedTopicsEnforcer"> CopartitionedTopicsEnforcer

```java
CopartitionedTopicsEnforcer copartitionedTopicsEnforcer()
```

`copartitionedTopicsEnforcer` creates a new [CopartitionedTopicsEnforcer](CopartitionedTopicsEnforcer.md) (with the [logPrefix](#logPrefix)).

`copartitionedTopicsEnforcer` is used when:

* `StreamsPartitionAssignor` is requested to [configure](StreamsPartitionAssignor.md#configure)

## <span id="internalTopicManager"> InternalTopicManager

```java
InternalTopicManager internalTopicManager()
```

`internalTopicManager` creates a new [InternalTopicManager](InternalTopicManager.md).

`internalTopicManager` is used when:

* `StreamsPartitionAssignor` is requested to [configure](StreamsPartitionAssignor.md#configure)
