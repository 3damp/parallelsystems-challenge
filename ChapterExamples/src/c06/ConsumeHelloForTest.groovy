package c06

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel



import org.jcsp.lang.*

class ConsumeHelloForTest implements CSProcess {  
	
  def ChannelInput inChannel
  def message  
  
  void run() {
    def first = inChannel.read()
    def second = inChannel.read()    
    message = "${first} ${second}!!!"    
    println message
  }
}

