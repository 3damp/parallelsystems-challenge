package c06

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

import c05.Queue
class QueueTest extends GroovyTestCase {
	
	void testQueue() {
		def QP2Q = Channel.one2one()
		def Q2QC = Channel.one2one()
		def QC2Q = Channel.one2one()
				
		def qProducer = new QProducerForTest ( put: QP2Q.out(), iterations: 50 )
		def queue     = new Queue ( put: QP2Q.in(), get: QC2Q.in(), 
				                    receive: Q2QC.out(), elements: 5)
		def qConsumer = new QConsumerForTest ( get: QC2Q.out(), 
			                                   receive: Q2QC.in() )
		def testList = [ qProducer, queue, qConsumer ]
		new PAR ( testList ).run()
				
		def expected = qProducer.sequence
		def actual = qConsumer.outSequence
		assertTrue(expected == actual)		
	}
}
