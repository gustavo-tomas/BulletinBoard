import scala.collection.immutable.ListMap
import scala.collection.mutable

class WordFrequencyCounter (eventManager: EventManager) {
  var word_freq: mutable.Map[String, Int] = scala.collection.mutable.Map[String, Int]()
  val em: EventManager = eventManager
  em.subscribe("valid_word", this.increment_count)
  em.subscribe("print", this.print_freq)

  def increment_count(word: String): Unit = {
    if(word_freq.contains(word)) word_freq(word) += 1
    else word_freq += (word -> 1)
  }

  def print_freq(event1: String): Unit = {
    val ordered_map = ListMap(word_freq.toSeq.sortWith(_._2 > _._2): _*)
    println("\n-> Most frequent words:")
    for(w <- ordered_map){
      println(w._1 + " - " + w._2)
    }
  }
}