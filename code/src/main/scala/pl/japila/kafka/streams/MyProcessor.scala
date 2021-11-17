package pl.japila.kafka.streams

import org.apache.kafka.streams.processor.api.{Processor, ProcessorContext, Record}

class MyProcessor[KIn, VIn, KOut, VOut](
    val forward: Boolean = false) extends Processor[KIn, VIn, KOut, VOut] {

  var context: ProcessorContext[KOut, VOut] = _
  override def init(context: ProcessorContext[KOut, VOut]): Unit = {
    this.context = context
  }

  override def process(record: Record[KIn, VIn]): Unit = {
    println(s">>> process(key=${record.key()}, value=${record.value()}) (forward=$forward)")
    if (forward) {
      context.forward(record.asInstanceOf[Record[KOut, VOut]])
    }
  }
}
