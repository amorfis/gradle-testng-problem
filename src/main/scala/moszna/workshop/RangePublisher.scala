package moszna.workshop

import org.reactivestreams.{Subscription, Publisher, Subscriber}

class RangePublisher[T](i: T, count: Long) extends Publisher[T] {
  override def subscribe(s: Subscriber[_ >: T]) = {
    s.onSubscribe(new Subscription {
      override def cancel() = ???

      override def request(n: Long) = {
        for(j <- 1 to n.toInt) {
          s.onNext(i)
        }
      }
    })
  }
}
