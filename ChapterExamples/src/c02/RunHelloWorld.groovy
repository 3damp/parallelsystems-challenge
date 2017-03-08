package c02

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

def connect = Channel.one2one()

def processList = [ 
                    new ProduceHW ( outChannel: connect.out() ),
                    new ConsumeHello ( inChannel: connect.in() )
                  ]
new PAR (processList).run()  
                 