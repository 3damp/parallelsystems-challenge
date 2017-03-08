package c19.net2.accessServer

import org.jcsp.lang.*
import org.jcsp.util.*
import org.jcsp.groovy.*
import org.jcsp.net2.*



class AccessProcess implements CSProcess, Serializable {

	def NetChannelLocation processReceiveLocation
	def NetChannelLocation accessRequestLocation
	
	void run (){
		def buttonChannel = Channel.one2one(new OverWriteOldestBuffer(5))
		new PAR ([new AccessInterface( buttonEvents: buttonChannel.out()),
			        new AccessCapability( buttonEvents: buttonChannel.in(),
								                    processReceiveLocation: processReceiveLocation,
																		accessRequestLocation:accessRequestLocation)]).run()
	}
}
