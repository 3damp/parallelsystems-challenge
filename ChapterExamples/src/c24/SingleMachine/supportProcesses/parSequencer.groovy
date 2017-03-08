package c24.SingleMachine.supportProcesses

import c24.SingleMachine.methods.defs;

import org.jcsp.lang.*;
import org.jcsp.groovy.*;



class parSequencer implements CSProcess {
	def n           // the value of n for this sequence
	def inList
	def outList
	
	void run(){	
		defs.sequencer(n, inList, outList)
	}
}
