# ProcessorNodeFactory

`ProcessorNodeFactory` is a [NodeFactory](NodeFactory.md).

## Creating Instance

`ProcessorNodeFactory` takes the following to be created:

* <span id="name"> Name
* <span id="predecessors"> Predecessor Nodes
* <span id="supplier"> [ProcessorSupplier](ProcessorSupplier.md)

`ProcessorNodeFactory` is created when:

* `InternalTopologyBuilder` is requested to [addProcessor](../InternalTopologyBuilder.md#addProcessor) and [addGlobalStore](../InternalTopologyBuilder.md#addGlobalStore)
