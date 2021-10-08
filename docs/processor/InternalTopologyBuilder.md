# InternalTopologyBuilder

## <span id="globalTopics"> Global Topics

```java
Set<String> globalTopics
```

`InternalTopologyBuilder` tracks global topics (names) in a `globalTopics` internal registry.

A new topic name is added in [addGlobalStore](#addGlobalStore).

## <span id="build"> Building Processor Topology

```java
ProcessorTopology build(
  Set<String> nodeGroup)
```

For every [NodeFactory](NodeFactory.md) (in the [nodeFactories](#nodeFactories) internal registry), if the name of the factory is in the given node group if defined or simply all node factories go through, `build` does the following:

1. Requests the `NodeFactory` to [build a ProcessorNode](NodeFactory.md#build) (and registers it in a local registry of processors by name)
1. For `ProcessorNodeFactory`s, [buildProcessorNode](#buildProcessorNode)
1. For `SourceNodeFactory`s, [buildSourceNode](#buildSourceNode)
1. For `SinkNodeFactory`s, [buildSinkNode](#buildSinkNode)

In the end, `build` creates a new [ProcessorTopology](ProcessorTopology.md).

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

### <span id="buildSourceNode"> Building Source Node

```java
void buildSourceNode(
  Map<String, SourceNode<?, ?>> topicSourceMap,
  Set<String> repartitionTopics,
  SourceNodeFactory<?, ?> sourceNodeFactory,
  SourceNode<?, ?> node)
```

`buildSourceNode` mutates (_changes_) the given `SourceNode` by topic name (`topicSourceMap`) and repartition topic names (`repartitionTopics`) collections.

---

When the [pattern](SourceNodeFactory.md#pattern) (of the given [SourceNodeFactory](SourceNodeFactory.md)) is defined, `buildSourceNode` [subscriptionUpdates](#subscriptionUpdates) and requests the `SourceNodeFactory` to [get the topics](SourceNodeFactory.md#getTopics). Otherwise, `buildSourceNode` requests the `SourceNodeFactory` for the [topics](SourceNodeFactory.md#topics).

`buildSourceNode` adds the topic to the given `topicSourceMap` collection.

For internal topics (in [internalTopicNamesWithProperties](#internalTopicNamesWithProperties) registry), `buildSourceNode` [decorates the name](#decorateTopic) before adding to the given `topicSourceMap` collection and adds them to the given `repartitionTopics` collection.

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

## <span id="rewriteTopology"> Rewriting Topology

```java
InternalTopologyBuilder rewriteTopology(
  StreamsConfig config)
```

`rewriteTopology` [setApplicationId](#setApplicationId) to the value of [application.id](../StreamsConfig.md#APPLICATION_ID_CONFIG) configuration property.

With [cache.max.bytes.buffering](../StreamsConfig.md#CACHE_MAX_BYTES_BUFFERING_CONFIG) enabled, `rewriteTopology`...FIXME

`rewriteTopology` requests the [global StoreBuilders](#globalStateBuilders) to [build StateStores](../state/StoreBuilder.md#build).

In the end, `rewriteTopology` [saves the StreamsConfig](#setStreamsConfig) (and returns itself).

`rewriteTopology` is used when:

* `KafkaStreams` is [created](../KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](../TopologyTestDriver.md#setupTopology)

## <span id="globalNodeGroups"> globalNodeGroups

```java
Set<String> globalNodeGroups()
```

`globalNodeGroups` collects [global source nodes](#isGlobalSource) from all the [node groups](#nodeGroups).

`globalNodeGroups` is used when:

* `InternalTopologyBuilder` is requested to build a [local](#buildTopology) (excluding global state nodes) and [global state](#buildGlobalStateTopology) topologies

## <span id="isGlobalSource"> isGlobalSource

```java
boolean isGlobalSource(
  String nodeName)
```

`isGlobalSource` finds a [NodeFactory](NodeFactory.md) (by given `nodeName`) in [nodeFactories](#nodeFactories) registry.

`isGlobalSource` is positive (`true`) when the `NodeFactory` is a [SourceNodeFactory](SourceNodeFactory.md) with one [topic](SourceNodeFactory.md#topics) only that is [global](#globalTopics). Otherwise, `isGlobalSource` is negative (`false`).

`isGlobalSource` is used when:

* `InternalTopologyBuilder` is requested to [globalNodeGroups](#globalNodeGroups), [describeGlobalStore](#describeGlobalStore) and [nodeGroupContainsGlobalSourceNode](#nodeGroupContainsGlobalSourceNode)

## <span id="addGlobalStore"> Registering Global Store

```java
<KIn, VIn> void addGlobalStore(
  StoreBuilder<?> storeBuilder,
  String sourceName,
  TimestampExtractor timestampExtractor,
  Deserializer<KIn> keyDeserializer,
  Deserializer<VIn> valueDeserializer,
  String topic,
  String processorName,
  ProcessorSupplier<KIn, VIn, Void, Void> stateUpdateSupplier)
```

`addGlobalStore`...FIXME

`addGlobalStore` is used when:

* `Topology` is requested to [addGlobalStore](../Topology.md#addGlobalStore)
* `GlobalStoreNode` is requested to `writeToTopology`
* `TableSourceNode` is requested to `writeToTopology`

## <span id="addProcessor"> Registering Processor

```java
void addProcessor(
  String name,
  ProcessorSupplier<KIn, VIn, KOut, VOut> supplier,
  String... predecessorNames)
```

`addProcessor` creates a [ProcessorNodeFactory](ProcessorNodeFactory.md) (that is then added to [nodeFactories](#nodeFactories) registry).

`addProcessor` adds the name to [nodeGrouper](#nodeGrouper) registry and requests it to [unite](#unite) the name with the given `predecessorNames`.

`addProcessor` is used when:

* `Topology` is requested to [addProcessor](../Topology.md#addProcessor)
* Some `GraphNode`s are requested to [writeToTopology](../kstream/GraphNode.md#writeToTopology)

## <span id="addStateStore"> Registering StateStore

```java
void addStateStore(
  StoreBuilder<?> storeBuilder,
  String... processorNames) // (1)
void addStateStore(
  StoreBuilder<?> storeBuilder,
  boolean allowOverride,
  String... processorNames)
```

1. Uses `allowOverride` flag disabled (`false`)

`addStateStore`...FIXME

`addStateStore` is used when:

* `Topology` is requested to [addProcessor](../Topology.md#addProcessor) and [addStateStore](../Topology.md#addStateStore)
* `KTableKTableJoinNode` is requested to `writeToTopology`
* `StatefulProcessorNode` is requested to `writeToTopology`
* `StateStoreNode` is requested to `writeToTopology`
* `StreamStreamJoinNode` is requested to `writeToTopology`
* `StreamToTableNode` is requested to `writeToTopology`
* `TableProcessorNode` is requested to `writeToTopology`
* `TableSourceNode` is requested to `writeToTopology`
