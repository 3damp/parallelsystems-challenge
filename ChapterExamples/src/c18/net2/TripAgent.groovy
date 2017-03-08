package c18.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.net2.*
import org.jcsp.lang.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
 
class TripAgent implements MobileAgent {
  
  def ChannelOutput toLocal
  def ChannelInput fromLocal
  def tripList = [ ]
  def results = [ ]
  def int pointer
                  
  def connect ( c ) {
    this.toLocal = c[0]
    this.fromLocal = c[1]
  }
  
  def disconnect (){
    toLocal = null
    fromLocal = null
  }

  void run() {
    toLocal.write (results)
    results = fromLocal.read()
    if (pointer > 0) {
      pointer = pointer - 1
      def nextChannel = NetChannel.one2net (tripList.get(pointer))
      disconnect()
      nextChannel.write(this)
    }
    else {
      println "Agent has returned to TripRoot"
    }
  }
}
