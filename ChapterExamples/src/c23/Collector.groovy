package c23

import c23.loaderObjects.WorkerInterface;

import org.jcsp.net2.tcpip.TCPIPNodeAddress
import org.jcsp.net2.*
import org.jcsp.net2.mobile.*
import netObjects.*
import org.jcsp.groovy.*
import org.jcsp.lang.*

class Collector implements WorkerInterface {
	
	def ChannelInputList inChannels
	def ChannelOutputList outChannels
	
	def connect(inChannels, outChannels){
		this.inChannels = inChannels
		this.outChannels = outChannels
	}
	
	void run(){
		//println "Collector starting"
		for ( i in 1..10){
			def dObj = inChannels[0].read()
			println "Value read = ${dObj.value}"
		}
		inChannels[0].read()  // terminating sentinel
		//println "Collector ending"
	}

}
