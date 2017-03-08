package c17.sniff

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import c05.*

class Comparator implements CSProcess {  
	
  def ChannelInput fromSystemOutput
  def ChannelInput fromSniffer
  
  void run() {
    def SNIFF = 0
    def COMPARE = 1
    def comparatorAlt = new ALT ([fromSniffer, fromSystemOutput ])
    def running = true
	
    while (running) {
      def index = comparatorAlt.priSelect()
      switch (index) {
        case SNIFF:
          def value = fromSniffer.read()
          def comparing = true
          while (comparing) {
            def result = (ScaledData) fromSystemOutput.read()
            if (result.original == value){
              if (result.scaled >= result.original) {
                println "\t\t\t\t\t WITHIN BOUNDS: ${result}"
                comparing = false
              }
              else {
                println "\t\t\t\t\t OUTWITH BOUNDS: ${result}"
                running = false
              } // end else
            } //end if
          } //end while index
          break
        case COMPARE:
          fromSystemOutput.read()
          break
      } // end switch      
    } // end while running
  } // end run
}
