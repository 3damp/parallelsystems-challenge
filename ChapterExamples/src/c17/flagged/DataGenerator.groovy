package c17.flagged

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.*
import org.jcsp.lang.*


class DataGenerator implements CSProcess {  
	
  def ChannelOutput outChannel
  def interval = 500
  
  void run() {
    println "Generator Started"
    def timer = new CSTimer()
    def i = 0
    while (true) {
      def v = new SystemData ( a: i, b: i+1)
      outChannel.write(v)
      i = i + 2
      timer.sleep(interval)
    }    
  }
}
