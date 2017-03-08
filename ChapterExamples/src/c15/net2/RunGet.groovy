package c15.net2
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import phw.util.*

def getNodeIP = "127.0.0.2"

def getNodeAddr = new TCPIPNodeAddress(getNodeIP, 3000)
Node.getInstance().init (getNodeAddr)

def comms = NetChannel.net2one()
def pList = [ new Get ( inChannel: comms , id: 0 ) ]

new PAR ( pList ).run()
