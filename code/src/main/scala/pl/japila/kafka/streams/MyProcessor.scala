package pl.japila.kafka.streams

import org.apache.kafka.streams.processor.api.{Processor, Record}

class MyProcessor[KIn, VIn, KOut, VOut] extends Processor[KIn, VIn, KOut, VOut] {
  override def process(record: Record[KIn, VIn]): Unit = {
    println(s">>> process(key=${record.key()}, value=${record.value()}")
  }
}
