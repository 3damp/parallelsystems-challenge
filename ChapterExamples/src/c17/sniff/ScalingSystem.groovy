package c17.sniff

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*

import c05.*
class ScalingSystem implements CSProcess{  
	
  def ChannelOutput toSniffer
  def ChannelOutput toComparator
  
  void run() {    
    def data1 = Channel.one2one()
    def data2 = Channel.one2one()
    def timedData = Channel.one2one()
    def scaledData = Channel.one2one()
    def oldScale = Channel.one2one()
    def newScale = Channel.one2one()
    def pause = Channel.one2one()
	
    def network = [ new GNumbers ( outChannel: data1.out() ),
		
                    new GPCopy ( inChannel: data1.in(),
                                  outChannel0: toSniffer,
                                  outChannel1: data2.out()),
							  
                    new GFixedDelay ( delay: 500, 
                        		      inChannel: data2.in(), 
                        		      outChannel: timedData.out() ),
								  
                    new Scale ( inChannel: timedData.in(),
                                outChannel: toComparator,
                                factor: oldScale.out(),
                                suspend: pause.in(),
                                injector: newScale.in(),
                                multiplier: 4,
                                scaling: 2 ),
							
                    new Controller ( testInterval: 7000,
                    		         computeInterval: 700,
                    		         addition: 3,
                                     factor: oldScale.in(),
                                     suspend: pause.out(),
                                     injector: newScale.out() )
                 ]
	
    new PAR ( network ).run()                                                            
  } // end run
}