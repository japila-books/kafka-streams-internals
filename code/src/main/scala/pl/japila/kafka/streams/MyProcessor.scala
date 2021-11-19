package pl.japila.kafka.streams

import org.apache.kafka.streams.processor.api.{ContextualProcessor, Record}

class MyProcessor[KIn, VIn, KOut, VOut](
    val forward: Boolean = false) extends ContextualProcessor[KIn, VIn, KOut, VOut] {

  override def process(record: Record[KIn, VIn]): Unit = {
    println(s">>> process(key=${record.key()}, value=${record.value()}) (forward=$forward)")
    if (forward) {
      context.forward(record.asInstanceOf[Record[KOut, VOut]])
    }
  }
}
