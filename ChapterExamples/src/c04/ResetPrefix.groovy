package c04

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class ResetPrefix implements CSProcess {
  
  def int prefixValue = 0
  def ChannelOutput outChannel
  def ChannelInput  inChannel
  def ChannelInput  resetChannel
  
  void run () {
    def guards = [ resetChannel, inChannel  ]
    def alt = new ALT ( guards )
    outChannel.write(prefixValue)
    while (true) {
      def index = alt.priSelect()
      if (index == 0 ) {    // resetChannel input
        def resetValue = resetChannel.read()
		inChannel.read()
        outChannel.write(resetValue)
      }
      else {    //inChannel input 
        outChannel.write(inChannel.read())        
      }
    }
  }
}
