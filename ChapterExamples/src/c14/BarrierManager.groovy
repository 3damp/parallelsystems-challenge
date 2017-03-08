package c14

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class BarrierManager implements CSProcess{
	
  def AltingBarrier timeAndHitBarrier
  def AltingBarrier finalBarrier
  def Barrier goBarrier
  def Barrier setUpBarrier

  void run() {
    def timeHitAlt = new ALT ([timeAndHitBarrier])
    def finalAlt = new ALT ([finalBarrier])
    setUpBarrier.sync()
	
    while (true){
      goBarrier.sync()
      def t = timeHitAlt.select()
      def f = finalAlt.select()
    }
  }
}
