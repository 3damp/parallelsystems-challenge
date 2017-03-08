package c05

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel



import org.jcsp.lang.*

class QProducer implements CSProcess { 
	 
  def ChannelOutput put
  def int iterations = 100
  def delay = 0  
  
  void run () {
	def timer = new CSTimer()
    println  "QProducer has started"
	
    for ( i in 1 .. iterations ) {
      put.write(i)
      timer.sleep (delay)
    }
	put.write(null)
  }
}
