package c18.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.MobileAgent


class Agent implements MobileAgent {  
	
  def ChannelOutput toLocal
  def ChannelInput fromLocal
  
  def results = [ ]    
                
  def connect ( c ) {
    this.toLocal = c[0]
    this.fromLocal = c[1]
  }  
  def disconnect () {
    toLocal = null
    fromLocal = null
  }  
  void run() {
    toLocal.write (results)
    results = fromLocal.read()
  }
}
