# NodeFactory

`NodeFactory<KIn, VIn, KOut, VOut>` is an [abstraction](#contract) of [named node factories](#implementations) (with [predecessors](#predecessors)).

`NodeFactory` is a `private static abstract class` of [InternalTopologyBuilder](../InternalTopologyBuilder.md).

## Contract

### <span id="build"> build

```java
ProcessorNode<KIn, VIn, KOut, VOut> build()
```

Used when:

* `InternalTopologyBuilder` is requested to [build a ProcessorTopology](../InternalTopologyBuilder.md#build)

### <span id="describe"> describe

```java
AbstractNode describe()
```

Used when:

* `InternalTopologyBuilder` is requested to [describeSubtopology](../InternalTopologyBuilder.md#describeSubtopology)

## Implementations

* [ProcessorNodeFactory](ProcessorNodeFactory.md)
* [SinkNodeFactory](SinkNodeFactory.md)
* [SourceNodeFactory](SourceNodeFactory.md)

## Creating Instance

`NodeFactory` takes the following to be created:

* <span id="name"> Name
* <span id="predecessors"> Predecessors

!!! note "Abstract Class"
    `NodeFactory` is an abstract class and cannot be created directly. It is created indirectly for the [concrete NodeFactory'ies](#implementations).
