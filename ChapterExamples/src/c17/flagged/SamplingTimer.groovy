package c17.flagged

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.*
import org.jcsp.lang.*


class SamplingTimer implements CSProcess {
	
  def ChannelOutput sampleRequest
  def sampleInterval 
   
  void run() {
    def timer = new CSTimer()
    while (true){
      timer.sleep(sampleInterval)
      sampleRequest.write(1)
    }
  }
}
