package com.nihirash.stator

import com.nihirash.stator.subscribtion.SyncSubscribtion

import scala.concurrent.ExecutionContext.Implicits.global

class IntStore extends Store[Int](new SyncSubscribtion[Int]) {
  override def isSame(t: Int, t1: Int): Boolean = t == t1
}

object TestApp extends App {

  val store = new IntStore
  store.subscribe(x => x.map(println))
  store.subscribe(x => println(x.toString()))
  store.upsert(1)
  readLine()
  store.upsert(10)
  store.clean()
  readLine()
}
