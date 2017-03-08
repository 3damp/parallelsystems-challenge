package c04
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*

class ResetUser implements CSProcess {
	
  def ChannelOutput resetValue
  def ChannelOutput toConsole
  def ChannelInput fromConverter
  def ChannelOutput toClearOutput
	
  void run() {
	toConsole.write( "Please input reset values\n" )
	while (true) {
	  def v = fromConverter.read()
	  toClearOutput.write("\n")
	  resetValue.write(v)
	}
  }
}
