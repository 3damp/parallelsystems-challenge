package c09
  
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class EventGenerator implements CSProcess { 
	 
  def ChannelOutput outChannel
  def int source = 0
  def int initialValue = 0
  def int minTime = 100
  def int maxTime = 1000
  def int iterations = 10 
   
  void run () {    
    def es2udd = Channel.one2one()    
    println "Event Generator for source $source has started"  
	  
    def eventGeneratorList = [ 
            new EventStream ( source: source, 
                              initialValue: initialValue, 
                              iterations: iterations, 
                              outChannel: es2udd.out() ),
            new UniformlyDistributedDelay ( minTime: minTime, 
                                            maxTime: maxTime, 
                                            inChannel: es2udd.in(), 
                                            outChannel: outChannel )
            ]
	
    new PAR (eventGeneratorList).run()
  }
}
    