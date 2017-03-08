package c22.worker;

import org.jcsp.lang.*;
import org.jcsp.groovy.*;

import c22.universalClasses.*

class CopyOfBaseNet implements CSProcess {
	
	/*
	 * BaseNet has some slightly different constructor 
	 * properties compared with Base.  It is passed the 
	 * location (net address) of the channel end that connects 
	 * from the Emitter to the Base as fromEmitterLoc.  This 
	 * channel end is created during the initial phase of RunBase.
	 */
	
	def toEmitter
	def fromEmitterLoc
	def fromEmitter
	def toCollector
	def baseId
	
	void run(){
		// it is required to set up the net connections in the final version
		// channelAddress will be net location of 
		// input channel to Base from Emitter
		toEmitter.write(new InitObject(id: baseId, channelAddress: fromEmitterLoc))
		def startWork = Channel.one2one()
		def workFinished = Channel.one2one()
		def sharedData = []
		def sync = (Signal)fromEmitter.read() //synchronisation signal
		println "Base: $baseId initialised and about to run internal processes"
		def getter = new GetInput ( toEmitter: toEmitter,
									baseId: baseId,
									fromEmitter: fromEmitter,
									toWorker: startWork.out(),
									sharedData: sharedData )
		def worker = new DoWork ( workOn: startWork.in(), 
								  workCompleted: workFinished.out(),
								  workerId: baseId, 
								  sharedData: sharedData )
		def putter = new SendOutput ( workerFinished: workFinished.in(), 
									  toCollector: toCollector,
									  sharedData: sharedData)
		new PAR([getter, worker, putter]).run()
		println "Base: $baseId terminated"
	}

}
