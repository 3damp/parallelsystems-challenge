package c15.net2
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import phw.util.*

import c12.canteen.*

def chefNodeIP = "127.0.0.1"
def canteenNodeIP = "127.0.0.2"
def philosopherNodeIP = "127.0.0.3"

def TCPIPNodeAddress chefNodeAddr = new TCPIPNodeAddress(chefNodeIP,3003)
Node.getInstance().init (chefNodeAddr)
def  canteenAddress = new TCPIPNodeAddress(canteenNodeIP,3000)
def cooked = NetChannel.one2net(canteenAddress, 50)
println "cooked location = ${cooked.getLocation()}"

def processList = [ new Kitchen ( supply: cooked) ]
new PAR ( processList ).run()     
