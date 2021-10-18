# AssignorConfiguration

## Creating Instance

`AssignorConfiguration` takes the following to be created:

* <span id="configs"> Configuration Properties

`AssignorConfiguration` is created when:

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
