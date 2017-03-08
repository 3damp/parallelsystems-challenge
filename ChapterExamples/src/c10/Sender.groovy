package c10

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class Sender implements CSProcess {
	
  def ChannelOutput toElement
  def int element
  def int nodes
  def int iterations = 12
  
  void run() {
    for ( i in 1 .. iterations ) {
      def dest = (i % (nodes) ) + 1
      if ( dest != element ) {
        def packet = new RingPacket ( source: element, destination: dest , value: (element * 100) + i , full: true)
        toElement.write(packet)
        println "Sender ${element}: has sent " + packet.toString()
      }
    }
  }
}

    
