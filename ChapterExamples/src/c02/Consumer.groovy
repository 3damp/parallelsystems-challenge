package c02
    
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel



import org.jcsp.lang.*
    
class Consumer implements CSProcess {
  
  def ChannelInput inChannel
  
  void run() {
    def i = 1000
    while ( i > 0 ) {
      i = inChannel.read()
      println "the input was : ${i}"
    }
    println "Finished"
  }
}

