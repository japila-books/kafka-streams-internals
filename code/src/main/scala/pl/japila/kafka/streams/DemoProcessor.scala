package pl.japila.kafka.streams

import org.apache.kafka.streams.processor.{Cancellable, PunctuationType, Punctuator, TaskId}
import org.apache.kafka.streams.processor.api.{ContextualProcessor, ProcessorContext, Record}

import java.time.Duration

class DemoProcessor[KIn, VIn, KOut, VOut](
    val forward: Boolean = false) extends ContextualProcessor[KIn, VIn, KOut, VOut] {

  var cancellable: Option[Cancellable] = None

  override def init(context: ProcessorContext[KOut, VOut]): Unit = {
    super.init(context)

    // Expand one comment at a time to discover (learn) more
    /**
     * println(s"[$taskId] Executed at $timestamp")
     */
    /**
     * println(s"[$taskId][forward=$forward] Executed at $timestamp")
     */
    def createPunctuator(taskId: TaskId): Punctuator = (timestamp: Long) => {
      println(s"[$taskId][forward=$forward] Executed at $timestamp")
    }

    cancellable = Some(
      context.schedule(
        Duration.ofSeconds(5),
        PunctuationType.WALL_CLOCK_TIME,
        createPunctuator(context.taskId())))
  }

  override def process(record: Record[KIn, VIn]): Unit = {
    val key = record.key()
    val value = record.value()
    println(s">>> process(key=$key, value=$value) (forward=$forward)")
    value match {
      case stop: String if "STOP".equalsIgnoreCase(stop) =>
        println(s">>> [${context().taskId()}] Stopping the punctuator")
        cancellable.foreach(_.cancel())
        cancellable = None
      case _ =>
    }
    if (forward) {
      context.forward(record.asInstanceOf[Record[KOut, VOut]])
    }
  }
}
