package c09

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class EventStream implements CSProcess {  
	
  def int source = 0
  def int initialValue = 0
  def int iterations = 10
  def ChannelOutput outChannel
    
  void run () {
    def i = initialValue
	
    1.upto(iterations) {
      def e = new EventData ( source: source, data: i )
      outChannel.write(e)
      i = i + 1
    }
    println "Source $source has finished"
  }
}

