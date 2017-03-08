package c17.flagged

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.groovy.*
import org.jcsp.lang.*

class Gatherer implements CSProcess {  
	
  def ChannelInput inChannel
  def ChannelOutput outChannel
  def ChannelOutput gatheredData
  
  void run(){	  
    while (true){		
      def v = inChannel.read()
      if ( v instanceof  FlaggedSystemData) {
        def  s = new SystemData ( a: v.a, b: v.b, c: v.c)
        outChannel.write(s)
        gatheredData.write(v)        
      }
      else {
        outChannel.write(v)        
      }  // end else
    }  // end while  
  }  // end run
}
