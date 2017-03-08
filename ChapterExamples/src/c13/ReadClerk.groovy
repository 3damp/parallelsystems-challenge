package c13

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class ReadClerk implements CSProcess {
	
  def ChannelInput cin
  def ChannelOutput cout
  def CrewMap data
  
  void run () {
    println "ReadClerk has started "
    while (true) {
      def d = new DataObject()
      d = cin.read()
      d.value = data.get ( d.location )
      println "RC: Reader ${d.pid} has read ${d.value} from ${d.location}"
      cout.write(d)
    }
  }
}

  