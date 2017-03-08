package c02
        
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import phw.util.*
import org.jcsp.lang.*
  
class Producer implements CSProcess {
  
  def ChannelOutput outChannel
  
  void run() {
    def i = 1000
    while ( i > 0 ) {
      i = Ask.Int ("next: ", -100, 100)
      outChannel.write (i)
    }
  }
}
