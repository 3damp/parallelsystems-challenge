package c24.SingleMachine.supportProcesses

import c24.SingleMachine.methods.defs;

import org.jcsp.lang.*;
import org.jcsp.groovy.*;



class parFindEqualKeys implements CSProcess {
	def words
	def startIndex
	def inList
	def outMap
	
	void run(){	
		defs.extractEqualValues(words, startIndex, inList, outMap)
	}

}
