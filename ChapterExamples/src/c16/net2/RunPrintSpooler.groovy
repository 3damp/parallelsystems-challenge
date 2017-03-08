package c16.net2
  // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import phw.util.*

def spoolers = Ask.Int ("Number of spoolers ? ", 1, 9)

def printSpoolerIP = "127.0.0.1"
def psAddress = new TCPIPNodeAddress(printSpoolerIP, 2000)
Node.getInstance().init(psAddress)
def pRequest = NetChannel.net2one()		// cn = 50
def pRelease = NetChannel.net2one()		// cn = 51
println "pRequest location = ${pRequest.getLocation()}"
println "pRelease location = ${pRelease.getLocation()}"

new PAR ( [ new PrintSpooler ( printerRequest: pRequest, 
                               printerRelease: pRelease, 
                               spoolers : spoolers  
                             )
          ] ).run()

