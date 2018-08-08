package com.nihirash.stator.subscribtion

class SyncSubscribtion[T] extends Subscribtion[T] {
  override def notify(state: Seq[T]): Unit = subscribers.foreach(_(state))
}
