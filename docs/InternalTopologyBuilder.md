# InternalTopologyBuilder

## Creating Instance

`InternalTopologyBuilder` takes the following to be created:

* <span id="namedTopology"> Topology Name

`InternalTopologyBuilder` is created when:

* `Topology` is [created](Topology.md#internalTopologyBuilder)

## <span id="nodeGroups"> Node Groups

```java
Map<Integer, Set<String>> nodeGroups
```

`InternalTopologyBuilder` defines `nodeGroups` internal registry of subtopologies and an associated group of (source) topics.

`nodeGroups` is initially undefined (`null`) and is [built](#makeNodeGroups) on demand when undefined that happens after `InternalTopologyBuilder` is requested for the following:

* [addSource](#addSource)
* [addSink](#addSink)
* [addProcessor](#addProcessor)
* [addStateStore](#addStateStore)
* [addGlobalStore](#addGlobalStore)
* [connectProcessorAndStateStores](#connectProcessorAndStateStores)

Node groups are uniquely identified by **node group ID** (starting from `0`).

Used when:

* [Building a topology](#buildTopology)
* [Building a sub-topology](#buildSubtopology)
* [globalNodeGroups](#globalNodeGroups)
* [topicGroups](#topicGroups)
* [Describing a topology](#describe)

### <span id="makeNodeGroups"> makeNodeGroups

```java
Map<Integer, Set<String>> makeNodeGroups()
```

For every node (in the [nodeFactories](#nodeFactories) registry) `makeNodeGroups` [putNodeGroupName](#putNodeGroupName).

`makeNodeGroups` uses local mutable `nodeGroups` and `nodeGroupId` values that can be modified every [putNodeGroupName](#putNodeGroupName).

In the end, `makeNodeGroups` returns the `nodeGroups` local collection.

### <span id="putNodeGroupName"> putNodeGroupName

```java
int putNodeGroupName(
  String nodeName,
  int nodeGroupId,
  Map<Integer, Set<String>> nodeGroups,
  Map<String, Set<String>> rootToNodeGroup)
```

`putNodeGroupName` requests the [nodeGrouper](#nodeGrouper) for the name of the root node of the given `nodeName`.

`putNodeGroupName` looks up the name of the root node in the given `rootToNodeGroup`.

If the node group is found (by the name of the root node), `putNodeGroupName` simply adds the given `nodeName` and returns the given `nodeGroupId` (unchanged).

Otherwise, if the name of the root node is not among the available node groups (in the given `rootToNodeGroup`), `putNodeGroupName` adds the root name to the given `rootToNodeGroup` and `nodeGroups` (with an empty node group and a new node group ID).

In the end, `putNodeGroupName` returns a new or the given node group ID (based on availability of the root node).

### <span id="nodeGrouper"> Node Grouper

`InternalTopologyBuilder` creates a node grouper (`QuickUnion<String>`) when [created](#creating-instance).

The node grouper is requested to add a node name for the following:

* [addSource](#addSource)
* [addSink](#addSink)
* [addProcessor](#addProcessor)
* [addGlobalStore](#addGlobalStore)

The node grouper is requested to unite names (of a node and predecessors) for the following:

* [addSink](#addSink)
* [addProcessor](#addProcessor)
* [addGlobalStore](#addGlobalStore)
* [connectProcessorAndStateStore](#connectProcessorAndStateStore)

In the end, the node grouper is requested for a root node in [putNodeGroupName](#putNodeGroupName).

## <span id="describe"> Describing Topology

```java
TopologyDescription describe()
```

`describe` creates a new `TopologyDescription` (that is going to be the returned value in the end).

For every [node group](#nodeGroups) `describe` checks if the group contains a [global (state) source](#isGlobalSource).

If so, `describe` [describeGlobalStore](#describeGlobalStore). Otherwise, `describe` [describeSubtopology](#describeSubtopology).

`describe` is used when:

* `Topology` is requested to [describe](Topology.md#describe)

## <span id="copartitionSourceGroups"> copartitionSourceGroups

```java
List<Set<String>> copartitionSourceGroups
```

`InternalTopologyBuilder` defines `copartitionSourceGroups` internal registry of groups of source processors that need to be co-partitioned.

A new entry is added when:

* `InternalTopologyBuilder` is requested to [copartitionSources](#copartitionSources)

The registry is used when `InternalTopologyBuilder` is requested for the following:

* [maybeUpdateCopartitionSourceGroups](#maybeUpdateCopartitionSourceGroups)
* [validateCopartition](#validateCopartition)
* [copartitionGroups](#copartitionGroups)

## <span id="maybeUpdateCopartitionSourceGroups"> maybeUpdateCopartitionSourceGroups

```java
void maybeUpdateCopartitionSourceGroups(
  String replacedNodeName,
  String optimizedNodeName)
```

`maybeUpdateCopartitionSourceGroups`...FIXME

`maybeUpdateCopartitionSourceGroups` is used when:

* `InternalStreamsBuilder` is requested to [maybeOptimizeRepartitionOperations](kstream/InternalStreamsBuilder.md#maybeOptimizeRepartitionOperations)

## <span id="copartitionGroups"> copartitionGroups

```java
Collection<Set<String>> copartitionGroups()
```

`copartitionGroups`...FIXME

`copartitionGroups` is used when:

* `RepartitionTopics` is requested to [setup](RepartitionTopics.md#setup)

## <span id="copartitionSources"> copartitionSources

```java
void copartitionSources(
  Collection<String> sourceNodes)
```

`copartitionSources` simply adds the given `sourceNodes` to the [copartitionSourceGroups](#copartitionSourceGroups) internal registry.

`copartitionSources` is used when:

* `AbstractStream` is requested to [ensureCopartitionWith](kstream/AbstractStream.md#ensureCopartitionWith)
* `KTableImpl` is requested to [doJoinOnForeignKey](kstream/KTableImpl.md#doJoinOnForeignKey)

## <span id="internalTopicNamesWithProperties"> internalTopicNamesWithProperties

```java
Map<String, InternalTopicProperties> internalTopicNamesWithProperties
```

`InternalTopologyBuilder` defines `internalTopicNamesWithProperties` internal registry of all the internal topics with their corresponding properties.

A new internal topic is added when:

* `InternalTopologyBuilder` is requested to [addInternalTopic](#addInternalTopic)

The registry is used when:

* `InternalTopologyBuilder` is requested to [validateCopartition](#validateCopartition), [buildSinkNode](#buildSinkNode), [buildSourceNode](#buildSourceNode), [topicGroups](#topicGroups), [maybeDecorateInternalSourceTopics](#maybeDecorateInternalSourceTopics)
* `SinkNodeFactory` is requested to [build a processor node](processor/SinkNodeFactory.md#build)

## <span id="addInternalTopic"> addInternalTopic

```java
void addInternalTopic(
  String topicName,
  InternalTopicProperties internalTopicProperties)
```

`addInternalTopic`...FIXME

`addInternalTopic` is used when:

* `KTableImpl` is requested to [doJoinOnForeignKey](kstream/KTableImpl.md#doJoinOnForeignKey)
* `GroupedTableOperationRepartitionNode` is requested to [writeToTopology](kstream/GroupedTableOperationRepartitionNode.md#writeToTopology)
* `OptimizableRepartitionNode` is requested to [writeToTopology](kstream/OptimizableRepartitionNode.md#writeToTopology)
* `UnoptimizableRepartitionNode` is requested to [writeToTopology](kstream/UnoptimizableRepartitionNode.md#writeToTopology)

## <span id="validateCopartition"> validateCopartition

```java
void validateCopartition()
```

`validateCopartition`...FIXME

`validateCopartition` is used when:

* `InternalStreamsBuilder` is requested to [buildAndOptimizeTopology](#buildAndOptimizeTopology)

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

For every [NodeFactory](processor/NodeFactory.md) (in the [nodeFactories](#nodeFactories) internal registry), if the name of the factory is in the given node group if defined or simply all node factories go through, `build` does the following:

1. Requests the `NodeFactory` to [build a ProcessorNode](processor/NodeFactory.md#build) (and registers it in a local registry of processors by name)
1. For `ProcessorNodeFactory`s, [buildProcessorNode](#buildProcessorNode)
1. For `SourceNodeFactory`s, [buildSourceNode](#buildSourceNode)
1. For `SinkNodeFactory`s, [buildSinkNode](#buildSinkNode)

In the end, `build` creates a new [ProcessorTopology](processor/ProcessorTopology.md).

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

When the [pattern](processor/SourceNodeFactory.md#pattern) (of the given [SourceNodeFactory](processor/SourceNodeFactory.md)) is defined, `buildSourceNode` [subscriptionUpdates](#subscriptionUpdates) and requests the `SourceNodeFactory` to [get the topics](processor/SourceNodeFactory.md#getTopics). Otherwise, `buildSourceNode` requests the `SourceNodeFactory` for the [topics](processor/SourceNodeFactory.md#topics).

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

* `KafkaStreams` is [created](KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](TopologyTestDriver.md#setupTopology)

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

* `KafkaStreams` is [created](KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](TopologyTestDriver.md#setupTopology)

## <span id="rewriteTopology"> Rewriting Topology

```java
InternalTopologyBuilder rewriteTopology(
  StreamsConfig config)
```

`rewriteTopology` [setApplicationId](#setApplicationId) to the value of [application.id](StreamsConfig.md#APPLICATION_ID_CONFIG) configuration property.

With [cache.max.bytes.buffering](StreamsConfig.md#CACHE_MAX_BYTES_BUFFERING_CONFIG) enabled, `rewriteTopology`...FIXME

`rewriteTopology` requests the [global StoreBuilders](#globalStateBuilders) to [build StateStores](state/StoreBuilder.md#build).

In the end, `rewriteTopology` [saves the StreamsConfig](#setStreamsConfig) (and returns itself).

`rewriteTopology` is used when:

* `KafkaStreams` is [created](KafkaStreams.md#creating-instance)
* `TopologyTestDriver` is requested to [setupTopology](TopologyTestDriver.md#setupTopology)

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

`isGlobalSource` finds a [NodeFactory](processor/NodeFactory.md) (by given `nodeName`) in [nodeFactories](#nodeFactories) registry.

`isGlobalSource` is positive (`true`) when the `NodeFactory` is a [SourceNodeFactory](processor/SourceNodeFactory.md) with one [topic](processor/SourceNodeFactory.md#topics) only that is [global](#globalTopics). Otherwise, `isGlobalSource` is negative (`false`).

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

* `Topology` is requested to [addGlobalStore](Topology.md#addGlobalStore)
* `GlobalStoreNode` is requested to `writeToTopology`
* `TableSourceNode` is requested to `writeToTopology`

## <span id="addProcessor"> Registering Processor

```java
void addProcessor(
  String name,
  ProcessorSupplier<KIn, VIn, KOut, VOut> supplier,
  String... predecessorNames)
```

`addProcessor` creates a [ProcessorNodeFactory](processor/ProcessorNodeFactory.md) (that is then added to [nodeFactories](#nodeFactories) registry).

`addProcessor` adds the name to [nodeGrouper](#nodeGrouper) to [unite](#unite) the name with the given `predecessorNames`.

`addProcessor` is used when:

* `Topology` is requested to [addProcessor](Topology.md#addProcessor)
* Some `GraphNode`s are requested to [writeToTopology](kstream/GraphNode.md#writeToTopology)

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

* `Topology` is requested to [addProcessor](Topology.md#addProcessor) and [addStateStore](Topology.md#addStateStore)
* `KTableKTableJoinNode` is requested to `writeToTopology`
* `StatefulProcessorNode` is requested to `writeToTopology`
* `StateStoreNode` is requested to `writeToTopology`
* `StreamStreamJoinNode` is requested to `writeToTopology`
* `StreamToTableNode` is requested to `writeToTopology`
* `TableProcessorNode` is requested to `writeToTopology`
* `TableSourceNode` is requested to `writeToTopology`

## <span id="topicGroups"> topicGroups

```java
Map<Subtopology, TopicsInfo> topicGroups()
```

`topicGroups`...FIXME

`topicGroups` is used when:

* `RepartitionTopics` is requested to [setup](RepartitionTopics.md#setup)
* `StreamsPartitionAssignor` is requested for [consumer group assignment](StreamsPartitionAssignor.md#assign)

## <span id="addSource"> addSource

```java
void addSource(
  Topology.AutoOffsetReset offsetReset,
  String name,
  TimestampExtractor timestampExtractor,
  Deserializer<?> keyDeserializer,
  Deserializer<?> valDeserializer,
  Pattern topicPattern)
void addSource(
  Topology.AutoOffsetReset offsetReset,
  String name,
  TimestampExtractor timestampExtractor,
  Deserializer<?> keyDeserializer,
  Deserializer<?> valDeserializer,
  String... topics)
```

`addSource` adds the topics to the [sourceTopicNames](#sourceTopicNames) internal registry.

`addSource` creates a new [SourceNodeFactory](processor/SourceNodeFactory.md) and adds the factory to the [nodeFactories](#nodeFactories) registry (under the given `name`).

`addSource` adds the given `name` and the `topics` to the [nodeToSourceTopics](#nodeToSourceTopics) registry.

`addSource` adds the given `name` to [nodeGrouper](#nodeGrouper) and clears out the [nodeGroups](#nodeGroups) (so it has to be rebuilt next time it is requested).

`addSource` is used when:

* `GroupedTableOperationRepartitionNode` is requested to `writeToTopology`
* `OptimizableRepartitionNode` is requested to `writeToTopology`
* `StreamSourceNode` is requested to [writeToTopology](kstream/StreamSourceNode.md#writeToTopology)
* `TableSourceNode` is requested to `writeToTopology`
* `Topology` is requested to [addSource](Topology.md#addSource)
* `UnoptimizableRepartitionNode` is requested to `writeToTopology`
