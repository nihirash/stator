package com.nihirash.stator.subscribtion

import scala.concurrent.{ExecutionContext, Future}

class AsyncSubscribtion[T](implicit executionContext: ExecutionContext) extends Subscribtion[T] {
  override def notify(state: Seq[T]): Unit = Future(subscribers.foreach(s => s(state)))
}
