package c24.Distributed.processes

import c24.SingleMachine.methods.defs;

import org.jcsp.lang.*;
import org.jcsp.groovy.*;



class parExtractUniqueSequences implements CSProcess{
	def equalMap
	def n
	def startIndex
	def words
    def equalWordMap
	
	void run(){
		defs.extractUniqueSequences ( equalMap, n, 
								      startIndex, words, 
                                      equalWordMap )
	}

}
