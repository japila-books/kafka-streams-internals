# TopologyTestDriver

`TopologyTestDriver` helps writing tests to verify behavior of topologies (created with [Topology](Topology.md) or [StreamsBuilder](kstream/StreamsBuilder.md)).

```scala
import org.apache.kafka.streams.TopologyTestDriver
```

## <span id="build"><span id="library-dependency"> Library Dependency

`TopologyTestDriver` belongs to a separate module and has to be defined as a dependency in a build configuration (e.g. `build.sbt`).

```text
val kafkaVersion = "{{ kafka.version }}"
libraryDependencies += "org.apache.kafka" % "kafka-streams-test-utils" % kafkaVersion % Test
```
