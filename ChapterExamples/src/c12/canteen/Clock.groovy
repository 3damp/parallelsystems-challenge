package c12.canteen

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class Clock implements CSProcess {
  
  def ChannelOutput toConsole
  
  void run() {

    def tim = new CSTimer()
    def tick = 0

    while (true) {
      toConsole.write ("Time: $tick \n")
      tim.sleep (1000)
      tick = tick + 1
    }

  }

}
