package c04

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*

class ResetNumbers implements CSProcess {
  
  def ChannelOutput outChannel
  def ChannelInput resetChannel
  def int initialValue = 0
  
  void run() {
    
    def a = Channel.one2one ()
    def b = Channel.one2one()
    def c = Channel.one2one()
    
    def testList = [ new ResetPrefix ( prefixValue: initialValue, 
                                       outChannel: a.out(), 
                                       inChannel: c.in(),
                                       resetChannel: resetChannel ),
                     new GPCopy ( inChannel: a.in(), 
                            	  outChannel0: outChannel, 
                            	  outChannel1: b.out() ),
                     new GSuccessor ( inChannel: b.in(), 
                            		  outChannel: c.out()) 
                   ]
    new PAR ( testList ).run()    
  }
}
                         
