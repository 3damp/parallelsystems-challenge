package c17.counted

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.groovy.*
import org.jcsp.lang.*

class CountedEvaluator implements CSProcess {  
	
  def ChannelInput inChannel
  
  void run() {
    while (true) {
      def v = inChannel.read()
      //def ok = ( (v.value % 2) == 0 )
      def ok = ( ( v.value / (v.counter - 1) ) == 2 )
      println "Evaluation: ${ok} from " + v.toString()
    }    
  }
}
