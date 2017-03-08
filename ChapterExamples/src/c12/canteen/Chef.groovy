package c12.canteen
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Chef implements CSProcess {
  
  def ChannelOutput supply
  def ChannelOutput toConsole
  
  void run () {

    def tim = new CSTimer()   
    def CHICKENS = 4
      
    toConsole.write( "Starting ... \n")
    while(true){      
      toConsole.write( "Cooking ... \n")    // cook 4 chickens
      tim.after (tim.read () + 2000)        // this takes 2 seconds to cook
      toConsole.write( "$CHICKENS chickens ready ... \n")
      supply.write (CHICKENS)            
      toConsole.write( "Taking chickens to Canteen ... \n")
      supply.write (0)                     
    }
  }  
}
