import scala.io.Source

class DataStorage (eventManager: EventManager) {
  val em: EventManager = eventManager
  em.subscribe("load", this.load)

  def load(path: String): Unit = {
    val source = Source.fromFile(path).getLines()
    for(lines <- source){
      var line = lines.split(" ").map(w => w.replaceAll("[^a-zA-Z]", "").toLowerCase).toList.filter(_.nonEmpty)
      for(w <- line){
        em.publish(List("word", w))
      }
    }
    em.publish(List("print", null))
  }
}