# Kafka Streams Sandbox

Use the project to play with [Kafka Streams](https://kafka.apache.org/documentation/streams/).

## Create Topics

```text
export KAFKA_HOME=~/dev/oss/kafka/
```

```text
$KAFKA_HOME/bin/kafka-topics.sh \
  --bootstrap-server :9092 \
  --create \
  --topic input-topic \
  --partitions 3 \
  --replication-factor 1
```

```text
$KAFKA_HOME/bin/kafka-console-producer.sh \
  --bootstrap-server :9092 \
  --property parse.key=true \
  --property key.separator=, \
  --topic input-topic
```

Sending `stop` message to partition `0` (`-p 0`) to stop a punctuator.

```text
echo stop | kcat -P -b :9092 -t input-topic -p 0
```

Sending a record with a key and a value (`-K ,`):

```text
echo 1,one | kcat -P -b :9092 -K , -t input-topic -p 2
```