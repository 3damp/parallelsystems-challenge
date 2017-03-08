package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class BadC implements CSProcess {
	
  def ChannelInput inChannel
  def ChannelOutput outChannel 
   
  void run() {
    println "BadC: Starting"
	
    while (true) {
      println "BadC: outputting"
      outChannel.write(1)
      println "BadC: inputting"
      def i = inChannel.read()
      println "BadC: looping"
    }
  }
}

      