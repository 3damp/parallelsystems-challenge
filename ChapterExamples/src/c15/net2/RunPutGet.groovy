package c15.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

def comms = Channel.one2one()

def network = [new Put (outChannel: comms.out()),
			   new Get (inChannel: comms.in())]

new PAR(network).run()
