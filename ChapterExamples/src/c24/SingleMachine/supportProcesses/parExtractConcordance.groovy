package c24.SingleMachine.supportProcesses

import c24.SingleMachine.methods.defs;

import org.jcsp.lang.*;
import org.jcsp.groovy.*;



class parExtractConcordance implements CSProcess{
	def equalMap
	def n
	def startIndex
	def words
	def minSeqLen
	def printWriter
	
	void run(){
		defs.extractConcordance ( equalMap, n, 
								  startIndex, words, 
								  minSeqLen, printWriter )
		printWriter.flush()
		printWriter.close()
	}

}
