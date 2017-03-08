package c03

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.plugAndPlay.*
import org.jcsp.lang.*
import org.jcsp.groovy.*

def S2P = Channel.one2one()

def testList = [ new GSquares ( outChannel: S2P.out() ),
                 new GPrint ( inChannel: S2P.in(),
                              heading : "Squares" )
               ]

new PAR ( testList ).run()                                               