object Main extends App{
  var event_type = "load"
  var path = new java.io.File(".").getCanonicalPath + "/src/main/scala/" + "bigtext.txt"
  val start_list = List(event_type, path)

  var em  = new EventManager
  var ds  = new DataStorage(em)
  var swf = new StopWordFilter(em)
  var wfc = new WordFrequencyCounter(em)
  em.publish(start_list)
}