class EventManager {
  var subscriptions = scala.collection.mutable.Map[String, List[String => Unit]]()

  def subscribe(event_type: String, handler: String => Unit): Unit = {
    if(subscriptions.contains(event_type))
      subscriptions(event_type) = subscriptions(event_type) :+ handler

    else
      subscriptions += (event_type -> List(handler))
  }

  def publish(event: List[String]): Unit = {
    val event_type = event(0)
    if(subscriptions.contains(event_type)){
      for(h <- subscriptions(event_type)){
        h(event(1))
      }
    }
  }
}