package c08;

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.*
import org.jcsp.lang.*


class Server implements CSProcess{
	  
  def ChannelInputList fromMux
  def ChannelOutputList toMux  
  def dataMap = [ : ] 
                   
  void run() {
    def serverAlt = new ALT(fromMux)
	
    while (true) {
      def index = serverAlt.select()
      def key = fromMux[index].read()
      toMux[index].write(dataMap[key])
    }    
  }
}
