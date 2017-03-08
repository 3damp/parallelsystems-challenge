package c19.net2.accessServer

import org.jcsp.lang.*
import org.jcsp.net2.*
import c19.net2.netObjects.*




class AccessCapability implements CSProcess {

	def ChannelInput buttonEvents
	def NetChannelLocation processReceiveLocation
	def NetChannelLocation accessRequestLocation
	
	void run (){
		def serviceRequired = buttonEvents.read()
		def clientRequest  = new ClientRequestData(processReceiveLocation: processReceiveLocation,
																							 serviceRequired: serviceRequired)
		def toAccess = NetChannel.any2net(accessRequestLocation)
		toAccess.write(clientRequest)
	}
}
