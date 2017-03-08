package c15.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class Receiver implements CSProcess {
  
  def ChannelInput inChannel
  
  void run() {
    while (true) {
      def v = inChannel.read()
      println "$v"
    }
  }
}

