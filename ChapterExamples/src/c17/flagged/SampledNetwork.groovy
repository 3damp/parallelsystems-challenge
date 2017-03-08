package c17.flagged

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.*
import org.jcsp.lang.*


class SampledNetwork implements CSProcess {  
	
  def ChannelInput inChannel
  def ChannelOutput outChannel
  
  void run() {
	  
    while (true) {
      def v = inChannel.read()
      v.c = v.a + v.b
      outChannel.write(v)
    }    
  }
}
