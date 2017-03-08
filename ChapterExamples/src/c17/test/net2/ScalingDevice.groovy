package c17.test.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


import c05.*
class ScalingDevice implements CSProcess {
	
  def ChannelInput inChannel
  def ChannelOutput outChannel  
  
  void run() {
	println "scaling device started"
    def oldScale = Channel.one2one()
    def newScale = Channel.one2one()
    def pause = Channel.one2one()
	
    def scaler = new Scale ( inChannel: inChannel,
                              outChannel: outChannel,
                              factor: oldScale.out(),
                              suspend: pause.in(),
                              injector: newScale.in(),
                              multiplier: 2,
                              scaling: 2 )
	
    def control =  new Controller ( testInterval: 7000,
                                     computeInterval: 700,
                                     addition: 1,
                                     factor: oldScale.in(),
                                     suspend: pause.out(),
                                     injector: newScale.out() ) 
	   
    def testList = [ scaler, control]  
	  
    new PAR(testList).run()                 
  }
}
