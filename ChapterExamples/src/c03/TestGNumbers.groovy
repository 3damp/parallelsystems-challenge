package c03

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.groovy.plugAndPlay.*
import org.jcsp.lang.*
import org.jcsp.groovy.*

def N2P = Channel.one2one()

def testList = [ new GNumbers ( outChannel: N2P.out() ),
                 new GPrint ( inChannel: N2P.in(),
                              heading : "Numbers" )
               ]
new PAR ( testList ).run()
                                               