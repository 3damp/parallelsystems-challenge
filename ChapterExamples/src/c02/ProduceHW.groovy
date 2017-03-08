package c02

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel



import org.jcsp.lang.*

class ProduceHW implements CSProcess {
  
  def ChannelOutput outChannel
  
  void run() {
    def hi = "Hello"
    def thing = "World"
    outChannel.write ( hi )
    outChannel.write ( thing )   
  } 
}

      
  
  
