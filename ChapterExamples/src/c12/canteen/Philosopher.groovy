package c12.canteen;

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*

class Philosopher implements CSProcess{

  def ChannelOutput service
  def ChannelInput deliver
  def int philosopherId

  void run() {
    
    def console = Channel.one2one()    
    def philosopher = new PhilosopherBehaviour ( service: service,
                                                  deliver: deliver,
                                                  toConsole: console.out(),
                                                  id: philosopherId)
    def philosopherConsole = new GConsole ( toConsole: console.in(),
                                            frameLabel: "Philosopher $philosopherId")
    new PAR( [ philosopher, philosopherConsole]).run()
  }
}
