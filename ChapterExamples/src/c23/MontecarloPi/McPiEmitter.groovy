package c23.MontecarloPi

import c23.loaderObjects.WorkerInterface;

import org.jcsp.groovy.ChannelInputList;
import org.jcsp.groovy.ChannelOutputList;

 

class McPiEmitter implements WorkerInterface {
	
	def ChannelInputList inChannels 
	def ChannelOutputList outChannels 
	def workers = 1
	def iterations = 192
	
	def connect(inChannels, outChannels){
		this.inChannels = inChannels
		this.outChannels = outChannels
	}
	
	void run(){
		println "running McPiEmitter"
		for ( w in 0 ..< workers) outChannels[w].write(iterations / workers)
	}
}
