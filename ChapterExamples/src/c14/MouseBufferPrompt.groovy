package c14

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class MouseBufferPrompt implements CSProcess{
	
  def ChannelOutput returnPoint
  def ChannelOutput getPoint
  def ChannelInput receivePoint  
  def Barrier setUpBarrier

  void run () {
    setUpBarrier.sync()
    while (true) {
      getPoint.write( 1 )
      def point = receivePoint.read()
      returnPoint.write( point )
    }    
  }
}
