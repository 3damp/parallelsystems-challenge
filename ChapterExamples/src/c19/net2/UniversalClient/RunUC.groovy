package c19.net2.UniversalClient

import org.jcsp.lang.*
import org.jcsp.net2.*;
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import org.jcsp.util.*


def ipChannel = Channel.one2one(new OverWriteOldestBuffer(5))

new PAR([ new UCInterface(sendNodeIdentity: ipChannel.out()), 
				  new UCCapability(receiveNodeIdentity: ipChannel.in() )]).run()
					
	