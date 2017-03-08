package c10;


// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*

class ExtraElementv1 implements CSProcess {
	
  def ChannelInput fromRing
  def ChannelOutput toRing
  
  void run () {
    while (true) {
      def packet = (RingPacket) fromRing.read()
      println "Extra Element 0 has read " + packet.toString()
      toRing.write( packet )
      println "Extra Element 0 has written " + packet.toString()
    }
  }
}
