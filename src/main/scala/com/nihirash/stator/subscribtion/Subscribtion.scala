package com.nihirash.stator.subscribtion

trait Subscribtion[T] {
  var subscribers: Seq[Seq[T] => Any] = Seq.empty

  def subscribe(subscriber: Seq[T] => Any): Unit =
    subscribers.synchronized {
      subscribers = subscribers :+ subscriber
    }

  def unsubscribe(subscriber: Seq[T] => Any): Unit =
    subscribers.synchronized {
      subscribers = subscribers.filter(_ != subscriber)
    }

  def notify(state: Seq[T]): Unit
}
