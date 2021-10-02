# InternalTopologyBuilder

## <span id="build"> Building Processor Topology

```java
ProcessorTopology build(
  Set<String> nodeGroup)
```

`build` creates a new [ProcessorTopology](ProcessorTopology.md).

For every [NodeFactory](NodeFactory.md) (in the [nodeFactories](#nodeFactories) internal registry), if the name of the factory is in the given node group if defined or simply all node factories go through, `build` does the following:

1. Requests the `NodeFactory` to [build a ProcessorNode](NodeFactory.md#build) (and registers it in a local registry of processors by name)
1. For `ProcessorNodeFactory`s, [buildProcessorNode](#buildProcessorNode)
1. For `SourceNodeFactory`s, [buildSourceNode](#buildSourceNode)
1. For `SinkNodeFactory`s, [buildSinkNode](#buildSinkNode)

`build` is used when:

* `InternalTopologyBuilder` is requested to build a [topology](#buildTopology), a [subtopology](#buildSubtopology) and a [global state topology](#buildGlobalStateTopology)

### <span id="buildProcessorNode"> buildProcessorNode

```java
void buildProcessorNode(
  Map<String, ProcessorNode<?, ?, ?, ?>> processorMap,
  Map<String, StateStore> stateStoreMap,
  ProcessorNodeFactory<?, ?, ?, ?> factory,
  ProcessorNode<Object, Object, Object, Object> node)
```

`buildProcessorNode`...FIXME

### <span id="buildSourceNode"> buildSourceNode

```java
void buildSourceNode(
  Map<String, SourceNode<?, ?>> topicSourceMap,
  Set<String> repartitionTopics,
  SourceNodeFactory<?, ?> sourceNodeFactory,
  SourceNode<?, ?> node)
```

`buildSourceNode`...FIXME

### <span id="buildSinkNode"> buildSinkNode

```java
void buildSinkNode(
  Map<String, ProcessorNode<?, ?, ?, ?>> processorMap,
  Map<String, SinkNode<?, ?>> topicSinkMap,
  Set<String> repartitionTopics,
  SinkNodeFactory<?, ?> sinkNodeFactory,
  SinkNode<?, ?> node)
```

`buildSinkNode`...FIXME

## <span id="buildTopology"> Building (Local) Processor Topology

```java
ProcessorTopology buildTopology()
```

`buildTopology` [initializes subscription](#initializeSubscription) and then [builds a topology](#build) (of the [node groups](#nodeGroups) without the [global node groups](#globalNodeGroups)).

`buildTopology` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](../TopologyTestDriver.md#setupTopology)

## <span id="buildSubtopology"> Building Processor SubTopology

```java
ProcessorTopology buildSubtopology(
  int topicGroupId)
```

`buildSubtopology` takes the `topicGroupId` node group (from the [nodeGroups](#nodeGroups)) and [builds a topology](#build).

`buildSubtopology` is used when:

* `ActiveTaskCreator` is requested to [createTasks](ActiveTaskCreator.md#createTasks) and [createActiveTaskFromStandby](ActiveTaskCreator.md#createActiveTaskFromStandby)
* `StandbyTaskCreator` is requested to [createTasks](StandbyTaskCreator.md#createTasks) and [createStandbyTaskFromActive](StandbyTaskCreator.md#createStandbyTaskFromActive)

## <span id="buildGlobalStateTopology"> Building Global State Processor Topology

```java
ProcessorTopology buildGlobalStateTopology()
```

`buildGlobalStateTopology` [builds a topology](#build) of the [global node groups](#globalNodeGroups) if there are any.

`buildGlobalStateTopology` assumes that the [applicationId](#applicationId) has already been set or throws a `NullPointerException`:

```text
topology has not completed optimization
```

`buildGlobalStateTopology` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](../TopologyTestDriver.md#setupTopology)

## <span id="rewriteTopology"> rewriteTopology

```java
InternalTopologyBuilder rewriteTopology(
  StreamsConfig config)
```

`rewriteTopology` [setApplicationId](#setApplicationId) to the value of [application.id](../StreamsConfig.md#APPLICATION_ID_CONFIG) configuration property.

With [cache.max.bytes.buffering](../StreamsConfig.md#CACHE_MAX_BYTES_BUFFERING_CONFIG) enabled, `rewriteTopology`...FIXME

`rewriteTopology` requests the [global StoreBuilders](#globalStateBuilders) to [build a StateStore](../state/StoreBuilder.md#build).

In the end, `rewriteTopology` [saves the StreamsConfig](#setStreamsConfig) (and returns itself).

`rewriteTopology` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](../TopologyTestDriver.md#setupTopology)
