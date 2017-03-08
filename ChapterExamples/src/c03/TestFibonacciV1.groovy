package c03

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.groovy.plugAndPlay.*
import org.jcsp.groovy.examples.*
import org.jcsp.lang.*
import org.jcsp.groovy.*

def F2P = Channel.one2one()

def testList = [ new FibonacciV1 ( outChannel: F2P.out() ),
                 new GPrint ( inChannel: F2P.in(), 
                              heading: "Fibonacci V1" )
               ]

new PAR ( testList ).run()  
                        