package c15.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class Sender implements CSProcess {
  
  def ChannelOutput outChannel
  def String id
  
  void run() {
    def timer = new CSTimer()
    while (true) {
      timer.sleep(10000)
      outChannel.write ( id )
    }
  }
}

      
  
