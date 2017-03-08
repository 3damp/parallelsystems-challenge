package c13

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class WriteClerk implements CSProcess {
	
  def ChannelInput cin
  def ChannelOutput cout
  def CrewMap data
  
  void run () {
    println "WriteClerk has started "
    while (true) {
      def d = new DataObject()
      d = cin.read()
      data.put ( d.location, d.value )
      println "WC: Writer ${d.pid} has written ${d.value} to ${d.location}"
      cout.write(d)
    }
  }
}

  