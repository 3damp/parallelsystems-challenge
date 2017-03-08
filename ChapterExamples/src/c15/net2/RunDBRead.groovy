package c15.net2
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import phw.util.*
import org.jcsp.groovy.plugAndPlay.*
import c13.Read

def dbIp = "127.0.0.1"
def readBase = 100
def readerBaseIP = "127.0.0."
def readerId = Ask.Int ("Reader process ID (0..4)? ", 0, 4)
def readerIndex = readBase + readerId
def readerIP = readerBaseIP + readerIndex
def readerAddress = new TCPIPNodeAddress(readerIP, 1000)
Node.getInstance().init(readerAddress)
    
println "Read Process $readerId, $readerIP is creating its Net channels "

//NetChannelInput 
def fromDB = NetChannel.numberedNet2One(75)  // the net channel from the database
println "fromDB location = ${fromDB.getLocation()}"

//NetChannelOutput 
def dbAddress =  new TCPIPNodeAddress(dbIp, 3000)
def toDB = NetChannel.one2net(dbAddress, readerIndex) // the net channel to the database
println "toDB location = ${toDB.getLocation()}"
toDB.write(0)
fromDB.read()

println "Read Process $readerId has created its Net channels "
def consoleChannel = Channel.one2one()
def pList = [ 
	new Read ( id:readerId, r2db: toDB, db2r: fromDB, toConsole: consoleChannel.out() ),
	new GConsole(toConsole:consoleChannel.in(), frameLabel: "Reader $readerId"  ) 
	]
new PAR (pList).run()
