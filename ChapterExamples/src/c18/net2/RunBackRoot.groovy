package c18.net2
 // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import phw.util.*

def rootIP = "127.0.0.1"
def rootAddress = new TCPIPNodeAddress(rootIP, 3000)
Node.getInstance().init(rootAddress)
def fromRing = NetChannel.net2one() // 50

def int iterations = Ask.Int ("Number of Iterations ? ", 1, 9)
def String initialValue = Ask.string ( "Initial List Value ? ")

def backChannel = NetChannel.net2one()  // 51
println " BackRoot channel location = ${backChannel.getLocation()} "

def nextNodeIP = "127.0.0.2"
def nextNodeAddress = new TCPIPNodeAddress(nextNodeIP, 3000)
def toRing = NetChannel.one2net(nextNodeAddress, 50)

toRing.write(0)
fromRing.read()

def rootNode = new BackRoot ( inChannel: fromRing, 
                               outChannel: toRing,
                               iterations: iterations,
                               initialValue: initialValue,
                               backChannel: backChannel)

new PAR ( [rootNode] ).run()
