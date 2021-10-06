# Global Stores

[StreamsBuilder.addGlobalStore](StreamsBuilder.md#addGlobalStore) adds a global [StateStore](processor/StateStore.md) to a topology.

Such a `StateStore` sources its data from all partitions of the provided input topic. This store uses the source topic as changelog (and during restore will insert records directly from the source).

Global stores should not be added to `Processor`, `Transformer`, or `ValueTransformer` (unlike regular stores). They have read-only access to all global stores by default.

There will be exactly one instance of this `StateStore` per Kafka Streams instance.

A [SourceNode](processor/SourceNode.md) will be added to consume the data arriving from the partitions of the input topic.
