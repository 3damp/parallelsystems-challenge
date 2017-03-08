package c17.test.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import c05.*


class CollectNumbers implements CSProcess {  
	
  def ChannelInput inChannel
  def collectedList = []
  def scaledList = [] 
  def iterations = 20
  
  void run() {
    println "Collector Started"
    for ( i in 1 .. iterations) {
      def result = (ScaledData) inChannel.read()
      collectedList << result.original
      scaledList << result.scaled
    }
    println "Collector Finished"
    println "Original: ${collectedList}"
    println "Scaled  : ${scaledList}"
  }
}
