package com.nihirash.stator

import com.nihirash.stator.subscribtion.Subscribtion

abstract class Store[T](subscribtion: Subscribtion[T]) {

  @volatile var state: Seq[T] = Seq.empty

  def isSame(t: T, t1: T): Boolean

  def subscribe(fun: Seq[T] => Any): Unit = subscribtion.subscribe(fun)

  def unsubscribe(fun: Seq[T] => Any): Unit = subscribtion.unsubscribe(fun)

  def upsert(element: T): Unit = {
      val updated = state.map(e => if (isSame(e, element)) element else e)
      state = if (updated == state) state :+ element else updated
      subscribtion.notify(state)
    }

  def applyReducer(fun: Seq[T] => Seq[T]): Unit = {
    apply(fun(state))
    subscribtion.notify(state)
  }

  def find(element: T): Option[T] =
      state.find(e => isSame(e, element))

  def clean(): Unit = {
    apply(Seq.empty)
  }

  def apply(newElements: Seq[T]): Unit = {
      state = newElements
      subscribtion.notify(state)
    }
}
