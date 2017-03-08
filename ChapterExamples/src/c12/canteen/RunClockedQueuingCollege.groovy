package c12.canteen
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.* 
import phw.util.*
 
def philosophers = Ask.Int ("Number of Philosophers (>1)? ", 2, 9)

def service = Channel.any2one ()
def deliver = Channel.one2any ()
def supply = Channel.one2one ()
    
def philosopherList = (0 .. philosophers).collect{
                i -> return new Philosopher( philosopherId: i, 
                                             service: service.out(), 
                                             deliver: deliver.in()  )
                 }
    
def processList = [ new ClockedQueuingServery ( service:service.in(), 
                                                deliver:deliver.out(), 
                                                supply:supply.in()),
                    new Kitchen (supply:supply.out())
                  ]

processList = processList +  philosopherList
    
new PAR ( processList ).run()   
  