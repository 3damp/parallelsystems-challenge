package c18.net2
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import phw.util.*

def int nodeId = Ask.Int ("Node identification (2..9) ? ", 2, 9)
def Boolean last = Ask.Boolean ("Is this the last node? - ( y or n):")
 
def ipBase = "127.0.0."
def nodeIP = ipBase + nodeId
def nodeAddress = new TCPIPNodeAddress(nodeIP, 3000)
Node.getInstance().init(nodeAddress)
def fromRing = NetChannel.net2one()
fromRing.read()

def nextNodeIP = (last) ? "127.0.0.1" : ipBase + (nodeId + 1)

def nextNodeAddress = new TCPIPNodeAddress(nextNodeIP, 3000)
def toRing = NetChannel.one2net(nextNodeAddress, 50)
toRing.write(0)

def processNode = new ProcessNode ( inChannel: fromRing,
                                     outChannel: toRing,
                                     nodeId: nodeId) 

new PAR ([ processNode]).run()



  
