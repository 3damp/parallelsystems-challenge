package c17.counted

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.* 
import org.jcsp.lang.*


class CountedSamplingTimer implements CSProcess {
	
  def ChannelOutput sampleRequest
  def sampleInterval
  def ChannelInput countReturn
  def ChannelOutput countToGatherer 
   
  void run() {
    def timer = new CSTimer()
    while (true){
      timer.sleep(sampleInterval)
      sampleRequest.write(1)
      def c= countReturn.read()
      countToGatherer.write(c)
    }
  }
}
