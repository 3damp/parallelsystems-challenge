package c18.net2
 // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import phw.util.*

def int nodeId = Ask.Int ("Node identification (2..9)? ", 2, 9)

def ipBase = "127.0.0."
def nodeIP = ipBase + nodeId
def nodeAddress = new TCPIPNodeAddress(nodeIP, 3000)
Node.getInstance().init(nodeAddress)

def rootNodeIP = "127.0.0.1"
def rootNodeAddress = new TCPIPNodeAddress(rootNodeIP, 3000)

def toRoot = NetChannel.any2net(rootNodeAddress, 50)

def processNode = new TripNode ( toRoot: toRoot,
                                  nodeId: nodeId) 

new PAR ([processNode]).run()



  
