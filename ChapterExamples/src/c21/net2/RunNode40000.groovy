package c21.net2
 
  
import org.jcsp.lang.*
import org.jcsp.net2.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import phw.util.*

def dataGenIP = "127.0.0.1"
def gathererIP = "127.0.0.2"

def pList = [ ]
def vList = [ ]
              
def processList = new NodeProcess ( nodeId: 4,
									                   nodeIPFinalPart: 6,
                                     toGathererIP: gathererIP,
                                     toDataGenIP: dataGenIP,
                                     processList: pList,
                                     vanillaList: vList
                                   )

new PAR ([ processList]).run()
