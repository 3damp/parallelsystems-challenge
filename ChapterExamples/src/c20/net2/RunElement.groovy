package c20.net2
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import phw.util.*

def int nodeId = Ask.Int ("Node identification (2..9) ? ", 2, 9)
def Boolean last = Ask.Boolean ("Is this the last node? - ( y or n):")
 def int sentMessages = Ask.Int("Number of messages to be sent by a Sender (100 - 2000)? ", 100, 2000)
def int nodes = Ask.Int("Number of nodes (excluding the Extra Node) ? ", 1, 8)

def ipBase = "127.0.0."
def nodeIP = ipBase + nodeId
def nodeAddress = new TCPIPNodeAddress(nodeIP, 3000)
Node.getInstance().init(nodeAddress)
def fromRing = NetChannel.net2one()

def nextNodeIP = (last) ? "127.0.0.1" : ipBase + (nodeId + 1)
println "next node is $nextNodeIP"

fromRing.read()
def nextNodeAddress = new TCPIPNodeAddress(nextNodeIP, 3000)
def toRing = NetChannel.one2net(nextNodeAddress, 50)
toRing.write(0)

def processNode = new AgentElement ( fromRing: fromRing,
                                     toRing: toRing,
                                     element: nodeId,
                                     iterations: sentMessages,
                                     nodes: nodes) 

new PAR ([ processNode]).run()
