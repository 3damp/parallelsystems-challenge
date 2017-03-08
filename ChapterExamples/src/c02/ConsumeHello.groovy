package c02

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel



import org.jcsp.lang.*

class ConsumeHello implements CSProcess {
  
  def ChannelInput inChannel
  
  void run() {
    def first = inChannel.read()
    def second = inChannel.read()
    println "\n${first} ${second}!\n"
  }
}

