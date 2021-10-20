scalaVersion := "2.13.6"

val kafkaVersion = "3.0.0"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % kafkaVersion
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % kafkaVersion
libraryDependencies += "org.apache.kafka" % "kafka-streams-test-utils" % kafkaVersion

val slf4jVersion = "2.0.0-alpha5"
libraryDependencies += "org.slf4j" % "slf4j-api" % slf4jVersion
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % slf4jVersion

