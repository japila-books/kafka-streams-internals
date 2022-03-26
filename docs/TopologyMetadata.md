# TopologyMetadata

## <span id="buildAndRewriteTopology"> buildAndRewriteTopology

```java
void buildAndRewriteTopology()
```

`buildAndRewriteTopology`...FIXME

`buildAndRewriteTopology` is used when:

* `KafkaStreams` is [created](KafkaStreams.md#creating-instance)

## <span id="registerAndBuildNewTopology"> registerAndBuildNewTopology

```java
void registerAndBuildNewTopology(
  InternalTopologyBuilder newTopologyBuilder)
```

`registerAndBuildNewTopology`...FIXME

`registerAndBuildNewTopology` is used when:

* `KafkaStreamsNamedTopologyWrapper` is requested to `addNamedTopology`

## <span id="buildAndVerifyTopology"> buildAndVerifyTopology

```java
void buildAndVerifyTopology(
  InternalTopologyBuilder builder)
```

`buildAndVerifyTopology`...FIXME

`buildAndVerifyTopology` is used when:

* `TopologyMetadata` is requested to [registerAndBuildNewTopology](#registerAndBuildNewTopology) and [buildAndRewriteTopology](#buildAndRewriteTopology)
